package com.example.userAccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.userAccount.model.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}