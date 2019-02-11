package com.dongle.group.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.dongle.dongleMemberJoin.service.DongleMemberJoinService;
import com.dongle.group.model.vo.Group;
import com.dongle.group.model.vo.GroupMember;
import com.dongle.manager.model.service.ManagerService;
import com.dongle.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamePolicy;

/**
 * Servlet implementation class CreateDongleServlet
 */
@WebServlet("/createDongle")
public class CreateDongleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateDongleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Member loginMember=(Member)request.getSession().getAttribute("loginMember");
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
		String saveDir=root+"images"+File.separator+"group_profile";
		int maxSize=1024*1024*10;//10MB
		
		MultipartRequest mr=new MultipartRequest(request, 
				saveDir,
				maxSize,
				"UTF-8",
				new MyFileRenamePolicy());
		
		request.setCharacterEncoding("UTF-8");
		
		Group newGroup = new Group();
		
		String groupName = mr.getParameter("dongleName");
		System.out.println("그룹네임 : " + groupName + "ㅁㄴㅇㄹ");
		String[] topicCode = mr.getParameterValues("topicCode");
		String[] topicName = mr.getParameterValues("topicName");
		
		String topicNameStr = "";
		if(topicCode != null)
		{
			for(int i = 0; i < topicCode.length; i++)
			{
				
				topicNameStr += topicName[i];
				if(i != topicCode.length)
				{
					topicNameStr += ",";
				}
			}
		}
		String[] metroCode = mr.getParameterValues("metroCode");
		String[] metroName = mr.getParameterValues("metroName");
		String[] areaCode = mr.getParameterValues("areaCode");
		String[] areaName = mr.getParameterValues("areaName");
		String[] townCode = mr.getParameterValues("townCode");
		String[] townName = mr.getParameterValues("townName");
		String[] locCode = null;
		String locName = "";
		
		if(metroCode!=null)
		{
			locCode = new String[metroCode.length];
			
			
			for(int i = 0; i < metroCode.length; i++)
			{
				locCode[i] = new ManagerService().selectLocCtgCode(metroCode[i], areaCode[i], townCode[i]);
				locName += metroName[i] + (areaName[i].equals("")?"":" "+areaName[i]) + (townName[i].equals("")?"":" "+townName[i]);
				if(i != metroCode.length)
				{
					locName += ",";
				}
			}
		}
		System.out.println("록네임" + locName);
		
		String dateCtg = mr.getParameter("activetime");
		System.out.println(dateCtg);
		System.out.println(mr.getParameter("minAge"));
		int minAge = mr.getParameter("minAge").equals("")?0:Integer.parseInt(mr.getParameter("minAge"));
		int maxAge = mr.getParameter("maxAge").equals("")?0:Integer.parseInt(mr.getParameter("maxAge"));
		
		String intro = mr.getParameter("intro");	
		
		newGroup.setGroupName((groupName)); 
		newGroup.setTopicCode(topicNameStr);
		newGroup.setLocCtgCode(locName);
		newGroup.setGroupDateCtg(dateCtg);
		newGroup.setMinAge(minAge);
		newGroup.setMaxAge(maxAge);
		newGroup.setGroupIntro(intro);
		newGroup.setGroupImageOldPath(mr.getOriginalFileName("upfile"));
		newGroup.setGroupImageNewPath(mr.getFilesystemName("upfile"));
		
		
		
		int result1 = new ManagerService().insertDongle(loginMember.getMemberNo(), newGroup);
		int result2 = new ManagerService().updateMultiTopic(topicCode, result1);
	    int result3 = new ManagerService().updateMultiLoc(locCode, result1);
		int result4 = new DongleMemberJoinService().insertdonglejoin2(result1,loginMember.getMemberNo());
		String msg="";
		String loc="";
		String view="/Dongle_view/msg.jsp";
		
		if(result1>0)
	      {
	         GroupMember m = new GroupMember();
	         
	         m.setGroupMemberNickname("매니저");
	         m.setGroupNo(result1);
	         m.setMemberNo(loginMember.getMemberNo());
	         m.setGroupMemberImageOldPath("default.png");
	         
	         int result = new DongleMemberJoinService().insertdonglejoin(m);
	         
	         msg="동글 생성 성공";
	         loc="/communityJoin?groupNo="+ result1;

	      }
		else 
		{
			
			msg="동글 생성 실패";
			loc="/serveMainView";
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
