package data;

import java.util.List;

import entities.Exercise;
import entities.User;
import entities.Workout;

public interface PersistenceDAO {

	User createNewUser(User user);
	User persistUser(User user);
	User updateUserWeight(User user, int weight);
	void changePassword(User user, String newPass);
	void updateUserHeight(User user, int newHeight);
	void createWorkout(User user);
	void createWorkoutExercise(User user);
	Exercise getExerciseByName(String exerciseName);
	List<Exercise> getListOfExercises();
	User login(User user);
	Exercise getExerciseById(User user, int id);
	Workout persistWorkout(Workout workout);
		
}
