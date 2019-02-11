package com.dongle.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.member.model.service.MemberService;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class PwdFindEndServlet
 */
@WebServlet("/pwdFindEnd")
public class PwdFindEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdFindEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userEmail = request.getParameter("userEmail");
		String pwdHint = request.getParameter("pwdHint");
		String hintAnswer = request.getParameter("hintAnswer");
		System.out.println("입력아이디"+userId + " & " + "입력이메일"+userEmail + "힌트"+pwdHint+"답"+hintAnswer);
		Member m = new Member();
		m.setMemberId(userId);
		m.setEmail(userEmail);
		m.setPwdHintList(pwdHint);
		m.setPwdHintAnswer(hintAnswer);
		
		Member checkMember= new MemberService().selectPwd(m);
		System.out.println("m"+m);
		System.out.println("checkMember"+checkMember);
		String msg="";
		String loc="";
		String view="";
		if(m.getEmail().length()<1 || m.getMemberId().length()<1 || m.getPwdHintAnswer().length()<1)
		{
			request.setAttribute("msg","아이디,이메일,힌트를 입력해주세요.");
			request.setAttribute("loc","/PwdFind");
			view="views/common/msg.jsp";
		}
		else if(!m.getPwdHintAnswer().equals(checkMember.getPwdHintAnswer()))
		{
			request.setAttribute("msg","잘못된 답변입니다.");
			request.setAttribute("loc","/PwdFind");
			view="views/common/msg.jsp";
		}
		else
		{
			if(checkMember.getMemberId().equals(userId) && checkMember.getEmail().equals(userEmail) && checkMember.getPwdHintList().equals(pwdHint) && checkMember.getPwdHintAnswer().equals(hintAnswer))
			{
				view="/resetPassword";
				request.setAttribute("userId", userId);
			}
			else if(!checkMember.getEmail().equals(userEmail)) {
				request.setAttribute("msg","회원 정보가 없습니다.");
				request.setAttribute("loc","/PwdFind");
				view="views/common/msg.jsp";
			}
		}
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
