package com.dongle.gallery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.gallery.model.service.GalleryService;
import com.dongle.gallery.model.vo.GalleryCommentJoin;

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
		
		int rs=new GalleryService().insertGalComment(gcj);
		
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		if(rs!=0)
		{
			msg="댓글을 등록하였습니다.";
			loc="/gallery/galleryAllList?groupNo="+groupNo+"&albumCode="+albumCode+"&galNo="+galNo+"&galFileNo="+galFileNo;
		}
		else {
			msg="댓글 등록에 실패하였습니다. 다시 시도해주세요.";
			loc="/gallery/galleryAllList?groupNo="+groupNo+"&albumCode="+albumCode+"&galNo="+galNo+"&galFileNo="+galFileNo;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
