package com.dongle.main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.board.model.service.BoardService;
import com.dongle.board.model.vo.Board;
import com.dongle.group.model.vo.Group;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class CommunityBoardListServlet
 */
@WebServlet("/community/boardList")
public class CommunityBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityBoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int groupNo=Integer.parseInt(request.getParameter("groupNo"));
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
		System.out.println(loginMember.getMemberId());
		List<Board> list = new BoardService().selectList(groupNo,loginMember.getMemberId());
		request.setAttribute("list",list);
		request.getRequestDispatcher("/views/comunityMain/MainBoardList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
