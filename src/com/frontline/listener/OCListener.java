package com.frontline.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OCListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {

		System.out.println("\n" + "留言板準備開店囉..." + "\n");
	}

	public void contextDestroyed(ServletContextEvent sce) {

		System.out.println("\n" + "留言板準備打烊囉..." + "\n");
	}
}
