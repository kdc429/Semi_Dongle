package com.dongle.dongleMemberJoin.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.dongle.dongleMemberJoin.service.DongleMemberJoinService;
import com.dongle.group.model.vo.GroupMember;
import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamePolicy;

/**
 * Servlet implementation class DongleMemberUpdateServlet
 */
@WebServlet("/dongleMemberUpdate")
public class DongleMemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DongleMemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(!ServletFileUpload.isMultipartContent(request))
		{
			request.setAttribute("msg", "동글등록오류[B-001]");
			request.setAttribute("loc", "/board/boardList");
			request.getRequestDispatcher("/Dongle_view/msg.jsp").forward(request, response);
			return;
		}
		//파일 기본경로
		String root=getServletContext().getRealPath("/");
		//파일경로를 설정할때 구분자!
		/*String saveDir=root+"upload/board";*/
		String saveDir=root+"upload"+File.separator+"board";
		
		//파일 크기설정
		int maxSize=1024*1024*10;//10MB
		
		
		//파일 업로드 객체 생성
		//filname rename
		MultipartRequest mr=new MultipartRequest(request, 
				saveDir,
				maxSize,
				"UTF-8",
				new MyFileRenamePolicy());
		
		request.setCharacterEncoding("UTF-8");
		
//		String nickname = request.getParameter("nickname");
//		String upfile =  request.getParameter("upfile");
		
		GroupMember m = new GroupMember();
		
		m.setGroupMemberNickname(mr.getParameter("dongle_nickname"));
		m.setGroupNo(Integer.parseInt(mr.getParameter("groupNo")));
		m.setMemberNo(Integer.parseInt(mr.getParameter("memberNo")));
		m.setGroupMemberImageNewPath(mr.getOriginalFileName("upfile"));
		m.setGroupMemberImageOldPath(mr.getFilesystemName("upfile"));
		
		int result = new DongleMemberJoinService().insertdongleupdate(m);
		
		//응답처리
		String msg="";
		String loc="";
		String view="/Dongle_view/msg.jsp";
		
		if(result>0)
		{
			msg="정보수정 성공";
			loc="/communityJoin?groupNo="+mr.getParameter("groupNo");

		}
		else 
		{
			msg="정보수정 실패";
			loc="/Dongle_Community_view/DongleMemberJoin.jsp";
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
