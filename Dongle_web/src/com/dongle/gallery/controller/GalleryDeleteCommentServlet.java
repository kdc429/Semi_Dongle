package com.dongle.gallery.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.gallery.model.service.GalleryService;
import com.dongle.gallery.model.vo.GalleryCommentJoin;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class GalleryDeleteCommentServlet
 */
@WebServlet("/gallery/deleteComment")
public class GalleryDeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GalleryDeleteCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int galCommentNo = Integer.parseInt(request.getParameter("galCommentNo"));
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		
		int rs = new GalleryService().deleteComment(groupNo,galCommentNo);
		
		
		

		int galFileNo=Integer.parseInt(request.getParameter("galFileNo"));
		int galNo=Integer.parseInt(request.getParameter("galNo"));
		
		List<GalleryCommentJoin> gclist = new GalleryService().selectGalCommentList(groupNo,galFileNo,galNo);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
