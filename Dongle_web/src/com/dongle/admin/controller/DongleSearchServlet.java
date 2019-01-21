package com.dongle.admin.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.admin.service.AdminService;
import com.dongle.group.model.vo.ListGroup;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class DongleSearchServlet
 */
@WebServlet("/admin/dongleSearch")
public class DongleSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DongleSearchServlet() {
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
		
		String searchType=request.getParameter("dongle-searchType");
		
		String searchKeyword=request.getParameter("searchKeyword");
		
		List<ListGroup> dongleList=null;
		switch(searchType)
		{
			case "dongleName" : dongleList=new AdminService().selectDongleName(searchKeyword);break;
			case "managerId" : dongleList=new AdminService().selectManagerId(searchKeyword);break;
			case "dongleEnDate" : dongleList=new AdminService().selectDongleEnDate(searchKeyword);break;
			case "metro" : dongleList=new AdminService().selectMetro(searchKeyword);break;
			
		}
		
		request.setAttribute("dongleList", dongleList);
		request.getRequestDispatcher("/Dongle_view/admin_dongleSearch.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
