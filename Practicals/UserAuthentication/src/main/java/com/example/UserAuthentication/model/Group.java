package com.example.UserAuthentication.model;



import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Column(nullable = false)
    private String groupName;

    @OneToMany(mappedBy = "group")
    private Set<User> users;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Group(Long groupId, String groupName, Set<User> users) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.users = users;
	}

	public Group() { }

	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", groupName=" + groupName + ", users=" + users + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(groupId, groupName, users);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		return Objects.equals(groupId, other.groupId) && Objects.equals(groupName, other.groupName)
				&& Objects.equals(users, other.users);
	}
	
	
    
    
}
