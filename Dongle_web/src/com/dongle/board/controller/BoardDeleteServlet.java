package com.dongle.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.board.model.service.BoardService;
import com.dongle.board.model.vo.Board;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/board/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));
		int groupNo=Integer.parseInt(request.getParameter("groupNo"));
		System.out.println("들어오냐"+boardNo+groupNo);
		int rs=new BoardService().deleteBoard(boardNo,groupNo);
		
		if(rs!=0)
		{
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("게시글 삭제 성공");
		}
		else {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("게시글 삭제 실패");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
