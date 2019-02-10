package com.dongle.manager.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.group.model.service.GroupService;
import com.dongle.group.model.vo.Group;
import com.dongle.group.model.vo.LocalCtg;
import com.dongle.group.model.vo.MultiLocation;
import com.dongle.group.model.vo.MultiLocationName;
import com.dongle.group.model.vo.MultiTopicName;
import com.dongle.group.model.vo.TopicCtg;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class ManagerView
 */
@WebServlet("/manager/managerView")
public class ManagerView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Member loginMember=(Member)request.getSession().getAttribute("loginMember");
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		int managerNo = Integer.parseInt(request.getParameter("managerNo"));
		Group g = new GroupService().selectGrInfo(groupNo); //그룹정보 받아오기
		List<MultiLocationName> locList = new GroupService().selectSearchLocation(""+groupNo); //해당 그룹 지역
		List<MultiTopicName> topicList = new GroupService().selectSearchTopic(""+groupNo); //해당 그룹 주제
		
		List<TopicCtg> topicCtg = new GroupService().selectTopicCtg();
		List<LocalCtg> localCtg = new GroupService().selectLocalCtg();
		
		System.out.println(managerNo);
		
		
		if(loginMember==null||loginMember.getMemberNo() != managerNo) 
		{
			request.setAttribute("msg", "잘못된 경로로 접속하셨습니다");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/Dongle_view/msg.jsp").forward(request, response);
			return;
		}
		
		
		request.setAttribute("groupNo", groupNo);
		request.setAttribute("group", g);
		request.setAttribute("locList", locList);
		request.setAttribute("topicList", topicList);
		request.setAttribute("topicCtg", topicCtg);
		request.setAttribute("localCtg", localCtg);
		
		request.getRequestDispatcher("/views/manager/manager_main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
