(clear)

(deftemplate person "People in actuarial database"
    (slot age (default -1))
    (slot name )
    (slot gender))
    
(deftemplate oldest-male (slot name) (slot age))

(assert (person (gender Male) (name "Mitt Romney") (age 61) ))
(assert (person (gender Male) (name "Killian") (age 75) ))
(assert (person (name "Bob Smith") (age 34) (gender Male)))
(assert (person (gender Male) (name "Tom Smith") (age 32) ))
(assert (person (name "Mary Smith") (age 34) (gender Female)))
(assert (person  (name "George Bush") (gender Male)))

(assert (person (gender Female)))

(defrule oldest-male-rule
    ?oldest <- (oldest-male (age ?a1) (name ?n1))
    (person (name ?n2) (age ?a2 &: (> ?a2 ?a1)) (gender Male))
    =>
    (retract ?oldest)
    (assert (oldest-male (age ?a2) (name ?n2)))
)

(defrule show-oldest-male
    ?f1 <- (done)
    ?f2 <- (oldest-male (name ?n) (age ?a))
    =>
    (printout t "The oldest male is: " ?n " with " ?a " years old." crlf)
    (retract ?f1)
    (retract ?f2)
)

(deffunction find-oldest-male ()
    (assert (oldest-male (age 0) (name nil)))
    (run)
    (assert (done))
    (run)
)