#!/bin/bash

echo 'Deleting all containers'
docker stop $(docker ps -a -q)
docker rm -f $(docker ps -a -q)

echo 'Deleting all images'
docker rmi -f $(docker images -q)

echo 'Creating images'
docker build -t testimage --rm=true ./TestApp/SimpleApp/
docker build -t testserver --rm=true ./TestApp/ServerApp/

echo 'Creating containers'
docker run -d --name=testcontainer1 testimage
docker run -d --name=testcontainer2 testimage
docker run -d --name=testcontainer3 testimage
docker run -d --name=server1 testserver
docker run -d --name=server2 testserver
docker run -d --name=server3 testserver

echo 'Done.'
