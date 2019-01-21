package com.dongle.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.board.model.service.BoardService;
import com.dongle.board.model.vo.BoardComment;
import com.dongle.member.model.vo.Member;

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
		int boardCommentLevel=Integer.parseInt(request.getParameter("boardCommentLevel"));
		int boardCommentRef=Integer.parseInt(request.getParameter("boardCommentRef"));
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
/*		int groupNo = Integer.parseInt(request.getParameter("groupNo"));*/
		int groupNo=1;
		System.out.println(groupNo+" : "+boardNo+boardCommentContent+boardCommentLevel+boardCommentRef);
		
		BoardComment bc=new BoardComment();
		
		bc.setGroupNo(groupNo);
		bc.setMemberNo(loginMember.getMemberNo());
		bc.setBoardNo(boardNo);
		bc.setBoCommentContent(boardCommentContent);
		bc.setBoCommentLevel(boardCommentLevel);
		bc.setBoCommentRef(boardCommentRef);
		
		int result=new BoardService().insertBoComment(bc);
		
		if(result>0)
		{
			response.setContentType("text/html;charset=UTF-8");
			
			response.getWriter().println("댓글 등록 성공");
		}
		else
		{
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("댓글 등록 실패");
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
