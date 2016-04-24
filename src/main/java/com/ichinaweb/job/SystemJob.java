package com.ichinaweb.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import com.ichinaweb.kit.DateKit;


@Component("systemJob")
public class SystemJob {
	private Logger log = LoggerFactory.getLogger(SystemJob.class);
	private static final String TIME_LIST = "timeList";
	
	@Scheduled(cron = "0/10 * * * * ?")
	public void job0() {
		ServletContext sc = ContextLoader.getCurrentWebApplicationContext().getServletContext();
		List<String> timeList = (List<String>) sc.getAttribute(TIME_LIST);
		if (timeList == null) {
			timeList = new ArrayList<String>();
			sc.setAttribute(TIME_LIST, timeList);
		}
		timeList.add(DateKit.simpleDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}

}
