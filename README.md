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
Go to the [Spring Initializer](https://start.spring.io/)

In "Spring Intializr" web page, enter following info
- Choose "Maven Project", Language "Java" and Spring Boot version "2.5.9"
- Group: Keep default, no need to change
- Artifact: type “userAccount”
- Name: type “userAccount”
- Description: type any description
- Choose “Jar”, it will include embedded Tomcat server provided by Spring Boot
- Choose Java SDK 11

Add the following Dependencies
- Spring Web: required for RESTful web applications.
- Spring Data JPA: required to access the data from the database. JPA (Java Persistence API) 
- PostgresSQL Driver: required to connect with PostgresSQL database.

My Screenshot
<img width="1191" alt="Screenshot1" src="https://user-images.githubusercontent.com/48862763/150665780-bb9a8016-8266-4ad0-8105-df1913e09e68.png">

Click the Generate button at the bottom of the screen, this will generate a project zip file

## Step 1.1: Create dynamic web project in Eclipse
Next, let's extract the zip file to a local folder
Open Eclipse IDE and go to File and select Import. Under Maven, choose Existing Maven Projects and choose the local folder you extract the zip file
Click on Finish, then a web project will be created

UserAccountApplication.java is the program entry point with @SpringBootApplication annotations

<img width="258" alt="Screenshot2" src="https://user-images.githubusercontent.com/48862763/150666329-fd4835b9-09f1-4ade-884f-ca5672e55f76.png">

## Step 2: Add sub-packages to the project
 
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
@Table(name = "user")
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
Right click file, select "Source" -> "Generate getter and setter"

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
  
 
 
