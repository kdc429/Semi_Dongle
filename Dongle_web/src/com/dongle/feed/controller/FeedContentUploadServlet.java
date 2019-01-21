package com.dongle.feed.controller;

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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.dongle.feed.model.service.FeedService;
import com.dongle.feed.model.vo.FeedNoResult;
import com.dongle.feed.model.vo.FileList;
import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.FeedFileRenamePolicy;

/**
 * Servlet implementation class FeedContentUpServlet
 */
@WebServlet("/feed/feedContentUpload")
public class FeedContentUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedContentUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int result=0;
		if(!ServletFileUpload.isMultipartContent(request)) {
			new Gson().toJson(result,response.getWriter());
			return;
		}
		//저장할 디렉토리 설정
		String dir=getServletContext().getRealPath("/images/feed-images");
		// 최대 파일 크기 설정
		int maxSize=1024*1024*1024;
		//파일 업로드 객체 생성
		//filename rename
		MultipartRequest mr=new MultipartRequest(request,dir,maxSize,"UTF-8",new FeedFileRenamePolicy());
		//DB 연결할 파라미터 값
		int memberNo = Integer.parseInt(mr.getParameter("memberNo"));
		int groupNo = Integer.parseInt(mr.getParameter("groupNo"));
		String feedContent=(String)mr.getParameter("content");
		FeedNoResult fnr=null;
		
		//DB에 저장할 데이터 리스트 
		List<FileList> uploadFileList =new ArrayList();
		FileList fl=null;
		//파일 네임 받아오기(열거)
		Enumeration<String> files=mr.getFileNames();
		//피드 컨텐트 부터 업로드 시작
		fnr=new FeedService().insertFeedContent(memberNo,groupNo,feedContent);
		//DB 입력후 피드 넘버 가져오기
		int feedNo=fnr.getFeedNo();
		//feed_file_tab 에 입력할 데이터 리스트에 입력
		int i=0;
		if(!files.hasMoreElements()) {
			
			result=fnr.getFeedResult();
			new Gson().toJson(result,response.getWriter());
			return;
		}else {
			while(files.hasMoreElements()){
				System.out.println("??");
				fl=new FileList();
				fl.setFeedNo(feedNo);
				fl.setFeedOriFilePath((String)files.nextElement()); 
				fl.setFeedRenameFilePath(mr.getFilesystemName("image"+i));                      
				uploadFileList.add(fl);
				i++;
				
				if(fnr.getFeedResult()>0) {
					
					result=new FeedService().insertFeedFile(feedNo,groupNo, uploadFileList);
					
					response.setContentType("application/json;charset=UTF-8");
					new Gson().toJson(result,response.getWriter());
					
				}else {
					//실패하면 종료
					new Gson().toJson(result,response.getWriter());
				}
	        }
		}
		
		
		//컨텐트 입력성공 하면 파일 정보 입력
		
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
