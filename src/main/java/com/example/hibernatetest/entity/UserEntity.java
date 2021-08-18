package com.example.hibernatetest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "USER_ENTITY")
public class UserEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@Column(name="FIRST_NAME")
    private String firstName;

	@Column(name="LAST_NAME")
    private String lastName;
	
	@Column(name="IS_NEW")
    private String isNew;

	
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}
	

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
}
