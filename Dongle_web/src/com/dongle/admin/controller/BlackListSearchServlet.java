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
 * Servlet implementation class BlackListSearch
 */
@WebServlet("/admin/searchBlack")
public class BlackListSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlackListSearchServlet() {
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
		System.out.println("1");
		String searchBlack=request.getParameter("search-black");
		String searchNonBlack=request.getParameter("search-nonblack");
		List<Member> blackList = new AdminService().searchBlack(searchBlack, "1");
		List<Member> nonBlackList = new AdminService().searchBlack(searchNonBlack, "0");
		System.out.println("2");
		request.setAttribute("search-black", searchBlack);
		request.setAttribute("search-nonblack", searchNonBlack);
		request.setAttribute("blackList", blackList);
		request.setAttribute("nonBlackList", nonBlackList);
		System.out.println("3");
		request.getRequestDispatcher("/Dongle_view/admin_memberBlackSearch.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
