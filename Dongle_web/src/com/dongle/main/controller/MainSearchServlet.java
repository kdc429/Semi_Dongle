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
import com.dongle.group.model.vo.GroupMemberCount;
import com.dongle.group.model.vo.MultiLocationName;
import com.dongle.group.model.vo.MultiTopicName;
import com.dongle.main.model.service.MainService;
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
		
		String[] groupNums = null; // 정렬용 그룹 넘버
		String groupNo; // 정렬용 그룹넘버 조인
		
		int totalGroup;
		int totalPage;
		

		int cPage;

		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		int numPerPage;
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch (NumberFormatException e) {
			numPerPage = 10;
		}

		String barPage = "";
		int pageSize = 5;
		int pageNo = ((cPage - 1) / pageSize) * pageSize + 1;
		int pageEnd = pageNo + pageSize - 1;
		
		List<Group> groupList=new GroupService().selectAllGroupList();
		List<TopicCategory> topicList=new MainService().selectTopicCategory();
		
		if (groupList.size() == 0) {

			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);


		}
		
		totalGroup = groupList.size();
		totalPage = (int) Math.ceil((double) totalGroup / numPerPage);
		if (pageNo == 1) {
			barPage += "<span>[이전]</span>";
		} else {
			barPage += "<li class='page-bar'><input type='hidden' value='" + (pageNo - 1) + "'>[이전]</li>";
		}

		while (!(pageNo > pageEnd || pageNo > totalPage)) {
			if (cPage == pageNo) {
				barPage += "<span class='cPage'><input type='hidden' value='" + pageNo + "'>" + pageNo + "</span>";
			} else {
				barPage += "<li class='page-bar'><input type='hidden' value='" + (pageNo) + "'>" + pageNo
						+ "</li>";
			}
			pageNo++;
		}

		// [다음] 구현

		if (pageNo > totalPage) {
			barPage += "<span>[다음]</span>";
		} else {
			barPage += "<li class='page-bar'><input type='hidden' value='" + (pageNo) + "'>" + pageNo + "</li>";
		}
		
		groupNums = new String[groupList.size()];

		for (int i = 0; i < groupNums.length; i++) {
			groupNums[i] = groupList.get(i).getGroupNo() + "";
		}
		groupNo = String.join(",", groupNums);
		
		if(groupNo.equals("")) {
			groupNo=0+"";
		}
		
		groupList = new MainService().selectGroupSortDate(groupNo, cPage, numPerPage);
		
		groupNums=new String[groupList.size()];
		for (int i = 0; i < groupNums.length; i++) {
			groupNums[i] = groupList.get(i).getGroupNo() + "";
		}
		groupNo = String.join(",", groupNums);
		
		if(groupNo.equals("")) {
			groupNo=0+"";
		}
		
		if(groupList.size()==0) {
			
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("topicList", topicList);
			request.getRequestDispatcher("/Dongle_view/search.jsp").forward(request, response);
			
			return;
		}
		
		List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
		List<MultiTopicName> topicList2=new GroupService().selectSearchTopic(groupNo);
		List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
		request.setAttribute("memberCountList", memberCountList);
		request.setAttribute("locationList", locationList);
		request.setAttribute("topicList2", topicList2);
		request.setAttribute("cPage", cPage);
		request.setAttribute("pageBar", barPage);
		request.setAttribute("groupList", groupList);
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
