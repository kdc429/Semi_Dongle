package com.dongle.feed.controller;

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

import com.dongle.feed.model.service.FeedService;
import com.dongle.feed.model.vo.Feed;
import com.dongle.feed.model.vo.FeedNoResult;
import com.dongle.feed.model.vo.FileList;
import com.dongle.group.model.service.GroupService;
import com.dongle.group.model.vo.Group;
import com.dongle.group.model.vo.GroupMember;
import com.dongle.member.model.vo.Member;
import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;

import common.FeedFileRenamePolicy;

/**
 * Servlet implementation class FeedUpdateEndServlet
 */
@WebServlet("/feed/feedUpdateEnd")
public class FeedUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member loginMember=(Member)request.getSession().getAttribute("loginMember");
		int result=0;
		if(!ServletFileUpload.isMultipartContent(request)) {
			new Gson().toJson(result,response.getWriter());
			System.out.println("안올라감");
			return;
		}
		//저장할 디렉토리 설정
		String dir=getServletContext().getRealPath("/images/feed-images");
		// 최대 파일 크기 설정
		int maxSize=1024*1024*1024;
		//파일 업로드 객체 생성
		//filename rename
		MultipartRequest mr=new MultipartRequest(request,dir,maxSize,"UTF-8",new FeedFileRenamePolicy());
		int feedNo=Integer.parseInt(mr.getParameter("feedNo"));
		int groupNo=Integer.parseInt(mr.getParameter("groupNo"));
		String content=(String)mr.getParameter("updateContent");
		List<String> delFileList=new ArrayList();
		for(int i=0;;i++) {
			if((String)mr.getParameter("deleteFileList"+i)!=null) {
				delFileList.add((String)mr.getParameter("deleteFileList"+i));
			}else {
				break;
			}
		}
		String[] fileNums=null;
		if(delFileList!=null) {
			
			fileNums=new String[delFileList.size()];
			for(int i=0;i<fileNums.length;i++) {
				fileNums[i]=delFileList.get(i);
			}
			
		}

		//DB에 저장할 데이터 리스트 
		List<FileList> uploadFileList =new ArrayList();
		FileList fl=null;
		//파일 네임 받아오기(열거)
		Enumeration<String> files=mr.getFileNames();
				
		//피드 컨텐트 부터 업로드 시작
		//feed_file_tab 에 입력할 데이터 리스트에 입력
		int i=0;
		int j=0;
		int n=0;
		
		
		if(fileNums!=null) {
			
			result=new FeedService().updateFeed(feedNo,content,fileNums);
			
		}else {
			result=new FeedService().updateFeedContent(feedNo,content);
			
		}
		
		if(files.hasMoreElements()!=false) {
			
			while(files.hasMoreElements()){
				String fileName=(String)files.nextElement();
				fl=new FileList();
				fl.setFeedNo(feedNo);
				System.out.println(fileName);
				fl.setFeedOriFilePath(fileName); 
				if(fileName.substring(0, 5).equals("image")) {
					fl.setFeedRenameFilePath(mr.getFilesystemName("image"+i));
					System.out.println("00"+fl.getFeedRenameFilePath());
					i++;
				}else if(fileName.substring(0, 5).equals("video")) {
					fl.setFeedRenameFilePath(mr.getFilesystemName("video"+j));
					System.out.println("00"+fl.getFeedRenameFilePath());
					j++;
				}else {
					fl.setFeedRenameFilePath(mr.getFilesystemName("files"+n));
					n++;
				}
				                      
				uploadFileList.add(fl);
			}	
			result=new FeedService().insertFeedFile(feedNo,groupNo, uploadFileList);	
		}
		
		System.out.println(result);
		
		if(result>0) {
			new Gson().toJson(result,response.getWriter());
		}else {
			new Gson().toJson(result,response.getWriter());
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
