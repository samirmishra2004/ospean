package com.cognizant.osp.constant;

public interface OspeanSqlConstant {

	public static String GET_USER_SQL=" select * from osp_user where user_name=? and user_password=? ";
	public static String GET_USER_BY_NAME_SQL=" select * from osp_user where user_name=?  ";
	
}
