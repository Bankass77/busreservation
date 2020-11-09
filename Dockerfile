#Pull base image
FROM ubuntu:latest
RUN \
#Update
apt-get update -y && \
#Install Java
apt-get install default-jre -y
ADD ./target/busreservation-0.0.1-SNAPSHOT.jar  busreservation.jar
EXPOSE 8080
CMD java -jar busreservation.jar
