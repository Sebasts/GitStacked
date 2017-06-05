package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import data.PersistenceDAO;
import entities.User;

@SessionAttributes({"user"})
@Controller
public class WorkoutController {

	@Autowired
	private PersistenceDAO dao;
	
	@ModelAttribute("user")
	public User newUser() {
		return new User();
	}
	
	@RequestMapping(path = "createUser.do", method = RequestMethod.GET) 
	public ModelAndView signupForm() {
		ModelAndView mv = new ModelAndView();
//		mv.addObject("user", user);
		mv.setViewName("signup.jsp");
		return mv;
	}
	
	@RequestMapping(path = "createUser.do", method = RequestMethod.POST) 
	public ModelAndView createUser(@ModelAttribute("user") User user) {
		dao.createNewUser(user);
//		ModelAndView mv = new ModelAndView("profile.jsp", "user", user);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("profile.jsp");
		return mv;
	}
	
	@RequestMapping(path = "login.do", method = RequestMethod.GET) 
	public ModelAndView loginForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login.jsp");
		return mv;
	}
	
	@RequestMapping(path = "login.do", method = RequestMethod.POST) 
	public ModelAndView login(@ModelAttribute("user") User user) {
		ModelAndView mv = new ModelAndView();
		if (dao.login(user) == null) {
			mv.setViewName("incorrectLogin.jsp");
			return mv;
		}
		mv.addObject("user", dao.login(user));
		mv.setViewName("profile.jsp");
		return mv;
	}
	
	@RequestMapping(path = "editUser.do", method = RequestMethod.POST) 
	public ModelAndView editWeight(@ModelAttribute("user") User user) {
		dao.updateUserWeight(user, user.getWeight());
		ModelAndView mv = new ModelAndView("profile.jsp", "user", user);
		return mv;
	}
	
	@RequestMapping(path = "getExercise.do", method = RequestMethod.GET) 
	public ModelAndView getExerciseByName(@RequestParam("exerciseName") String exerciseName) {
		ModelAndView mv = new ModelAndView("exercisePage.jsp", "exercise", dao.getExerciseByName(exerciseName));
		return mv;
	}
	
	@RequestMapping(path = "createWorkout.do", method = RequestMethod.GET)
	public ModelAndView createWorkout() {
		ModelAndView mv = new ModelAndView("workoutBuilder.jsp", "exercises", dao.getListOfExercises());
		return mv;
	}
	
//	@RequestMapping(path = "createWorkout.do", method = RequestMethod.POST)
//	public ModelAndView createWorkout(@RequestParam("")) {
//		ModelAndView mv = new ModelAndView("workoutBuilder.jsp", "exercise", dao.getListOfExercises());
//		return mv;
//	}
	
}
