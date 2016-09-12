(clear)

;;*******************************
;; Imports
	(import javax.swing.*)
	(import javax.swing.JFrame)
	(import javax.swing.border.EmptyBorder)
	(import java.awt.event.ActionListener)
	(import java.awt.BorderLayout)
	(import java.awt.GridLayout)
	(import java.awt.Color)

;;********************************
;;Media Advisor rules

;Stimulus situation
(defrule rule1
        ?fact <- (environment "papers"|"manuals"|"documents"|"textbooks")
        =>
        (retract ?fact)
        (assert (stimulus-situation "verbal"))
)

(defrule rule2
        ?fact <- (environment "pictures"|"illustrations"|"photographs"|"diagrams")
        =>
        (retract ?fact)
        (assert (stimulus-situation "visual"))
)

(defrule rule3
        ?fact <- (environment "machines"|"buildings"|"tools")
        =>
        (retract ?fact)
        (assert (stimulus-situation "physical object"))
)

(defrule rule4
        ?fact <- (environment "numbers"|"formulas"|"computer programs")
        =>
        (retract ?fact)
        (assert (stimulus-situation "symbolic"))
)

;Stimulus response
(defrule rule5
        ?fact <- (job "lecturing"|"advising"|"counselling")
        =>
        (retract ?fact)
        (assert (stimulus-response "oral"))
)

(defrule rule6
        ?fact <- (job "building"|"repairing"|"troubleshooting")
        =>
        (retract ?fact)
        (assert (stimulus-response "hands-on"))
)

(defrule rule7
        ?fact <- (job "writing"|"typing"|"drawing")
        =>
        (retract ?fact)
        (assert (stimulus-response "documented"))
)

(defrule rule8
        ?fact <- (job "evaluating"|"reasoning"|"investigating")
        =>
        (retract ?fact)
        (assert (stimulus-response "analytical"))
)

;Medium
(defrule rule9
        ?sit <- (stimulus-situation "physical object")
        ?resp <- (stimulus-response "hands-on")
        ?feedback <- (feedback-required TRUE)
        =>
        (retract ?sit)
        (retract ?resp)
        (retract ?feedback)
        (assert (medium workshop))
)

(defrule rule10
        ?sit <- (stimulus-situation "symbolic")
        ?resp <- (stimulus-response "analytical")
        ?feedback <- (feedback-required TRUE)
        =>
        (retract ?sit)
        (retract ?resp)
        (retract ?feedback)
        (assert (medium "lecture - tutorial"))
)

(defrule rule11
        ?sit <- (stimulus-situation "visual")
        ?resp <- (stimulus-response "documented")
        ?feedback <- (feedback-required FALSE)
        =>
        (retract ?sit)
        (retract ?resp)
        (retract ?feedback)
        (assert (medium videocassette))
)

(defrule rule12
        ?sit <- (stimulus-situation "visual")
        ?resp <- (stimulus-response "oral")
        ?feedback <- (feedback-required TRUE)
        =>
        (retract ?sit)
        (retract ?resp)
        (retract ?feedback)
        (assert (medium "lecture - tutorial"))
)

(defrule rule13
        ?sit <- (stimulus-situation "verbal")
        ?resp <- (stimulus-response "analytical")
        ?feedback <- (feedback-required TRUE)
        =>
        (retract ?sit)
        (retract ?resp)
        (retract ?feedback)
        (assert (medium "lecture - tutorial"))
)

(defrule rule14
        ?sit <- (stimulus-situation "verbal")
        ?resp <- (stimulus-response "oral")
        ?feedback <- (feedback-required TRUE)
        =>
        (retract ?sit)
        (retract ?resp)
        (retract ?feedback)
        (assert (medium "lecture - tutorial"))
)

;;*****************************************
;; Main program

; Global defs
	(defglobal ?*frame* = 0)
	(defglobal ?*content* = 0)
	(defglobal ?*envbox* = 0)
	(defglobal ?*jobbox* = 0)
	(defglobal ?*feedback* = 0)
	(defglobal ?*output* = 0)

;;*****************************************
;; Media advisor functionality

; Default medium rule, only fires if nothing else was found
(defrule default
        ?done <- (done)
        ?sit <- (stimulus-situation ?)
        ?resp <- (stimulus-response ?)
        ?feedback <- (feedback-required ?)
        =>
        (retract ?sit)
        (retract ?resp)
        (retract ?feedback)
        (assert (medium unknown))
)

; Medium found, change text on the jlabel
(defrule result
        ?done <- (done)
        ?medf <- (medium ?m)
        =>
        (retract ?done)
        (retract ?medf)
        (set-output ?m)
        (printout t "Medium is " ?m crlf)
)

; Find medium function
(deffunction find-medium (?e ?j ?f)
        (assert (environment ?e))
        (assert (job ?j))
        (assert (feedback-required ?f))
        (run)
        (assert (done))
        (run)
)

; Create frame
(deffunction create-frame ()
        (bind ?*frame* (new JFrame "Media Advisor"))
        (bind ?*content* (?*frame* getContentPane))
        (?*content* setLayout (new GridLayout 5 2 5 5))
        (?*content* setBorder (new EmptyBorder 10 10 10 10))
)

; Add content to the frame
(deffunction add-content ()
        ;; Create all panels
        (?*content* add (new JLabel "Environment"))
        (bind ?*envbox* (new JComboBox (list
                "papers"
                "manuals"
                "documents"
                "textbooks"
                "pictures"
                "illustrations"
                "photographs"
                "diagrams"
                "machines"
                "buildings"
                "tools"
                "numbers"
                "formulas"
                "computer programs"
        )))
        (?*content* add ?*envbox*)

        (?*content* add (new JLabel "Job"))
        (bind ?*jobbox* (new JComboBox (list
                "lecturing"
                "advising"
                "counselling"
                "building"
                "repairing"
                "troubleshooting"
                "writing"
                "typing"
                "drawing"
                "evaluating"
                "reasoning"
                "investigating"
        )))
        (?*content* add ?*jobbox*)

        (?*content* add (new JLabel "Provide feedback"))
        (bind ?*feedback* (new JCheckBox))
        (?*feedback* setSelected TRUE)
        (?*content* add ?*feedback*)

        (?*content* add (new JPanel))
        (bind ?button (new JButton "Generate suitable medium"))
        (?*content* add ?button)

        (?*content* add (new JLabel "The suggested medium is: "))
        (bind ?*output* (new JLabel))
        (?*content* add ?*output*)

        ;; Add behaviour to the button panel
        (?button addActionListener
                (implement ActionListener using
                        (lambda (?name ?event)
                                (find-medium
                                        (?*envbox* getSelectedItem)
                                        (?*jobbox* getSelectedItem)
                                        (?*feedback* isSelected)
                                )
                        )
                )
        )
)

; Show the frame
(deffunction show-frame ()
        (?*frame* pack)
        (?*frame* setVisible TRUE)
        (?*frame* setLocationRelativeTo nil)
)

; Change text on the output label
(deffunction set-output (?t)
        (?*output* setText ?t)
)

;;*****************************************
;; Main program init
(defrule init
        ?f <- (initial-fact)
        =>
        (retract ?f)
        (create-frame)
        (add-content)
        (show-frame)
)

(reset)
(run)
