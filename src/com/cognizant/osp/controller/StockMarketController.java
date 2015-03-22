package com.cognizant.osp.controller;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.osp.constant.OspeanConstants;
import com.cognizant.osp.exception.OspeanException;
import com.cognizant.osp.formbean.EmailBean;
import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.appengine.api.urlfetch.URLFetchServicePb.URLFetchResponse.Header;
import com.google.appengine.api.utils.SystemProperty;
import com.google.appengine.tools.admin.Application;


@Controller
public class StockMarketController {
	Logger log=Logger.getLogger(this.getClass().getName());
	
	@RequestMapping("/viewStockChart")
	public ModelAndView getStockPage(HttpServletRequest req,HttpServletResponse res){
		ModelAndView mvc =new ModelAndView("StockChart");	
			
		
		return mvc;
	}
	
	@RequestMapping("/getStockChart")
	public void fetchStockChart(HttpServletRequest req,HttpServletResponse res)throws OspeanException{
		//ModelAndView mvc =new ModelAndView("OperationSuccess"); 
		
	    log.info("inside fetchStockChart");
	    String stockNm=req.getParameter("stockName");
	    log.info("inside fetchStockChart stock name : "+stockNm);
	    
	    PrintWriter pw=null;
	    		try {
	    		HTTPResponse fetchedResp=	getResponse(OspeanConstants.appuOnlineUrl, null);
	    		List<HTTPHeader> hl=fetchedResp.getHeaders();
	    	for(HTTPHeader h:hl){
	    		log.info(h.getName()+" = "+h.getValue());
	    	//	res.setHeader(h.getName(), h.getValue());
	    	}
	    	
	    	res.setContentLength(fetchedResp.getContent().length);
	    	res.setContentType("text/html");
	    	
	    	
	    	
	    	ByteArrayInputStream in = new ByteArrayInputStream(fetchedResp.getContent());
	        OutputStream out = res.getOutputStream();

	        // Copy the contents of the file to the output stream
	         byte[] buf = new byte[1024];
	         int count = 0;
	         while ((count = in.read(buf)) >= 0) {
	           out.write(buf, 0, count);
	        }
	      out.close();
	      in.close();
				/*pw=	res.getWriter();
				pw.print(new String(fetchedResp.getContent()));
				pw.flush();*/
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	/*private boolean isEmpty(String s){
		if(s!=null && !s.trim().equals("")) return false; else return true;
	}*/
	
	private HTTPResponse getResponse(String url,String payload) throws IOException {
		  URLFetchService service=URLFetchServiceFactory.getURLFetchService();
		  URL uri=new URL(url);
		  HTTPRequest request=new HTTPRequest(uri,HTTPMethod.GET,FetchOptions.Builder.doNotFollowRedirects().setDeadline(20.0));
		  
		 
		
		  if(payload!=null && !"".equals(payload)){
			  request.setPayload(payload.getBytes());
		  }
		  
		  HTTPResponse response=service.fetch(request);
		 
		  String token=(new String(response.getContent()));
		  
		  System.out.println("=== *Response Payload Start*-1 ====");
		  System.out.println(token);
		  System.out.println("=== Response Payload End====");
		  return response;
		}
}
