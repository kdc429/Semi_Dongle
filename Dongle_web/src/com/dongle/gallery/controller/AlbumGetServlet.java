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
import com.dongle.group.model.vo.GroupMember;

/**
 * Servlet implementation class AlbumGetServlet
 */
@WebServlet("/gallery/albumGet")
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
		//동글그룹번호와 들어온 멤버번호 받기
		int groupNo=Integer.parseInt(request.getParameter("groupNo"));
		int memberNo=Integer.parseInt(request.getParameter("memberNo"));
		
		//동호회 회원인지 아닌지 group_member_tab에서 확인
		GroupMember gm = new GalleryService().groupMemberCheck(groupNo,memberNo);
		
		
		if(memberNo==0||gm==null)
		{
			request.setAttribute("msg", "회원만 열람 가능합니다. 동글에 가입해주세요.");
			request.setAttribute("loc", "/communityJoin?groupNo="+groupNo);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		List<AlbumCategory> list = new GalleryService().albumGet(groupNo);
		request.setAttribute("groupNo", groupNo);
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
