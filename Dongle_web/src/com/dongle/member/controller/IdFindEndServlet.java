package com.dongle.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.main.model.service.MainService;
import com.dongle.member.model.service.MemberService;
import com.dongle.member.model.vo.Member;

import javafx.scene.control.Alert;

/**
 * Servlet implementation class IdEndServlet
 */
@WebServlet("/idFindEnd")
public class IdFindEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdFindEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String userName = request.getParameter("userName");
			String userEmail = request.getParameter("userEmail");
			System.out.println("입력이름"+userName + " & " + "입력이메일"+userEmail);
			Member m = new Member();
			m.setMemberName(userName);
			m.setEmail(userEmail);
			
			Member checkMember= new MemberService().selectId(m);
			System.out.println("m"+m);
			System.out.println("checkMember"+checkMember);
			String msg="";
			String loc="";
			String view="";
			if(m.getEmail().length()<1 || m.getMemberName().length()<1)
			{
				request.setAttribute("msg","이름과 이메일을 입력해주세요.");
				request.setAttribute("loc","/");
				view="views/common/msg.jsp";
			}
			else
			{
				if(checkMember.getMemberName().equals(userName) && checkMember.getEmail().equals(userEmail))
				{
					request.setAttribute("msg","찾으시는 아이디는 "+checkMember.getMemberId()+"입니다");			
					view="views/common/msg.jsp";
					String script = "self.close()";
					request.setAttribute("script", script);
					request.setAttribute("loc","/");
				}
				else if(!checkMember.getEmail().equals(userEmail)){
					
					request.setAttribute("msg","회원 정보가 없습니다.");
					request.setAttribute("loc","/");
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
