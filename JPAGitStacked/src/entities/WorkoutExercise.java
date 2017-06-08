package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * WorkoutExercise entity represents join table between Exercise and Workout in the database
 * WorkoutExercise has an exercise with other relevant data such as reps and weight
 * Many to One relationship with Workout
 */

@Entity
@Table(name="workoutexercise")
public class WorkoutExercise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int reps;
	private int duration;
	private LocalDate date;
	private int weight;
	
	@Enumerated(EnumType.STRING)
	@Column(name="typeId")
	private Type type;
	
	@ManyToOne
	@JoinColumn(name="workoutId")
	private Workout workout;
	
	@OneToOne
	@JoinColumn(name="exerciseId")
	private Exercise exercise;
	
	public WorkoutExercise() {}
	public WorkoutExercise(Exercise exercise, int reps, int weight, LocalDate date) {
		this.exercise = exercise;
		this.reps = reps;
		this.weight = weight;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		LocalDate p = LocalDate.parse(date.toString(), dtf);
		this.date = p;
	}
	public WorkoutExercise(Exercise exercise, int reps, int weight, LocalDate date, int duration) {
		this.exercise = exercise;
		this.reps = reps;
		this.weight = weight;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate p = LocalDate.parse(date.toString(), dtf);
		this.date = p;
		this.duration = duration;
	}
	
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
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
				+ ", weight=" + weight + ", type=" + type + ", workout=" + workout + ", exercise=" + exercise + "]";
	}
	
}
