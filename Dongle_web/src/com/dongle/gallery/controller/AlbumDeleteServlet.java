package com.dongle.gallery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.gallery.model.service.GalleryService;

/**
 * Servlet implementation class AlbumDeleteServlet
 */
@WebServlet("/gallery/albumDelete")
public class AlbumDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlbumDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String albumName = request.getParameter("albumName");
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		String albumCode = request.getParameter("albumCode");
		System.out.println(albumName);
		
		int rs = new GalleryService().deleteAlbum(groupNo,albumCode);
		if(rs!=0)
		{
			response.setContentType("text/csv;charset=UTF-8");
			response.getWriter().println("앨범을 삭제하였습니다.");
		}
		else {
			response.setContentType("text/csv;charset=UTF-8");
			response.getWriter().println("앨범을 삭제하지 못했습니다. 다시 시도해주세요.");
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
