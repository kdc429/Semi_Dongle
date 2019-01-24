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
import com.dongle.feed.model.vo.FeedComment;
import com.dongle.feed.model.vo.FeedFile;
import com.dongle.group.model.service.GroupService;
import com.dongle.group.model.vo.Group;
import com.dongle.group.model.vo.GroupMember;
import com.dongle.member.model.service.MemberService;
import com.dongle.member.model.vo.Member;
import com.dongle.member.model.vo.ReportReason;

/**
 * Servlet implementation class FeedListViewServlet
 */
@WebServlet("/feed/feedListView")
public class FeedListViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedListViewServlet() {
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
		System.out.println(":"+currentFeed);
		
		int pageFeed=10;
		int startFeedNo=(currentFeed/pageFeed)*pageFeed+1;
		int endFeedNo=currentFeed+pageFeed;
		
		Group g=new GroupService().selectGrInfo(groupNo);
		GroupMember gm = new GroupService().selectGmInfo(groupNo,loginMember.getMemberNo());
		List<Feed> feedList=new FeedService().selectFeed(groupNo,startFeedNo,endFeedNo);
		List<GroupMember> memberlist = new GroupService().selectMemberList(groupNo);
		List<FeedFile> feedFileList=new FeedService().selectFeedFileList(groupNo);
		List<FeedComment> feedCommentList=new FeedService().selectFeedCommentList(groupNo);
		List<FeedComment> level2FeedCommentList=new FeedService().selectLevel2FeedCommentList();
		List<ReportReason> reportCategory=new MemberService().selectReportCategory();
		request.setAttribute("loginMember", loginMember);
		request.setAttribute("memberList",memberlist);
		request.setAttribute("feedList", feedList);
		request.setAttribute("group", g);
		request.setAttribute("groupMember", gm);
		request.setAttribute("feedFileList", feedFileList);
		request.setAttribute("feedCommentList", feedCommentList);
		request.setAttribute("level2FeedCommentList", level2FeedCommentList);
		request.setAttribute("reportCategory", reportCategory);
		request.getRequestDispatcher("/Dongle_Community_view/sectionFeed.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
