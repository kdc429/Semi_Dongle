package com.dongle.gallery.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.dongle.gallery.model.service.GalleryService;
import com.dongle.gallery.model.vo.GalleryPath;
import com.dongle.member.model.vo.Member;
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
		int groupNo = Integer.parseInt(mr.getParameter("groupNo"));
		int memberNo = Integer.parseInt(mr.getParameter("memberNo"));
		String albumCode = mr.getParameter("albumCode");
		int imageCount = Integer.parseInt(mr.getParameter("image_count"));
		List galNoList= new GalleryService().distictGalNoList(albumCode, groupNo);
		int galNo = Integer.parseInt(galNoList.get(0).toString())+1;
		String galFileContent = mr.getParameter("galFileContent");
		System.out.println(groupNo+" : "+memberNo+" : "+albumCode+" : "+imageCount+" : "+galNo);
		
		GalleryPath gp = new GalleryPath();
		gp.setGroupNo(groupNo);
		gp.setMemberNo(memberNo);
		gp.setAlbumCode(albumCode);
		gp.setGalNo(galNo);
		gp.setGalFileContent(galFileContent);
		if(imageCount==1){gp.setGalMultiStatus("N");}
		else {gp.setGalMultiStatus("Y");}
		
		ArrayList<String> oldFileName = new ArrayList<String>();
		ArrayList<String> newFileName = new ArrayList<String>();
		try {
			Enumeration<String> files = mr.getFileNames();
			while(files.hasMoreElements()){
				String name = (String)files.nextElement();
				oldFileName.add(mr.getOriginalFileName(name));
				newFileName.add(mr.getFilesystemName(name));
			}
			for(int i=0;i<oldFileName.size();i++)
			{
				System.out.println(oldFileName.get(i));
				System.out.println(newFileName.get(i));
			}

		}catch(Exception e)
		{
			// 예외처리
			e.printStackTrace();
		}
		
		int rs = new GalleryService().insertGallery(gp,oldFileName,newFileName,imageCount);
		if(rs!=0)
		{
			System.out.println("파일 업로드 성공");
			response.setContentType("text/csv;charset=UTF-8");
			response.getWriter().println("업로드 성공");
		}
		else {
			System.out.println("파일 업로드 실패, 다시 시도해주세요.");
			response.setContentType("text/csv;charset=UTF-8");
			response.getWriter().println("업로드 실패");
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
