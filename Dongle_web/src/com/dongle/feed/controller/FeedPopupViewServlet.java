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

/**
 * Servlet implementation class FeedUpdateServlet
 */
@WebServlet("/feed/feedPopupView")
public class FeedPopupViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedPopupViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int feedNo=Integer.parseInt(request.getParameter("feedNo"));
		Member loginMember=(Member)request.getSession().getAttribute("loginMember");
		Feed feed=new FeedService().selectFeedOne(feedNo);
		List<FeedFile> feedFileList=new FeedService().selectFeedFileListOne(feedNo);
		GroupMember groupMember=new GroupService().selectGmInfo(feed.getGroupNo(), feed.getMemberNo());
		
		request.setAttribute("feed", feed);
		request.setAttribute("feedFileList", feedFileList);
		request.setAttribute("loginMember", loginMember);
		request.setAttribute("groupMember", groupMember);
		request.getRequestDispatcher("/views/feed/feedPopup.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
