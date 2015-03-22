package com.cognizant.osp.service;

import com.cognizant.osp.dao.persistent.OSPeanUser;
import com.cognizant.osp.exception.OspeanException;

public interface UserService {
	public OSPeanUser authenticateUser(String user,String pwd) throws OspeanException;
	public boolean  saveUser(OSPeanUser user) throws OspeanException;
	public boolean userExist(String userId)
			throws OspeanException;	
}
