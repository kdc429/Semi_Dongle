package com.dongle.group.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.group.model.service.GroupService;
import com.dongle.group.model.vo.Group;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class CommunityJoinServlet
 */
@WebServlet("/communityJoin")
public class CommunityJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Member loginMember = (Member)request.getSession().getAttribute("loginMember");// 세션에서 받아온 로그인 멤버 객체
		System.out.println(loginMember.getMemberNo());
		int gNo=Integer.parseInt(request.getParameter("groupNo")); //그룹넘버
		System.out.println(gNo);
		Group g=new GroupService().selectGrInfo(gNo); //그룹정보 받아오기
		String view="/Dongle_view/msg.jsp";
		String msg="";
		String loc="";
		
		if(g==null) { //데이터 없을시 에러페이지 이동으로 변경예정
			msg="접속실패!";
			loc="/login";
			request.getRequestDispatcher(view).forward(request, response);
			request.setAttribute("loc",loc);
		}else {
			loc="/Dongle_Community_view/Community_main.jsp";
			request.setAttribute("group", g);
			request.getRequestDispatcher(loc).forward(request, response);
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
