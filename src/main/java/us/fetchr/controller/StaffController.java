package us.fetchr.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nimbusds.jose.JOSEException;

import us.fetchr.dao.StaffDao;
import us.fetchr.dao.StudentDao;
import us.fetchr.dbModels.Staff;
import us.fetchr.util.CookieUtil;
import us.fetchr.util.JwtUtil;

public class StaffController 
{
	private static final String SIGNING_KEY = "ForGodSoLovedThe$$Worldthathegavehisonlybegottensonthatwhosoeverbelivedinhimshouldnotperish";
	private static final String jwtTokenCookieName = "THE-INSTITUTE";
	
	@Autowired
	StaffDao staffService;
	
	@Autowired
	StudentDao studentService;
	
	@RequestMapping(value="/login", method = RequestMethod.POST, params={"user_category=staff"})
	public String loginForStaff(@Valid Staff staff, BindingResult bindingResult, Model model, HttpServletRequest request, HttpServletResponse httpServletResponse) throws JOSEException
	{
		//Check for Errors
		if(bindingResult.hasErrors())
		{
			model.addAttribute("error3", "Binding errors");
			return "login";
		}
		if(staff.getPassword() == null || staff.getPassword().length() < 5)
		{
			model.addAttribute("error2", "Password not valid");
			return "login";
		}
		
		//Check if the Customer exist on the DB
		Staff staff2 = staffService.findByUsername(staff.getUsername());
		if (staff2 == null)
		{
			model.addAttribute("error3", "Your Credentials do not exist");
			return "login";	
		}
		
		//create token and setup cookie
		Map<String, Object> tokenParam = new HashMap<>();
		tokenParam.put("X-Name-Id", staff2.getName());
		tokenParam.put("X-Email-Id", staff2.getEmail());
		
		String token = JwtUtil.generateToken(SIGNING_KEY, staff2.getUsername(), request.getServerName(), tokenParam);
		CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");
		
		model.addAttribute("success", 1);
		return "login";
		//return "redirect:/";	
	}
	
	
	/***********SIGNUP*************/
	@RequestMapping(value="/signup", method=RequestMethod.POST, params={"user_category=staff"})
	public String staffSignUp(@Valid Staff staff, BindingResult bindingResult, Model model)
	{

		if (bindingResult.hasErrors()){
			model.addAttribute("error3", "Binding Error");
			return "signup";
		}
		
		if(staff.getPassword() == null || staff.getPassword().length() < 5){
			model.addAttribute("error2", "Password not valid");
			return "signup";
		}
		
		Staff staff2 = staffService.findByUsernameAndEmail(staff.getUsername(), staff.getEmail());
		if(staff2 != null)
		{
			model.addAttribute("error2", "The Username or email already exist");
			return "signup";
		}
		//save
		staffService.save(staff);
		model.addAttribute("save", 1);
		return "signup";
	}
}
