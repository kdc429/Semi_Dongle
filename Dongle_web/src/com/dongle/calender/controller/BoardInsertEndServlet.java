package com.dongle.calender.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.dongle.calender.model.service.BoardService;
import com.dongle.calender.model.vo.Board;
import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamePolicy;

/**
 * Servlet implementation class BoardInsertEndServlet
 */
@WebServlet("/board/boardFormEnd")
public class BoardInsertEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!ServletFileUpload.isMultipartContent(request))
		{
			request.setAttribute("msg", "게시판등록오류[B-001]");
			request.setAttribute("loc", "/board/boardList");
			request.getRequestDispatcher("/Dongle_view/msg.jsp").forward(request, response);
			return;
		}
		//파일 기본경로
		String root=getServletContext().getRealPath("/");
		//파일경로를 설정할때 구분자!
		/*String saveDir=root+"upload/board";*/
		String saveDir=root+"upload"+File.separator+"board";
		
		//파일 크기설정
		int maxSize=1024*1024*10;//10MB
		
		
		//파일 업로드 객체 생성
		//filname rename
		MultipartRequest mr=new MultipartRequest(request, 
				saveDir,
				maxSize,
				"UTF-8",
				new MyFileRenamePolicy());
		
		
		
		Board b=new Board();
		
		b.setCaldate_d((mr.getParameter("today1")+" "+mr.getParameter("today2")+":00"));
		b.setCaltitle(mr.getParameter("caltitle"));
		b.setCalcontent(mr.getParameter("calcontent"));
		//cost에 관한거 아직 안 만듬.
		b.setCalorfile(mr.getOriginalFileName("upfile"));
		b.setRefile(mr.getFilesystemName("upfile"));
		
		System.out.println(b);
	
		int result=new BoardService().insertBoard(b);
		
		//응답처리
		String msg="";
		String loc="";
		String view="/Dongle_view/msg.jsp";
		
		if(result>0)
		{
			msg="게시판 등록성공";
			loc="/board/boardList";
		}
		else 
		{
			msg="게시판 등록 실패";
			loc="/Dongle_Community_view/boardInsert";
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
