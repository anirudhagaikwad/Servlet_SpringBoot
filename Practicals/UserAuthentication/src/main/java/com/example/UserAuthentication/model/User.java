package com.example.UserAuthentication.model;


import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String mobileNumber;

    @Column
    private String alternateMobile;

    @Column(nullable = false)
    private String aadharNumber;

    @Column(nullable = false)
    private String panNumber;

    @Column(nullable = false)
    private String primaryAddress;

    @Column
    private String secondaryAddress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAlternateMobile() {
		return alternateMobile;
	}

	public void setAlternateMobile(String alternateMobile) {
		this.alternateMobile = alternateMobile;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getPrimaryAddress() {
		return primaryAddress;
	}

	public void setPrimaryAddress(String primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	public String getSecondaryAddress() {
		return secondaryAddress;
	}

	public void setSecondaryAddress(String secondaryAddress) {
		this.secondaryAddress = secondaryAddress;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User(Long userId, String firstName, String lastName, String email, String password, String mobileNumber,
			String alternateMobile, String aadharNumber, String panNumber, String primaryAddress,
			String secondaryAddress, Group group) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.alternateMobile = alternateMobile;
		this.aadharNumber = aadharNumber;
		this.panNumber = panNumber;
		this.primaryAddress = primaryAddress;
		this.secondaryAddress = secondaryAddress;
		this.group = group;
	}

	public User() { }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", mobileNumber=" + mobileNumber + ", alternateMobile=" + alternateMobile
				+ ", aadharNumber=" + aadharNumber + ", panNumber=" + panNumber + ", primaryAddress=" + primaryAddress
				+ ", secondaryAddress=" + secondaryAddress + ", group=" + group + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(aadharNumber, alternateMobile, email, firstName, group, lastName, mobileNumber, panNumber,
				password, primaryAddress, secondaryAddress, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(aadharNumber, other.aadharNumber)
				&& Objects.equals(alternateMobile, other.alternateMobile) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(group, other.group)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(mobileNumber, other.mobileNumber)
				&& Objects.equals(panNumber, other.panNumber) && Objects.equals(password, other.password)
				&& Objects.equals(primaryAddress, other.primaryAddress)
				&& Objects.equals(secondaryAddress, other.secondaryAddress) && Objects.equals(userId, other.userId);
	}
	
	
    
    
}
