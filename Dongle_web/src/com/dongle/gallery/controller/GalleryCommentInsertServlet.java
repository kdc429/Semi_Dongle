package com.dongle.gallery.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.gallery.model.service.GalleryService;
import com.dongle.gallery.model.vo.GalleryCommentJoin;
import com.dongle.gallery.model.vo.GalleryPath;
import com.dongle.group.model.service.GroupService;
import com.dongle.group.model.vo.Group;
import com.dongle.member.model.vo.Member;
import com.dongle.member.model.vo.ReportReason;
import com.google.gson.Gson;

/**
 * Servlet implementation class GalleryCommentInsertServlet
 */
@WebServlet("/gallery/commentInsert")
public class GalleryCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GalleryCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int groupNo=Integer.parseInt(request.getParameter("groupNo"));
		int galNo=Integer.parseInt(request.getParameter("galNo"));
		int galCommentWriterNo=Integer.parseInt(request.getParameter("galCommentWriterNo"));
		String galCommentContent = request.getParameter("galCommentContent");
		int galCommentLevel = Integer.parseInt(request.getParameter("galCommentLevel"));
		int galCommentRef = Integer.parseInt(request.getParameter("galCommentRef"));
		int galFileNo=Integer.parseInt(request.getParameter("galFileNo"));
		String albumCode=request.getParameter("albumCode");
		
		GalleryCommentJoin gcj =  new GalleryCommentJoin();
		gcj.setGroupNo(groupNo);
		gcj.setGalNo(galNo);
		gcj.setGalCommentContent(galCommentContent);
		gcj.setGalCommentLevel(galCommentLevel);;
		gcj.setGalFileNo(galFileNo);
		gcj.setMemberNo(galCommentWriterNo);
		gcj.setGalCommentRef(galCommentRef);
		
		System.out.println("gcj는 "+gcj);
		Group g = new GroupService().selectGrInfo(groupNo);
		
		//신고 카테고리 뽑아오기
		List<ReportReason> relist = new GalleryService().selectReportReason();	
		int rs=new GalleryService().insertGalComment(gcj);
		if(rs!=0)
		{
			List<GalleryPath> gplist = new GalleryService().selectOneList(groupNo,galNo,albumCode);
			if(gplist!=null) {
				//갤러리 해당 댓글 뽑아오기
				List<GalleryCommentJoin> gclist = new GalleryService().selectGalCommentList(groupNo,galFileNo,galNo);
				if(gclist!=null) {
					request.setAttribute("gclist", gclist);
					System.out.println("2코멘트gplist: "+gplist);
					System.out.println("2코멘트gclist: "+gclist);
				}
				request.setAttribute("relist", relist);
				request.setAttribute("gplist", gplist);
				request.setAttribute("groupNo", groupNo);
		        request.setAttribute("g", g);
				request.getRequestDispatcher("/views/gallery/commentInsert.jsp").forward(request, response);
			}
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
