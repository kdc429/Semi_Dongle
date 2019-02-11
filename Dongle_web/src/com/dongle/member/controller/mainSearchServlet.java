package com.dongle.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.group.model.service.GroupService;
import com.dongle.group.model.vo.Group;
import com.dongle.main.model.service.MainService;
import com.dongle.main.model.vo.LocationCategory;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class mainSearchServlet
 */
@WebServlet("/main/searchPage")
public class mainSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mainSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
		/*int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		Group g = new GroupService().selectGrInfo(groupNo);*/
		List<Group> groupList = new GroupService().selectAllGroupList();
		
		String loc="";
		loc="/Dongle_view/search.jsp";
		/*request.setAttribute("group", g);
		request.setAttribute("groupNo",groupNo);*/
		request.setAttribute("loginMember",loginMember);
		request.setAttribute("groupList",groupList);
		request.getRequestDispatcher(loc).forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
