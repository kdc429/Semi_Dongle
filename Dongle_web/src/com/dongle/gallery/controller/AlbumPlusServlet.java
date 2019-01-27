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
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class AlbumPlusServlet
 */
@WebServlet("/gallery/albumPlus")
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
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
		int groupNo=Integer.parseInt(request.getParameter("groupNo"));
		if(loginMember.getMemberId()==null&&!loginMember.getMemberId().equals("admin"))
		{
			request.setAttribute("msg", "잘못된 경로로 접근하였습니다.");
			request.setAttribute("loc", "/albumGet?groupNo="+groupNo);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		//그룹의 앨범 뽑아오기
		List<AlbumCategory> list = new GalleryService().albumGet(groupNo);
		
		request.setAttribute("list", list);
		request.setAttribute("groupNo", groupNo);
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
