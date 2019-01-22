package com.dongle.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dongle.board.model.service.BoardService;
import com.dongle.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardCommentServlet
 */
@WebServlet("/board/commentInsert")
public class BoardCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));
		String boardCommentContent=request.getParameter("boardCommentContent");
		String groupMemberNickname=request.getParameter("groupMemberNickname");
		int boardCommentLevel=Integer.parseInt(request.getParameter("boardCommentLevel"));
		int boardCommentRef=Integer.parseInt(request.getParameter("boardCommentRef"));
		
		System.out.println(boardNo+
				boardCommentContent+
				boardCommentLevel+
				boardCommentRef);
		BoardComment bc=new BoardComment();
		bc.setGroupMemberNickname(groupMemberNickname);
		bc.setBoCommentContent(boardCommentContent);
		bc.setBoCommentLevel(boardCommentLevel);
		bc.setBoCommentRef(boardCommentRef);
		
		int result=new BoardService().insertComment(bc);
		
		String msg="";
		String loc="/board/boardView?no="+boardNo;
		if(result>0)
		{
			msg="댓글등록성공";
		}
		else
		{
			msg="댓글등록 실패";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		RequestDispatcher rd=request.getRequestDispatcher("/views/common/msg.jsp");
		rd.forward(request, response);		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
