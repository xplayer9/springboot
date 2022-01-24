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
        public UserAccount createUserAccount(UserAccount acct) {
            return rep.save(acct);
        }

        // READ
        public List<UserAccount> getUserAccount() {
            return rep.findAll();
        }

        // DELETE
        public void deleteUserAccount(Long empId) {
            rep.deleteById(empId);
        }
        
        // UPDATE
        public UserAccount updateUserAccount(Long id, UserAccount acct) {
        	UserAccount cur = rep.findById(id).get();
            cur.setName(acct.getName());
            cur.setEmail(acct.getEmail());
            return rep.save(cur);        
        }
}