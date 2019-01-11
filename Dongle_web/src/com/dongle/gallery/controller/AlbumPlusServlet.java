package com.dongle.gallery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AlbumPlusServlet
 */
@WebServlet("/albumPlus")
public class AlbumPlusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlbumPlusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId=request.getParameter("memberId");
		String groupNo=request.getParameter("groupNo");
		System.out.println(groupNo);
		if(memberId==null||!memberId.equals("admin"))
		{
			request.setAttribute("msg", "잘못된 경로로 접근하였습니다.");
			request.setAttribute("loc", "/albumGet?no="+groupNo);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		request.setAttribute("groupNo", groupNo);
		request.setAttribute("memberId", memberId);
		request.getRequestDispatcher("/views/gallery/albumPlus.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}