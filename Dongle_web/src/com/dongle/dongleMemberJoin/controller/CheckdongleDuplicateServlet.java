package com.dongle.dongleMemberJoin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dongle.dongleMemberJoin.service.DongleMemberJoinService;
import com.dongle.group.model.vo.GroupMember;



/**
 * Servlet implementation class CheckIdDuplicateServlet
 */
@WebServlet("/NicknameCheck")
public class CheckdongleDuplicateServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckdongleDuplicateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      //파라미터로 전송된 id가 DB에 있는지 확인하는것!
      String nickname=request.getParameter("nickname");
      
      GroupMember m=new GroupMember();
      m.setGroupMemberNickname(nickname);
      GroupMember m1=new DongleMemberJoinService().selectMember(nickname);   
      
/*      Member m=new Member();
      m.setMemberId(userId);
      Member m1=new MemberService().selectMember(m);*/
      
      boolean isAble=(m1==null)?true:false;
      response.getWriter().println(isAble);
      
      
      
      
   
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}