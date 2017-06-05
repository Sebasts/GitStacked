package data;

import entities.Exercise;
import entities.User;

public interface PersistenceDAO {

	void createNewUser(User user);
	void updateUserWeight(User user, int weight);
	void changePassword(User user, String newPass);
	void updateUserHeight(User user, int newHeight);
	void createWorkout(User user);
	void createWorkoutExercise(User user);
	Exercise getExerciseByName(String exerciseName);
		
}
