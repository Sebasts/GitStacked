package data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import entities.Exercise;
import entities.User;


@Transactional
@Component
public class PersistenceDAOImpl implements PersistenceDAO {
	
//	@PersistenceContext
//	private EntityManager em;
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("GitStacked");
	EntityManager em = emf.createEntityManager();
	
	@Override
	public User createNewUser(User user) {
		if(em == null){
			System.out.println("em is null");
		}
		em.persist(user);
		return null;
	}

	@Override
	public User updateUserWeight(User user, int weight) {
		return null;
		
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
