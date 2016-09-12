(clear)

(deftemplate person "People in actuarial database"
    (slot age (default -1))
    (slot name)
    (slot gender))

(defglobal ?*cnt_male* = 0)
(defglobal ?*sum_age_male* = 0)

(defrule male-ages
    (person (name ?n) (age ?a) (gender Male) )
    =>
    (if (<> ?a -1) then
    	(bind ?*cnt_male* (+ ?*cnt_male* 1))
    	(bind ?*sum_age_male* (+ ?*sum_age_male* ?a))
    	(printout t "nMales: " ?*cnt_male* crlf)
    	(printout t "Average: " (/ ?*sum_age_male* ?*cnt_male*) crlf)
    )
    (printout t ?n " is " ?a " years old " crlf))


(assert (person (name "Bob Smith") (age 34) (gender Male)))
(assert (person (gender Male) (name "Tom Smith") (age 32) (gender Male)))
(assert (person (name "Mary Smith") (age 34) (gender Female)))
(assert (person (gender Female) (age 40) (name "Catherine Jude")))
(assert (person (gender Male) (age 33) (name "Desmond Julius")))
(assert (person (gender Male) (name "Louis Litt")))