package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import data.PersistenceDAO;
import entities.Exercise;
import entities.LoginUserType;
import entities.User;
import entities.Workout;
import entities.WorkoutExercise;

@SessionAttributes({ "user", "workout", "userWorkoutExercises"})
@Controller
public class WorkoutController {

	@Autowired
	private PersistenceDAO dao;

	@PersistenceContext
	EntityManager em;

	@ModelAttribute("exercise")
	public Exercise newExercise() {
		return new Exercise();
	}

	@ModelAttribute("user")
	public User newUser() {
		return new User();
	}
	@ModelAttribute("userWorkoutExercises")
	public List<WorkoutExercise> newUserWorkoutExercises() {
		return new ArrayList<>();
	}

	@RequestMapping(path = "createUser.do", method = RequestMethod.GET)
	public ModelAndView signupForm() {
		ModelAndView mv = new ModelAndView();
		// mv.addObject("user", user);
		mv.setViewName("signup.jsp");
		return mv;
	}

	@RequestMapping(path = "createUser.do", method = RequestMethod.POST)
	public ModelAndView createUser(@ModelAttribute("user") User user) {
		System.out.println(user);
		dao.createNewUser(user);
		// ModelAndView mv = new ModelAndView("profile.jsp", "user", user);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("profile.jsp");
		return mv;
	}

	@RequestMapping(path = "login.do", method = RequestMethod.GET)
	public ModelAndView loginForm(@ModelAttribute("user") User user) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login.jsp");
		return mv;
	}

	@RequestMapping(path = "login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user") User user) {
		System.out.println("**************"+user);
		ModelAndView mv = new ModelAndView();
		if (dao.login(user) == null) {
			mv.setViewName("incorrectLogin.jsp");
			return mv;
		}
		if (dao.login(user).getLoginUsertype() == LoginUserType.ADMIN) {
			mv.addObject("user", dao.login(user));
			mv.addObject("users", dao.getAllUsers());
			mv.addObject("exercises", dao.getAllExercises());
			mv.setViewName("admin.jsp");
			return mv;
		}
			mv.addObject("user", dao.login(user));
			
			List<Workout> userWorkouts = dao.getWorkoutsFromUser(dao.login(user));
			mv.addObject("userWorkouts", userWorkouts);
			mv.setViewName("profile.jsp");
			return mv;
	}

	@RequestMapping(path = "editUser.do", method = RequestMethod.POST)
	public ModelAndView editWeight(@ModelAttribute("user") User user) {
		System.out.println(user);
		ModelAndView mv = new ModelAndView("profile.jsp", "user", dao.persistUser(user));
		return mv;
	}

	@RequestMapping(path = "getExercise.do", method = RequestMethod.GET)
	public ModelAndView getExerciseByName(@RequestParam("exerciseName") String exerciseName) {
		ModelAndView mv = new ModelAndView("exercisePage.jsp", "exercise", dao.getExerciseByName(exerciseName));
		return mv;
	}

	@RequestMapping(path = "createWorkout.do", method = RequestMethod.GET)
	public ModelAndView createWorkout(@ModelAttribute("user") User user, WorkoutExercise workoutexercise) {
		ModelAndView mv = new ModelAndView("workoutBuilder.jsp", "exercises", dao.getListOfExercises());
		// user.getWorkouts().get(0)
		return mv;
	}

	@RequestMapping(path = "updateUserType.do", method = RequestMethod.POST)
	public ModelAndView createWorkout(@ModelAttribute("user") User user, String username, String choice) {
		User tempUser = em.find(User.class, dao.getUserIdByUsername(username));
		if (choice.equals("ADMIN")) {
			tempUser.setLoginUsertype(LoginUserType.ADMIN);
		} else {
			tempUser.setLoginUsertype(LoginUserType.USER);
		}
		dao.persistUser(tempUser);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin.jsp");
		mv.addObject("users", dao.getAllUsers());
		mv.addObject("user", user);
		return mv;
	}

	@RequestMapping(path = "createWorkout.do", method = RequestMethod.POST)
	public ModelAndView publishWorkout(@RequestParam("exerciseId") Integer id, @ModelAttribute("user") User user,
			@RequestParam("reps") Integer reps, @RequestParam("name") String name,
			@RequestParam("weight") Integer weight,
			@RequestParam(value = "duration", required = false) Integer duration) {
		Exercise exercise = dao.getExerciseById(user, id);
		WorkoutExercise workoutexercise = null;
		if (duration == null) {
			workoutexercise = new WorkoutExercise(exercise, reps, weight);
		} else {
			workoutexercise = new WorkoutExercise(exercise, reps, weight, duration);
		}
		System.out.println(workoutexercise);
		Workout workout = new Workout();
		workoutexercise.setWorkout(workout);
		workout.addWorkoutExercise(workoutexercise);
		workout.setName(name);
		workout.setUser(user);
		dao.persistWorkouts(workout);
		ModelAndView mv = new ModelAndView("profile.jsp");
		List<Workout> userWorkouts = dao.getWorkoutsFromUser(user);
		mv.addObject("userWorkouts", userWorkouts);
		mv.addObject("user", user);
		for (Workout workout2 : user.getWorkouts()) {
			System.out.println(workout2);
		}
		return mv;
	}
	
	//user adds exercise to their workout list
	@RequestMapping(path = "createWorkoutList.do", method = RequestMethod.POST)
	public String addExerciseToWorkoutList(@RequestParam("exerciseId") Integer id, @ModelAttribute("user") User user, WorkoutExercise workoutExercise, @ModelAttribute("userWorkoutExercises") List<WorkoutExercise> userWorkoutExercises) {
		Exercise exercise = dao.getExerciseById(user, id);
//		WorkoutExercise workoutexercise = null;
		workoutExercise.setExercise(exercise);
		userWorkoutExercises.add(workoutExercise);
		System.out.println("in create Workout List");
		System.out.println(workoutExercise);
//		Workout workout = new Workout();
//		workoutexercise.setWorkout(workout);
//		workout.addWorkoutExercise(workoutexercise);
//		workout.setName(name);
//		workout.setUser(user);
//		dao.persistWorkouts(workout);
		ModelAndView mv = new ModelAndView();
		mv.addObject(userWorkoutExercises);
//		List<Workout> userWorkouts = dao.getWorkoutsFromUser(user);
//		mv.addObject("userWorkouts", userWorkouts);
//		mv.addObject("user", user);
//		for (Workout workout2 : user.getWorkouts()) {
//			System.out.println(workout2);
//		}
		return "redirect:redirect.do";
	}
	
	//user completes building their workout and this list is persisted
	@RequestMapping(path = "completeWorkout.do", method = RequestMethod.POST)
	public ModelAndView publishWorkout(@ModelAttribute("user") User user, @ModelAttribute("userWorkoutExercises") List<WorkoutExercise> userWorkoutExercises) {
//		Exercise exercise = dao.getExerciseById(user, id);
//		WorkoutExercise workoutexercise = null;
		System.out.println("in complete Workout");
//		System.out.println(workoutExercise);
		Workout workout = new Workout();
		workout.setWorkoutExercise(userWorkoutExercises);
		System.out.println("Getting user workouts");
		List<Workout> userWorkouts = dao.getWorkoutsFromUser(user);
		userWorkouts.add(workout);
		user.setWorkouts(userWorkouts);
//		workoutexercise.setWorkout(workout);
//		workout.addWorkoutExercise(workoutexercise);
//		workout.setName(name);
//		workout.setUser(user);
//		dao.persistWorkouts(workout);
		ModelAndView mv = new ModelAndView("profile.jsp");
		mv.addObject("userWorkouts", userWorkouts);
		mv.addObject("user", user);
		for (Workout workout2 : user.getWorkouts()) {
			System.out.println(workout2);
		}
		return mv;
	}
	
	@RequestMapping(path= "redirect.do") 
	public ModelAndView redirect(@ModelAttribute("user") User user, @ModelAttribute("userWorkoutExercises") List<WorkoutExercise> userWorkoutExercises) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(userWorkoutExercises);
		mv.addObject("exercises", dao.getListOfExercises());
		mv.setViewName("workoutBuilder.jsp");
		return mv;
	}

	@RequestMapping(path = "logout.do")
	public ModelAndView logoutUser(@ModelAttribute("user") User user) {
		ModelAndView mv = new ModelAndView();
		User u = new User();
		user = u;
		mv.setViewName("index.jsp");
		mv.addObject("user", user);
		return mv;
	}

	@RequestMapping(path = "createExercise.do", method = RequestMethod.POST)
	public ModelAndView createExercise(Exercise exercise) {
		System.out.println(exercise);
		dao.createExercise(exercise);
		ModelAndView mv = new ModelAndView();
		mv.addObject("exercise", exercise);
		mv.addObject("users", dao.getAllUsers());
		mv.addObject("exercises", dao.getAllExercises());
		mv.setViewName("admin.jsp");
		return mv;
	}

	@RequestMapping(path = "removeWorkout.do", method = RequestMethod.POST)
	public ModelAndView removeWorkout(@ModelAttribute("user") User user, Integer id) {
		dao.removeWorkout(id);
		System.out.println("%%%%%%%%%%%%%%%%%" + id);
		ModelAndView mv = new ModelAndView();
		List<Workout> userWorkouts = dao.getWorkoutsFromUser(user);
		System.out.println("^^^^^^^^^^^^^^^^^^^^" + userWorkouts);
		mv.addObject("userWorkouts", userWorkouts);
		mv.setViewName("profile.jsp");
		return mv;
	}

	
	@RequestMapping(path = "deleteExercise.do", method = RequestMethod.POST)
	public ModelAndView deleteExercise(@ModelAttribute("user")User user, Exercise name, String choice) {
		System.out.println(name+"AAAAAAAAAHHHHHHHHHHHHHHHHHHHH");
		dao.deleteExercise(name, choice);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.addObject("users", dao.getAllUsers());
		mv.addObject("exercises", dao.getAllExercises());
		mv.setViewName("admin.jsp");
		return mv;
	}
	
}
