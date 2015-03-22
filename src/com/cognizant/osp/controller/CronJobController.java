package com.cognizant.osp.controller;

import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.osp.constant.OspeanConstants;


@Controller
public class CronJobController {
	Logger log=Logger.getLogger(this.getClass().getName());
	
	@RequestMapping("/cronaction")
	public void cronAction(){
	
		log.info("inside cronAction");
	
			if(OspeanConstants.cronJobDataQ.size()>9){
				OspeanConstants.cronJobDataQ.clear();
				OspeanConstants.cronJobDataQ.add("Cron Job Execution Time: "+new Date());
			}else{
				OspeanConstants.cronJobDataQ.add("Cron Job Execution Time: "+new Date());
			}
		log.info("exit cronAction cronJobDataQ size: "+OspeanConstants.cronJobDataQ.size());
		
	}
	@RequestMapping("/cronactionview")
	public ModelAndView viewCronExecution(){
		log.info("inside cronactionview");
		ModelAndView mvc = new ModelAndView("CronProcessView");
		mvc.addObject("cronDataQ",OspeanConstants.cronJobDataQ);
		log.info("exit cronactionview");
		return mvc;
	}
	
}
