package com.twitter.resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterException;



@WebServlet("/postStatus")
public class PostServlet extends HttpServlet{
	private static final long serialVersionUID = 2132731135996613711L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String text = request.getParameter("text");
        // get twitter object from session
        Twitter twitter = (Twitter)request.getSession().getAttribute("twitter");
        String msg = "";
        try {
            twitter.updateStatus(text);
            msg = "Status updated successfully, Check your twitter profile";
        } catch (TwitterException e) {
        	msg = e.getErrorMessage();
            throw new ServletException(e);
        }
        request.setAttribute("message", msg);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

}
