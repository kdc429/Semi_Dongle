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
import com.dongle.group.model.vo.MultiLocation;
import com.dongle.group.model.vo.MultiLocationName;
import com.dongle.group.model.vo.MultiTopic;
import com.dongle.group.model.vo.MultiTopicName;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String locArr; // 로케이션
		String topicArr; // 토픽
		String time; // 시간
		String keyword; // 검색 키워드
		String sortCheck; // 정렬조건
		String[] groupNums = null; // 정렬용 그룹 넘버
		String groupNo; // 정렬용 그룹넘버 조인
		int minAge; // 최소 연령
		int maxAge; // 최대 연령
		int totalGroup;
		int totalPage;
		try {
			String[] locArrData = (String[]) request.getParameterValues("locArray");
			locArr = String.join("','", locArrData);
			locArr = "'" + locArr + "'";
		} catch (Exception e) {

			locArr = null;

		}

		try {
			String[] topicArrData = (String[]) request.getParameterValues("topicArray");
			topicArr = String.join(",", topicArrData);

		} catch (Exception e) {

			topicArr = null;

		}

		try {

			minAge = Integer.parseInt(request.getParameter("minAge"));

		} catch (NumberFormatException e) {

			minAge = 0;

		}

		try {

			maxAge = Integer.parseInt(request.getParameter("maxAge"));

		} catch (NumberFormatException e) {

			maxAge = 0;

		}
		try {

			keyword = (String) request.getParameter("keyword");

		} catch (Exception e) {

			keyword = null;
		}

		try {

			time = (String) request.getParameter("time");

		} catch (Exception e) {

			time = null;

		}

		try {

			sortCheck = (String) request.getParameter("sortCheck");

		} catch (Exception e) {

			sortCheck = "date";

		}

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

		System.out.println(topicArr);
		System.out.println(locArr);
		System.out.println(minAge + "=minAge");
		System.out.println(maxAge);
		System.out.println(keyword);
		if (keyword != null) {

			List<Group> groupList = new MainService().selectKeywordCheckList(keyword);

			if (groupList.size() == 0) {

				request.setAttribute("groupList", groupList);
				request.setAttribute("cPage", cPage);
				request.setAttribute("pageBar", barPage);
				request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
				return;

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
			

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
				
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr != null && topicArr != null && minAge > 0 && maxAge > 0 && time != null) {// 1

			List<Group> groupList = new MainService().selectAllCheckList(locArr, topicArr, minAge, maxAge, time);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr == null && topicArr != null && minAge > 0 && maxAge > 0 && time != null) {// 2

			// locArr==null
			List<Group> groupList = new MainService().selectLocationNullCheckList(topicArr, minAge, maxAge, time);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr == null && topicArr == null && minAge > 0 && maxAge > 0 && time != null) {// 3

			// locArr==null && topicArr==null
			List<Group> groupList = new MainService().selectLocTopicNullCheckList(minAge, maxAge, time);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr == null && topicArr == null && minAge == 0 && maxAge > 0 && time != null) {// 4

			// locArr==null && topicArr==null && minAge==0
			List<Group> groupList = new MainService().selectLocTopicMinNullCheckList(maxAge, time);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr == null && topicArr == null && minAge == 0 && maxAge == 0 && time != null) {// 5

			// locArr==null,topicArr==null,minAge==0,maxAge==0,
			List<Group> groupList = new MainService().selectLocTopicMinMaxNullCheckList(time);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr != null && topicArr != null && minAge > 0 && maxAge > 0 && time == null) {// 6

			// time==null
			List<Group> groupList = new MainService().selectTimeNullCheckList(locArr, topicArr, minAge, maxAge);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr != null && topicArr != null && minAge > 0 && maxAge == 0 && time == null) {// 7
			// time==null maxAge==0
			List<Group> groupList = new MainService().selectMaxTimeNullCheckList(locArr, topicArr, minAge);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr != null && topicArr != null && minAge == 0 && maxAge == 0 && time == null) {// 8

			// time==null maxAge==0 minAge==0
			List<Group> groupList = new MainService().selectMinMaxTimeNullCheckList(locArr, topicArr);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr != null && topicArr == null && minAge == 0 && maxAge == 0 && time == null) {// 9

			// time==null maxAge==0 minAge==0 topicArr==null
			List<Group> groupList = new MainService().selectTopicMinMaxTimeNullCheckList(locArr);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr != null && topicArr != null && minAge == 0 && maxAge > 0 && time == null) {// 10

			// time==null maxAge==0 topicArr==null
			List<Group> groupList = new MainService().selectMinTimeNullCheckList(locArr, topicArr, maxAge);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr == null && topicArr != null && minAge == 0 && maxAge == 0 && time == null) {// 11

			// time==null maxAge==0 topicArr==null
			List<Group> groupList = new MainService().selectTopicCheckList(topicArr);
			
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

			if ("date".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo="0"+"";
					
				}
				
				groupList = new MainService().selectGroupSortDate(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				System.out.println(groupNo);
				if(groupNo.equals("")) {
					groupNo="0"+"";
					
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);
			

		} else if (locArr == null && topicArr != null && minAge > 0 && maxAge == 0 && time != null) {// 12

			// time==null maxAge==0 locArr==null
			List<Group> groupList = new MainService().selectLocMaxNullCheckList(topicArr, minAge, time);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr == null && topicArr != null && minAge == 0 && maxAge == 0 && time != null) {// 13

			// time==null max,minAge==0 locArr==null
			List<Group> groupList = new MainService().selectTopicTimeCheckList(topicArr, time);
			
			if (groupList.size() == 0) {

				request.setAttribute("groupList", groupList);
				request.setAttribute("cPage", cPage);
				request.setAttribute("pageBar", barPage);
				request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);


			}
			
			totalGroup = groupList.size();
			totalPage = (int) Math.ceil((double) totalGroup / numPerPage);
			if (pageNo == 1) {
				barPage += "<span class='page-bar'>[이전]</span>";
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr == null && topicArr != null && minAge > 0 && maxAge == 0 && time == null) {// 14

			// time==null max,minAge==0 locArr==null
			List<Group> groupList = new MainService().selectTopicMinCheckList(topicArr, minAge);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr == null && topicArr != null && minAge > 0 && maxAge > 0 && time == null) {// 15

			// time==null locArr==null
			List<Group> groupList = new MainService().selectLocTimeNullCheckList(topicArr, minAge, maxAge);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr == null && topicArr == null && minAge > 0 && maxAge > 0 && time == null) {// 16

			// time==null locArr==null topic==null
			List<Group> groupList = new MainService().selectMinMaxCheckList(minAge, maxAge);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr == null && topicArr == null && minAge > 0 && maxAge == 0 && time == null) {// 17

			// time==null locArr==null topic==null max=null
			List<Group> groupList = new MainService().selectMinCheckList(minAge);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr == null && topicArr == null && minAge > 0 && maxAge == 0 && time != null) {// 18

			// time==null locArr==null topic==null max=null
			List<Group> groupList = new MainService().selectMinTimeCheckList(minAge, time);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr == null && topicArr == null && minAge == 0 && maxAge > 0 && time == null) {// 19

			// time==null locArr==null topic==null max=null
			List<Group> groupList = new MainService().selectMaxCheckList(maxAge);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr == null && topicArr != null && minAge == 0 && maxAge > 0 && time == null) {// 20

			List<Group> groupList = new MainService().selectTopicMaxCheckList(topicArr, maxAge);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr != null && topicArr == null && minAge > 0 && maxAge > 0 && time == null) {// 21

			List<Group> groupList = new MainService().selectTopicTimeNullCheckList(locArr, minAge, maxAge);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr != null && topicArr == null && minAge == 0 && maxAge == 0 && time != null) {// 22

			List<Group> groupList = new MainService().selectLocTimeCheckList(locArr, time);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr != null && topicArr == null && minAge == 0 && maxAge > 0 && time != null) {// 23

			List<Group> groupList = new MainService().selectTopicMinNullCheckList(locArr, maxAge, time);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr != null && topicArr != null && minAge == 0 && maxAge > 0 && time != null) {// 24

			List<Group> groupList = new MainService().selectMinNullCheckList(locArr, topicArr, maxAge, time);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr != null && topicArr == null && minAge > 0 && maxAge > 0 && time != null) {// 25

			List<Group> groupList = new MainService().selectTopicNullCheckList(locArr, minAge, maxAge, time);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr != null && topicArr == null && minAge > 0 && maxAge == 0 && time != null) {// 26

			List<Group> groupList = new MainService().selectTopicMaxNullCheckList(locArr, minAge, time);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr != null && topicArr != null && minAge == 0 && maxAge == 0 && time != null) {// 27

			List<Group> groupList = new MainService().selectMinMaxNullCheckList(locArr, topicArr, time);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr != null && topicArr == null && minAge > 0 && maxAge == 0 && time == null) {// 28

			List<Group> groupList = new MainService().selectLocMinCheckList(locArr, minAge);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
			
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr != null && topicArr == null && minAge == 0 && maxAge > 0 && time == null) {// 29

			List<Group> groupList = new MainService().selectLocMaxCheckList(locArr, maxAge);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
			
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr != null && topicArr != null && minAge != 0 && maxAge == 0 && time != null) {// 30

			List<Group> groupList = new MainService().selectMaxNullCheckList(locArr, topicArr, minAge, time);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr == null && topicArr != null && minAge == 0 && maxAge > 0 && time != null) {// 31

			List<Group> groupList = new MainService().selectLocMinNullCheckList(topicArr, maxAge, time);
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				if(groupList.size()==0) {
					return;
				}
			}
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		} else if (locArr == null && topicArr == null && minAge == 0 && maxAge == 0 && time == null) {

			List<Group> groupList = new MainService().selectAllNullCheckList();
			
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

			if ("date".equals(sortCheck)) {
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
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
					
				}
				if(groupList.size()==0) {
					return;
				}
				
			} else if ("memberCnt".equals(sortCheck)) {
				groupNums = new String[groupList.size()];

				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				groupList = new MainService().selectGroupSortMemberCnt(groupNo, cPage, numPerPage);
				
				groupNums=new String[groupList.size()];
				for (int i = 0; i < groupNums.length; i++) {
					groupNums[i] = groupList.get(i).getGroupNo() + "";
				}
				groupNo = String.join(",", groupNums);
				
				
				if(groupNo.equals("")) {
					groupNo=0+"";
				}
				
				if(groupNo!=null) {
					List<MultiLocationName> locationList=new GroupService().selectSearchLocation(groupNo);
					List<MultiTopicName> topicList=new GroupService().selectSearchTopic(groupNo);
					List<GroupMemberCount> memberCountList=new GroupService().selectMemberCount(groupNo);
					request.setAttribute("memberCountList", memberCountList);
					request.setAttribute("locationList", locationList);
					request.setAttribute("topicList", topicList);
				}
				if(groupList.size()==0) {
					return;
				}
				
			}
			System.out.println(groupList.size());
			System.out.println(barPage);
			request.setAttribute("groupList", groupList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("pageBar", barPage);
			request.getRequestDispatcher("/Dongle_view/searchDongle.jsp").forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
