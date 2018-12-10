# RecordStore

School project for our Java course. Goal is to create app which can view, create, update and delete
records by streaming from ~~h2~~ MariaDb database.

## Requirements:
* MariaDb(tested on 10.4)
* Java 8 or newer
* Tomcat 9 at least

## Alternatively
* Install only Docker and follow the instructions below.
https://www.docker.com/products/docker-desktop

## Instructions
1. Run the initial sql script found in /mariadb/docker-entrypoint-initdb.d folder in local MariaDb client.
Database should be running on localhost:3306/records. 

2. To build the project type gradlew on cmd:
````bash
gradlew
````

This will create the .war file to build/lib folder.

3. Copy the .war file under tomcat's webapp folder. 
This will automatically start the RecordStore app. 

4. GOTO address: http://localhost/8080/home

## Alternative instructions
RecordStore can also be run with docker by using these instructions:
1. Register an Docker account here: https://www.docker.com/products/docker-desktop
2. Download and install Docker
3. After installing write docker-compose up on cmd
````bash
docker-compose up
````

4. Go to address:
````bash
http://localhost:8080/home
````

From the homepage you are able to create artists or albums.

### Database details
Database connection details can be found from main/resources/application.conf
