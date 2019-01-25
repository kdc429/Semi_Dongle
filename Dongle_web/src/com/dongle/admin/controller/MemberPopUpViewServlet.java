package com.dongle.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.admin.model.service.AdminService;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class MemberPopUpViewServlet
 */
@WebServlet("/admin/memberPopUpView")
public class MemberPopUpViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPopUpViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		
		Member m = new AdminService().selectMember(memberNo);
		
		request.setAttribute("member", m);
		request.getRequestDispatcher("/Dongle_view/admin_memberPopUpView.jsp").forward(request, response);;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
