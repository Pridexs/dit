#!/bin/bash

COL_BLUE="\x1b[34;01m"
COL_RESET="\x1b[39;49;00m"

#############################################################################

echo -e $COL_BLUE"List all queues"$COL_RESET

read q_name

curl -s -X GET -H 'Accept: application/json' http://localhost:5000/queues | python -m json.tool
echo""

#############################################################################

echo -e $COL_BLUE"Create a queue (Type the name): "$COL_RESET

read q_name

curl -s -X POST -H 'Accept: application/json' http://localhost:5000/queues -d '{"name": "'$q_name'"}'
echo""

#############################################################################

echo -e $COL_BLUE"Delete a queue (Type the name): "$COL_RESET

read q_name

curl -s -X DELETE -H 'Accept: application/json' http://localhost:5000/queues/$q_name
echo""

#############################################################################

echo -e $COL_BLUE"Get number of messages of a queue (Type the name): "$COL_RESET

read q_name

curl -s -X GET -H 'Accept: application/json' http://localhost:5000/queues/$q_name/msgs/count
echo""

#############################################################################

echo -e $COL_BLUE"Write a message (Type the queue name and message content): "$COL_RESET

read q_name
read m_content

curl -s -X POST -H 'Accept: application/json' http://localhost:5000/queues/$q_name/msgs -d '{"content": "'$m_content'"}'
echo""

#############################################################################

echo -e $COL_BLUE"Read a message (Type the queue name): "$COL_RESET

read q_name

curl -s -X GET -H 'Accept: application/json' http://localhost:5000/queues/$q_name/msgs
echo""

#############################################################################

echo -e $COL_BLUE"Consume a message (Type the queue name): "$COL_RESET

read q_name

curl -s -X DELETE -H 'Accept: application/json' http://localhost:5000/queues/$q_name/msgs
echo""
