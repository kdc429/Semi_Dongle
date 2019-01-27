package com.dongle.main.controller;

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
import com.dongle.main.model.vo.TopicCategory;

/**
 * Servlet implementation class MainSearchServlet
 */
@WebServlet("/main/mainSearch")
public class MainSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Group> groupList=new GroupService().selectAllGroupList();
		List<LocationCategory> locationList=new MainService().selectMetroCode();
		List<TopicCategory> topicList=new MainService().selectTopicCategory();
		request.setAttribute("groupList", groupList);
		request.setAttribute("metroCodeList", locationList);
		request.setAttribute("topicList", topicList);
		request.getRequestDispatcher("/Dongle_view/search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
