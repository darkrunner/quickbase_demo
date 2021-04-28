# Materials for developer interviews for Quickbase

## Purpose
The purpose of this exercise is not to give a "gotcha" question or puzzle, but a straight-forward (albeit contrived)
example of the kind of requirement that might arise in a real project so that we have shared context for a technical 
conversation during the interview. We are interested in how you approach a project, so you should feel free to add new 
class files as well modify the files that are provided as you see fit. Use of your favorite libraries or frameworks is
fine, but not required. How you demonstrate the correctness of your implementation is up to you.

## Requirements
The project requirement is to aggregate data (in this case population statistics) from two disparate sources.
We've provided two classes to represent those sources. `DBManager.java`, provides access to a SQL database containing population
data for cities.  Each city is in a state within a country.  You need to write a method to retrieve the total
population for each country.  The other class, `StatService.java`, returns a `List<Pair<String, Integer>>` containing 
country population data. For the purposes of this exercise, we've provided a concrete class that just returns a 
hard-coded list, but in a real project, assume it would be calling an API.

The assignment is to implement a solution that consumes these two data sources and returns the combined list of
countries and their populations. In the event of duplicate population data for a given country, the data from
the sql database should be used. 

## Building and Running the code

You may import and run the project within the IDE of your choice or run the following gradle command to build and run the application.

# Standalone H2

From the root dir execute `./gradlew build bootRun` to build and run the application as standalone service with embedded H2 database.

# Docker compose postgreSQL

From the root dir execute `./gradlew build -Ppostgres docker` to build the application and create the necessary docker image.
After that execute `docker-compose up -d --build` to bring up the necessary postgreSQL and application containers.
To shut down the setup use `docker-compose down`.

**Note!**
Docker and Docker-compose are required for this operation!


## Capabilities

The application provides statistics information about the population of the available countries. Breakdown per state and city is also available.
Corrections functionality is implemented as well in order to map the data provided by the Rest service to correspond to the one available in the database.

**Visit:** 
- `http://localhost:8080/v3/api-docs` to get the OpenAPI definition of the services.
- `http://localhost:8080/swagger-ui.html` to use the SwaggerUI page for testing the functionality

For commandline users to get the population data execute: `curl -X 'GET' 'http://localhost:8080/population'`

## TODO

Tests need to be implemented!


