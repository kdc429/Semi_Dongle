package com.dongle.main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.buf.StringUtils;

import com.dongle.group.model.vo.Group;
import com.dongle.main.model.service.MainService;

/**
 * Servlet implementation class MainSearchEndServlet
 */
@WebServlet("/main/mainSearchEnd")
public class MainSearchEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainSearchEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locArr;
		String topicArr;
		
		

		int minAge;
		int maxAge;
		try {
			String[] locArrData=(String[])request.getParameterValues("locArray");
			locArr=String.join("','",locArrData);
			locArr="'"+locArr+"'";
		}catch(Exception e) {
			
			locArr=null;
			
		}
		
		try {
			String[] topicArrData=(String[])request.getParameterValues("topicArray");
			topicArr=String.join(",",topicArrData);
			
		}catch(Exception e) {
			
			topicArr=null;
			
		}
		
		try {
			
			minAge=Integer.parseInt(request.getParameter("minAge"));

		}catch(NumberFormatException e) {
			
			minAge=0;
			
		}
		
		try {
			
			maxAge=Integer.parseInt(request.getParameter("maxAge"));
			
		}catch(NumberFormatException e) {
			
			maxAge=0;
			
		}
		System.out.println(topicArr);
		System.out.println(locArr);
		String time=(String)request.getParameter("time");
		System.out.println(minAge+"=minAge");
		System.out.println(maxAge);
		if(locArr!=null&&topicArr!=null&&minAge>0&&maxAge>0&&time!=null) {//1
			
			List<Group> groupList=new MainService().selectAllCheckList(locArr,topicArr,minAge,maxAge,time);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr==null&&topicArr!=null&&minAge>0&&maxAge>0&&time!=null) {//2
			
			//locArr==null
			List<Group> groupList=new MainService().selectLocationNullCheckList(topicArr,minAge,maxAge,time);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr==null&&topicArr==null&&minAge>0&&maxAge>0&&time!=null) {//3
			
			//locArr==null && topicArr==null
			List<Group> groupList=new MainService().selectLocTopicNullCheckList(minAge,maxAge,time);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr==null&&topicArr==null&&minAge==0&&maxAge>0&&time!=null) {//4
			
			//locArr==null && topicArr==null && minAge==0
			List<Group> groupList=new MainService().selectLocTopicMinNullCheckList(maxAge,time);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr==null&&topicArr==null&&minAge==0&&maxAge==0&&time!=null) {//5
			
			// locArr==null,topicArr==null,minAge==0,maxAge==0,
			List<Group> groupList=new MainService().selectLocTopicMinMaxNullCheckList(time);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr!=null&&topicArr!=null&&minAge>0&&maxAge>0&&time==null) {//6
			
			// time==null
			List<Group> groupList=new MainService().selectTimeNullCheckList(locArr,topicArr,minAge,maxAge);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr!=null&&topicArr!=null&&minAge>0&&maxAge==0&&time==null) {//7
			// time==null maxAge==0
			List<Group> groupList=new MainService().selectMaxTimeNullCheckList(locArr,topicArr,minAge);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
			
		}else if(locArr!=null&&topicArr!=null&&minAge==0&&maxAge==0&&time==null) {//8
			
			// time==null maxAge==0 minAge==0
			List<Group> groupList=new MainService().selectMinMaxTimeNullCheckList(locArr,topicArr);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr!=null&&topicArr==null&&minAge==0&&maxAge==0&&time==null) {//9
			
			// time==null maxAge==0 minAge==0 topicArr==null
			List<Group> groupList=new MainService().selectTopicMinMaxTimeNullCheckList(locArr);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr!=null&&topicArr!=null&&minAge==0&&maxAge>0&&time==null) {//10
			
			// time==null maxAge==0 topicArr==null
			List<Group> groupList=new MainService().selectMinTimeNullCheckList(locArr,topicArr,maxAge);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr==null&&topicArr!=null&&minAge==0&&maxAge==0&&time==null) {//11
			
			// time==null maxAge==0 topicArr==null
			List<Group> groupList=new MainService().selectTopicCheckList(topicArr);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr==null&&topicArr!=null&&minAge>0&&maxAge==0&&time!=null) {//12
			
			// time==null maxAge==0 locArr==null
			List<Group> groupList=new MainService().selectLocMaxNullCheckList(topicArr,minAge,time);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr==null&&topicArr!=null&&minAge==0&&maxAge==0&&time!=null) {//13
			
			// time==null max,minAge==0 locArr==null
			List<Group> groupList=new MainService().selectTopicTimeCheckList(topicArr,time);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr==null&&topicArr!=null&&minAge>0&&maxAge==0&&time==null) {//14
			
			// time==null max,minAge==0 locArr==null
			List<Group> groupList=new MainService().selectTopicMinCheckList(topicArr,minAge);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr==null&&topicArr!=null&&minAge>0&&maxAge>0&&time==null) {//15
			
			// time==null locArr==null
			List<Group> groupList=new MainService().selectLocTimeNullCheckList(topicArr,minAge,maxAge);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr==null&&topicArr==null&&minAge>0&&maxAge>0&&time==null) {//16
			
			// time==null locArr==null topic==null
			List<Group> groupList=new MainService().selectMinMaxCheckList(minAge,maxAge);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr==null&&topicArr==null&&minAge>0&&maxAge==0&&time==null) {//17
			
			// time==null locArr==null topic==null max=null
			List<Group> groupList=new MainService().selectMinCheckList(minAge);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr==null&&topicArr==null&&minAge>0&&maxAge==0&&time!=null) {//18
			
			// time==null locArr==null topic==null max=null
			List<Group> groupList=new MainService().selectMinTimeCheckList(minAge,time);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr==null&&topicArr==null&&minAge==0&&maxAge>0&&time==null) {//19
			
			// time==null locArr==null topic==null max=null
			List<Group> groupList=new MainService().selectMaxCheckList(maxAge);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr==null&&topicArr!=null&&minAge==0&&maxAge>0&&time==null) {//20
			
			
			List<Group> groupList=new MainService().selectTopicMaxCheckList(topicArr,maxAge);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr!=null&&topicArr==null&&minAge>0&&maxAge>0&&time==null) {//21
			
			
			List<Group> groupList=new MainService().selectTopicTimeNullCheckList(locArr,minAge,maxAge);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr!=null&&topicArr==null&&minAge==0&&maxAge==0&&time!=null) {//22
			
			
			List<Group> groupList=new MainService().selectLocTimeCheckList(locArr,time);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr!=null&&topicArr==null&&minAge==0&&maxAge>0&&time!=null) {//23
			
			
			List<Group> groupList=new MainService().selectTopicMinNullCheckList(locArr,maxAge,time);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr!=null&&topicArr!=null&&minAge==0&&maxAge>0&&time!=null) {//24
			
			
			List<Group> groupList=new MainService().selectMinNullCheckList(locArr,topicArr,maxAge,time);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr!=null&&topicArr==null&&minAge>0&&maxAge>0&&time!=null) {//25
			
			
			List<Group> groupList=new MainService().selectTopicNullCheckList(locArr,minAge,maxAge,time);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr!=null&&topicArr==null&&minAge>0&&maxAge==0&&time!=null) {//26
			
			
			List<Group> groupList=new MainService().selectTopicMaxNullCheckList(locArr,minAge,time);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr!=null&&topicArr!=null&&minAge==0&&maxAge==0&&time!=null) {//27
			
			
			List<Group> groupList=new MainService().selectMinMaxNullCheckList(locArr,topicArr,time);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr!=null&&topicArr==null&&minAge>0&&maxAge==0&&time==null) {//28
			
			
			List<Group> groupList=new MainService().selectLocMinCheckList(locArr,minAge);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr!=null&&topicArr==null&&minAge==0&&maxAge>0&&time==null) {//29
			
			
			List<Group> groupList=new MainService().selectLocMaxCheckList(locArr,maxAge);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr!=null&&topicArr!=null&&minAge!=0&&maxAge==0&&time!=null) {//30
			
			
			List<Group> groupList=new MainService().selectMaxNullCheckList(locArr,topicArr,minAge,time);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}else if(locArr==null&&topicArr!=null&&minAge==0&&maxAge>=0&&time!=null) {//31
			
			
			List<Group> groupList=new MainService().selectLocMinNullCheckList(topicArr,maxAge,time);
			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
