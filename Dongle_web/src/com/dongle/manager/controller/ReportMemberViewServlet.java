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
import com.dongle.manager.model.service.ManagerService;
import com.dongle.member.model.vo.DongleRptMember;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class ReportMemberViewServlet
 */
@WebServlet("/manager/reportMemberView")
public class ReportMemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportMemberViewServlet() {
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
			
		if(loginMember==null||loginMember.getMemberNo() != managerNo) 
		{
			request.setAttribute("msg", "잘못된 경로로 접속하셨습니다");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/Dongle_view/msg.jsp").forward(request, response);
			return;
		}
		
		List<DongleRptMember> rptList = new ManagerService().selectRptMember(groupNo);
		
		request.setAttribute("groupNo", groupNo);
		request.setAttribute("group", g);
		request.setAttribute("rptList", rptList);
	
		request.getRequestDispatcher("/views/manager/manager_report.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
