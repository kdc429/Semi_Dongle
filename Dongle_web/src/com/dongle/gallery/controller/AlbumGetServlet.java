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
import com.dongle.gallery.model.vo.GalleryPath;
import com.dongle.group.model.vo.GroupMember;
import com.dongle.member.model.vo.Member;

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
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
		
		//동호회 회원인지 아닌지 group_member_tab에서 확인
		GroupMember gm = new GalleryService().groupMemberCheck(groupNo,loginMember.getMemberNo());
		System.out.println("동호회 회원이니?" + gm);
		/*if((gm.getMemberNo()!=loginMember.getMemberNo()))
		{
			request.setAttribute("msg", "회원만 열람 가능합니다. 동글에 가입해주세요.");
			request.setAttribute("loc", "/communityJoin?groupNo="+groupNo);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}*/
		//그룹의 앨범 뽑아오기
		List<AlbumCategory> list = new GalleryService().albumGet(groupNo);
		//메인 이미지로 띄우기 위한 해당 앨범의 갤러리 뽑아오기
		List<GalleryPath> galList = new GalleryService().albumAndGalList(groupNo);
		System.out.println("albumGetServlst의 "+list.size()+list);
		System.out.println("albumGetServlst의 "+galList.size()+galList);
		
		request.setAttribute("galList", galList);
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
