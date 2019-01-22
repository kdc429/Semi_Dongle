package com.dongle.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.admin.model.service.AdminService;
import com.dongle.group.model.vo.ListGroup;

/**
 * Servlet implementation class SortDongleList
 */
@WebServlet("/admin/sortDongleList")
public class SortDongleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SortDongleListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<ListGroup> dongleList = null;
		String flag = request.getParameter("flag");
		String rowValue = request.getParameter("rowValue");
		String searchType=request.getParameter("dongle-searchType");
		String searchKeyword=request.getParameter("searchKeyword");
		
		if(searchType == null)
		{
			searchType = "";
		}
		if(searchKeyword == null)
		{
			searchKeyword = "";
		}
		switch(rowValue)
		{
		case "dongleName": dongleList = new AdminService().sortDongleName(searchType, searchKeyword, flag);break;
		case "managerId" : dongleList = new AdminService().sortManagerId(searchType, searchKeyword, flag);break;
		case "topic" : dongleList = new AdminService().sortTopic(searchType, searchKeyword, flag);break;
		case "metro" : dongleList = new AdminService().sortMetro(searchType, searchKeyword, flag);break;
		case "date" : dongleList = new AdminService().sortDate(searchType, searchKeyword, flag);break;
		case "minAge" : dongleList = new AdminService().sortMinAge(searchType, searchKeyword, flag);break;
		case "maxAge" : dongleList = new AdminService().sortMaxAge(searchType, searchKeyword, flag);break;
		case "enrollDate" : dongleList = new AdminService().sortDongleEnrollDate(searchType, searchKeyword, flag);break;
		case "reportCnt" : dongleList = new AdminService().sortDongleReportCnt(searchType, searchKeyword, flag);break;
		
		}
		
		request.setAttribute("dongleList", dongleList);
		System.out.println("서치타입 : " + searchType);
		if(searchType.equals(""))
			request.getRequestDispatcher("/Dongle_view/admin_dongleList.jsp").forward(request, response);
		else
			request.getRequestDispatcher("/Dongle_view/admin_dongleSearch.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
