package com.cognizant.osp.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cognizant.osp.dao.persistent.OSPeanUser;
import com.cognizant.osp.exception.OspeanException;

public interface UserDAO extends UserDetailsService{
	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException;	
	
	public boolean saveUser(OSPeanUser user)
			throws OspeanException;	
	
	public boolean userExist(String userId)
			throws OspeanException;	

}
