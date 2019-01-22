package com.dongle.main.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.board.model.service.BoardService;
import com.dongle.board.model.vo.Board;

/**
 * Servlet implementation class CommunityBoardViewServlet
 */
@WebServlet("/community/boardView")
public class CommunityBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityBoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			int groupNo = Integer.parseInt(request.getParameter("groupNo"));
			System.out.println(boardNo+" : "+groupNo);
			
			Board b = new BoardService().selectOne(boardNo,groupNo);
			System.out.println("b가 무엇? : "+b);
			
			String view="";
			if(b!=null)
			{
				request.setAttribute("board", b);
				view="views/comunityMain/MainBoardView.jsp";
				request.setAttribute("groupNo",groupNo);
			}
			else
			{
				request.setAttribute("msg", "조회한 공지사항이 존재하지않습니다");
				request.setAttribute("loc", "/comunityMain/MainBoardList?groupNo"+groupNo);
				request.setAttribute("groupNo", groupNo);
				view="/view/common/msg.jsp";
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
