package entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * Workout Entity represents Many to One relationship with User
 * Contains list of WorkoutExercises
 */

@Entity
@Table(name="workout")
public class Workout {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate date;
	private String name;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@OneToMany(mappedBy="workout", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<WorkoutExercise> workoutExercise;
	
	public Workout(LocalDate date) {
		this.date = date;
	}
	public Workout() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void addWorkoutExercise(WorkoutExercise we) {
		if (workoutExercise == null) {
			workoutExercise = new ArrayList<>();
			workoutExercise.add(we);
		}
		else {
			workoutExercise.add(we);
		}
	}
	public List<WorkoutExercise> getWorkoutExercise() {
		return workoutExercise;
	}
	public void setWorkoutExercise(List<WorkoutExercise> workoutExercise) {
		this.workoutExercise = workoutExercise;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Workout [id=" + id + ", date=" + date + ", name=" + name + ", user=" + user.getFName() + ", workoutExercise="
				+ workoutExercise.size() + "]";
	}
}
