# Overtime manager
#### Spring / Angular web application for managing overtime in your workplace

## Tools used
* IntelliJ IDEA Community for backend development
* Visual Studio Code for frontend development
* Docker for easy setup of local database
* MySQL as a database
* Spring Boot
* Spring Framework 4
* Flyway for DB provisioning and schema migrations
* Gradle as a build system
* Lombok for generating boilerplate code
* Angular5 for front-end
* Bulma CSS

## Local environment setup
First, make sure that you have following tools installed on your machine:
* JDK8
* Docker and docker-compose
* npm

Please also make sure that docker service is running - for example by typing `docker info` in command line/terminal.

1. Clone to repository onto your local machine.
0. Run local DB using docker. From the root directory of the project type 
    ```
    docker-compose -f docker/docker-compose-local.yaml up -d
    ```
    > *For Windows users:* you can also use Gradle task that is provided: `gradlew.bat runLocalDb`.

0. Run backend application. Type in terminal `gradlew.bat bootRun`
    
    Sometimes database won't start yet and you might get an error during launch that Spring can't connect to MySQL. In such case, please wait few seconds and try again.
0. Run frontend application. Type in terminal:
    ```
    cd front/
    npm start
    ```
0. Now you can launch the browser and visit `http://localhost:4200` to see the working application.