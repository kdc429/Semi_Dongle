package com.dongle.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.board.model.service.BoardService;
import com.dongle.gallery.model.service.GalleryService;

/**
 * Servlet implementation class BoardCommentReportServlet
 */
@WebServlet("/board/boardCommentReport")
public class BoardCommentReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int groupNo=Integer.parseInt(request.getParameter("groupNo"));
		int memberNo=Integer.parseInt(request.getParameter("memberNo"));
		int reportBoCommentNo=Integer.parseInt(request.getParameter("reportBoCommentNo"));
		String reportCode=(String)request.getParameter("reportCode");
		
		int rs= new GalleryService().insertReport(groupNo,memberNo,reportCode);
		if(rs!=0) {
			int result = new BoardService().updateBoardCommentReportStatus(groupNo,reportBoCommentNo);
			
			 if(result!=0)
		      {
		         response.setContentType("text/csv;charset=UTF-8");
		         response.getWriter().println("신고 완료 하였습니다.");
		      }
		      else {
		         response.setContentType("text/csv;charset=UTF-8");
		         response.getWriter().println("신고 실패하였습니다. 다시 시도해주세요.");
		      }
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
