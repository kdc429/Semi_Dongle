package com.dongle.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.dongle.admin.model.service.AdminService;
import com.dongle.group.model.vo.EditPickGroup;
import com.oreilly.servlet.MultipartRequest;

import common.EditFileRenamePolicy;

/**
 * Servlet implementation class EditPickUpdateSerlvet
 */
@WebServlet("/admin/editPickUpdate")
public class EditPickUpdateSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPickUpdateSerlvet() {
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
		String saveDir=root+"upload"+File.separator+"editPick"; 
		int maxSize=1024*1024*10;
		MultipartRequest mr = new MultipartRequest(request,saveDir,maxSize,"UTF-8",new EditFileRenamePolicy());
		
		int groupNo=Integer.parseInt(mr.getParameter("groupNo"));
		String editContent = mr.getParameter("editContext");
		int newGroupNo=0;
		if(mr.getParameter("newGroupNo")!=null)
		{newGroupNo=Integer.parseInt(mr.getParameter("newGroupNo"));}
		EditPickGroup ep = new EditPickGroup();
		ep.setGroupNo(groupNo);
		ep.setEditContent(editContent);
		

		
		if(newGroupNo==0) {
			if(mr.getFileNames()!=null) {
				System.out.println(mr.getParameter("image_0"));
				Enumeration<String> files = mr.getFileNames();
				String name = (String)files.nextElement();
				ep.setEditOldFilePath(mr.getOriginalFileName(name));
				ep.setEditNewFilePath(mr.getFilesystemName(name));
				
				int rs = new AdminService().editPickUpdate(ep);
				
				if(rs!=0)
				{
					System.out.println("에디터 픽 변경 성공1");
					response.setContentType("text/csv;charset=UTF-8");
					response.getWriter().println("에디터 픽 변경 성공");
				}
				else {
					System.out.println("에디터 픽 변경 실패1, 다시 시도해주세요.");
					response.setContentType("text/csv;charset=UTF-8");
					response.getWriter().println("에디터 픽 변경 실패, 다시 시도해주세요.");
				}
			}
			else
			{
				int rs = new AdminService().editPickUpdateContent(ep);
				
				if(rs!=0)
				{
					System.out.println("에디터 픽 변경 성공2");
					response.setContentType("text/csv;charset=UTF-8");
					response.getWriter().println("에디터 픽 변경 성공");
				}
				else {
					System.out.println("에디터 픽 변경 실패2, 다시 시도해주세요.");
					response.setContentType("text/csv;charset=UTF-8");
					response.getWriter().println("에디터 픽 변경 실패, 다시 시도해주세요.");
				}
				
			}
		}
			
		else
		{
			Enumeration<String> files = mr.getFileNames();
			String name = (String)files.nextElement();
			ep.setEditOldFilePath(mr.getOriginalFileName(name));
			ep.setEditNewFilePath(mr.getFilesystemName(name));
			
			int rs = new AdminService().editPickChange(ep,newGroupNo);
			
			if(rs!=0)
			{
				System.out.println("새로운 에디터 픽 등록 성공");
				response.getWriter().println("새로운 에디터 픽 등록 성공");
				response.setContentType("text/csv;charset=UTF-8");
			}
			else {
				System.out.println("새로운 에디터 픽 등록 실패, 다시 시도해주세요.");
				response.getWriter().println("새로운 에디터 픽 등록 실패, 다시 시도해주세요.");
				response.setContentType("text/csv;charset=UTF-8");
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
