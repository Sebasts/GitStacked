package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import Entities.User;


@Transactional
@Component
public class PersistenceDAOImpl implements PersistenceDAO {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void createNewUser(User user) {
		em.persist(user);
		
	}

	@Override
	public void updateUserWeight(User user, int weight) {
		// TODO Auto-generated method stub
		
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

	
	
}
