package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		User user = new User();
		ModelAndView mv = new ModelAndView("signup.jsp", "user", user);
		return mv;
	}
	
	@RequestMapping(path = "createUser.do", method = RequestMethod.POST) 
	public ModelAndView createUser(@ModelAttribute("user") User user) {
		dao.createNewUser(user);
		ModelAndView mv = new ModelAndView("profile.jsp", "user", user);
		return mv;
	}
	
	@RequestMapping(path = "editUser.do", method = RequestMethod.POST) 
	public ModelAndView editWeight(@ModelAttribute("user") User user) {
		dao.updateUserWeight(user, user.getWeight());
		ModelAndView mv = new ModelAndView("profile.jsp", "user", user);
		return mv;
	}
	
	
}
