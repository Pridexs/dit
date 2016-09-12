;; Name: Alexandre Maros
;; D14128553

(deftemplate used-car (slot cost) (slot colour (default black)) (slot make) (slot mileage) (slot year_of_sale))
(deftemplate new-car (slot cost) (slot colour (default black)) (slot make) (slot warranty))

(deftemplate cheapest-used-car (slot cost) (slot colour (default black)) (slot make) (slot mileage) (slot year_of_sale))

(deffacts car-facts
    (cheapest-used-car (cost 999999999))
    (used-car (cost 12000) (make ford) (mileage 30000) (year_of_sale 1999))
    (used-car (cost 15000) (colour red) (make volkswagen) (mileage 60000) (year_of_sale 2003))
    (used-car (cost 9000) (colour red) (make volkswagen) (mileage 50000) (year_of_sale 2013))
    (used-car (cost 9000) (make fiat) (mileage 1000) (year_of_sale 1995))
    (used-car (cost 8000) (make fiat) (colour pink) (mileage 1000) (year_of_sale 1999))
    (used-car (cost 9000) (make fiat) (colour green) (mileage 1000) (year_of_sale 1995))
    (new-car (cost 20000) (colour red) (make ford) (warranty 2))
    (new-car (cost 30000) (colour pink) (make audi) (warranty 5))
    (new-car (cost 40000) (make ford) (warranty 3))
)

;; ##########################################
;; 1.
(defrule display-one
    (new-car  (make ?m &~volkswagen) (colour ?col &: (or (= ?col black) (= ?col red))))
    (new-car (cost ?c) (make ?m) (colour ?col) (warranty ?w))
    =>
    (printout t "Question 1. Car: " ?m " Price: " ?c " Colour: " ?col " Warranty: " ?w crlf)
)

;; ##########################################
;; 2.
(defrule display-two
    (or
        (new-car (make ?m) (cost ?c &: (< ?c 30000)) (colour ?col))
        (used-car (make ?m) (cost ?c) (colour ?col) (mileage ?mil &: (< ?mil 50000)))
    )
    =>
    (printout t "Question 2. Car: " ?m " Price: " ?c " Colour: " ?col crlf)
)

;; ##########################################
;; 3.
(defrule find-volkswagen
    ?f1 <- (isThereAnyVolks ?v&FALSE)
    (used-car (make ?m&volkswagen))
    =>
    (assert (isThereAnyVolks TRUE))
)

(defrule display-three
    (not (isThereAnyVolks ?v&TRUE))
    (car_year ?y)
    (used-car (make ?m) (cost ?c) (colour ?col) (mileage ?mil) (year_of_sale ?y))
    =>
    (printout t "Question 3. Car: " ?m " Price: " ?c " Colour: " ?col " Mileage: " ?mil crlf)
)

;; ##########################################
;; 4.
(defrule display-four
    (or
        (used-car (make ?m&~volkswagen) (cost ?c) (colour ?col) (mileage ?mil &: (and (> ?mil 20000) (< ?mil 50000))) (year_of_sale ?y))
        (used-car (make ?m&volkswagen) (cost ?c  &: (< ?c 10000)) (colour ?col) (mileage ?mil) (year_of_sale ?y &: (> ?y 2012)))
    )
    =>
    (printout t "Question 4. Car: " ?m " Price: " ?c " Colour: " ?col " Mileage: " ?mil " Year of sale: " ?y crlf)
)

;; ##########################################
;; 5.
(defrule find-min-price
    ?f1 <- (cheapest-used-car (cost ?min_c))
    (used-car (make ?m) (cost ?c  &: (< ?c ?min_c)) (colour ?col &: (and (<> ?col black) (<> ?col red))) (mileage ?mil) (year_of_sale ?y))
    =>
    ;(printout t "Found a cheaper car: " ?m " - " ?c " - " ?col " - " ?mil " - " ?y crlf)
    (modify ?f1 (make ?m) (cost ?c) (colour ?col) (mileage ?mil) (year_of_sale ?y))
)

(defrule print-cheapest-car
    (done)
    (cheapest-used-car (make ?m) (cost ?c) (colour ?col) (mileage ?mil) (year_of_sale ?y))
    =>
    (if (<> ?c 999999999) then
        (printout t "Cheapest car: " ?m " - " ?c " - " ?col " mileage: " ?mil " - " ?y crlf)
    else
        (printout t "No cheapest car found with the requirements." crlf)
    )
)

;; ##########################################
(defrule user-input
    (initial-fact)
    =>
    (printout t "Type a year for question 3: ")
    (bind ?car_year (read))
    (assert (car_year ?car_year))
    (assert (done))
    (run)
)

(reset)
(assert (isThereAnyVolks FALSE))
(run)
