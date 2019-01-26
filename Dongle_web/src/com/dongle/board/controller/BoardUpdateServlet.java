package com.dongle.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dongle.board.model.service.BoardService;
import com.dongle.board.model.vo.Board;
import com.dongle.board.model.vo.BoardPath;

/**
 * Servlet implementation class BoardUpdate
 */
@WebServlet("/board/boardUpdate")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int groupNo=Integer.parseInt(request.getParameter("groupNo"));
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));
		//bp를 받아와라라라라라라
		BoardPath bp=new BoardService().selectBoardPath(boardNo,groupNo);
		System.out.println("들어옴"+boardNo+" : "+groupNo+" : "+bp);
		
		String view="";
		if(bp!=null)
		{
			view="/views/board/boardUpdate.jsp";
			request.setAttribute("groupNo", groupNo);
			request.setAttribute("boardPath", bp);
			/*request.setAttribute("board", b);*/
		}
		else
		{
			request.setAttribute("msg", "조회된 자료가 없습니다");
			request.setAttribute("loc", "/board/boardList?groupNo="+groupNo);
			view="/views/common/msg.jsp";
		}
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
