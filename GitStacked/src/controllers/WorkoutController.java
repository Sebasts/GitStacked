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

@SessionAttributes({ "user", "workout", "userWorkoutExercises", "workoutExercise" })
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

	@ModelAttribute("workoutExercise")
	public WorkoutExercise newWorkoutExercise() {
		return new WorkoutExercise();
	}

	@ModelAttribute("userWorkoutExercises")
	public List<WorkoutExercise> newUserWorkoutExercises() {
		return new ArrayList<>();
	}

	// Forwards user to sign up page
	@RequestMapping(path = "createUser.do", method = RequestMethod.GET)
	public ModelAndView signupForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("signup.jsp");
		return mv;
	}

	// User created and persisted to database
	@RequestMapping(path = "createUser.do", method = RequestMethod.POST)
	public ModelAndView createUser(@ModelAttribute("user") User user) {
		dao.createNewUser(user);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("profile.jsp");
		return mv;
	}

	// User forwarded to login page
	@RequestMapping(path = "login.do", method = RequestMethod.GET)
	public ModelAndView loginForm(@ModelAttribute("user") User user) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login.jsp");
		return mv;
	}

	// Handles login validation
	@RequestMapping(path = "login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user") User user) {
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

	// User updates account information and update persisted to database
	@RequestMapping(path = "editUser.do", method = RequestMethod.POST)
	public ModelAndView editWeight(@ModelAttribute("user") User user) {
		ModelAndView mv = new ModelAndView("profile.jsp", "user", dao.persistUser(user));
		mv.addObject("userWorkouts", dao.getWorkoutsFromUser(user));
		return mv;
	}
	
	@RequestMapping(path = "home.do")
	public ModelAndView goHome(@ModelAttribute("user") User user) {
		ModelAndView mv = new ModelAndView("profile.jsp", "user", dao.persistUser(user));
		mv.addObject("userWorkouts", dao.getWorkoutsFromUser(user));
		return mv;
	}

	@RequestMapping(path = "getExercise.do", method = RequestMethod.GET)
	public ModelAndView getExerciseByName(@RequestParam("exerciseName") String exerciseName) {
		ModelAndView mv = new ModelAndView("exercisePage.jsp", "exercise", dao.getExerciseByName(exerciseName));
		return mv;
	}

	// Forwards user and sends list of workouts to workoutBuilder jsp
	@RequestMapping(path = "createWorkout.do", method = RequestMethod.GET)
	public ModelAndView createWorkout(@ModelAttribute("user") User user, WorkoutExercise workoutexercise) {
		ModelAndView mv = new ModelAndView("workoutBuilder.jsp", "exercises", dao.getListOfExercises());
		List<Workout> userWorkouts = dao.getWorkoutsFromUser(user);
		mv.addObject("userWorkouts", userWorkouts);
		return mv;
	}

	// Route to handle UserType updates
	@RequestMapping(path = "updateUserType.do", method = RequestMethod.POST)
	public ModelAndView updateUserType(@ModelAttribute("user") User user, String username, String choice) {
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
		mv.addObject("exercises", dao.getAllExercises());
		return mv;
	}

	//Route to create workout from workoutBuilder jsp
	//Checks for changes to existing workout or build a new workout
	@RequestMapping(path = "createWorkout.do", method = RequestMethod.POST)
	public String publishWorkout(@RequestParam("exerciseId") Integer id, 
			@ModelAttribute("user") User user,
			@RequestParam("reps") Integer reps, 
			@RequestParam("weight") Integer weight,
			@RequestParam(value = "workoutName", required = false) String workoutName,
			@RequestParam(value = "newWorkoutName", required = false) String newWorkoutName,
			@RequestParam(value = "duration", required = false) Integer duration) 
	{
		Workout workout = new Workout();
		if (newWorkoutName.equals("")) {
			for (Workout w : dao.getWorkoutsFromUser(user)) {
				if (workoutName.equals(w.getName())) {
					workout = w;
				}
			}
		} else {
			workout.setName(newWorkoutName);
		}
		Exercise exercise = dao.getExerciseById(user, id);
		WorkoutExercise workoutexercise = null;
		if (duration == null) {
			workoutexercise = new WorkoutExercise(exercise, reps, weight);
		} else {
			workoutexercise = new WorkoutExercise(exercise, reps, weight, duration);
		}
		workoutexercise.setWorkout(workout);
		workout.addWorkoutExercise(workoutexercise);
		workout.setUser(user);
		dao.persistWorkouts(workout);

		
		return "redirect:workoutRedirect.do";
	}

	//User adds WorkoutExercise and redirected to workoutBuilder jsp
	@RequestMapping(path = "createWorkoutList.do", method = RequestMethod.POST)
	public String addExerciseToWorkoutList(@RequestParam("exerciseId") Integer id, @ModelAttribute("user") User user,
			@ModelAttribute("workoutExercise") WorkoutExercise workoutExercise,
			@ModelAttribute("userWorkoutExercises") List<WorkoutExercise> userWorkoutExercises) {
		Exercise exercise = dao.getExerciseById(user, id);
		workoutExercise.setExercise(exercise);
		userWorkoutExercises.add(workoutExercise);
		Workout workout = new Workout();
		workout.addWorkoutExercise(workoutExercise);
		workout.setUser(user);
		workoutExercise.setWorkout(workout);
		dao.persistWorkouts(workout);
		ModelAndView mv = new ModelAndView();
		mv.addObject(userWorkoutExercises);

		return "redirect:redirect.do";

		// System.out.println("Names: " + workoutExerciseCO.getName());
		// System.out.println("Duration: " + workoutExerciseCO.getDuration());
		// System.out.println("Reps: " + workoutExerciseCO.getReps());
		// System.out.println("ExerciseId: " +
		// workoutExerciseCO.getExerciseId());
		// String[] names = workoutExerciseCO.getName().get(0).split(",");
		// String[] duration =
		// workoutExerciseCO.getDuration().get(0).split(",");
		// for(int i=0; i<names.length;i++){
		// WorkoutExercise w = new WorkoutExercise();
		// w.setDuration(duration[i]);
		// w.setReps(reps[i]);
		// dao.persist(w);
		// }
	}

	//Workout is persisted to database and User forwarded to profile page
	@RequestMapping(path = "completeWorkout.do", method = RequestMethod.POST)
	public ModelAndView publishWorkout(@ModelAttribute("user") User user,
			@ModelAttribute("userWorkoutExercises") List<WorkoutExercise> userWorkoutExercises) {
		Workout workout = new Workout();
		workout.setWorkoutExercise(userWorkoutExercises);
		List<Workout> userWorkouts = dao.getWorkoutsFromUser(user);
		userWorkouts.add(workout);
		user.setWorkouts(userWorkouts);
		workout.setUser(user);
		ModelAndView mv = new ModelAndView("profile.jsp");
		return mv;
	}

	@RequestMapping(path = "redirect.do")
	public ModelAndView redirect(@ModelAttribute("user") User user,
			@ModelAttribute("userWorkoutExercises") List<WorkoutExercise> userWorkoutExercises) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(userWorkoutExercises);
		mv.addObject("exercises", dao.getListOfExercises());
		mv.setViewName("workoutBuilder.jsp");
		return mv;
	}
	@RequestMapping(path = "workoutRedirect.do")
	public ModelAndView workoutRedirect(@ModelAttribute("user") User user,
			@ModelAttribute("userWorkoutExercises") List<WorkoutExercise> userWorkoutExercises) {
		ModelAndView mv = new ModelAndView();
		if (dao.getWorkoutsFromUser(user) != null) {
			List<Workout> userWorkouts = dao.getWorkoutsFromUser(user);
			mv.addObject("userWorkouts", userWorkouts);
			}
			mv.addObject("user", user);
		mv.addObject(userWorkoutExercises);
		mv.addObject("exercises", dao.getListOfExercises());
		mv.setViewName("profile.jsp");
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
		ModelAndView mv = new ModelAndView();
		List<Workout> userWorkouts = dao.getWorkoutsFromUser(user);
		mv.addObject("userWorkouts", userWorkouts);
		mv.setViewName("profile.jsp");
		return mv;
	}

	@RequestMapping(path = "removeWorkoutExercise.do", method = RequestMethod.POST)
	public ModelAndView removeWorkoutExercise(@ModelAttribute("user") User user, Integer id) {
		dao.removeWorkoutExercise(id);
		ModelAndView mv = new ModelAndView();
		List<Workout> userWorkouts = dao.getWorkoutsFromUser(user);
		mv.addObject("userWorkouts", userWorkouts);
		mv.setViewName("profile.jsp");
		return mv;
	}

	@RequestMapping(path = "deleteExercise.do", method = RequestMethod.POST)
	public ModelAndView deleteExercise(@ModelAttribute("user") User user, Exercise name, String choice) {
		dao.deleteExercise(name, choice);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.addObject("users", dao.getAllUsers());
		mv.addObject("exercises", dao.getAllExercises());
		mv.setViewName("admin.jsp");
		return mv;
	}

}
