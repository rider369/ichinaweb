package com.ichinaweb.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;

import com.ichinaweb.kit.DateKit;

public class TimerJob implements Job {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
//		ServletContext sc = ContextLoader.getCurrentWebApplicationContext().getServletContext();
//		List<String> timeList = (List<String>) sc.getAttribute("timeList");
//		if (timeList == null) {
//			timeList = new ArrayList<String>();
//			sc.setAttribute("timeList", timeList);
//		}
//		timeList.add(DateKit.simpleDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}


}
