package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import entities.Exercise;
import entities.LoginUserType;
import entities.User;
import entities.Workout;
import entities.WorkoutExercise;

@Transactional
@Component
public class PersistenceDAOImpl implements PersistenceDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User createNewUser(User user) {
		if (em == null) {
			System.out.println("em is null");
			return user;
		}
		user.setLoginUsertype(LoginUserType.USER);
		em.persist(user);
		System.out.println(user);
		System.out.println("user created");
		return user;
	}

	// Logs in user and populates user object with database contents upon
	// successful login
	@Override
	public User login(User user) {
		String qry = "select u from User u where u.username = :username";
		User u = em.createQuery(qry, User.class).setParameter("username", user.getUsername()).getSingleResult();
		if (u != null) {
			if (u.getPassword().equals(user.getPassword())) {
				return u;
			}
		}
		return null;
	}

	// User can search for an exercise
	@Override
	public Exercise getExerciseByName(String exerciseName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exercise> getListOfExercises() {
		String query = "select e from Exercise e";
		List<Exercise> exercises = em.createQuery(query, Exercise.class).getResultList();
		return exercises;
	}

	// Method to persist Workout created to the database so it may be added to
	// User view
	@Override
	public Workout persistWorkout(Workout workout) {
		Workout tempWorkout = new Workout();
		tempWorkout = em.find(workout.getClass(), workout.getId());
		if (workout.getDate() != null) {
			tempWorkout.setDate(workout.getDate());
		}
		System.out.println(tempWorkout);
		tempWorkout.setUser(workout.getUser());
		tempWorkout.setWorkoutExercise(workout.getWorkoutExercise());
		em.merge(workout);
		em.flush();
		return workout;
	}

	// Method to persist created User to database for future access
	@Override
	public User persistUser(User user) {
		em.merge(user);
		em.flush();
		return user;
	}

	// Method implements SQL query to remove a workout from a user in the
	// database
	@Override
	public void removeWorkout(int id) {
		String sql = "delete from Workout where id = :id";
		em.createQuery(sql).setParameter("id", id).executeUpdate();
	}

	// Method removes a single WorkoutExercise from User Workout
	@Override
	public void removeWorkoutExercise(int id) {
		String sql = "delete from WorkoutExercise where id = :id";
		em.createQuery(sql).setParameter("id", id).executeUpdate();
	}

	// Method to persist Workout created by User into database
	@Override
	public User persistWorkouts(Workout w) {
		em.merge(w);
		em.flush();
		return w.getUser();
	}

	@Override
	public List<User> getAllUsers() {
		String query = "select u from User u";
		List<User> users = em.createQuery(query, User.class).getResultList();
		return users;
	}

	@Override
	public List<Exercise> getAllExercises() {
		String query = "select e from Exercise e";
		List<Exercise> exercises = em.createQuery(query, Exercise.class).getResultList();
		return exercises;
	}

	@Override
	public int getUserIdByUsername(String username) {
		String query = "select u from User u where u.username = :username";
		User tempUser = em.createQuery(query, User.class).setParameter("username", username).getSingleResult();
		return tempUser.getId();
	}

	@Override
	public Exercise getExerciseById(User user, int id) {
		String query = "select e from Exercise e where e.id = :id";
		Exercise exercise = em.createQuery(query, Exercise.class).setParameter("id", id).getSingleResult();
		return exercise;
	}

	@Override
	public void createExercise(Exercise exercise) {
		em.persist(exercise);
		em.flush();
	}

	@Override
	public List<Workout> getWorkoutsFromUser(User user) {
		List<Workout> userWorkouts = new ArrayList<>();
		try {
			String query = "select u from User u join fetch u.workouts where u.id = :id";
			User userWorkout = em.createQuery(query, User.class).setParameter("id", user.getId()).getSingleResult();
			userWorkouts = userWorkout.getWorkouts();
		} catch (Exception e) {
			User tempUser = em.find(User.class, user.getId());
			tempUser.setWorkouts(new ArrayList<Workout>());
			userWorkouts = tempUser.getWorkouts();
		}
		return userWorkouts;
	}

	@Override
	public void deleteExercise(Exercise exercise, String choice) {
		if (choice.equals("ACTIVE")) {
			Exercise ex = em.find(Exercise.class, getExerciseIdByName(exercise.getName()));
			ex.setActive(1);
			em.merge(ex);
		} else {
			Exercise ex = em.find(Exercise.class, getExerciseIdByName(exercise.getName()));
			ex.setActive(0);
			em.merge(ex);
		}
	}

	@Override
	public int getExerciseIdByName(String name) {
		String query = "select e from Exercise e where e.name = :name";
		Exercise tempName = em.createQuery(query, Exercise.class).setParameter("name", name).getSingleResult();
		return tempName.getId();
	}
	
	@Override
	public int getCaloriesByWorkout(Workout workout) {
		int id = workout.getId();
		List<WorkoutExercise> we = workout.getWorkoutExercise();
		String query = "select we from WorkoutExercise we join fetch we.exercise.calories where we.workoutId = :id";
		Exercise tempName = em.createQuery(query, Exercise.class).setParameter("id", id).getSingleResult();
		return 0;
	}

}
