package com.michal.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttApplicationContext implements ApplicationContextAware {

	private static ApplicationContext CONTEXT;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		CONTEXT = context;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getBean(Class beanClass) {
		return CONTEXT.getBean(beanClass);
	}
}