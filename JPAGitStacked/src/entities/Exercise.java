package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Entity to represent Exercise table in database
 */

@Entity
@Table(name="exercise")
public class Exercise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int active;
	private String name;
	private String description;
	private String imageUrl;
	private int calories;
	
	@Enumerated(EnumType.STRING)
	@Column(name="muscleGroup")
	private MuscleGroup muscleGroup;
	
	public String getTodaysDate() {
		System.out.println("************" + new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String dateString = sdf.format(new Date());
		return dateString;
	}
	
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public MuscleGroup getMuscleGroup() {
		return muscleGroup;
	}
	public void setMuscleGroup(MuscleGroup muscleGroup) {
		this.muscleGroup = muscleGroup;
	}

	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Exercise [id=" + id + ", active=" + active + ", name=" + name + ", description=" + description
				+ ", imageUrl=" + imageUrl + ", calories=" + calories + ", muscleGroup=" + muscleGroup + "]";
	}
}
