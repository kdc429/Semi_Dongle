package com.dongle.feed.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;

import com.dongle.feed.model.service.FeedService;
import com.dongle.feed.model.vo.FeedComment;
import com.google.gson.Gson;

/**
 * Servlet implementation class FeedInsertCommentServlet
 */
@WebServlet("/feed/feedInsertComment")
public class FeedInsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedInsertCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String str=(String)request.getParameter("strCommentData");
		System.out.println(str);
		int groupNo=Integer.parseInt(request.getParameter("groupNo"));
		System.out.println(groupNo);
		int feedNo=Integer.parseInt(request.getParameter("feedNo"));
		int feedCommentWriterNo=Integer.parseInt(request.getParameter("feedCommentWriterNo"));
		int feedCommentLevel=Integer.parseInt(request.getParameter("feedCommentLevel"));
		String feedCommentContent=request.getParameter("feedCommentContent");
		int feedCommentRef=Integer.parseInt(request.getParameter("feedCommentRef"));
		
		FeedComment feedComment=new FeedComment();
		feedComment.setGroupNo(groupNo);
		feedComment.setFeedNo(feedNo);
		feedComment.setMemberNo(feedCommentWriterNo);
		feedComment.setFeCommentLevel(feedCommentLevel);
		feedComment.setFeCommentContent(feedCommentContent);
		feedComment.setFeCommentRef(feedCommentRef);
		int result=0;
		result=new FeedService().insertFeedComment(feedComment);
		
		new Gson().toJson(result,response.getWriter());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
