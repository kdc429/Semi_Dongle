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
import com.dongle.feed.model.vo.FeedFile;
import com.dongle.group.model.service.GroupService;
import com.dongle.group.model.vo.GroupMember;
import com.dongle.member.model.vo.Member;
import com.google.gson.Gson;

/**
 * Servlet implementation class FeedUpdateEndServlet
 */
@WebServlet("/feed/feedUpdateEnd")
public class FeedUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member loginMember=(Member)request.getSession().getAttribute("loginMember");
		int feedNo=Integer.parseInt(request.getParameter("feedNo"));
		String content=(String)request.getParameter("updateContent");
		String[] fileNo=(String[])request.getParameterValues("fileNoList");
		System.out.println(":"+fileNo);
		System.out.println(request.getParameter("fileNoList")+":");
		int result=0;
		if(fileNo!=null) {
			
			result=new FeedService().updateFeed(feedNo,content,fileNo);
		}else {
			result=new FeedService().updateFeedContent(feedNo,content);
		}
		System.out.println(result);
		
		if(result>0) {
			new Gson().toJson(result,response.getWriter());
		}else {
			new Gson().toJson(result,response.getWriter());
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
