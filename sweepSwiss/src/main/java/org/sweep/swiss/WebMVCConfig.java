package org.sweep.swiss;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


public class WebMVCConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		//super.addViewControllers(registry);
		registry.addViewController("/login").setViewName("login.html");
		registry.addViewController("/logout").setViewName("login.html");
	}
}
