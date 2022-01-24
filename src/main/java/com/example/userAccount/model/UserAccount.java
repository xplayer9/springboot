package com.example.userAccount.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "`user`")
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
