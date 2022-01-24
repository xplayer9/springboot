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
