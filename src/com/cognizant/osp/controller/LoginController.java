package com.cognizant.osp.controller;

import java.security.Principal;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.osp.dao.persistent.OSPeanUser;
import com.cognizant.osp.exception.OspeanException;
import com.cognizant.osp.service.UserService;



@Controller
public class LoginController {

	Logger log=Logger.getLogger(this.getClass().getName());
	
	@Autowired
	UserService userService;
	
	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}



	@RequestMapping("/login")
	public ModelAndView doLogin(HttpServletRequest req,HttpServletResponse res){
		ModelAndView mvc =new ModelAndView("Login"); 
		
		log.info("login....!!");

		
		
		return mvc;
	}
	@RequestMapping("/welcome")
	public ModelAndView welcome(HttpServletRequest req,HttpServletResponse res){
		ModelAndView mvc =new ModelAndView("Welcome"); 
		log.info("welcome....!!");

		String userName = "not logged in"; // Any default user  name
	    Principal principal = req.getUserPrincipal();
	    if (principal != null) {
	        userName = principal.getName();
	    }

	    mvc.addObject("username", userName);

	    // By adding a little code (same way) you can check if user has any
	    // roles you need, for example:

	    boolean fAdmin = req.isUserInRole("ADMIN");
	    mvc.addObject("isAdmin", fAdmin);
		
		
		
		
		return mvc;
	}
	@RequestMapping("/accessdenied")
	public ModelAndView noAccess(HttpServletRequest req,HttpServletResponse res){
		ModelAndView mvc =new ModelAndView("404"); 
		
		log.info("accessdenied....!!");
		
		
		mvc.addObject("Error", "Access Denied !!");	
		
		
		
		return mvc;
	}
	
	@RequestMapping("/authFailed")
	public ModelAndView authFailed(HttpServletRequest req,HttpServletResponse res){
		ModelAndView mvc =new ModelAndView("Login"); 
		
		
		log.info("authFailed....!!");
		
		mvc.addObject("Error", "Incorrect User/Password. !!");	
		
		
		
		return mvc;
	}
	
}
