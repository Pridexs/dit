
;;
;; Alexandre Maros - D14128553
;;


;; ****************
;; RULES

(defrule rule1
    ?f1 <- (environment "papers"|"manuals"|"documents"|"textbooks")
    =>
    (retract ?f1)
    (assert (stimulus_situation "verbal"))
)

(defrule rule2
    ?f1 <- (environment "pictures"|"illustrations"|"photographs"|"diagrams")
    =>
    (retract ?f1)
    (assert (stimulus_situation "visual"))
)

(defrule rule3
    ?f1 <- (environment "machines"|"buildings"|"tools")
    =>
    (retract ?f1)
    (assert (stimulus_situation "physical_object"))
)

(defrule rule4
    ?f1 <- (environment "numbers"|"formulas"|"computer_programs")
    =>
    (retract ?f1)
    (assert (stimulus_situation "symbolic"))
)

(defrule rule5
    ?f1 <- (job "lecturing"|"advising"|"counselling")
    =>
    (retract ?f1)
    (assert (stimulus_response "oral"))
)

(defrule rule6
    ?f1 <- (job "building"|"repairing"|"troubleshooting")
    =>
    (retract ?f1)
    (assert (stimulus_response "hands_on"))
)

(defrule rule7
    ?f1 <- (job "writing"|"typing"|"drawing")
    =>
    (retract ?f1)
    (assert (stimulus_response "documented"))
)

(defrule rule8
    ?f1 <- (job "evaluating"|"reasoning"|"investigating")
    =>
    (retract ?f1)
    (assert (stimulus_response "analytical"))
)

(defrule rule9
    ?f1 <- (stimulus_situation "physical_object")
    ?f2 <- (stimulus_response "hands_on")
    ?f3 <- (feedback_required "true")
    =>
    (retract ?f1)
    (retract ?f2)
    (retract ?f3)
    (assert (medium "workshop"))
)

(defrule rule10
    ?f1 <- (stimulus_situation "symbolic")
    ?f2 <- (stimulus_response "analytical")
    ?f3 <- (feedback_required "true")
    =>
    (retract ?f1)
    (retract ?f2)
    (retract ?f3)
    (assert (medium "lecture_tutorial"))
)

(defrule rule11
    ?f1 <- (stimulus_situation "visual")
    ?f2 <- (stimulus_response "documented")
    ?f3 <- (feedback_required "false")
    =>
    (retract ?f1)
    (retract ?f2)
    (retract ?f3)
    (assert (medium "videocassette"))
)

(defrule rule12
    ?f1 <- (stimulus_situation "visual")
    ?f2 <- (stimulus_response "oral")
    ?f3 <- (feedback_required "true")
    =>
    (retract ?f1)
    (retract ?f2)
    (retract ?f3)
    (assert (medium "lecture_tutorial"))
)

(defrule rule13
    ?f1 <- (stimulus_situation "verbal")
    ?f2 <- (stimulus_response "analytical")
    ?f3 <- (feedback_required "true")
    =>
    (retract ?f1)
    (retract ?f2)
    (retract ?f3)
    (assert (medium "lecture_tutorial"))
)

(defrule rule14
    ?f1 <- (stimulus_situation "verbal")
    ?f2 <- (stimulus_response "oral")
    ?f3 <- (feedback_required "true")
    =>
    (retract ?f1)
    (retract ?f2)
    (retract ?f3)
    (assert (medium "role_play_exercises"))
)

(defrule show-medium
    ?f1 <- (done)
    ?f2 <- (medium ?m)
    =>
    (retract ?f1)
    (retract ?f2)
    (printout t "Medium: " ?m crlf)
)

; Default Rule that will be triggered
; if there is no suggestion.
(defrule no-medium
    ?f1 <- (stimulus_situation ?ss)
    ?f2 <- (stimulus_response ?sr)
    ?f3 <- (feedback_required ?f)
    ?f4 <- (done)
    =>
    (retract ?f1)
    (retract ?f2)
    (retract ?f3)
    (retract ?f4)
    (printout t "no advice" crlf)
)

;; ****************

;; ****************
;; Main
(defrule init-rule
    (initial-fact)
    =>
    (printout t "What sort of environment is a trainee dealing with on the job? " crlf)
	(bind ?e (readline))
    (assert (environment ?e))
	(printout t "What is the trainee job? ? " crlf)
	(bind ?j (readline))
	(assert (job ?j))
	(printout t "Feedback required? (true/false) " crlf)
	(bind ?f (readline))
	(assert (feedback_required ?f))
	(run)
	(assert (done))
	(run))

(reset)
(run)
