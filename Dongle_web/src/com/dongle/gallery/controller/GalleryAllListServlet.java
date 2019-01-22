package com.dongle.gallery.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.dongle.gallery.model.service.GalleryService;
import com.dongle.gallery.model.vo.GalleryCommentJoin;
import com.dongle.gallery.model.vo.GalleryPath;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class GalleryAllListServlet
 */
@WebServlet("/gallery/galleryAllList")
public class GalleryAllListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GalleryAllListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		String albumCode = request.getParameter("albumCode");
		int galFileNo=Integer.parseInt(request.getParameter("galFileNo"));
		int galNo=Integer.parseInt(request.getParameter("galNo"));
		Member loginMember=(Member)(request.getSession().getAttribute("loginMember"));
		int dataNum=Integer.parseInt(request.getParameter("dataNum"));

		
		//해당 갤러리 리스트 뽑아오기
		List<GalleryPath> gplist = new GalleryService().selectOneList(groupNo,galNo,albumCode);

		
		if(gplist!=null) {
			//갤러리 해당 댓글 뽑아오기
			List<GalleryCommentJoin> gclist = new GalleryService().selectGalCommentList(groupNo,galFileNo,galNo);
			if(gclist!=null) {
				request.setAttribute("gclist", gclist);
				System.out.println("gplst: "+gplist);
				System.out.println("gclst: "+gclist);
			}
			request.setAttribute("gplist", gplist);
			request.setAttribute("groupNo", groupNo);
			request.getRequestDispatcher("/views/gallery/galleryModal.jsp").forward(request, response);
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
