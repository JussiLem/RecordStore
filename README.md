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

## Instructions on a local setup
1. Run the initial sql script found in /mariadb/docker-entrypoint-initdb.d folder in local MariaDb client.
Database should be running on localhost:3306/records. 
Don't worry about the tables, they will be created when the app is started for the first time.

2. To build the project go to the root folder of the project and type gradlew on cmd:
````bash
gradlew
````

This will download all the implementations, run the unit tests and create the .war file to build/lib folder.

3. Copy the .war file created in build/lib folder to tomcat's webapp folder. 
This will automatically start the RecordStore app.

4. GOTO address: http://localhost:8080/RecordStore-1.0/home

## Alternative Docker instructions
RecordStore can also be run with docker by using these instructions:
1. Register an Docker account here: https://www.docker.com/products/docker-desktop
2. Download and install Docker
3. After installing write docker-compose up on cmd/shell
````bash
docker-compose up
````

This will create database and the application in a few minutes.

4. Go to address:
http://localhost:8080/RecordStore-1.0/home

5. To stop the containers press **CTRL+C**

6. From the homepage you are able to create artists or albums.

#### Note
Sometimes the database might not be ready before wait-for-it script timer runs out. 
This will make the recordstore tomcat to timeout and exit. 
If this occurs, just press **ctrl+c** and run ````docker-compose up```` again.

### Database details
Database connection details can be found from src/main/resources/application.conf


## Issues
* Missing track servlets
* Listing albums don't work
* Deleting artist don't work
* all the links don't work properly