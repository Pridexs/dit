
;;
;; Alexandre Maros - D14128553
;;

;; ****************
;; Imports
(import javax.swing.*)
(import javax.swing.JFrame)
(import java.awt.event.ActionListener)
(import java.awt.BorderLayout)
(import java.awt.Color)
(import java.awt.BorderLayout)
(import java.awt.GridLayout)
(import javax.swing.border.EmptyBorder)

;; ****************

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
    ?f3 <- (feedback_required TRUE)
    =>
    (retract ?f1)
    (retract ?f2)
    (retract ?f3)
    (assert (medium "workshop"))
)

(defrule rule10
    ?f1 <- (stimulus_situation "symbolic")
    ?f2 <- (stimulus_response "analytical")
    ?f3 <- (feedback_required TRUE)
    =>
    (retract ?f1)
    (retract ?f2)
    (retract ?f3)
    (assert (medium "lecture_tutorial"))
)

(defrule rule11
    ?f1 <- (stimulus_situation "visual")
    ?f2 <- (stimulus_response "documented")
    ?f3 <- (feedback_required FALSE)
    =>
    (retract ?f1)
    (retract ?f2)
    (retract ?f3)
    (assert (medium "videocassette"))
)

(defrule rule12
    ?f1 <- (stimulus_situation "visual")
    ?f2 <- (stimulus_response "oral")
    ?f3 <- (feedback_required TRUE)
    =>
    (retract ?f1)
    (retract ?f2)
    (retract ?f3)
    (assert (medium "lecture_tutorial"))
)

(defrule rule13
    ?f1 <- (stimulus_situation "verbal")
    ?f2 <- (stimulus_response "analytical")
    ?f3 <- (feedback_required TRUE)
    =>
    (retract ?f1)
    (retract ?f2)
    (retract ?f3)
    (assert (medium "lecture_tutorial"))
)

(defrule rule14
    ?f1 <- (stimulus_situation "verbal")
    ?f2 <- (stimulus_response "oral")
    ?f3 <- (feedback_required TRUE)
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
    (?*lOutput* setText ?m)
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
    (?*lOutput* setText "no advice")
	(printout t "no advice" crlf)
)

(deffunction find-medium (?e ?j ?f)
    (assert (environment ?e))
    (assert (job ?j))
    (assert (feedback_required ?f))
    (run)
    (assert (done))
    (run)
)

;; ****************

;; ****************
;; GLOBALS
(defglobal ?*f* = 0)
(defglobal ?*c* = 0)
(defglobal ?*cEnvironment* = 0)
(defglobal ?*cJob* = 0)
(defglobal ?*bFeedback* = 0)
(defglobal ?*lOutput* = 0)
(defglobal ?*checkFeedback* = 0)
(defglobal ?*m* = 0)
;; ****************

;; ****************
;; GUI
(deffunction create-frame ()
  (bind ?*f* (new JFrame "Media Advisor"))
  (bind ?*c* (?*f* getContentPane))
  (?*c* setLayout (new GridLayout 5 2 5 5)))


(deffunction add-widgets ()
  ;; Environments
  (?*c* add (new JLabel "Environment"))
  (bind ?*cEnvironment* (new JComboBox))
  (?*cEnvironment* addItem "papers")
  (?*cEnvironment* addItem "manuals")
  (?*cEnvironment* addItem "documents")
  (?*cEnvironment* addItem "textbooks")
  (?*cEnvironment* addItem "pictures")
  (?*cEnvironment* addItem "illustrations")
  (?*cEnvironment* addItem "photographs")
  (?*cEnvironment* addItem "diagrams")
  (?*cEnvironment* addItem "machines")
  (?*cEnvironment* addItem "buildings")
  (?*cEnvironment* addItem "tools")
  (?*cEnvironment* addItem "numbers")
  (?*cEnvironment* addItem "formulas")
  (?*cEnvironment* addItem "computer_programs")
  (?*c* add ?*cEnvironment*)

  ;; Jobs
  (?*c* add (new JLabel "Job"))
  (bind ?*cJob* (new JComboBox))
  (?*cJob* addItem "lecturing")
  (?*cJob* addItem "advising")
  (?*cJob* addItem "counselling")
  (?*cJob* addItem "building")
  (?*cJob* addItem "repairing")
  (?*cJob* addItem "troubleshooting")
  (?*cJob* addItem "writing")
  (?*cJob* addItem "typing")
  (?*cJob* addItem "drawing")
  (?*cJob* addItem "evaluating")
  (?*cJob* addItem "reasoning")
  (?*cJob* addItem "investigating")
  (?*c* add ?*cJob*)

  ;; Feedback required ?
  (?*c* add (new JLabel "Feedback"))
  (bind ?*checkFeedback* (new JCheckBox))
  (?*checkFeedback* setSelected TRUE)
  (?*c* add ?*checkFeedback*)
  
  ;; Button
  (?*c* add (new JLabel ""))
  (bind ?bFeedback (new JButton "Generate Medium"))
  (?*c* add ?bFeedback)

  ;; Label and Output
  (?*c* add (new JLabel "Suggested medium: "))
  (bind ?*lOutput* (new JLabel))
  (?*c* add ?*lOutput*)

  (?bFeedback addActionListener
    (implement ActionListener using
      (lambda (?name ?event)
        (find-medium
          (?*cEnvironment* getSelectedItem)
          (?*cJob* getSelectedItem)
          (?*checkFeedback* isSelected)))))
)

(deffunction show-frame ()
  (?*f* pack)
  ;(?*f* setSize 400 250)
  (?*f* setVisible TRUE))

  (deffunction add-behaviours ()
    (?*f* setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE)))

;; ****************

;; ****************
;; Main
(defrule init-rule
    (initial-fact)
    =>
    (create-frame)
    (add-widgets)
    (add-behaviours)
    (show-frame))

(reset)
(run)
