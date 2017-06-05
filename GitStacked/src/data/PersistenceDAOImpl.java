package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;






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

	
	
}
