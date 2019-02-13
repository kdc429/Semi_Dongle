package com.dongle.dongleMemberJoin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.group.model.service.GroupService;
import com.dongle.group.model.vo.GroupMember;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class DongleUpdateServlet
 */
@WebServlet("/dongleUpdateView")
public class DongleUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DongleUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
	    int groupNo = Integer.parseInt(request.getParameter("groupNo"));     
	       

	    GroupMember gm = new GroupService().selectGmInfo(groupNo,loginMember.getMemberNo());
	    request.setAttribute("groupNo", groupNo);
	    request.setAttribute("groupMember", gm);
	         
	    request.getRequestDispatcher("/Dongle_Community_view/DongleMemberupdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
