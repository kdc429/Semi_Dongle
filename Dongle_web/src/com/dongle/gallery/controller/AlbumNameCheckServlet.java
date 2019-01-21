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
@WebServlet("/albumNameCheck")
public class AlbumNameCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlbumNameCheckServlet() {
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
		String memberId=request.getParameter("memberId");
		System.out.println("여기 albumcheck서블릿: "+groupNo+" : "+memberId);
		AlbumCategory ac = new AlbumCategory();
		ac.setAlbumName(albumNameP);
		/*ac.setGroupNo(groupNo);*/
		AlbumCategory oldAc = new GalleryService().checkAlbumName(ac,groupNo);
		System.out.println(oldAc);
		
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		if(oldAc!=null)
		{
			msg="같은 앨범명이 존재합니다. 다시 시도해 주세요.";
			loc="/communityJoin?groupNo="+groupNo;
		}
		else
		{
			int rs=new GalleryService().insertAlbum(albumNameP,groupNo);
			if(rs!=0)
			{
				msg="앨범 등록에 성공하였습니다.";
				loc="/communityJoin?groupNo="+groupNo;
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
