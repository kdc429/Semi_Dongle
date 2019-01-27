package com.dongle.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.group.model.service.GroupService;
import com.dongle.group.model.vo.EditPickGroup;
import com.dongle.group.model.vo.Group;
import com.dongle.group.model.vo.LocalCtg;
import com.dongle.group.model.vo.TopicCtg;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class ServeMainViewServlet
 */
@WebServlet("/serveMainView")
public class ServeMainViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeMainViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Member loginMember=(Member)request.getSession().getAttribute("loginMember");
		GroupService gs= new GroupService();
		List<Group> joinList=gs.selectGroup(loginMember.getMemberId());
		List<EditPickGroup> editList=gs.selectEditGr();
		List<TopicCtg> topicCtg = new GroupService().selectTopicCtg();
		List<LocalCtg> localCtg = new GroupService().selectLocalCtg();
		
		request.setAttribute("list", joinList);
		request.setAttribute("editList", editList);
		request.setAttribute("topicCtg", topicCtg);
		request.setAttribute("localCtg", localCtg);
		RequestDispatcher rd = request.getRequestDispatcher("Dongle_view/main.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
