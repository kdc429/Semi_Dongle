package com.dongle.manager.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.group.model.service.GroupService;
import com.dongle.group.model.vo.LocalCtg;

/**
 * Servlet implementation class CompareAreaServlet
 */
@WebServlet("/manager/compareArea")
public class CompareAreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompareAreaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String areaCode = request.getParameter("areaCode");
		
		List<LocalCtg> localCtg = new GroupService().selectLocalCtg();
		
		List<String> townCodeList = new ArrayList();
		List<String> townNameList = new ArrayList();
		
		for(LocalCtg local : localCtg)
		{
			if(areaCode.equals(local.getAreaCode()))
			{
				townCodeList.add(local.getTownCode());
				townNameList.add(local.getLocTownName());
				
			}
		}
		
		request.setAttribute("townCodeList", townCodeList);
		request.setAttribute("townNameList", townNameList);
		
		request.getRequestDispatcher("/views/manager/manager_compareArea.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
