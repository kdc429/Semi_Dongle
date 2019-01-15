package com.dongle.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.member.model.vo.Member;
import com.dongle.member.service.MemberService;



/**
 * Servlet implementation class UpdatePasswordEndServelt
 */
@WebServlet("/updatePasswordEnd")
/*@WebServlet(name="UpdatePasswordEndServelt",urlPatterns="/updatePasswordEnd")*/
public class UpdatePasswordEndServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordEndServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cPassword=request.getParameter("password");
		String nPassword=request.getParameter("password_new");
		String id=request.getParameter("userId");
		System.out.println("current : "+cPassword + " new : "+nPassword+" id : "+id);
		Member m=new Member();
		m.setMemberId(id);
		m=new MemberService().selectMember(m);
		String msg="";
		String loc="";
		String view="/Dongle_view/msg.jsp";
		
		if(m.getMemberPwd().equals(cPassword))
		{
			Member data=new Member();
			data.setMemberId(id);
			data.setMemberPwd(nPassword);
			int result=new MemberService().updatePassword(data);
			
			if(result>0)
			{
				msg="비밀번호 변경 성공!";
				String script="self.close()";
				request.setAttribute("script", script);
			}
			else 
			{
				msg="비밀번호 변경에 실패!";
				loc="/Dongle_view/updatePassword?userId="+id;
			}
		}
		else
		{
			msg="현재암호와 일치하지 않습니다!";
			loc="/Dongle_view/updatePassword?userId="+id;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
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
