package com.cognizant.osp.exception;

public class OspeanException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OspeanException(){
		super();
	}


	public OspeanException(String message){
		super(message);
	}
 
	public OspeanException(String message,Throwable th ){
		super(message,th);
	}
	
}
