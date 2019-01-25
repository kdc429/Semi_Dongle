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
 * Servlet implementation class ReportMemberSubmitServlet
 */
@WebServlet("/manager/reportMemberSubmit")
public class ReportMemberSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportMemberSubmitServlet() {
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
		int selectMember = Integer.parseInt(request.getParameter("selectMember"));
		int rptCount = Integer.parseInt(request.getParameter("rptCount"));
		
		if(loginMember==null||loginMember.getMemberNo() != managerNo) 
		{
			request.setAttribute("msg", "잘못된 경로로 접속하셨습니다");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/Dongle_view/msg.jsp").forward(request, response);
			return;
		}
		
		int result = 0;
		if(rptCount <2) //신고 횟수가 3 미만은 카운트 + 1 / 3 이상이면 블랙 후 MEMBER_TAB의 신고 횟수 +1
		{
			result = new ManagerService().submitRptMember(groupNo, selectMember, rptCount);
		}
		else 
		{
			result = new ManagerService().submitRptMember(groupNo, selectMember, rptCount);
			result = new ManagerService().blackMember(groupNo, selectMember); 
			result = new ManagerService().cntUpRptMember(selectMember);
		}
		
		result = new ManagerService().deleteRptMember(groupNo, selectMember);
		
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
