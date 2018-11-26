FROM mariadb:10.4
ENV TZ Helsinki/Finland
ENV LANG=C.UTF-8
ENV LANGUAGE=C.UTF-8
ENV LC_ALL=C.UTF-8
COPY docker-entrypoint-initdb.d/V01_initial_script.sql /docker-entrypoint-initdb.d/
EXPOSE 3306
CMD ["mysqld"]