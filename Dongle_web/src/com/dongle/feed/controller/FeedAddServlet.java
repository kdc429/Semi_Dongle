package com.dongle.feed.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.feed.model.service.FeedService;
import com.dongle.feed.model.vo.Feed;
import com.dongle.group.model.service.GroupService;
import com.dongle.group.model.vo.Group;
import com.dongle.member.model.vo.Member;
import com.google.gson.Gson;

/**
 * Servlet implementation class FeedAddServlet
 */
@WebServlet("/feed/feedAdd")
public class FeedAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member loginMember=(Member)request.getSession().getAttribute("loginMember");
		int groupNo=Integer.parseInt(request.getParameter("groupNo"));
		int currentFeed;
		try {
			currentFeed=Integer.parseInt(request.getParameter("currentFeed"));
		}catch(NumberFormatException e) {
			currentFeed=0;
		}
		
		int pageFeed=10;
		int startFeedNo=(currentFeed/pageFeed)*pageFeed+1;
		int endFeedNo=currentFeed+pageFeed;
		System.out.println(startFeedNo);
		System.out.println(endFeedNo);
		
		Group g=new GroupService().selectGrInfo(groupNo);
		List<Feed> feedList=new FeedService().selectFeed(groupNo,startFeedNo,endFeedNo);
		feedList.get(0);
		response.setContentType("application/json;charset=UTF-8");
		new Gson().toJson(feedList,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
