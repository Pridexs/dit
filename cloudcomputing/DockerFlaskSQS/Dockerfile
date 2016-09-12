FROM python:2.7

MAINTAINER pridexs

# Update the OS
RUN apt-get update
RUN apt-get -y upgrade

# Copy the application folder
ADD /boto_server /boto_server

# Get pip to download and install requirements:
RUN pip install -r /boto_server/requirements.txt

EXPOSE 5000

WORKDIR /boto_server
CMD python server.py
