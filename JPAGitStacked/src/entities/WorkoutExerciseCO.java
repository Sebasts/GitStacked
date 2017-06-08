package entities;


/*
 * WorkoutExercise Command Object for constructing multiple WorkoutExercises
 * Takes in String from jsp form and creates command objects
 */

public class WorkoutExerciseCO {
	
	private String reps;
	private String weight;
	private String duration;
	private String newWorkoutName;
	private String exerciseId;
	
	public String getReps() {
		return reps;
	}
	public void setReps(String reps) {
		this.reps = reps;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getNewWorkoutName() {
		return newWorkoutName;
	}
	public void setNewWorkoutName(String newWorkoutName) {
		this.newWorkoutName = newWorkoutName;
	}
	public String getExerciseId() {
		return exerciseId;
	}
	public void setExerciseId(String exerciseId) {
		this.exerciseId = exerciseId;
	}

	
	

}
