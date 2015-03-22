package com.cognizant.osp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cognizant.osp.PMF;
import com.cognizant.osp.dao.UserDAO;
import com.cognizant.osp.dao.persistent.OSPeanUser;
import com.cognizant.osp.exception.OspeanException;


public class UserDAOImpl implements UserDAO{
Logger log=Logger.getLogger(this.getClass().getName());
PersistenceManager pm = PMF.getInstance().getPersistenceManager();
	@Override
	public UserDetails loadUserByUsername(String userId)
			throws UsernameNotFoundException {
		OSPeanUser ospUser=null;
		
		
	
		log.info("loadUserBy UserId: "+userId);
		try{
		ospUser=pm.getObjectById(OSPeanUser.class,userId);
	    List<Authority> authList=new ArrayList<Authority>();
		authList.add(new Authority(ospUser.getUserRole()));
	    
		
		 
		
		return new org.springframework.security.core.userdetails.User(
				 ospUser.getUserId(), ospUser.getPassword(), true, true, true, true,
				 authList);
	    
		
		}catch (Exception e) {
			
				 throw new UsernameNotFoundException("No Row found userName: "+userId); 
			 
		}
		
		
	}
/***
 * 
 * @param osPeanUser
 * @throws OspeanException
 */
	public boolean userExist(String userId) throws OspeanException{
		
		OSPeanUser ospUser=null;
		
		Object[] imputParam=new Object[1];
		
		imputParam[0]=userId;
		
		ospUser=pm.getObjectById(OSPeanUser.class,userId);
			if(ospUser!=null){
				return true;
			}
			
			return false;
		
	}
@Override
public boolean saveUser(OSPeanUser user) throws OspeanException {
	

		
	PersistenceManager pm = PMF.getInstance().getPersistenceManager();
	Transaction tx = pm.currentTransaction();
	OSPeanUser savedUser=null;
	   try { 
	        tx.begin(); 
	        Query q=pm.newQuery(OSPeanUser.class);
	        savedUser= pm.makePersistent(user);
	        tx.commit(); 
	        

			
	    } catch (Exception ex) { 
	        if (tx.isActive()) { 
	            tx.rollback(); 
	        } 
	        throw ex;
	    } finally { 
	      pm.close(); 
	    } 
		System.out.println( " user created.");
		
		if(savedUser==null) return false ;else return true;
	
	
}
	
}
class Authority implements GrantedAuthority{	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String authority="";
	public Authority(String auth) {
		this.authority=auth;
	}
	@Override
	public String getAuthority() {
		
		return authority;
	}
	
}

 class UserRowMapper implements RowMapper<OSPeanUser> {
	   public OSPeanUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		   OSPeanUser user = new OSPeanUser();
		   user.setUserId(rs.getString("user_id"));
		   user.setFirstName(rs.getString("first_nm"));
		   user.setLastName(rs.getString("last_nm"));
		   user.setUserRole(rs.getString("user_role"));
		   user.setPassword(rs.getString("user_password"));
	      return user;
	   }
	}