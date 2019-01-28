package com.dongle.manager.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.dongle.group.model.service.GroupService;
import com.dongle.group.model.vo.Group;
import com.dongle.manager.model.service.ManagerService;
import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamePolicy;

/**
 * Servlet implementation class UpdateDongleServlet
 */
@WebServlet("/manager/updateDongle")
public class UpdateDongleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDongleServlet() {
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
		String saveDir=root+"images"+File.separator+"group_profile";
		int maxSize=1024*1024*10;//10MB
		
		MultipartRequest mr=new MultipartRequest(request, 
				saveDir,
				maxSize,
				"UTF-8",
				new MyFileRenamePolicy());
		
		request.setCharacterEncoding("UTF-8");
		int groupNo = Integer.parseInt(mr.getParameter("groupNo"));
		Group oldGroup = new GroupService().selectGrInfo(groupNo);
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
				locName += metroName[i] + (areaName[i].equals("==중분류==")?"":" "+areaName[i]) + (townName[i].equals("==소분류==")?"":" "+townName[i]);
				if(i != metroCode.length)
				{
					locName += ",";
				}
			}
		}
		
		
		
		
		String dateCtg = mr.getParameter("activetime");
		System.out.println(dateCtg);
		System.out.println(mr.getParameter("minAge"));
		int minAge = mr.getParameter("minAge").equals("")?0:Integer.parseInt(mr.getParameter("minAge"));
		int maxAge = mr.getParameter("maxAge").equals("")?0:Integer.parseInt(mr.getParameter("maxAge"));
		
		String intro = mr.getParameter("intro");	
		
		newGroup.setGroupNo(Integer.parseInt(mr.getParameter("groupNo")));
		newGroup.setGroupName((groupName.equals("")?oldGroup.getGroupName():groupName)); 
		newGroup.setTopicCode(topicNameStr.equals("")?oldGroup.getTopicCode():topicNameStr);
		newGroup.setLocCtgCode(locName.equals("")?oldGroup.getLocCtgCode():locName);
		newGroup.setGroupDateCtg(dateCtg.equals("")?oldGroup.getGroupDateCtg():dateCtg);
		newGroup.setMinAge(minAge==0?oldGroup.getMinAge():minAge);
		newGroup.setMaxAge(maxAge==0?oldGroup.getMaxAge():maxAge);
		newGroup.setGroupIntro(intro.equals("")?oldGroup.getGroupIntro():intro);
		newGroup.setGroupImageOldPath(mr.getOriginalFileName("upfile")==null?oldGroup.getGroupImageOldPath():mr.getOriginalFileName("upfile"));
		newGroup.setGroupImageNewPath(mr.getFilesystemName("upfile")==null?oldGroup.getGroupImageNewPath():mr.getFilesystemName("upfile"));
		
		
		
		int result1 = new ManagerService().updateDongle(newGroup);
		int result2 = 0;
		int result3 = 0;
		int result4 = 0;
		int result5 = 0;
		
		if(topicCode != null)
		{
			result3 = new ManagerService().deleteMultiTopic(groupNo);
			result2 = new ManagerService().updateMultiTopic(topicCode, groupNo);
		}
		else
		{
			result2 = 1;
			result3 = 1;
			
		}
		if(locCode != null)
		{
			result4 = new ManagerService().deleteMultiLoc(groupNo);
			result5 = new ManagerService().updateMultiLoc(locCode, groupNo);
		}
		else
		{
			result4 = 1;
			result5 = 1;
			
		}
		
		
		String msg="";
		String loc="";
		String view="/Dongle_view/msg.jsp";
		if(result1>0)
		{
			msg="정보수정 성공";
			loc="/communityJoin?groupNo="+groupNo;

		}
		else 
		{
			
			msg="정보수정 실패";
			loc="/communityJoin?groupNo="+groupNo;
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
