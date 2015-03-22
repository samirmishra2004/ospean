package com.cognizant.osp.controller;

import java.io.UnsupportedEncodingException;
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

import com.cognizant.osp.exception.OspeanException;
import com.cognizant.osp.formbean.EmailBean;
import com.google.appengine.api.utils.SystemProperty;
import com.google.appengine.tools.admin.Application;


@Controller
public class EmailController {
	Logger log=Logger.getLogger(this.getClass().getName());
	
	@RequestMapping("/admin/viewemailpage")
	public ModelAndView email(HttpServletRequest req,HttpServletResponse res){
		ModelAndView mvc =new ModelAndView("Email");	
			
		
		return mvc;
	}
	String application_id = SystemProperty.applicationId.get();
	String sender = "donotreply@" + application_id + ".appspotmail.com";
	String APP_SERVICE="ospean-1@appspot.gserviceaccount.com";
	@RequestMapping("/admin/mailBsendemail")
	public ModelAndView sendEmail(@ModelAttribute("emailBean") EmailBean emailBean)throws OspeanException{
		ModelAndView mvc =new ModelAndView("OperationSuccess"); 
		
	    log.info("To "+emailBean.getEmaiTo());
	    log.info("getBodyText "+emailBean.getBodyText());
	    
	    Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);        

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(sender, "OSPean"));
           // msg.addRecipient(Message.RecipientType.TO,
            //                 new InternetAddress("user@example.com", "Mr. User"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(emailBean.getEmaiTo()));
            if(!isEmpty(emailBean.getEmailCC())){
            	msg.addRecipient(Message.RecipientType.CC,
                        new InternetAddress(emailBean.getEmailCC()));	
            }
            if(!isEmpty(emailBean.getEmailBCC())){
            	msg.addRecipient(Message.RecipientType.BCC,
                        new InternetAddress(emailBean.getEmailBCC()));	
            }
            msg.setSubject(emailBean.getSubject());
            msg.setText(emailBean.getBodyText());
            Transport.send(msg);

        } catch (AddressException e) {
            log.log(Level.SEVERE, e.getMessage());
            mvc =new ModelAndView("OperationFailed");
            mvc.addObject("err", "AddressException: could not send email");
        } catch (MessagingException e) {
        	log.log(Level.SEVERE, e.getMessage());
        	mvc =new ModelAndView("OperationFailed");
            mvc.addObject("err", "MessagingException: could not send email");
        } catch (UnsupportedEncodingException e) {
        	log.log(Level.SEVERE, e.getMessage());
        	mvc =new ModelAndView("UnsupportedEncodingException");
            mvc.addObject("err", "MessagingException: could not send email");
		}
        mvc.addObject("msg", "Message Sent Sucessfully!! ");
		return mvc;
	}
	
	private boolean isEmpty(String s){
		if(s!=null && !s.trim().equals("")) return false; else return true;
	}
}
