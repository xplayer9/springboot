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
  
Click the Generate button at the bottom of the screen. This will download a zip file containing your project boilerplate. Extract the zip file to your preferred folder location.

Open Eclipse IDE and go to File and select Import. Under Maven, choose Existing Maven Projects. Click on Next.

Browse the directory where you extracted the zip file, select the root folder where the pom.xml file is present. Click on Finish to import your project into your Eclipse IDE.

Notice that there is a file named EmployeeApplication.java. This is the entry point that will launch the Spring Boot application.

The @SpringBootApplication includes the features of the below Spring Boot annotations:
  
 ## Step 2: Add sub-packages to the project
 
- DAO: The DAO (data access layer) 
In Eclipse, right-click on the com.example.employee package and Select New then Package as seen below:
- Repository: This layer is similar to the DAO layer which connects to the database and accesses the data
- Service: This layer calls the DAO layer to get the data and perform business logic on it
- Model: The class contains all the Java objects that will be mapped to the database table using
- Controller: REST APIs controller will process the REST API request
 
 [ADD Picture]
 
 
 ### Step 2.1: Add class to the project
 
```Java
package com.example.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="emp_id")
            private Long id;
        
        @Column(name="first_name")
        private String firstName;
        
        @Column(name="last_name")
        private String lastName;
        
        @Column(name="email_id")
        private String emailId;
}
```

 ### Step 2.2: Add class to the project
 ### Step 2.3: Add class to the project
 ### Step 2.4: Add class to the project
 ### Step 2.5: Add class to the project
 
 
 ## Step 3: Setup PostgresSQL Database
 
  [ADD Picture]
  
  
 ## Step 4: Build Application
 
 To connect PostgresSQL, you’ll have to provide the database details to Spring Boot
 Open the application.properties file and add the below content
 
 [ADD Details]
 
 Right click on the employee folder in the Project Explorer on Eclipse and select Run As then choose 4 Maven build
 And Edit Configuration pop-up will open. Type spring-boot:run in Goals, Click on Apply then Run
 The application will now start building. A successful build will display the following on the console:
 
 [ADD Picture]
 
 
  ## Step 5: Test Application by using RestMan
  
  [ADD Picture]
  
 
 
