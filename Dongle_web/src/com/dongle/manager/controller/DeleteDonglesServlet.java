package com.dongle.manager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.manager.model.service.ManagerService;

/**
 * Servlet implementation class DeleteDonglesServlet
 */
@WebServlet("/manager/deleteDongle")
public class DeleteDonglesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDonglesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String checkPwd = request.getParameter("checkPwd");
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		String memeberPwd = request.getParameter("memberPwd");
		System.out.println("groupNo : " + groupNo);
		int result = new ManagerService().deleteDongle(groupNo);
		
		if(result > 0)
		{
			request.setAttribute("loc", "/Dongle_view/main.jsp");
			request.setAttribute("msg", "동글 삭제를 성공하였습니다.");
			
		}
		else
		{
			request.setAttribute("loc", "/Dongle_view/main.jsp");
			request.setAttribute("msg", "동글 삭제를 실패하였습니다.");
		}
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
