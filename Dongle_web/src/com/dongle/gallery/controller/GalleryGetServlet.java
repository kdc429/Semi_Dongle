package com.dongle.gallery.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.gallery.model.service.GalleryService;
import com.dongle.gallery.model.vo.GalleryPath;
import com.dongle.group.model.vo.GroupMember;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class GalleryGetServlet
 */
@WebServlet("/gallery/galleryGet")
public class GalleryGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GalleryGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String albumCode = request.getParameter("albumCode");
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
/*		System.out.println(albumCode+" "+groupNo+" : "+loginMember.getMemberId());*/
		//동호회 회원인지 아닌지 group_member_tab에서 확인
		GroupMember gm = new GalleryService().groupMemberCheck(groupNo,loginMember.getMemberNo());
		System.out.println(gm);
/*		if(gm.getMemberNo()==0||!loginMember.getMemberId().equals("admin"))
		{
			request.setAttribute("msg", "회원만 열람 가능합니다. 동글에 가입해주세요.");
			request.setAttribute("loc", "/communityJoin?groupNo="+groupNo);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}	*/	
		

		//test : gal_no만 distinct된 dao에 갔다오기
		List<Integer> galNoList = new GalleryService().distictGalNoList(albumCode,groupNo); //7,6,5,4,3,2,1
		System.out.println(galNoList);
		//test : 전체 list뽑아오기
		List<GalleryPath> list =new GalleryService().getAllList(albumCode,groupNo);
		//##테스트 중입니다.
		ArrayList<GalleryPath> tList= new ArrayList<GalleryPath>();
		//같은 galNo끼리 저장된 ArrayList tList뽑아내기
		for(int i=0; i<galNoList.size();i++) //7회 (인덱스는 0부터 6)
		{
			List<GalleryPath> t2List= new ArrayList<GalleryPath>();
			for(int j=0;j<list.size();j++) //36 (인덱스는 0부터 35)
			{
				if(galNoList.get(i)==list.get(j).getGalNo())
				{
					
					t2List.add(list.get(j));
				}
			}
			tList.add(i, t2List.get(0));
		}
		//tList의 length가 totalMember와 같음
		System.out.println(tList);
		
		int totalMember = new GalleryService().selectGalleryCount(albumCode,groupNo);//tList랑 같음
		request.setAttribute("groupNo", groupNo);
		request.setAttribute("albumCode", albumCode);
		request.setAttribute("groupMember", gm);
		request.setAttribute("totalList", totalMember);
		request.getRequestDispatcher("/views/gallery/galleryView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
