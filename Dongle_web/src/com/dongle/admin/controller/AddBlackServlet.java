package com.dongle.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.admin.service.AdminService;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class AddBlackServlet
 */
@WebServlet("/admin/addBlack")
public class AddBlackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBlackServlet() {
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
			request.setAttribute("loc", "/Dongle_view/main.jsp");
			request.getRequestDispatcher("/Dongle_view/msg.jsp").forward(request, response);
			return;
		}
		
		String memberNo[] = request.getParameterValues("member-nonblack");
		int result = new AdminService().addBlack(memberNo);
		
		if(result > 0)
		{
			request.setAttribute("msg", "블랙리스트에 추가되었습니다");
			request.setAttribute("loc", "/admin/blackMemberList");
			request.getRequestDispatcher("/Dongle_view/msg.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("msg", "블랙리스트 추가를 실패하셨습니다");
			request.setAttribute("loc", "/admin/blackMemberList");
			request.getRequestDispatcher("/Dongle_view/msg.jsp").forward(request, response);
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
