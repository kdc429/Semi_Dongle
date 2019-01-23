package com.dongle.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.admin.model.service.AdminService;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class MemberSearchServlet
 */
@WebServlet("/admin/memberSearch")
public class MemberSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Member loginMember=(Member)request.getSession().getAttribute("loginMember");
		if(loginMember==null||!loginMember.getMemberId().equals("admin")) 
		{
			request.setAttribute("msg", "잘못된 경로로 접속하셨습니다");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/Dongle_view/msg.jsp").forward(request, response);
			return;
		}
		
		String searchType=request.getParameter("member-searchType");
		String searchKeyword=request.getParameter("searchKeyword");
		
		List<Member> memberList=null;
		switch(searchType)
		{
			case "memberId" : memberList=new AdminService().selectMemberId(searchKeyword);break;
			case "memberName" : memberList=new AdminService().selectMemberName(searchKeyword);break;
			case "phone" :memberList=new AdminService().selectPhone(searchKeyword);break;
			case "email" : memberList=new AdminService().selectEmail(searchKeyword);break;
			
		}
		
		request.setAttribute("memberList", memberList);
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
