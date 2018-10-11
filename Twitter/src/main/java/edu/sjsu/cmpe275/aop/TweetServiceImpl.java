package edu.sjsu.cmpe275.aop;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.aop.aspect.StatsAspect;
//TreeMap<Integer, Integer> tmap = new TreeMap<Integer, Integer>();
//for (Map.Entry m:tmap.entrySet()) System.out.println("Frequency of " + m.getKey() +  " is " + m.getValue());

//HashMap<Integer, Integer> hmap =  new HashMap<Integer, Integer>();

public class TweetServiceImpl implements TweetService {

	@Override
	public void tweet(String user, String message) throws IllegalArgumentException, IOException {
		// TODO Auto-generated method stub
		System.out.printf("\n \n METHOD EXECUTION:-  Executing The Method(tweet) with USER(%s) and Tweet(%s)\n", user, message);
		if(message.length() > StatsAspect.biggest_length.length())
		{
			StatsAspect.biggest_length = message;
		}
		if(message.length() > 140) throw new IllegalArgumentException(user);
		
	}

	@Override
	public void follow(String follower, String followee) throws IllegalArgumentException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void block(String user, String followee) throws IOException {
		// TODO Auto-generated method stub
		
	}

    /***
     * Following is a dummy implementation.
     * You can tweak the implementation to suit your need, but this file is NOT part of the submission.
     */
	
    /*
     * HashMap<String,ArrayList<String>> tweet_map = new HashMap<String,ArrayList<String>>();
    HashMap<String,ArrayList<String>> follow_map = new HashMap<String,ArrayList<String>>();
    public static HashMap<String,ArrayList<String>> blocked_list_map = new HashMap<String,ArrayList<String>>();
    ArrayList<String> arr_list = new ArrayList<String>();

    public void tweet(String user, String message) throws IllegalArgumentException, IOException {
    	if(message.length()>140) throw new IllegalArgumentException("Message length should be less than 140");
    	else
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
            System.out.println(tweet_map);
    		//System.out.printf("User %s tweeted message: %s\n", user, message);
    	}
    	
    }

	public void follow(String follower, String followee) throws IOException 
	{//follower = bob, followee = raj
		//System.out.printf("Value of bob os ",Collections.singletonList(follow_map));
		if(follow_map.containsKey(follower))
        {
            ArrayList<String> followeeId_list = follow_map.get(follower);
            if(!followeeId_list.contains(followee))
            {
                followeeId_list.add(followee);
                follow_map.put(follower,followeeId_list);
            }

        }
        else
        {
            ArrayList<String> new_list = new ArrayList<String>();
            new_list.add(followee);
            
            //System.out.printf("value of follower",follower);
            //System.out.printf("value of follower",new_list);
            follow_map.put(follower,new_list);
        }
        System.out.println(follow_map);
       	//System.out.printf("User %s followed user %s \n", follower, followee);
    }

	public void block(String user, String follower) throws IOException 
	{
       	System.out.printf("User %s  AND user %s \n", user, follower);		
       	
       	if(follow_map.containsKey(user))
        {
            ArrayList<String> names = follow_map.get(user);
            //System.out.println(names);
            if(names.contains(follower))
            {
                names.remove((Object)follower);
                //System.out.println(names);
                follow_map.put(user,names);
                System.out.println("User successfully blocked");
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
                System.out.println("blocked list");
               
                	//blocked_list_map.put(user)
            }
            else
            {
            	System.out.printf("User %s does not have %s as a follower  \n", user, follower);	
            }
            
        }
       	else
       	{
       		System.out.printf("User %s does not exist in the database", user);
       	}
        
	}*/
	
}
