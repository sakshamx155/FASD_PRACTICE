package com.klu.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.klu.config.AppConfig;
import com.klu.model.Order;

public class MainApp {

    private static ApplicationContext ctx;

	public static void main(String[] args) {

        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Order or = ctx.getBean(Order.class);
        or.display();
    }
}