package com.cognizant.osp.service.impl;

import javax.jdo.JDOObjectNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;

import com.cognizant.osp.dao.UserDAO;
import com.cognizant.osp.dao.persistent.OSPeanUser;
import com.cognizant.osp.exception.OspeanException;
import com.cognizant.osp.service.UserService;

public class UserServiceImpl implements UserService{
	
	UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public OSPeanUser authenticateUser(String user, String pwd)
			throws OspeanException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveUser(OSPeanUser user) throws OspeanException {
		return userDAO.saveUser(user);
		//return false;
	}

	@Override
	public boolean userExist(String userId) throws OspeanException {
		try{
		return userDAO.userExist(userId);
		}catch(JDOObjectNotFoundException edae){
			return false;
		}
	}



//	public OSPeanUser authenticateUser(String user,String pwd) throws OspeanException {
//		return userDAO.authenticateUser(user, pwd);
//	}

}
