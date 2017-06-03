package unitTests;

import data.PersistenceDAO;
import data.PersistenceDAOImpl;
import entities.User;

public class PersistTest {

	
	
	public static void main(String[] args) {
		PersistenceDAO dao = new PersistenceDAOImpl();
		
		User u = new User();
		
		u.setWeight(140);
		u.setHeightInch(140);
		u.setWeight(140);
		u.setWeight(140);
		u.setWeight(140);
		u.setWeight(140);
		
		dao.createNewUser(u);
	}
}
