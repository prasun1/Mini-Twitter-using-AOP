package edu.sjsu.cmpe275.aop.aspect;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeMap;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.TweetStatsImpl;

@Aspect
@Order(0)
@Configuration
public class StatsAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     */
	
	public static TreeMap<String,ArrayList<String>> tweet_map = new TreeMap<String,ArrayList<String>>();
	public static TreeMap<String,ArrayList<String>> follow_map = new TreeMap<String,ArrayList<String>>();
    public static TreeMap<String,ArrayList<String>> blocked_list_map = new TreeMap<String,ArrayList<String>>();
    ArrayList<String> arr_list = new ArrayList<String>();
    public static String biggest_length = "";
    TweetStatsImpl new_tweet = new TweetStatsImpl();

	@AfterReturning(
			value = "execution(public void edu.sjsu.cmpe275.aop.TweetServiceImpl.tweet(..))",
			returning = "result")
	public void after_tweeting(JoinPoint joinPoint, Object result)  
	{
		String user = (String) joinPoint.getArgs()[0];
		String message = (String) joinPoint.getArgs()[1];
		
		/*if(message.length() > biggest_length.length())
		{
			biggest_length = message;
		}*/
		if (message.length() < 140)
{
		if(tweet_map.containsKey(user))
        {
			
			
            ArrayList<String> a = tweet_map.get(user);
            //ArrayList<Integer> names = new ArrayList<Integer>();
            a.add(message);
            tweet_map.put(user, a);
            arr_list.add(user);
        }
        else
        {
            ArrayList<String> names = new ArrayList<String>();
            names.add(message);
            tweet_map.put(user, names);
            arr_list.add(user);
            
        }
        //System.out.printf("HERE ARE%s",tweet_map);
		System.out.printf("RESULT:-  User %s successfully tweeted message: %s\n", user, message);
		
		
		
		//System.out.printf("After the %s executuion of the metohd %s\n", name_user,arr );
		//stats.resetStats();
	}
	
}
	@AfterReturning("execution(public void edu.sjsu.cmpe275.aop.TweetService.follow(..))")
	public void after_following(JoinPoint joinPoint)
	{
		
		String followee = (String) joinPoint.getArgs()[0];
		String follower = (String) joinPoint.getArgs()[1];
		System.out.printf("\n \n METHOD EXECUTION:-  Executing The Method (%s) with User (%s) as Followee and User (%s) as Follower\n", joinPoint.getSignature().getName(),followee,follower);
		if(follow_map.containsKey(follower))
        {
            ArrayList<String> followeeId_list = follow_map.get(follower);
            if(!followeeId_list.contains(followee))
            {
                followeeId_list.add(followee);
                follow_map.put(follower,followeeId_list);
                System.out.printf("RESULT:-  User (%s) successfully followed User (%s)\n", (String) joinPoint.getArgs()[0], (String) joinPoint.getArgs()[1]);
            }
            
            
        }
        else
        {
            ArrayList<String> new_list = new ArrayList<String>();
            new_list.add(followee);
            
            //System.out.printf("value of follower",follower);
            //System.out.printf("value of follower",new_list);
            follow_map.put(follower,new_list);
            //System.out.println();
            System.out.printf("RESULT:-  User (%s) successfully followed User (%s) \n", followee, follower);
        }
        //System.out.println(follow_map);
	}
	
	@AfterReturning("execution(public void edu.sjsu.cmpe275.aop.TweetService.block(..))")
	public void after_blocking(JoinPoint joinPoint)
	{
		//System.out.printf("\n \n METHOD EXECUTION:-  Executing The Method (%s)\n", joinPoint.getSignature().getName());
		String user = (String) joinPoint.getArgs()[0];
		String follower = (String) joinPoint.getArgs()[1];
		System.out.printf("\n \n METHOD EXECUTION:-  Executing The Method (%s) with User (%s) as User and User (%s) as to be blocked\n", joinPoint.getSignature().getName(),user,follower);
		
		//System.out.printf("User %s  AND user %s \n", user, follower);		
       	//System.out.printf("This is %s",follow_map);
       	if(follow_map.containsKey(user))
        {
            ArrayList<String> names = follow_map.get(user);
            //System.out.println(names);
            if(names.contains(follower))
            {
                names.remove((Object)follower);
                //System.out.println(names);
                follow_map.put(user,names);
                System.out.printf("RESULT:- User (%s) has successfully blocked User (%s)\n \n", user, follower);
                //System.out.println(follow_map);
                if(blocked_list_map.containsKey(follower)) //blocked user: key, kisne kiya: list(user)
                {
                	ArrayList<String> blocked_names = blocked_list_map.get(follower);
                	//Set<String> s = new HashSet<String>(list);
                	blocked_names.add(user);
                	Set<String> hs1 = new LinkedHashSet<>(blocked_names);
                    ArrayList<String> al2 = new ArrayList<>(hs1);
                	blocked_list_map.put(follower, al2);
                }
                else
                {
                	ArrayList<String> new_blocked_user = new ArrayList<String>();
                	new_blocked_user.add(user);
                	Set<String> hs1 = new LinkedHashSet<>(new_blocked_user);
                    ArrayList<String> al2 = new ArrayList<>(hs1);
                	blocked_list_map.put(follower, al2);
                }
                //System.out.println("blocked list");
                
                	//blocked_list_map.put(user)
            }
            else
            {
            	System.out.printf("RESULT:- User (%s) does not have (%s) as a follower  \n", user, follower);	
            }
        }
       	else
       	{
       		System.out.printf("RESULT:-  User (%s) does not exist in the database \n \n ", user);
       	}
       	//System.out.println(blocked_list_map);
	}
	
	
	@AfterReturning("execution(* edu.sjsu.cmpe275.aop.TweetStatsImpl.getMostBlockedFollower())")
	public void get_Most_Blocked_Follower(JoinPoint joinPoint) 
	{
		System.out.printf("\n \n Executing The Method (%s)", joinPoint.getSignature().getName());
		//System.out.printf("Most Blocked User after call to function %s is %s",joinPoint, new_tweet.getMostBlockedFollower());
	}
	@After("execution(* edu.sjsu.cmpe275.aop.TweetStatsImpl.resetStatsAndSystem())")
	public void resetting_all_data(JoinPoint joinPoint) 
	{
		System.out.printf("Executing The Method (%s)\n", joinPoint.getSignature().getName());
		tweet_map.clear();
		follow_map.clear();
		blocked_list_map.clear();
		//length_of_biggest_string
		System.out.printf("Tweet Map = %s \n",tweet_map);
		System.out.printf("Follow Map = %s \n",follow_map);
		System.out.printf("Blocked_List Map = %s \n", blocked_list_map);
	}
	@Before("execution(* edu.sjsu.cmpe275.aop.TweetStatsImpl.getLengthOfLongestTweet())"
			)
	public void length_of_longest_tweet(JoinPoint joinPoint) 
	{
		System.out.printf("\n \n Executing The Method (%s)", joinPoint.getSignature().getName());
		//System.out.printf("Length of Longest Tweet %s",joinPoint);
	}
	
	@Before("execution(* edu.sjsu.cmpe275.aop.TweetStatsImpl.getMostFollowedUser())")
	public void getMostFollowedUser(JoinPoint joinPoint) 
	{
		System.out.printf("\n \n Executing The Method (%s)", joinPoint.getSignature().getName());
		//System.out.printf("Length of Longest Tweet %s",joinPoint);
		
	}
		@Before("execution(* edu.sjsu.cmpe275.aop.TweetStatsImpl.getMostPopularMessage())")
		public void getMostPopularMessage(JoinPoint joinPoint) 
		{
			System.out.printf("\n \n Executing The Method (%s)", joinPoint.getSignature().getName());
			//System.out.printf("\n \n Executing The Method (%s)", joinPoint.getSignature().getName());
			//System.out.printf("Length of Longest Tweet %s",joinPoint);
			
		}
	
	@Before("execution(* edu.sjsu.cmpe275.aop.TweetStatsImpl.getMostProductiveUser())")
	public void getMostProductiveUser(JoinPoint joinPoint) 
	{
		System.out.printf("\n \n Executing The Method (%s)", joinPoint.getSignature().getName());
		//System.out.printf("Length of Longest Tweet %s",joinPoint);
		
	}
}
