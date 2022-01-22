# Build up SpringBoot+RESTFul+PostgresSQL in Java 

# Environmental Setup
1. SpringBoot 2.5.9
2. PostgresSQL 14
3. Tomcat 9.0.56 embedded in SpringBoot
4. Eclipse 2021-12
5. Restman extension for Chrome
6. Java 8 above

# Advantages of using SpringBoot
- Fast and easy development
- Embedded Tomcat server into application
- No need for the deployment of war files and XML configuration
- Easy generate dependencies in Spring Initializer

# Step-By-Step Procedures

## Step 1: Create Sprint from Spring Intializr
Go to the Spring Initializr site. Under Project, choose “Maven” and then “Java” as the language. Note that this tutorial is built with Spring Boot version 2.5.6

Include the following identifiers under "Project Metadata" for your project:
- Group: keep it as a default value.
- Artifact: you can provide “employee”.
- Name: same as the artifact name, "employee".
- Description: provide a description about the project

Choose “Jar” as the Packaging type as the application will run in the embedded Tomcat server provided by Spring Boot.

Add the following Dependencies to the project:
- Spring Web: required for building RESTful web applications.
- Spring Data JPA: required to access the data from the database. JPA (Java Persistence API) 
- PostgresSQL Driver: required to connect with PostgresSQL database.

[ADD Picture here]
  
 
