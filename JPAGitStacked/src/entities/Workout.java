package entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="workout")
public class Workout {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date date;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	@OneToMany(mappedBy="workout")
	private List<WorkoutExercise> workoutExercise;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Workout [id=" + id + ", date=" + date + ", user=" + user + "]";
	}
}
