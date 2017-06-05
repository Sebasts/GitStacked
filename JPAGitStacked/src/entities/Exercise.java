package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="exercise")
public class Exercise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String desc;
	private String imageUrl;
	private int calories;
	@Enumerated(EnumType.STRING)
	@Column(name="muscleGroup")
	private MuscleGroup muscleGroup;
	@OneToMany(mappedBy="exercise")
	private List<WorkoutExercise> workoutExercise;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public List<WorkoutExercise> getWorkoutExercise() {
		return workoutExercise;
	}
	public void setWorkoutExercise(List<WorkoutExercise> workoutExercise) {
		this.workoutExercise = workoutExercise;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Exercise [id=" + id + ", name=" + name + ", desc=" + desc + ", imageUrl=" + imageUrl + ", calories="
				+ calories + ", muscleGroup=" + muscleGroup + "]";
	}
}
