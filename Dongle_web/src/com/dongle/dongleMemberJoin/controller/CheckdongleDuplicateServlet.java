package com.dongle.dongleMemberJoin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

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
      System.out.println("닉넴잉이 "+nickname);
      int groupNo = Integer.parseInt(request.getParameter("groupNo"));
      System.out.println("그룹넘버엉 " + groupNo);
      
      GroupMember m=new GroupMember();
      m.setGroupMemberNickname(nickname);
      GroupMember m1=new DongleMemberJoinService().selectMember(nickname, groupNo);   
      
/*      Member m=new Member();
      m.setMemberId(userId);
      Member m1=new MemberService().selectMember(m);*/
      
      boolean isAble=(m1==null);
      
      
      JSONObject json = new JSONObject();
      json.put("isAble", isAble);
      json.put("nickname", nickname);
      
      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=UTF-8");
      response.getWriter().print(json);
      
      
   
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}