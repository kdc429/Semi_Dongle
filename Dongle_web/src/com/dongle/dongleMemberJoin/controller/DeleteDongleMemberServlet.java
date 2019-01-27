package com.dongle.dongleMemberJoin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.dongleMemberJoin.service.DongleMemberJoinService;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class DeleteDongleMemberServlet
 */
@WebServlet("/deleteDongleMember")
public class DeleteDongleMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDongleMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Member loginMember=(Member)request.getSession().getAttribute("loginMember");
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		System.out.println("동글멤버삭제 확인용 : " +memberNo + " " + groupNo);
		if(loginMember==null||loginMember.getMemberNo() != memberNo) 
		{
			request.setAttribute("msg", "잘못된 경로로 접속하셨습니다");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/Dongle_view/msg.jsp").forward(request, response);
			return;
		}
		
		int result = new DongleMemberJoinService().deleteDongleMember(memberNo, groupNo);
		
		String loc="";
		String msg="";
		String view="/Dongle_view/msg.jsp";
		if(result > 0)
		{
			loc="/communityJoin?groupNo=" + groupNo;
			msg="탈퇴하셨습니다.";
		}
		else
		{
			loc="/communityJoin?groupNo=" + groupNo;
			msg="탈퇴에 실패하셨습니다.";
		}
		request.setAttribute("loc",loc);
		request.setAttribute("msg",msg);
		request.getRequestDispatcher(view).forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
