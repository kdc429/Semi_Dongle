package com.dongle.main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.gallery.model.service.GalleryService;
import com.dongle.gallery.model.vo.GalleryPath;
import com.dongle.group.model.service.GroupService;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class CommunityGalleryListServlet
 */
@WebServlet("/community/mainGallery")
public class CommunityGalleryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityGalleryListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member loginMember=(Member)(request.getSession().getAttribute("loginMember"));
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		
		//메인 이미지로 띄우기 위한 해당 앨범의 갤러리 뽑아오기
		List<GalleryPath> galList = new GalleryService().albumAndGalList(groupNo);
		request.setAttribute("groupNo",groupNo);
		request.setAttribute("galList",galList);
		System.out.println("갤러리 : "+galList);
		request.getRequestDispatcher("/views/comunityMain/MainMiniGallery.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
