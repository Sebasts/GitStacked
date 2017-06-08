package data;

import java.util.List;

import entities.Exercise;
import entities.User;
import entities.Workout;
import entities.WorkoutExercise;

/*
 * This blueprint which provides context for persisting GitStacked web application
 */

public interface PersistenceDAO {

	User createNewUser(User user);
	User persistUser(User user);
	Exercise getExerciseByName(String exerciseName);
	List<Exercise> getListOfExercises();
	int getUserIdByUsername(String username);
	User login(User user);
	void createExercise(Exercise exercise);
	void deleteExercise(Exercise exercise, String choice);
	int getExerciseIdByName(String name);
	List<User> getAllUsers();
	Exercise getExerciseById(User user, int id);
	Workout persistWorkout(Workout workout);
	User persistWorkouts(Workout w);
	List<Workout> getWorkoutsFromUser(User user);
	void removeWorkout(int id);
	List<Exercise> getAllExercises();
	int getCaloriesByWorkout(Workout workout);
	void removeWorkoutExercise(int id);

		
}
