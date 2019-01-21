package com.dongle.gallery.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.gallery.model.service.GalleryService;
import com.dongle.gallery.model.vo.AlbumCategory;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class AlbumNameCheckServlet
 */
@WebServlet("/gallery/albumInsert")
public class AlbumInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlbumInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String albumNameP = request.getParameter("albumNameP");
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
		int groupNo=Integer.parseInt(request.getParameter("groupNo"));
		
		int rs = new GalleryService().insertAlbum(albumNameP, groupNo);
		if(rs!=0)
		{
			response.setContentType("text/csv;charset=UTF-8");
			response.getWriter().println("앨범을 등록하였습니다.");
		}
		else {
			response.setContentType("text/csv;charset=UTF-8");
			response.getWriter().println("앨범을 등록하지 못했습니다. 다시 시도해주세요.");
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
