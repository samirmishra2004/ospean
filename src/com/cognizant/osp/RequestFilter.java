package com.cognizant.osp;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

public class RequestFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest req,
			HttpServletResponse res, FilterChain fch2)
			throws ServletException, IOException {
		
		
	    String url = req.getServletPath();
	    boolean allowedRequest = false;
		
	    if (!allowedRequest) {
	        HttpSession session = req.getSession(false);
	        if (null == session) {
	            res.sendRedirect("logout.do");
	        }
	    }
		
	    
	    
		fch2.doFilter(req, res);
		
	}
@Override
protected boolean shouldNotFilter(HttpServletRequest request)
		throws ServletException {
	if(request.getServletPath().equalsIgnoreCase("login.do")){
		return true;
	}
	if(request.getServletPath().equalsIgnoreCase("logout.do")){
		return true;
	}
	return false;
}
	

}
