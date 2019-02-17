package com.dongle.group.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.gallery.model.vo.GalleryPath;
import com.dongle.group.model.service.GroupService;
import com.dongle.group.model.vo.EditPickGroup;
import com.dongle.group.model.vo.Group;
import com.dongle.group.model.vo.GroupMember;
import com.dongle.group.model.vo.MultiLocation;
import com.dongle.group.model.vo.MultiTopic;
import com.dongle.main.model.service.MainService;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class CommunityJoinServlet
 */
@WebServlet("/communityJoin")
public class CommunityJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");// 세션에서 받아온 로그인 멤버 객체
		System.out.println(loginMember.getMemberNo());

		int groupNo=Integer.parseInt(request.getParameter("groupNo")); //그룹넘버
		int result = new GroupService().countMember(groupNo); //이렇게 해야 넘어감
		

		Group g=new GroupService().selectGrInfo(groupNo); //그룹정보 받아오기
		GroupMember gm = new GroupService().selectGmInfo(groupNo,loginMember.getMemberNo());
		List<GalleryPath> galList = new GroupService().selectAllGallery(groupNo);
		request.setAttribute("groupNo",groupNo);
		request.setAttribute("galList",galList);

		//에디터픽 선정된 그룹 여부
		List<EditPickGroup> editList = new GroupService().selectEditGr();
		List<MultiTopic> topicList=new GroupService().selectGrTopic(groupNo);
		List<MultiLocation> locationList=new GroupService().selectGrLoc(groupNo);
		
/*		System.out.println(editList);
		System.out.println(topicList);
		System.out.println(locationList);*/
		

		String view="/Dongle_view/msg.jsp";
		String msg="";
		String loc="";
		
		if(g==null) { //데이터 없을시 에러페이지 이동으로 변경예정
			msg="접속실패! 다시 시도해주세요.";
			loc="/login";
			request.setAttribute("loc",loc);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(view).forward(request, response);
		}else {

			loc="/Dongle_Community_view/Community_main.jsp";
			request.setAttribute("group", g);
			request.setAttribute("groupMember", gm);
			request.setAttribute("loc",loc);
			request.setAttribute("editList", editList);
			request.setAttribute("locList", locationList);
			request.setAttribute("topicList",topicList);
			request.setAttribute("loginMember", loginMember);
			request.setAttribute("result",result);

			request.getRequestDispatcher(loc).forward(request, response);
			
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
