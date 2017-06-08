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

	// EntityManagerFactory emf =
	// Persistence.createEntityManagerFactory("GitStacked");
	// EntityManager em = emf.createEntityManager();

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

	@Override
	public User updateUserWeight(User user, int weight) {
		return user;

	}

	@Override
	public void changePassword(User user, String newPass) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUserHeight(User user, int newHeight) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createWorkout(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createWorkoutExercise(User user) {
		// TODO Auto-generated method stub

	}

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

	@Override
	public Workout persistWorkout(Workout workout) {
		Workout tempWorkout = new Workout();
		tempWorkout = em.find(workout.getClass(), workout.getId());
		if (workout.getDate() != null) {
			tempWorkout.setDate(workout.getDate());
		}
		System.out.println(tempWorkout);

//		tempWorkout.setUserId(workout.getUserId());
//		tempWorkout.setWorkoutExercise(workout.getWorkoutExercise());

		em.merge(workout);
		em.flush();
		return workout;
	}

	@Override
	public User persistUser(User user) {
		user.getWorkouts().size();
		User tempUser = em.find(user.getClass(), user.getId());
		System.out.println(tempUser);
		System.out.println(tempUser.getWorkouts());
		tempUser.setFName(user.getFName());
		tempUser.setLName(user.getLName());
		tempUser.setHeightFeet(user.getHeightFeet());
		tempUser.setHeightInch(user.getHeightInch());
		tempUser.setPassword(user.getPassword());
		tempUser.setUserWeight(user.getUserWeight());
		tempUser.setWorkouts(user.getWorkouts());
		tempUser.setLoginUsertype(user.getLoginUsertype());

		em.merge(user);
		em.flush();
		// em.persist(tempUser);
		System.out.println(user.getWorkouts().size());
		return user;
	}


//	@Override
//	public User persistWorkouts(Workout w) {
//		User tempUser = em.find(w.getUser().getClass(), w.getUser().getId());
//		System.out.println(tempUser);
//		System.out.println(tempUser.getWorkouts());
//		tempUser.setFName(user.getFName());
//		tempUser.setLName(user.getLName());
//		tempUser.setHeightFeet(user.getHeightFeet());
//		tempUser.setHeightInch(user.getHeightInch());
//		tempUser.setPassword(user.getPassword());
//		tempUser.setUserWeight(user.getUserWeight());
//		tempUser.setWorkouts(user.getWorkouts());
//		tempUser.setLoginUsertype(user.getLoginUsertype());
		
//		em.persist(w);
//		em.flush();
//		em.persist(tempUser);
//		System.out.println(user.getWorkouts().size());
//		return w.getUserId();
//	}

	@Override
	public void removeWorkout(int id) {
		System.out.println("***********" + id);
		Workout workout = em.find(Workout.class, id);
		System.out.println("&&&&&&&&&&&&" + workout);
		em.remove(workout);
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
	public Exercise createExercise() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exercise getExerciseById(User user, int id) {
		String query = "select e from Exercise e where e.id = :id";
		Exercise exercise = em.createQuery(query, Exercise.class).setParameter("id", id).getSingleResult();
		return exercise;

	}


	@Override
	public void createExercise(Exercise exercise) {
		if (em == null) {
			System.out.println("em is null");
		}
		System.out.println(exercise);
		em.persist(exercise);
		em.flush();
		System.out.println("exercise created");

	}

	@Override
	public List<Workout> getWorkoutsFromUser(User user) {
		List<Workout> userWorkouts = new ArrayList<>();
		try {
			String query = "select u from User u join fetch u.workouts where u.id = :id";
			System.out.println(user);
			User userWorkout = em.createQuery(query, User.class).setParameter("id", user.getId()).getSingleResult();
			System.out.println(userWorkout);
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
			System.out.println(ex);
			ex.setActive(1);
			em.merge(ex);
			//UPDATE exercise SET active = 0 WHERE id = 43;
		} else {
			Exercise ex = em.find(Exercise.class, getExerciseIdByName(exercise.getName()));
			System.out.println(ex);
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
		// TODO Auto-generated method stub
		return 0;
	}
	
//	@Override
//	public int getCaloriesByWorkout(Workout workout) {
//		int id = workout.getId();
//		List<WorkoutExercise> we = workout.getWorkoutExercise();
//		String query = "select we from WorkoutExercise we join fetch we.exercises where we.id = :id";
//		return 0;
//		String query = "select we from WorkoutExercise we where  = :name";
//		Exercise tempName = em.createQuery(query, Exercise.class).setParameter("name", name).getSingleResult();
//		return tempName.getId();
//	}

	@Override
	public User persistWorkouts(Workout w) {
		// TODO Auto-generated method stub
		return null;
	}

}
