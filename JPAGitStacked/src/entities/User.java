package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.usertype.UserType;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fName;
	private String lName;
	private int weight;
	private int heightFeet;
	private int heightInch;
	private String username;
	private String password;
	@Enumerated(EnumType.STRING)
	private UserType usertype;
	@OneToMany(mappedBy = "user")
	private List<Workout> workouts;

	public String getFName() {
		return fName;
	}

	public void setFName(String fname) {
		this.fName = fname;
	}

	public String getLName() {
		return lName;
	}

	public void setLName(String lname) {
		this.lName = lname;
	}

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

	public UserType getUsertype() {
		return usertype;
	}

	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fName=" + fName + ", lName=" + lName + ", weight=" + weight + ", heightFeet="
				+ heightFeet + ", heightInch=" + heightInch + ", username=" + username + ", password=" + password
				+ ", usertype=" + usertype + "]";
	}
}