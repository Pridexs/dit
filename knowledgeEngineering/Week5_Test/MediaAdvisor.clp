(defrule rule1
    (environment papers|manuals|documents|textbooks)
    =>
    (assert (stimulus_situation verbal))
)

(defrule rule2
    (environment pictures|illustrations|photographs|diagrams)
    =>
    (assert (stimulus_situation visual))
)

(defrule rule3
    (environment machines|buildings|tools)
    =>
    (assert (stimulus_situation physical_object))
)

(defrule rule4
    (environment numbers|formulas|computer_programs)
    =>
    (assert (stimulus_situation symbolic))
)

(defrule rule5
    (job lecturing|advising|counselling)
    =>
    (assert (stimulus_response oral))
)

(defrule rule6
    (job building|repairing|troubleshooting)
    =>
    (assert (stimulus_response hands_on))
)

(defrule rule7
    (job writing|typing|drawing)
    =>
    (assert (stimulus_response documented))
)

(defrule rule8
    (job evaluating|reasoning|investigating)
    =>
    (assert (stimulus_response analytical))
)

(defrule rule9
    (stimulus_situation physical_object)
    (stimulus_response hands_on)
    (feedvack_required TRUE)
    =>
    (assert (medium workshop))
)

(defrule rule10
    (stimulus_situation symbolic)
    (stimulus_response analytical)
    (feedvack_required TRUE)
    =>
    (assert (medium lecture_tutorial))
)

(defrule rule11
    (stimulus_situation visual)
    (stimulus_response documented)
    (feedvack_required TRUE)
    =>
    (assert (medium videocassette))
)

(defrule rule12
    (stimulus_situation visual)
    (stimulus_response oral)
    (feedvack_required TRUE)
    =>
    (assert (medium lecture_tutorial))
)

(defrule rule13
    (stimulus_situation verbal)
    (stimulus_response analytical)
    (feedvack_required TRUE)
    =>
    (assert (medium lecture_tutorial))
)

(defrule rule9
    (stimulus_situation verbal)
    (stimulus_response oral)
    (feedvack_required TRUE)
    =>
    (assert (medium role_play_exercises))
)