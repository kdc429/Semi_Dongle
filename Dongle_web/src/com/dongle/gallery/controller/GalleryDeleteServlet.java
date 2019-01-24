package com.dongle.gallery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.gallery.model.service.GalleryService;

/**
 * Servlet implementation class GalleryDeleteServlet
 */
@WebServlet("/gallery/galleryDelete")
public class GalleryDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GalleryDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int groupNo =Integer.parseInt(request.getParameter("groupNo"));
		String albumCode = request.getParameter("albumCode");
		int galNo = Integer.parseInt(request.getParameter("galNo"));
		int rs= new GalleryService().galleryDelete(groupNo, albumCode, galNo);
		System.out.println(rs);
		if(rs!=0)
		{
			response.setContentType("text/csv;charset=UTF-8");
			response.getWriter().println("갤러리를 삭제하였습니다.");
		}
		else {
			response.setContentType("text/csv;charset=UTF-8");
			response.getWriter().println("갤러리를 삭제하지 못했습니다. 다시 시도해주세요.");
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