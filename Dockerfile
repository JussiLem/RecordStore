FROM tomcat:9
WORKDIR /usr/local/tomcat
RUN apt-get update
RUN rm -r /usr/local/tomcat/webapps*
EXPOSE 8080