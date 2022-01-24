# Java Project
# SpringBoot RESTFul Web Service and PostgreSQL
## Start From Environmental Setup
1. SpringBoot 2.5.9
2. PostgreSQL 14
3. Tomcat 9.0.56 embedded in SpringBoot
4. Eclipse 2021-12
5. RestMan extension in Chrome brower
6. Java 11

## Advantages of using SpringBoot
- Fast and easy development
- Embedded Tomcat server into application
- No need for the deployment of war files and XML configuration
- Easy to inject dependencies in Spring Initializer

# Step-By-Step Procedures

## Step 1: Create Spring project from Spring Intializr
Go to the [Spring Initializer](https://start.spring.io/)

In "Spring Intializer" web page, enter following info
- Choose "Maven Project", Language "Java" and Spring Boot version "2.5.9"
- Group: Keep default, no need to change
- Artifact: type “userAccount”
- Name: type “userAccount”
- Description: type any description
- Choose “Jar”, it will include embedded Tomcat server provided by Spring Boot
- Choose Java SDK 11

Add the following Dependencies
- Spring Web: required for RESTful web applications
- Spring Data JPA: required to access the data from the database. JPA (Java Persistence API) 
- PostgreSQL Driver: required to connect with PostgreSQL database

The Screenshot
<img width="1191" alt="Screenshot1" src="https://user-images.githubusercontent.com/48862763/150665780-bb9a8016-8266-4ad0-8105-df1913e09e68.png">

Click the "Generate" button at the bottom of the screen, this will generate a project Zip file

## Step 1.1: Create dynamic web project in Eclipse
Next, let's extract the Zip file to a local folder
Open Eclipse IDE and go to "File" and select "Import". Under Maven, choose Existing Maven Projects and choose the local folder you extract the Zip file
Click on "Finish", then a web project was created

UserAccountApplication.java is the program entry point with @SpringBootApplication annotations

<img width="258" alt="Screenshot2" src="https://user-images.githubusercontent.com/48862763/150666329-fd4835b9-09f1-4ade-884f-ca5672e55f76.png">

## Step 2: Add sub-class to the project
 
- Repository: DAO(Data Access Object) layer which connects and accesses to the database
- Service: This layer calls the DAO and perform CRUD operations
- Model: The class mapping to the database table and provides getter and setter functions
- Controller: he class mapping to REST APIs controller for HTTP requests
 
### Step 2.1: Model class
Right click "src/main/java", New -> Class, Package: om.example.userAccount.model, Class Name: UserAccount
 
```Java
package com.example.userAccount.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "`user`")   //double quato is needed for table name
public class UserAccount {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="email")
    private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
```
Right click the file, select "Source" -> "Generate getter and setter"

### Step 2.2: Repository class
Right click "src/main/java", New -> Class, Package: om.example.userAccount.repository, Class Name: UserAccountRepository

```Java
package com.example.userAccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.userAccount.model.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}
```

### Step 2.3: Service class
Right click "src/main/java", New -> Class, Package: om.example.userAccount.service, Class Name: UserAccountService

```Java
package com.example.userAccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userAccount.model.UserAccount;
import com.example.userAccount.repository.UserAccountRepository;
import java.util.List;

@Service
public class UserAccountService {

        @Autowired
        UserAccountRepository rep;     
        
        // CREATE 
        public UserAccount createEmployee(UserAccount acct) {
            return rep.save(acct);
        }

        // READ
        public List<UserAccount> getEmployees() {
            return rep.findAll();
        }

        // DELETE
        public void deleteEmployee(Long empId) {
            rep.deleteById(empId);
        }
        
        // UPDATE
        public UserAccount updateEmployee(Long id, UserAccount acct) {
        	UserAccount cur = rep.findById(id).get();
            cur.setName(acct.getName());
            cur.setEmail(acct.getEmail());
            return rep.save(cur);        
        }
}
```

### Step 2.4: Controller class
Right click "src/main/java", New -> Class, Package: om.example.userAccount.controller, Class Name: UserAccountController

```Java
package com.example.userAccount.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userAccount.model.UserAccount;
import com.example.userAccount.service.UserAccountService;

@RestController
@RequestMapping("/api")
public class UserAccountController {
	
        @Autowired
        UserAccountService empService;
        
        @PostMapping(value="/userAccounts")
        public UserAccount createuserAccount(@RequestBody UserAccount emp) {
            return empService.createUserAccount(emp);
        }
        
        @GetMapping(value="/userAccounts")
        public List<UserAccount> readuserAccounts() {
            return empService.getUserAccount();
        }

        @PutMapping(value="/userAccounts/{empId}")
        public UserAccount readuserAccounts(@PathVariable(value = "empId") Long id, @RequestBody UserAccount empDetails) {
            return empService.updateUserAccount(id, empDetails);
        }

        @DeleteMapping(value="/userAccounts/{empId}")
        public void deleteuserAccounts(@PathVariable(value = "empId") Long id) {
            empService.deleteUserAccount(id);
        }
}
```
 
 ## Step 3: Install PostgreSQL Database
 
 [PostgreSQL Download](https://www.postgresql.org/) <br>
 Create your user account
 Create database, name: postgres
 Connection configuration: host:localhost, port:1234
  
  
## Step 4: Build Application
 
To connect PostgreSQL, type database details in "application.properties" under "src/main/resources" as following

```Java
# Postgres database, account
spring.datasource.url = jdbc:postgresql://localhost:1234/postgres
spring.datasource.username  = postgres
spring.datasource.password  = 1234

#spring.datasource.driver-class-name=org.postgresql.Driver
# Keep the connection alive if idle for a long time (needed in production)
spring.datasource.testWhileIdle=true
spring.datasource.validationQuery=SELECT 1
# ===============================
# JPA / HIBERNATE
# ===============================
# Show or not log for each sql query
spring.jpa.show-sql=true
# Hibernate ddl auto (create, create-drop, update): with "create-drop" the database
# schema will be automatically created afresh for every start of application
spring.jpa.hibernate.ddl-auto=create-drop

# Allows Hibernate to generate SQL optimized for a particular DBMS
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```
 
Right click project on Eclipse and select "Run As" then choose "4 Maven build"
In the "main" tab, type "spring-boot:run" in "Goals"
In the "JRE" tab, type "-Dfork=false" in "VM Arguments". So, we can stop Tomcat in Eclipse
Click on Apply then Run

<img width="995" alt="Screenshot3" src="https://user-images.githubusercontent.com/48862763/150704056-63ebc893-aeda-4fc2-af7c-7e20aeed95ce.png">

## Step 5: Test Application by RestMan

RestMan is extension app for Chrome brower, install RestMan at first

### Step 5.1: Test HTTP POST

- Headers: Content-Type, application/json
<img width="881" alt="Screenshot4" src="https://user-images.githubusercontent.com/48862763/150705186-5496abd4-2719-4906-9651-69ad58670528.png">


- Body: Select "RAW", "JSON", and type following data
<img width="1340" alt="Screenshot5" src="https://user-images.githubusercontent.com/48862763/150705205-d1bd3b96-dd3b-40aa-8810-f319010315e5.png">


- HTTP Link: Select "POST", type "http://localhost:8080/api/userAccounts" and run. HTTP response "200" success code
<img width="1355" alt="Screenshot6" src="https://user-images.githubusercontent.com/48862763/150705227-1a635591-0299-4d21-b12a-c385aa63f26e.png">


- Here is the HTTP response result
<img width="1350" alt="Screenshot7" src="https://user-images.githubusercontent.com/48862763/150705236-0d006fdb-a17f-4c6e-879a-4b5142b7141f.png">


- Data inserted in PostgreSQL table 
<img width="391" alt="Screenshot8" src="https://user-images.githubusercontent.com/48862763/150704784-3b0fd743-2e9b-4570-b461-23a4f9a38b2f.png">


#### Same test procedures apply to "GET", "PUT" and "Delete" HTTP requests
