package com.dongle.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.board.model.service.BoardService;

/**
 * Servlet implementation class BoardCommentDeleteServlet
 */
@WebServlet("/board/boardCommentDelete")
public class BoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*int groupNo=Integer.parseInt(request.getParameter("groupNo"));*/
		/*int boardNo=Integer.parseInt(request.getParameter("boardNo"));*/
		int boCommentNo =Integer.parseInt(request.getParameter("bb"));
		System.out.println("댓글넘버"+boCommentNo);
		
		int result = new BoardService().deleteBoComment(boCommentNo);		
		
		if(result>0)
		{
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("댓글 삭제 성공");
		}
		else
		{
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("댓글 삭제 실패");
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
