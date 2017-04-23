package us.freeenergy.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import io.jsonwebtoken.Claims;
import us.freeenergy.util.CookieUtil;
import us.freeenergy.util.JwtUtil;

@Controller
public class HomeController 
{
	private static final String SIGNING_KEY = "ForGodSoLovedThe$$Worldthathegavehisonlybegottensonthatwhosoeverbelivedinhimshouldnotperish";
	private static final String jwtTokenCookieName = "THE-INSTITUTE";
	
	
	@RequestMapping(value="/")
	public String startPage(Model model, HttpServletRequest request)
	{
		//Get cookie and decode
		Claims claim = JwtUtil.decodeToken(request, jwtTokenCookieName, SIGNING_KEY);
		if(claim == null){
			model.addAttribute("tag","Login");
			return "index";
		}
		else{
			model.addAttribute("tag","Logout");
		}

		return "index";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(Model model)
	{
		return "login";
	}
	
	
	@RequestMapping(value="/login_logout")
	public String loginOrLogout(Model model, HttpServletRequest request)
	{
		//Get cookie and decode
		Claims claim = JwtUtil.decodeToken(request, jwtTokenCookieName, SIGNING_KEY);
		if(claim == null)	//user is not logged in
		{
			return "redirect:/login";
		}
		else						//user is already logged in
		{
			return "redirect:/logout";
		}
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletResponse httpServletResponse, Model model )
	{
		//clear cookie
		CookieUtil.clear(httpServletResponse, jwtTokenCookieName);
		model.addAttribute("logout", 1);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String register()
	{
		return "signup";
	}
	
	
	
}
