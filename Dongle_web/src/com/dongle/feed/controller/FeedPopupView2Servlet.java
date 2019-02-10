package com.dongle.feed.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.feed.model.service.FeedService;
import com.dongle.feed.model.vo.FeedFile;

/**
 * Servlet implementation class FeedPopupView2Servlet
 */
@WebServlet("/feed/feedPopupView2")
public class FeedPopupView2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedPopupView2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int feedFileNo=Integer.parseInt(request.getParameter("feedFileNo"));
		
		FeedFile ff=new FeedService().selectFeedFile(feedFileNo);
		
		request.setAttribute("feedFile", ff);
		request.getRequestDispatcher("/views/feed/feedPopup2.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
