package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.usertype.UserType;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int weight;
	private int heightFeet;
	private int heightInch;
	private String username;
	private String password;
	private UserType usertype;
	
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getHeightFeet() {
		return heightFeet;
	}
	public void setHeightFeet(int heightFeet) {
		this.heightFeet = heightFeet;
	}
	public int getHeightInch() {
		return heightInch;
	}
	public void setHeightInch(int heightInch) {
		this.heightInch = heightInch;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", weight=" + weight + ", heightFeet=" + heightFeet + ", heightInch=" + heightInch
				+ ", username=" + username + ", password=" + password + ", usertype=" + usertype + "]";
	}
}
