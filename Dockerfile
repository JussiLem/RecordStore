FROM openjdk:8 as compile
COPY . /RecordStore-training
ENV LANG C.UTF-8
ENV TZ Helsinki/Finland
RUN chmod 666 /RecordStore-training
RUN cd /RecordStore-training/ && ./gradlew

FROM tomcat:9
ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH
ENV CATALINA_OPTS -Xms2g -Xmx2g -Xss384k -Duser.timezone=Europe/Helsinki -Duser.country=FI -Duser.language=fi -Dfile.encoding=UTF-8 -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
WORKDIR $CATALINA_HOME
RUN apt-get update
RUN rm -r /$CATALINA_HOME/webapps*
COPY --from=compile /RecordStore-training/build/libs/* /$CATALINA_HOME/webapps/