//package unitTests.dao;
//
//import static org.junit.Assert.assertEquals;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.WebApplicationContext;
//
//import data.FilmDAO;
//import data.PersistenceDAO;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "../WEB-INF/Test-context.xml" })
//@WebAppConfiguration
//@Transactional
//public class WorkoutTest {
//
//	@Autowired
//	private WebApplicationContext wac;
//
//	@Autowired
//	private PersistenceDAO dao;
//
//	@PersistenceContext
//	private EntityManager em;
//
//	@Before
//	public void setUp() {
//		dao = (PersistenceDAO) wac.getBean("dao");
//	}
//
//	@Test
//	public void indexReturnsAListOfAllFilms(){
//		
//		assertEquals("ACADEMY DINOSAUR", dao.index().get(0).getTitle());
//	}
//	@Test
//	public void showReturnsTheCorrectFilm(){
//		
//		assertEquals("ACADEMY DINOSAUR", dao.show(1).getTitle());
//	}
//	
//	
//	@Test
//	public void destoryRemovesAFilmFromDb(){
//		
//		assertEquals(true, dao.destroy(1005));
//	}
//		
//	
//	@After
//	public void tearDown() {
//		dao = null;
//		em = null;
//		wac = null;
//	}
//}