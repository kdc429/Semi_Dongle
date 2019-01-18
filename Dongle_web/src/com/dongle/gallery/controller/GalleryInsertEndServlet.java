package com.dongle.gallery.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import common.GalleryFileRenamePolicy;

/**
 * Servlet implementation class GalleryInsertEndServlet
 */
@WebServlet("/gallery/insertGalleryEnd")
public class GalleryInsertEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GalleryInsertEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("들어옵니다");
		
		if(!ServletFileUpload.isMultipartContent(request))
		{
			response.sendRedirect("/index.jsp");
			return;
		}
		//파일 기본경로
		String root = getServletContext().getRealPath("/");
		//파일 경로를 설정할때 구분자! -> / 로 구분할 경우 운영체제에 따라 구분하지 못 할 경우가 있음(window는 상관없지만 간혹 다른 운영체제에서 안됨)
		
		/*String saveDir=root+"upload/board";*/
		String saveDir=root+"upload"+File.separator+"gallery"; //이렇게 기록하면 운영체제에 상관없이 가능
		//파일의 최대 크기 설정
		int maxSize=1024*1024*10;//10MB
		//파일 업로드 객체 생성
		//filename rename 
		MultipartRequest mr = new MultipartRequest(request,saveDir,maxSize,"UTF-8",new GalleryFileRenamePolicy());
		
		//DB에 저장하여면 비지니스로직처리하면 됨
		
		ArrayList<String> filename = new ArrayList<String>();
		try {
		Enumeration<String> files = mr.getFileNames();
			while(files.hasMoreElements()){
				String name = (String)files.nextElement();
				filename.add(mr.getOriginalFileName(name));
				System.out.println(filename);
			}
			for(int i=0;i<filename.size();i++)
			{
				System.out.println(filename.get(i));
			}
			

		}catch(Exception e){
		// 예외처리
		e.printStackTrace();
		}
		
		System.out.println("파일 업로드 성공");
		response.getWriter().println("업로드 성공");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
