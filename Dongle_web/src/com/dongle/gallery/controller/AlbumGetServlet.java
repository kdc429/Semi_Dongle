package com.dongle.gallery.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.gallery.model.service.GalleryService;
import com.dongle.gallery.model.vo.AlbumCategory;

/**
 * Servlet implementation class AlbumGetServlet
 */
@WebServlet("/albumGet")
public class AlbumGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlbumGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int groupNo=Integer.parseInt(request.getParameter("groupNo"));
		String memberId=request.getParameter("memberId");
		System.out.println("여기 albumget서블릿: "+groupNo+":"+memberId); //테스트를 위해 adminId가 박혀있음
		List<AlbumCategory> list = new GalleryService().albumGet(groupNo);
		request.setAttribute("groupNo", groupNo);
		request.setAttribute("memberId", memberId);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/gallery/albumView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
