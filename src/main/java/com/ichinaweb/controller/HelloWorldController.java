package com.ichinaweb.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("hello")
public class HelloWorldController{
	
	Logger log = LoggerFactory.getLogger(HelloWorldController.class);
	
	@RequestMapping
	public String load(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
		String msg = this.getClass().getResource("/target.xml").toString();
		modelMap.put("msg", msg);
		return "load";
	}

	@RequestMapping("/index")
	public String index(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ServletContext sc = request.getSession().getServletContext();
		List<String> timeList = (List<String>) sc.getAttribute("timeList");
		modelMap.put("timeList", timeList);
		modelMap.put("count", timeList.size());
		return "hello";
	}
	
	@RequestMapping("/web")
	public String web(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String[]> params = request.getParameterMap();
		InputStream is = new FileInputStream(new File("D:/target.xml"));
		SAXReader reader = new SAXReader();
		Document document = reader.read(is);
		Element element = document.getRootElement();
		List elementList = element.elements();
		
		for (int i = 0; i < elementList.size(); i++) {
			Element el = (Element) elementList.get(i);
			List attrList = el.attributes();
			boolean match = true;
			for (int j = 0; j < attrList.size(); j++) {
				DefaultAttribute attr = (DefaultAttribute) attrList.get(j);
				if (params.get(attr.getName()) == null || (!params.get(attr.getName())[0].toString().equals(attr.getValue()))) {
					match = false;
					break;
				}
			}
			if (match) {
				Element resultElement = el.element("result");
				if (resultElement != null) {
					modelMap.put("msg", resultElement.getData());
				}
				break;
			} 
		}
		return "web";
	}
	
	@Autowired
	private TestController testController1;
	
	
	@RequestMapping("/springtest")
	public String springtest(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
		
		testController1.method();
		
		return "web";
	}




}
