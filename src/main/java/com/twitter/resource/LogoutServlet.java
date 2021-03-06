package com.twitter.resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{
	 private static final long serialVersionUID = -4433102460849019660L;

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	// remove session
	        request.getSession().invalidate();
	        response.sendRedirect(request.getContextPath()+ "/");
	    }

}
