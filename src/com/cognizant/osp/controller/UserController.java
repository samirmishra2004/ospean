package com.cognizant.osp.controller;

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
public class UserController {
	
Logger log=Logger.getLogger(this.getClass().getName());
	
	@Autowired
	UserService userService;
	
	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/admin/creuserpage")
	public ModelAndView createUser(HttpServletRequest req,HttpServletResponse res){
		ModelAndView mvc =new ModelAndView("CreateUser"); 
		
		log.info("User Cretion Page....!!");

		
		
		return mvc;
	}
	
	@RequestMapping("/admin/saveUer")
	public ModelAndView saveUser(HttpServletRequest req,HttpServletResponse res) throws OspeanException{
		ModelAndView mvc =new ModelAndView("CreateUser"); 
		String userId=req.getParameter("userId");
		String password=req.getParameter("password");
		String passwordCnf=req.getParameter("passwordCnf");
		String firstNm=req.getParameter("firstNm");
		String lastNm=req.getParameter("lastNm");
		String userRole=req.getParameter("userRole");
		
		
		log.info("saveUser....!!");
		log.info("userId....!! "+userId);
		log.info("password....!! "+password);
		log.info("passwordCnf....!! "+passwordCnf);
		log.info("firstNm....!! "+firstNm);
		log.info("lastNm....!! "+lastNm);
		log.info("userRole....!! "+userRole);
		
		log.info("isEmpty(userId) "+isEmpty(userId));
		log.info("isEmpty(password) "+isEmpty(password));
		log.info("isEmpty(firstNm) "+isEmpty(firstNm));
		log.info("isEmpty(userRole) "+isEmpty(userRole));
		if(isEmpty(userId) || isEmpty(password) 
				|| isEmpty(firstNm) || isEmpty(userRole)){
			mvc.addObject("error", "Mandatory field missing!");
			return mvc;
		}
		log.info("!password.equals(passwordCnf) "+!password.equals(passwordCnf));
		if(!password.equals(passwordCnf)){
			mvc.addObject("error", "Password does not match !!");
			return mvc;
		}
		
		OSPeanUser newUser=new OSPeanUser();
		newUser.setUserId(userId);
		newUser.setPassword(password);
		newUser.setFirstName(firstNm);
		newUser.setLastName(lastNm);
		newUser.setUserRole(userRole);
		if(userService.userExist(userId)){
			mvc.addObject("error", "User Id: "+userId+" already exist !!");
			return mvc;
		}
		if(userService.saveUser(newUser)){
			mvc.addObject("msg", "User saved successfully !");
		}
		mvc.addObject("newUser", newUser);
		
		return mvc;
	}
	public boolean isEmpty(String s){	
		if(s!=null && !s.trim().equals(""))return false; else return true;
	}
}
