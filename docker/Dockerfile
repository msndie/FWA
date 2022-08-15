FROM tomcat:7-jdk8
EXPOSE 8080:8080
RUN apt update -y
RUN apt install maven -y
RUN rm /usr/local/tomcat/conf/tomcat-users.xml
RUN rm /etc/maven/settings.xml
COPY Cinema /Cinema
COPY tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY settings.xml /etc/maven/settings.xml
COPY script.sh /script.sh
WORKDIR /Cinema
# RUN /usr/local/tomcat/bin/startup.sh 
# ENTRYPOINT [ "mvn", "install", "tomcat7:deploy" ]
ENTRYPOINT [ "sh", "/script.sh" ]