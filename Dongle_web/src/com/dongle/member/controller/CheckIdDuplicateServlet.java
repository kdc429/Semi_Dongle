package com.dongle.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.dongle.member.model.service.MemberService;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class CheckIdDuplicateServlet
 */
@WebServlet("/checkIdDuplicate")
public class CheckIdDuplicateServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIdDuplicateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      //파라미터로 전송된 id가 DB에 있는지 확인하는것!
      String userId=request.getParameter("userId");
      System.out.println("글자");
      System.out.println(userId);
      Member m=new Member();
      m.setMemberId(userId);
      m=new MemberService().selectMember(m);
      System.out.println(m);
/*      String memberId;
      memberId = m.getMemberId();*/
      
      boolean isAble=(m==null);
      //id true면 사용가능, false면 사용불가
      System.out.println(isAble);
      
      JSONObject json = new JSONObject();
      json.put("isAble", isAble);
      json.put("userId", userId);
      
      
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