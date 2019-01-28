package com.dongle.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dongle.group.model.service.GroupService;
import com.dongle.group.model.vo.EditPickGroup;
import com.dongle.group.model.vo.Group;
import com.dongle.group.model.vo.LocalCtg;
import com.dongle.group.model.vo.TopicCtg;
import com.dongle.member.model.service.MemberService;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class LoginMember
 */
@WebServlet("/login")
public class LoginMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginMember() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("userId");//입력한 아이디	
		String pw = request.getParameter("password");//입력한 패스워드
		MemberService ms = new MemberService();
		GroupService gs= new GroupService();
		Member m = new Member();
		m.setMemberId(id);
		m.setMemberPwd(pw);		

		Member data = ms.selectMember(m);
		List<Group> joinList=gs.selectGroup(id);
		List<EditPickGroup> editList=gs.selectEditGr();
		List<Group> rankList=gs.selectRank();
		List<TopicCtg> topicCtg = new GroupService().selectTopicCtg();
		List<LocalCtg> localCtg = new GroupService().selectLocalCtg();
		
		String view = "";
		String msg = "";
		if (data == null) {
			// 아이디가 없다
			msg = "입력하신 아이디가 존재하지 않습니다.";
			view = "Dongle_view/msg.jsp";
			request.setAttribute("loc", "/");
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		} else {
			// 아이디가 일단 있다.
			if (pw.equals(data.getMemberPwd())) {
				// 로그인 성공
				System.out.println("로그인성공!");
				// 쿠키이용하여 회원아이디 저장하기
				view = "Dongle_view/main.jsp";// 메인화면으로 
				// 로그인이 성공했으므로 session객체에 값을 넣고 유지
				HttpSession session = request.getSession();// 세션생성~!
				session.setAttribute("loginMember", data);
				request.setAttribute("list", joinList);
				request.setAttribute("editList", editList);
				request.setAttribute("rankList", rankList);
				request.setAttribute("topicCtg", topicCtg);
				request.setAttribute("localCtg", localCtg);
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			} else {
				// 패스워드가 일치 하지 않음
				msg = "비밀번호가 일치하지 않습니다";
				view = "Dongle_view/msg.jsp";
				request.setAttribute("loc", "/");
				request.setAttribute("msg", msg);
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			}
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
