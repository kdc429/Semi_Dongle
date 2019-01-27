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
import com.google.gson.Gson;

/**
 * Servlet implementation class MainLocationSerachServlet
 */
@WebServlet("/main/mainLocationSearch2")
public class MainAreaSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainAreaSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locationCode=(String)request.getParameter("locationCode");
		
		List<LocationCategory> locationList=new MainService().selectAreaList(locationCode);
		request.setAttribute("locationList", locationList);
		response.setContentType("application/json;charset=UTF-8");
		new Gson().toJson(locationList,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
