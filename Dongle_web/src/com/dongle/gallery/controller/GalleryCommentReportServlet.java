package com.dongle.gallery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.gallery.model.service.GalleryService;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class GalleryCommentReportServlet
 */
@WebServlet("/gallery/galleryCommentReport")
public class GalleryCommentReportServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GalleryCommentReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Member loginMember=(Member)request.getSession().getAttribute("loginMember");
      int galNo=Integer.parseInt(request.getParameter("galNo"));
      int groupNo=Integer.parseInt(request.getParameter("groupNo"));
      int memberNo=Integer.parseInt(request.getParameter("memberNo"));
      int galCommentNo=Integer.parseInt(request.getParameter("galCommentNo"));
      int galCommentLevel=Integer.parseInt(request.getParameter("galCommentLevel"));
      String reportCode=(String)request.getParameter("reportCode");                                               
      System.out.println("galCommentNo: "+galCommentNo);
      
      int rs = new GalleryService().insertReport(groupNo,memberNo,reportCode);
      if(rs!=0) {
         int result = new GalleryService().updateGalleryCommentReport(groupNo,galNo,galCommentNo);
         if(galCommentLevel==1) {
            int result2 = new GalleryService().updateGalleryCommentReport2(groupNo, galNo, galCommentNo);
         }
          if(result!=0)
            {
               response.setContentType("text/csv;charset=UTF-8");
               response.getWriter().println("신고 완료 하였습니다.");
            }
            else {
               response.setContentType("text/csv;charset=UTF-8");
               response.getWriter().println("신고 실패하였습니다. 다시 시도해주세요.");
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