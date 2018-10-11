package edu.sjsu.cmpe275.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        /***
         * Following is a dummy implementation of App to demonstrate bean creation with Application context.
         * You may make changes to suit your need, but this file is NOT part of the submission.
         */

    	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetService");
        TweetStats stats = (TweetStats) ctx.getBean("tweetStats");

        try {
        	

            tweeter.tweet("alex", "second tweet");
            //tweeter.tweet("aex", "third tweet");
            tweeter.tweet("wex", "qweeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeerewwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwweeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            
            //tweeter.tweet("Prasun", "third tweet");
            
            
            //tweeter.tweet("alex", "second tweetaaaaaaaataaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaattttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttaaaaaaaaaa");
            tweeter.follow("alex", "bob");
            tweeter.follow("sonia", "alex");
            tweeter.block("alex", "bob");
            //tweeter.follow("shyam", "aex");
            //tweeter.follow("Prasun", "aex");
            /*tweeter.follow("Raj", "Suresh");
            tweeter.follow("Shyam", "raj");
            tweeter.follow("Raj", "Ram");
            tweeter.follow("jai", "wex");
            tweeter.follow("kumar", "wex");
            tweeter.follow("pankaj", "wex");
            //tweeter.follow("Suresh", "wex");
            tweeter.block("Suresh", "Raj");
            tweeter.block("Suresh", "Shyam");
            tweeter.block("Ram", "Raj");*/

            //tweeter.follow("ajay", "bob");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n Most productive user:  " + stats.getMostProductiveUser());
        System.out.println("\n Most popular user:  " + stats.getMostFollowedUser());
        System.out.println("\n Length of the longest tweet:  " + stats.getLengthOfLongestTweet());
        System.out.println("\n Most unpopular follower  " + stats.getMostBlockedFollower());
        
        System.out.println("\n Most popular message  " + stats.getMostPopularMessage());
        
        //System.out.println("\n Most Pro follower  " + stats.getMostBlockedFollower());
        //getMostProductiveUser
        //stats.getMostPopularMessage();
        stats.resetStatsAndSystem();
        //stats.getMostFollowedUser();
        //System.out.println();
        ctx.close();
    }
}
