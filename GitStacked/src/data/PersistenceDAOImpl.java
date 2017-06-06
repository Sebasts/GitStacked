package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import entities.Exercise;
import entities.LoginUserType;
import entities.User;
import entities.Workout;

@Transactional
@Component
public class PersistenceDAOImpl implements PersistenceDAO {
	
	@PersistenceContext
	private EntityManager em;
	
//	EntityManagerFactory emf = Persistence.createEntityManagerFactory("GitStacked");
//	EntityManager em = emf.createEntityManager();
	
	@Override
	public User createNewUser(User user) {
		if(em == null){
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
		tempWorkout.setUser(workout.getUser());
		tempWorkout.setWorkoutExercise(workout.getWorkoutExercise());
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
//		em.persist(tempUser);
		System.out.println(user.getWorkouts().size());
		return user;
	}

	@Override
	public User persistWorkouts(Workout w) {
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
		
		em.persist(w);
		em.flush();
//		em.persist(tempUser);
//		System.out.println(user.getWorkouts().size());
		return w.getUser();
	}
	@Override
	public List<User> getAllUsers() {
		String query = "select u from User u";
		List<User> users = em.createQuery(query, User.class).getResultList();
		return users;
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
		if(em == null){
			System.out.println("em is null");
//			return exercise;
		}
		exercise.setMuscleGroup(exercise.getMuscleGroup());
		System.out.println(exercise);
		em.persist(exercise);
		System.out.println("exercise created");
//		return exercise;
		
	}
	
}

//public User persistUser(User user) {
//	User tempUser = em.find(user.getClass(), user.getId());
//	System.out.println(tempUser);
//	System.out.println(tempUser.getWorkouts());
//	tempUser.setFName(user.getFName());
//	tempUser.setLName(user.getLName());
//	tempUser.setHeightFeet(user.getHeightFeet());
//	tempUser.setHeightInch(user.getHeightInch());
//	tempUser.setPassword(user.getPassword());
//	tempUser.setUserWeight(user.getUserWeight());
//	tempUser.setWorkouts(user.getWorkouts());
//	tempUser.setLoginUsertype(user.getLoginUsertype());
//	
//	em.merge(tempUser);
//	em.flush();
////	em.persist(tempUser);
//	System.out.println(user.getWorkouts().size());
//	return user;
//}
