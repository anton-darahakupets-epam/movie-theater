package com.epam.movietheater.aop;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.epam.movietheater.domain.Discount;
import com.epam.movietheater.domain.User;

@Aspect
@Component
public class DiscountAspect {
	static final private Logger LOGGER = Logger.getLogger(DiscountAspect.class);
	
	static private Map<User, Map<String, Integer>> counterDiscounts = new HashMap<>(); 
	
	static private Map<String, Integer> counter = new HashMap<>();
	
	@Pointcut(value = "execution(* com.epam.movietheater.discount.DiscountStrategy.getDiscount(..))")
	public void discountServiceGetDiscountMethod(){}
	
	@AfterReturning(pointcut = "discountServiceGetDiscountMethod() && args(user,..)", returning = "discount")
	public void counterDiscountsForUser(JoinPoint joinPoint, User user, Discount discount) {
		String type = discount.getDicountType().toString();
		
		/**
		 * if booking is false and discount = 0, then this action will be not registered by counterDiscounts
		 * if booking is true and discount not equals to 0, then this action will be registered by counterDiscounts
		 */
		if (discount.getTotalDiscount() != 0 && (discount.isBooking())) {
			changingCounterDiscounts(type, user, discount.getCounter());
		} 
		
		LOGGER.info(counterDiscounts.toString());
	}
	
	private void changingCounterDiscounts(String type, User user, int i) {
		if (counter.containsKey(type)) {
			counter.put(type, counter.get(type) + i);
			counterDiscounts.put(user, counter);
		} else {
			counter.put(type, i);
			counterDiscounts.put(user, counter);
		}
	}
}
