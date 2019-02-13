package com.dongle.board.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dongle.board.model.service.BoardService;
import com.dongle.board.model.vo.Board;
import com.dongle.group.model.service.GroupService;
import com.dongle.group.model.vo.Group;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class BoardTagServlet
 */
@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Member loginMember = (Member)request.getSession().getAttribute("loginMember");
      int groupNo=Integer.parseInt(request.getParameter("groupNo"));
      List<Board> list=new BoardService().selectList(groupNo,loginMember.getMemberId());
      
      Group group = new GroupService().selectGrInfo(groupNo);
      System.out.println("여기확인해주세요: "+groupNo+" : "+list);
      
      request.setAttribute("group", group);
      request.setAttribute("list", list);
      request.getRequestDispatcher("/views/board/boardList.jsp").forward(request, response);;
      
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}