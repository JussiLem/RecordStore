FROM openjdk:8 as compile
COPY . /RecordStore-training
ENV LANG C.UTF-8
ENV TZ Helsinki/Finland
RUN chmod 666 /RecordStore-training && \
cd /RecordStore-training/ && ./gradlew

FROM tomcat:9
ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH
ENV CATALINA_OPTS -Xms1g -Xmx1g -Xss384k -Dfile.encoding=UTF-8 -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
WORKDIR $CATALINA_HOME
COPY --from=compile /RecordStore-training/docker/wait-for-it.sh /$CATALINA_HOME/
RUN apt-get -q update
RUN rm -r /$CATALINA_HOME/webapps*
RUN chmod +x ./wait-for-it.sh
COPY --from=compile /RecordStore-training/build/libs/* /$CATALINA_HOME/webapps/
CMD ["./wait-for-it.sh", "--strict", "--timeout=60", "recordstoredb:3306", "--", "catalina.sh", "run"]