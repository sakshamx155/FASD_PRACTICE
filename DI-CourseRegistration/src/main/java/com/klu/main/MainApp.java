package com.klu.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.klu.model.CourseRegistration;

public class MainApp {

	public static void main(String[] args) {
		 ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
         CourseRegistration cr = (CourseRegistration)context.getBean("courseRegistration");
        		 cr.display();
	}

}
