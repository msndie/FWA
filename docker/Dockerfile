FROM tomcat:8-jdk8
RUN apt update -y
RUN apt install maven -y
COPY Cinema /Cinema
COPY script.sh /script.sh
WORKDIR /Cinema
ENTRYPOINT [ "sh", "/script.sh" ]