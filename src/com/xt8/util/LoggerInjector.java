package com.xt8.util;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

public class LoggerInjector implements BeanPostProcessor {

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	public Object postProcessBeforeInitialization(final Object bean,
			String beanName) throws BeansException {
		ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {
			public void doWith(Field field) throws IllegalArgumentException,
					IllegalAccessException {
				// make the field accessible if defined private
				ReflectionUtils.makeAccessible(field);
				if (field.getAnnotation(EnableLog.class) != null) {

					String simpleName = bean.getClass().getSimpleName();
					Logger logger = Logger.getLogger(simpleName);
					field.set(bean, logger);

					String fullName = bean.getClass().getName();
					// Util.sout("***************** LoggerInjector EnableLog in: ["
					// + fullName + "] *****************");
				}
			}
		});
		return bean;
	}
}
