package entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private int workoutId;
	private int exerciseId;
}
