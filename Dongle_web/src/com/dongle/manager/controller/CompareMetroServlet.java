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
 * Servlet implementation class CompareMetroServlet
 */
@WebServlet("/manager/compareMetro")
public class CompareMetroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompareMetroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String metroCode = request.getParameter("metroCode");
		
		List<LocalCtg> localCtg = new GroupService().selectLocalCtg();
		
		List<String> areaCodeList = new ArrayList();
		List<String> areaNameList = new ArrayList();
		
		boolean flag = false;
		for(LocalCtg local : localCtg)
		{
			
			if(metroCode.equals(local.getMetroCode()))
			{
				for(int i = 0; i < areaCodeList.size(); i++)
				{
					if(areaCodeList.get(i).equals(local.getAreaCode()))
					{
						flag = true;
						break;
					}
				}
				if(!flag)
				{
					areaCodeList.add(local.getAreaCode());
					areaNameList.add(local.getLocAreaName());
				}
			
				else
				{
					flag = false;
				}
				
				
			}
		}
		
		request.setAttribute("areaCodeList", areaCodeList);
		request.setAttribute("areaNameList", areaNameList);
		
		request.getRequestDispatcher("/views/manager/manager_compareMetro.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
