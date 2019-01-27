package com.dongle.main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.main.model.service.MainService;
import com.dongle.main.model.vo.LocationCategory;

/**
 * Servlet implementation class MainLocationSearchServlet
 */
@WebServlet("/main/mainLocationSearch")
public class MainLocationSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainLocationSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locationCode=(String)request.getParameter("locationCode");
		
		List<LocationCategory> locationList=new MainService().selectLocationList(locationCode);
		request.setAttribute("locationList", locationList);
		request.getRequestDispatcher("/Dongle_view/searchLocationCategory.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
