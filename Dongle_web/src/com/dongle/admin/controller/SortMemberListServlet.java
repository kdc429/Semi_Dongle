package com.dongle.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.admin.service.AdminService;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class SortListServlet
 */
@WebServlet("/admin/sortMemberList")
public class SortMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SortMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Member> memberList = null;
		String flag = request.getParameter("flag");
		String rowValue = request.getParameter("rowValue");
		String searchType=request.getParameter("member-searchType");
		String searchKeyword=request.getParameter("searchKeyword");
		
		if(searchType == null)
		{
			searchType = "";
		}
		if(searchKeyword == null)
		{
			searchKeyword = "";
		}
		switch(rowValue)
		{
		case "memberId": memberList = new AdminService().sortId(searchType, searchKeyword, flag);break;
		case "memberName" : memberList = new AdminService().sortName(searchType, searchKeyword, flag);break;
		case "gender" : memberList = new AdminService().sortGender(searchType, searchKeyword, flag);break;
		case "ssn" : memberList = new AdminService().sortSsn(searchType, searchKeyword, flag);break;
		case "phone" : memberList = new AdminService().sortPhone(searchType, searchKeyword, flag);break;
		case "address" : memberList = new AdminService().sortAddress(searchType, searchKeyword, flag);break;
		case "email" : memberList = new AdminService().sortEmail(searchType, searchKeyword, flag);break;
		case "enrollDate" : memberList = new AdminService().sortEnrollDate(searchType, searchKeyword, flag);break;
		case "blackList" : memberList = new AdminService().sortBlackList(searchType, searchKeyword, flag);break;
		case "reportCount" : memberList = new AdminService().sortReportCount(searchType, searchKeyword, flag);break;
		
		}
		
		request.setAttribute("memberList", memberList);
		if(searchType.equals(""))
			request.getRequestDispatcher("/Dongle_view/admin_memberList.jsp").forward(request, response);
		else
			request.getRequestDispatcher("/Dongle_view/admin_memberSearch.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
