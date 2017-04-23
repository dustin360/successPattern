package us.freeenergy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.nimbusds.jose.JOSEException;
import io.jsonwebtoken.Claims;
import us.fetchr.cpsso.entities.CourseOfStudy;
import us.fetchr.cpsso.entities.Student;
import us.fetchr.cpsso.entities.TimeLinePost;
import us.freeenergy.model.ApiCreateStudent;
import us.freeenergy.model.ApiCreateTimelinePost;
import us.freeenergy.service.ServiceClass;
import us.freeenergy.util.CookieUtil;
import us.freeenergy.util.JwtUtil;

@Controller
public class StudentController {
	private static final String SIGNING_KEY = "ForGodSoLovedThe$$Worldthathegavehisonlybegottensonthatwhosoeverbelivedinhimshouldnotperish";
	private static final String jwtTokenCookieName = "THE-INSTITUTE";
	
	@Autowired
	ServiceClass serviceClass;
	
	@RequestMapping(value="/login", method = RequestMethod.POST, params={"user_category=student"})
	public String loginForStudent(@Valid ApiCreateStudent student, BindingResult bindingResult, Model model, HttpServletRequest request, HttpServletResponse httpServletResponse) throws JOSEException
	{
		//Check for Errors
		if(bindingResult.hasErrors())
		{
			model.addAttribute("error3", "Binding errors");
			return "login";
		}
		if(student.getPassword() == null || student.getPassword().length() < 5)
		{
			model.addAttribute("error2", "Password not valid");
			return "login";
		}
		
		//Check if the Customer exist on the DB
		Student student2 = serviceClass.findByUsernameandPassword(student.getUsername(), student.getPassword());
		if (student2 == null)
		{
			model.addAttribute("error3", "Your Credentials do not exist");
			return "login";	
		}
		
		//create token and setup cookie
		Map<String, Object> tokenParam = new HashMap<>();
		tokenParam.put("X-Name-Id", student2.getName());
		tokenParam.put("X-Email-Id", student2.getEmail());
		
		String token = JwtUtil.generateToken(SIGNING_KEY, student2.getUsername(), request.getServerName(), tokenParam);
		CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");
		
		model.addAttribute("success", 1);
		return "login";
		//return "redirect:/";	
	}
	
	@RequestMapping("/student")
	public String welcomePage(HttpServletRequest request, Model model)
	{
		System.out.println("************WE ARE HERE*************");
		//Get cookie and decode
		Claims claim = JwtUtil.decodeToken(request, jwtTokenCookieName, SIGNING_KEY);
		if(claim == null){
			model.addAttribute("information","Your credentials don't exist, you must sign in first");
			return "login";
		}
		
		String email = null;
		String username = null;
		try {
			email = claim.get("X-Email-Id").toString();
			username = claim.getSubject();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (username == null || email == null){
			return "login";
		}
		
		Student student = serviceClass.findByUsernameandEmail(username, email);
		if(student == null){
			return "login";
		}

		model.addAttribute("name", student.getName());
		model.addAttribute("tag","Logout");		
		return "student";
		
	}
	
	
	@RequestMapping("/student_profile")
	public String profilePage(HttpServletRequest request, Model model)
	{

		//Get cookie and decode
		Claims claim = JwtUtil.decodeToken(request, jwtTokenCookieName, SIGNING_KEY);
		if(claim == null){
			model.addAttribute("information","Your credentials don't exist, you must sign in first");
			return "login";
		}
		
		String email = null;
		String username = null;
		try {
			email = claim.get("X-Email-Id").toString();
			username = claim.getSubject();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (username == null || email == null){
			return "login";
		}
		
		Student student = serviceClass.findByUsernameandEmail(username, email);
		if(student == null){
			return "login";
		}
		
		//********Populate models*************
		//name
		model.addAttribute("name", student.getName());
		
		//course of study
		CourseOfStudy courseOfStudy = serviceClass.findCourseofStudyById(student.getId());
		if (courseOfStudy == null){
			model.addAttribute("courseOfStudy", "Course of Study not registered yet");
		}
		else{
			model.addAttribute("courseOfStudy", courseOfStudy.getCourses());
		}
		
		//timeline post
		TimeLinePost timeLinePost = serviceClass.findTimelinePostByStudentId(student.getId());
		if (timeLinePost == null){
			model.addAttribute("timeLinePost", "No posts");
		}
		else{
			model.addAttribute("timeLinePost", timeLinePost.getTimelinePost());
		}
		
		model.addAttribute("tag","Logout");		
		return "student_profile";
		
	}
	/*
	*//***********SIGNUP*************//*
	@RequestMapping(value="/signup", method=RequestMethod.POST, params={"user_category=student"})
	public String studentSignUp(@Valid ApiCreateStudent student, BindingResult bindingResult, Model model)
	{

		if (bindingResult.hasErrors()){
			model.addAttribute("error3", "Binding Error");
			return "signup";
		}
		
		if(student.getPassword() == null || student.getPassword().length() < 5){
			model.addAttribute("error2", "Password not valid");
			return "signup";
		}
		
		ApiCreateStudent student2 = studentDao.findByUsernameAndEmail(student.getUsername(), student.getEmail());
		if(student2 != null)
		{
			model.addAttribute("error2", "The Username or email already exist");
			return "signup";
		}
		//save
		studentDao.save(student);
		model.addAttribute("save", 1);
		return "signup";
	}*/
	
	
}
