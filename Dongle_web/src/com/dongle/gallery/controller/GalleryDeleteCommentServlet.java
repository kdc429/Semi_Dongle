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
import com.dongle.gallery.model.vo.GalleryPath;
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
		System.out.println("뭐야 왜 안들어와 ㅠ");
		int galCommentNo = Integer.parseInt(request.getParameter("galCommentNo"));
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		
		int rs = new GalleryService().deleteComment(groupNo,galCommentNo);
		
		int galNo=Integer.parseInt(request.getParameter("galNo"));
		int galFileNo=Integer.parseInt(request.getParameter("galFileNo"));
		String albumCode=request.getParameter("albumCode");
		
		if(rs!=0)
		{

			List<GalleryPath> gplist = new GalleryService().selectOneList(groupNo,galNo,albumCode);
			if(gplist!=null) {
				//갤러리 해당 댓글 뽑아오기
				List<GalleryCommentJoin> gclist = new GalleryService().selectGalCommentList(groupNo,galFileNo,galNo);
				if(gclist!=null) {
					request.setAttribute("gclist", gclist);
					System.out.println("2코멘트gplist: "+gplist);
					System.out.println("2코멘트gclist: "+gclist);
				}
				request.setAttribute("gplist", gplist);
				request.setAttribute("groupNo", groupNo);
				request.getRequestDispatcher("/views/gallery/commentInsert.jsp").forward(request, response);
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
