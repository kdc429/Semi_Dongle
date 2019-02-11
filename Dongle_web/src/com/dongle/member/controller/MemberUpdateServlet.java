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
      String userName=request.getParameter("userName");   
      String ssn=request.getParameter("age");
      String phone=request.getParameter("phone");
      String address=request.getParameter("address");
      String email=request.getParameter("email");      
      

      String isAdmin = "";
   
      if(request.getParameter("isAdmin")!=null)
      {
         isAdmin = request.getParameter("isAdmin");
      }
//      Member m=new Member(userId,password,userName,gender,ssn,phone,address,email, null, 0, 0);
      
      Member m=new Member();
      m.setMemberId(userId);
      m.setMemberName(userName);
      m.setAddress(address);
      m.setSsn(ssn);
      m.setEmail(email);
      m.setPhone(phone);
      
      String pw = m.getMemberPwd();
      
      
      
      Member loginMember = (Member)request.getSession().getAttribute("loginMember");
      
      Member result1 = new MemberService().selectEmail(m);
      
      String msg="";
      String loc="";
      String view="";
      if(result1!=null)
      {   
         msg="존재하는 이메일 입니다. 다시 확인해주세요";
         response.setContentType("text/html;charset=UTF-8");
         response.getWriter().append(msg);
         return;
      }
      else {
         int result=new MemberService().memberUpdate(m);
         if(result>0)
         {
            if(isAdmin.equals("true"))
            {
               msg="회원정보수정을 완료했습니다.";
               //loc="/Dongle_view/memberView?userId="+m.getMemberId();
               
               String script="self.close();opener.location.reload();";
               request.setAttribute("script", script);
            }
            else
            {
               msg="회원정보수정을 완료했습니다.";
               response.setContentType("text/html;charset=UTF-8");
               response.getWriter().append(msg);
               return;
            }
         }
         else
         {
            msg="회원정보수정을 실패하였습니다.";
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().append(msg);
            return;
         }
      } 
      //페이지 포워딩
      //경로앞에 /써주면 : 절대경로
        //* root컨택스트가 붙임(어플리케이션명)
      //경로앞에 /안써주면 : 상대경로
       //* 받은경로에서부터 시작!
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}