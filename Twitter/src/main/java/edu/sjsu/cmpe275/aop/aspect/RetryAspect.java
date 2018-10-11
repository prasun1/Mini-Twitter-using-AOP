package edu.sjsu.cmpe275.aop.aspect;

import java.io.IOException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Around;

@Aspect
public class RetryAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     * @throws Throwable 
     */

	@Around("execution(public void edu.sjsu.cmpe275.aop.TweetServiceImpl.tweet(..))")
	public void tweet_exception(ProceedingJoinPoint joinPoint) throws Throwable {
		
		try {
			 joinPoint.proceed();
		 } catch(IllegalArgumentException e1) {
			 //e1.printStackTrace();
			 //System.err.printf("Tweet by User (%s) cannot be processed as his tweet length is greater than 140",e1.getMessage());
			 System.err.printf("The Tweet by (%s) has length more than 140 and cannot be processed. Please have a message lenth less than 140  \n",e1.getMessage());
		 } catch(IOException e2) {
			try {
				joinPoint.proceed();
				System.out.println("First Attempt: After three IOException, program will fail");
			} catch(IOException e3) {
			try {
				joinPoint.proceed();
				System.out.println("Second Attempt: Caught an IOException");
			} catch(IOException e4) {
				//e4.printStackTrace();
					System.out.println("Third Attempt: failed Permanently. Please restart the program");
				}
			}
		
		
	}
}
	
	
	
	@Around(
			value = "execution(public void edu.sjsu.cmpe275.aop.TweetServiceImpl.follow(..))")
	public void follow_exception(ProceedingJoinPoint joinPoint) throws Throwable {
		
		try {
			 joinPoint.proceed();
		 }  catch(IOException e2) {
			try {
				joinPoint.proceed();
				System.out.println("First Attempt: After three IOException, program will fail");
			} catch(IOException e3) {
			try {
				joinPoint.proceed();
				System.out.println("Second Attempt: Caught an IOException");
			} catch(IOException e4) {
				//e4.printStackTrace();
					System.out.println("Third Attempt: failed Permanently. Please restart the program");
				}
			}
		
	
		
		
	}
	}
	
	
	
	
	
	@Around("execution(public void edu.sjsu.cmpe275.aop.TweetServiceImpl.block(..))")
	public void blocking_exception(ProceedingJoinPoint joinPoint) throws Throwable {
		
		try {
			 joinPoint.proceed();
		 } catch(IOException e2) {
			try {
				joinPoint.proceed();
				System.out.println("First Attempt: After three IOException, program will fail");
			} catch(IOException e3) {
			try {
				joinPoint.proceed();
				System.out.println("Second Attempt: Caught an IOException");
			} catch(IOException e4) {
				//e4.printStackTrace();
					System.out.println("Third Attempt: failed Permanently. Please restart the program");
				}
			}
		
		
	}
	}
	
	
	
	
}
	



