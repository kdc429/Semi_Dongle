package com.dongle.group.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dongle.group.model.service.GroupService;
import com.dongle.group.model.vo.Group;
import com.dongle.group.model.vo.GroupMember;
import com.dongle.member.model.vo.Member;
import com.google.gson.Gson;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/memberList")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");// 세션에서 받아온 로그인 멤버 객체
		
		int groupNo=Integer.parseInt(request.getParameter("groupNo")); //그룹넘버		
		
		GroupMember gm = new GroupService().selectGmInfo(groupNo,loginMember.getMemberNo());
		Group g=new GroupService().selectGrInfo(groupNo); //그룹정보 받아오기	
		
		List<GroupMember> list = new GroupService().selectMemberList(groupNo);
		
		
		JSONArray jobjArr=new JSONArray();		
		
		for(GroupMember gm_list : list) {
			JSONObject obj=new JSONObject();
			obj.put("groupMemberImagePath", gm_list.getGroupMemberImagePath());
			obj.put("groupMemberNickname", gm_list.getGroupMemberNickname());
			obj.put("groupMemberEnrollData", gm_list.getGroupMemberEnrollDate());
			jobjArr.add(obj);	
		}
		
		System.out.println(jobjArr.size());
		response.setContentType("application/json;charset=UTF-8");
		new Gson().toJson(jobjArr,response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
