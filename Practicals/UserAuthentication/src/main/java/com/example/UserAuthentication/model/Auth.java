package com.example.UserAuthentication.model;


import java.util.Objects;

import jakarta.persistence.*;


@Entity
@Table(name = "auth")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

	public Long getAuthId() {
		return authId;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Auth(Long authId, User user, Group group) {
		super();
		this.authId = authId;
		this.user = user;
		this.group = group;
	}

	public Auth() { }

	@Override
	public String toString() {
		return "Auth [authId=" + authId + ", user=" + user + ", group=" + group + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(authId, group, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auth other = (Auth) obj;
		return Objects.equals(authId, other.authId) && Objects.equals(group, other.group)
				&& Objects.equals(user, other.user);
	}
	
	
    
    
}
