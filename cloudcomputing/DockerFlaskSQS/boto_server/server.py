import os
import sys
import json
from flask import Flask, Response, render_template, request
import urllib2
import boto.sqs
import boto.sqs.queue
from boto.sqs.message import Message
from boto.sqs.connection import SQSConnection
from boto.exception import SQSError

app = Flask(__name__)

def get_conn():
    response = urllib2.urlopen("http://ec2-52-30-7-5.eu-west-1.compute.amazonaws.com:81/key").read()
    key_id, secret_access_key = response.split(':')
    return boto.sqs.connect_to_region('eu-west-1', aws_access_key_id=key_id, aws_secret_access_key=secret_access_key)

@app.route("/")
def index():
   return  "Hello World!"

@app.route("/queues", methods=["GET"])
def queues_index():
    """
    List all queues

    curl -s -X GET -H 'Accept: application/json' http://localhost:5000/queues | python -m json.tool
    """
    all = []
    conn = get_conn()
    for q in conn.get_all_queues():
        all.append(q.name)
    resp = json.dumps(all)
    return Response(response=resp, mimetype="application/json")

# 1.
@app.route("/queues", methods=["POST"])
def create_queue():
    """
    Create a queue

    curl -s -X POST -H 'Accept: application/json' http://localhost:5000/queues -d '{"name": "my-queue"}'
    """
    body = request.get_json(force=True)
    # TO-DO: Add a check to see if the name is there
    q_name = body['name']

    conn = get_conn()
    try:
        rs = conn.create_queue(q_name)
    except Exception, e:
        print(e)
    resp = '{"name": "%s"}' % q_name
    return Response(response=resp, mimetype="application/json")

# 2.
@app.route("/queues/<q_name>", methods=["DELETE"])
def delete_queue(q_name):
    """
    Delete a queue

    curl -s -X DELETE -H 'Accept: application/json' http://localhost:5000/queues/<my-queue>
    """
    conn = get_conn()
    queue = conn.get_queue(q_name)
    if (queue is not None):
        try:
            conn.delete_queue(queue)
        except Exception, e:
            print(e)
    else:
        print("Queue " + q_name + " not found.")
    resp = '{"name": "%s"}' % q_name
    return Response(response=resp, mimetype="application/json")

@app.route("/queues/<q_name>/msgs/count", methods=["GET"])
def count_msgs_queue(q_name):
    """
    Counts the number of messages in a queue

    curl -s -X GET -H 'Accept: application/json' http://localhost:5000/queues/<my-queue>/msgs/count
    """
    msg_count = 0
    conn = get_conn()
    queue = conn.get_queue(q_name)
    if (queue is not None):
        msg_count = queue.count()
    resp = '{"msg_count": "%d"}' % msg_count
    return Response(response=resp, mimetype="application/json")

@app.route("/queues/<q_name>/msgs", methods=["POST"])
def write_msg_queue(q_name):
    """
    Write a message to the queue

    curl -s -X POST -H 'Accept: application/json' http://localhost:5000/queues/<my-queue>/msgs -d '{"content": "test message"}'
    """
    body = request.get_json(force=True)
    # TO-DO: Add a check to see if the name is there
    message = body['content']

    resp = ""
    conn = get_conn()
    queue = conn.get_queue(q_name)
    if (queue is not None):
        try:
            conn.send_message(queue, message)
            resp = '{"name": "%s" "content": "%s"}' % (q_name, message)
        except Exception, e:
            print(e)
    return Response(response=resp, mimetype="application/json")

@app.route("/queues/<q_name>/msgs", methods=["GET"])
def read_message(q_name):
    """
    Reads a message from the queue but doesn't erase it

    curl -s -X GET -H 'Accept: application/json' http://localhost:5000/queues/<my-queue>/msgs
    """
    resp = ""
    conn = get_conn()
    queue = conn.get_queue(q_name)
    if (queue is not None):
        try:
            msgs = conn.receive_message(queue, number_messages=1,attributes='All')
            if(len(msgs) > 0):
                resp = '{"message": "%s"}' % msgs[0].get_body()
        except Exception, e:
            print(e)
    return Response(response=resp, mimetype="application/json")

@app.route("/queues/<q_name>/msgs", methods=["DELETE"])
def consume_message(q_name):
    """
    Reads a message from the queue and deletes it

    curl -s -X DELETE -H 'Accept: application/json' http://localhost:5000/queues/<my-queue>/msgs
    """
    resp = ""
    conn = get_conn()
    queue = conn.get_queue(q_name)
    if (queue is not None):
        try:
            msgs = conn.receive_message(queue, number_messages=1,attributes='All')
            if (len(msgs) > 0):
                msg = msgs[0].get_body()
                deleted = conn.delete_message(queue, msgs[0])
                if (deleted):
                    resp = '{"message": "%s"}' % msg
        except Exception, e:
            print(e)
    return Response(response=resp, mimetype="application/json")

if __name__ == "__main__":
    app.run(host="0.0.0.0")
