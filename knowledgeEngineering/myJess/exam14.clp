
(deftemplate person (slot name) (slot gender) (slot weight) (slot age))

(assert (person (name Alfred) (gender Male) (weight 50) (age 20)))
(assert (person (name Julia) (gender Female) (weight 40) (age 16)))
(assert (person (name Ricardo) (gender Male) (weight 60) (age 62)))

(defrule show-males
		(person (name ?n) (age ?a) (gender ?g &: (= ?g Male)))
		=>
		(printout t ?n "-" ?a crlf)
)

(run)
