package com.twitter.resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twitter.utils.Setup;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;


@WebServlet("/signin")
public class SigninServlet extends HttpServlet{
	 private static final long serialVersionUID = -6205814293093350242L;

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// configure twitter api with consumer key and secret key
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			  .setOAuthConsumerKey(Setup.CONSUMER_KEY)
			  .setOAuthConsumerSecret(Setup.CONSUMER_SECRET);
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();
			request.getSession().setAttribute("twitter", twitter);
			try {
				
				// setup callback URL
			    StringBuffer callbackURL = request.getRequestURL();
			    int index = callbackURL.lastIndexOf("/");
			    callbackURL.replace(index, callbackURL.length(), "").append("/callback");
			
			    // get request object and save to session
			    RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
			    
			    System.out.println("Callback url is : " + callbackURL.toString());
			    request.getSession().setAttribute("requestToken", requestToken);
			    
			    // redirect to twitter authentication URL
			    response.sendRedirect(requestToken.getAuthenticationURL());
			
			} catch (TwitterException e) {
			    throw new ServletException(e);
			}

	    }

}
