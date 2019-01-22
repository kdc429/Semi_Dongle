package com.dongle.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.member.model.vo.Member;
import com.dongle.member.model.service.MemberService;



/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/memberUpdate")
//@WebServlet(name="MemberUpdateServlet", urlPatterns="/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		String userName=request.getParameter("userName");	
		String ssn=request.getParameter("age");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		String email=request.getParameter("email");

//		Member m=new Member(userId,password,userName,gender,ssn,phone,address,email, null, 0, 0);
		
		Member m=new Member();
		m.setMemberId(userId);
		m.setMemberName(userName);
		m.setAddress(address);
		m.setSsn(ssn);
		m.setEmail(email);
		m.setPhone(phone);
		
		int result=new MemberService().memberUpdate(m);
		
		String msg="";
		String loc="";
		String view="/Dongle_view/msg.jsp";
		
		if(result>0)
		{
			msg="회원정보수정을 완료했습니다.";
			loc="/Dongle_view/main.jsp";
		}
		else 
		{
			msg="회원정보수정을 실패하였습니다.";
			loc="/Dongle_view/memberView.jsp";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		//페이지 포워딩
		//경로앞에 /써주면 : 절대경로
		  //* root컨택스트가 붙임(어플리케이션명)
		//경로앞에 /안써주면 : 상대경로
		 //* 받은경로에서부터 시작!
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
