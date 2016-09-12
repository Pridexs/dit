;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;                             ;
; Alexandre Maros - D14128553 ;
;      DIT - Forecast         ;
;                             ;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(reset)

(defglobal ?*tomorrow-rain* = 0)
(defglobal ?*tomorrow-dry* = 0)

(deffunction cf (?v1 ?v2)
    (+ ?v1 (* ?v2 (- 1 ?v1)))
)

(defrule initial-rule
    ?f <- (initial-fact)
    =>
    (retract ?f)
    (printout t "What is the weather today? ")
    (assert (today (read)))

    (printout t "What is the rainfall today? ")
    (assert (rainfall (read)))

    (printout t "What is the temperature today? ")
    (assert (temperature (read)))
    (run)
    (assert (done))
    (run)
)

(defrule forecast-rule2
    (today rain)
    =>
    (bind ?*tomorrow-rain* (cf ?*tomorrow-rain* 0.5))
)

(defrule forecast-r2
    (today dry)
    =>
    (bind ?*tomorrow-dry* (cf ?*tomorrow-dry* 0.5))
)

(defrule forecast-rule3
    ?v <- (rainfall low)
    =>
    (retract ?v)
    (printout t "To what degree do you believe the rainfall is low? Enter a numeric certainty between 0 and 1.0 inclusive." crlf)
    (bind ?rainfall (read))
    (assert (rainfall-low ?rainfall))
)

(defrule forecast-rule4
    ?v <- (temperature cold)
    =>
    (retract ?v)
    (printout t "To what degree do you believe the temperature is cold? Enter a numeric certainty between 0 and 1.0 inclusive." crlf)
    (bind ?temperature (read))
    (assert (temperature-cold ?temperature))
)

(defrule forecast-rule5
    (today rain)
    (rainfall-low ?v)
    =>
    (bind ?min-cf (min 1 ?v))
    (bind ?value-cf (* 0.6 ?min-cf))
    (bind ?*tomorrow-dry* (cf ?*tomorrow-dry* ?value-cf))
)

(defrule forecast-rule6
    (today rain)
    (rainfall-low ?rainfall)
    (temperature-cold ?temperature)
    =>
    (bind ?min-cf (min ?rainfall ?temperature))
    (bind ?value-cf (* 0.7 ?min-cf))
    (bind ?*tomorrow-dry* (cf ?*tomorrow-dry* ?value-cf))
)

(defrule output-rule
    (done)
    =>
    (printout t "tomorrow is dry {" ?*tomorrow-dry* "}" crlf)
    (printout t "tomorrow is rain {" ?*tomorrow-rain* "}" crlf)
)

(run)
