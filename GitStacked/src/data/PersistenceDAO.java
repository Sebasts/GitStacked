package data;

import java.util.List;

import entities.Exercise;
import entities.User;
import entities.Workout;
import entities.WorkoutExercise;

public interface PersistenceDAO {

	User createNewUser(User user);
	User persistUser(User user);
	User updateUserWeight(User user, int weight);
	Exercise createExercise();
	void changePassword(User user, String newPass);
	void updateUserHeight(User user, int newHeight);
	void createWorkout(User user);
	void createWorkoutExercise(User user);
	Exercise getExerciseByName(String exerciseName);
	List<Exercise> getListOfExercises();
	int getUserIdByUsername(String username);
	User login(User user);
	void createExercise(Exercise exercise);
	void deleteExercise(Exercise exercise);
	int getExerciseIdByName(String name);
	List<User> getAllUsers();
	Exercise getExerciseById(User user, int id);
	Workout persistWorkout(Workout workout);
	User persistWorkouts(Workout w);
	List<Workout> getWorkoutsFromUser(User user);
	void removeWorkout(int id);
	List<Exercise> getAllExercises();

		
}
