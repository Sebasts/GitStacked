package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="workoutExercise")
public class WorkoutExercise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int reps;
	private int duration;
	private Date date;
	private int weight;
	@Enumerated(EnumType.STRING)
	private Type type;
	@ManyToOne
	@JoinColumn(name="workoutId")
	private Workout workout;
	@ManyToOne
	@JoinColumn(name="exerciseId")
	private Exercise exercise;
	public int getReps() {
		return reps;
	}
	public void setReps(int reps) {
		this.reps = reps;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Workout getWorkout() {
		return workout;
	}
	public void setWorkout(Workout workout) {
		this.workout = workout;
	}
	public Exercise getExercise() {
		return exercise;
	}
	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "WorkoutExercise [id=" + id + ", reps=" + reps + ", duration=" + duration + ", date=" + date
				+ ", weight=" + weight + ", type=" + type + "]";
	}
}
