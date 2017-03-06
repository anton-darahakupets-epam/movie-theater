package quoters;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

public class InjectRandomIntBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field: fields) {
			InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
			if(annotation != null) {
				System.out.println("Hello from postProcessBeforeInitialization");
				field.setAccessible(true);
				int max = annotation.max();
				int min = annotation.min();
				int value = max + min;
				ReflectionUtils.setField(field, bean, value);
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Hello from postProcessAfterInitialization");
		return bean;
	}

}
