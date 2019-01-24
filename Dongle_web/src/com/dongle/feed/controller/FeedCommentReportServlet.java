package com.dongle.feed.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.feed.model.service.FeedService;
import com.dongle.member.model.service.MemberService;
import com.dongle.member.model.vo.Member;
import com.google.gson.Gson;

/**
 * Servlet implementation class FeedCommentReport
 */
@WebServlet("/feed/feedCommentReport")
public class FeedCommentReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedCommentReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member loginMember=(Member)request.getSession().getAttribute("loginMember");
		int feedCommentNo=Integer.parseInt(request.getParameter("feedCommentNo"));
		int groupNo=Integer.parseInt(request.getParameter("groupNo"));
		int memberNo=Integer.parseInt(request.getParameter("memberNo"));
		String reportCode=(String)request.getParameter("reportCode");
		int result=0;
		
		result=new MemberService().insertReport(groupNo,memberNo,reportCode);
		
		if(result>0) {
			
			result=new FeedService().feedCommentStatusUpdate(feedCommentNo);
			
			if(result>0) {
				
				new Gson().toJson(result,response.getWriter());
			}else {
				result=0;
				new Gson().toJson(result,response.getWriter());
			}
			
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
