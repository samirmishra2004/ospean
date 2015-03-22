package com.cognizant.osp.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
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
public class LogoutController {

	Logger log=Logger.getLogger(this.getClass().getName());
	
	@Autowired
	UserService userService;
	
	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}



	@RequestMapping("/logout")
	public void logout(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		log.info("loging out...");
		
		
	}
	
}
