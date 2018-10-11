package edu.sjsu.cmpe275.aop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.aop.aspect.StatsAspect;

@Service
public class TweetStatsImpl implements TweetStats {
    /***
     * Following is a dummy implementation.
     * You are expected to provide an actual implementation based on the requirements.
     */
	/*HashMap<String,ArrayList<String>> tweet_map = new HashMap<String,ArrayList<String>>();
    HashMap<String,ArrayList<String>> follow_map = new HashMap<String,ArrayList<String>>();
    public static HashMap<String,ArrayList<String>> blocked_list_map = new HashMap<String,ArrayList<String>>();
    ArrayList<String> arr_list = new ArrayList<String>();*/
    
    //TweetServiceImpl object_one;
	
    @Override
	public void resetStatsAndSystem() {
		// TODO Auto-generated method stub
		System.out.println("\n Resetting all Followers, Followee and Tweets");
		
	}
    @Override
	public int getLengthOfLongestTweet() 
    {
		// TODO Auto-generated method stub
      
    	int length_of_biggest_string = StatsAspect.biggest_length.length();
		return length_of_biggest_string;
	}
    @Override
	public String getMostFollowedUser() 
	{
		// TODO Auto-generated method stub
		//System.out.printf("follow map %s", StatsAspect.follow_map);
		String key_new = null;
		ArrayList<String> arr_list = new ArrayList<String>();
		//System.out.println(object_one.blocked_list_map);
		//HashMap<String,ArrayList<String>> obj_map = new HashMap<String,ArrayList<String>>();
	    //obj_map = object_one.blocked_list_map;
	    //System.out.println(obj_map);
		for (String key : StatsAspect.follow_map.keySet()) {
		    ArrayList<String> value = StatsAspect.follow_map.get(key);
		    if(value.size() > arr_list.size())
		    {
		    	arr_list = value;
		    	key_new = key;
		    }
		}
		return key_new;
		
	}
    @Override
	public String getMostPopularMessage() {
		// TODO Auto-generated method stub
		Integer max_value = 0;
		String key = "";
		for(String i: StatsAspect.follow_map.keySet())
		{
			if(StatsAspect.tweet_map.containsKey(i))
			{
				ArrayList<String> value = StatsAspect.follow_map.get(i);
				if (value.size() > max_value)
				{
					max_value = value.size();
					key = i;
				}
			}
			
		}
		//System.out.printf(" mao %s",StatsAspect.follow_map);
		System.out.printf(key);
		if((StatsAspect.tweet_map.get(key) == null) || (StatsAspect.tweet_map.get(key).get(0) == null))
		{
			return null;
		}
		else
		{
			return  StatsAspect.tweet_map.get(key).get(0);
	}
}
    
	@Override
	public String getMostProductiveUser() {
		// TODO Auto-generated method stub
		
			// TODO Auto-generated method stub
			//System.out.printf("tweet map %s", StatsAspect.tweet_map);
			//Map<String, ArrayList<String>> treeMap = StatsAspect.tweet_map;
			//System.out.printf("tweet map first %s",((TreeMap<String, ArrayList<String>>) treeMap).firstKey());
			String key_new = null;
			String equal_key_new = "";
			
			Integer max_length = 0;
			ArrayList<String> arr_list = new ArrayList<String>();
			//System.out.println(object_one.blocked_list_map);
			//HashMap<String,ArrayList<String>> obj_map = new HashMap<String,ArrayList<String>>();
		    //obj_map = object_one.blocked_list_map;
		    //System.out.println(obj_map);
			ArrayList<String> key_value = new ArrayList<String>();
			for (String key : StatsAspect.tweet_map.keySet()) {
				Integer length = 0;
			    ArrayList<String> value = StatsAspect.tweet_map.get(key);
			    for(String i: value)
			    {
			    	length += i.length();
			    }
			    //System.out.printf("\n\n \n %s is key Here we study%s   and key is %s\n",key,length,max_length);
			    if(length > max_length)
			    {
			    	max_length = length;
			    	key_new = key;
			    	key_value.add(key);
			    }
			    else if(length == max_length)
			    {
			    	key_value.add(key);
			    	//SortedSet<String> keys = new TreeSet<>(StatsAspect.tweet_map.keySet());
			    	//System.out.println(key_value);
			    	Collections.sort(key_value);
			    	key_new = key_value.get(0);
			    	//System.out.printf("\n\n\nPrint is here \n%s",key_new);
			    }
			}
			return key_new;
		
	}
    @Override
	public String getMostBlockedFollower() {
		//System.out.println("Here I ma");
		//HashMap<String, ArrayList<String>> blocked_list_map = StatsAspect.blocked_list_map;
		//System.out.println(blocked_list_map);
		String key_new = null;
		ArrayList<String> arr_list = new ArrayList<String>();
		//System.out.println(object_one.blocked_list_map);
		//HashMap<String,ArrayList<String>> obj_map = new HashMap<String,ArrayList<String>>();
	    //obj_map = object_one.blocked_list_map;
	    //System.out.println(obj_map);
		for (String key : StatsAspect.blocked_list_map.keySet()) {
		    ArrayList<String> value = StatsAspect.blocked_list_map.get(key);
		    if(value.size() > arr_list.size())
		    {
		    	arr_list = value;
		    	key_new = key;
		    }
		    //System.out.println("Key = " + key_new + ", Value = " + arr_list);
		}
        
        return key_new;
		// TODO Auto-generated method stub
		//return (key_new,arr_list);
	}
	
}



