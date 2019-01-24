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
import com.dongle.feed.model.vo.FeedFile;
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
		int j=0;
		int n=0;
		if(files.hasMoreElements()==false) {
			
			Feed f=new FeedService().selectFeedOne(feedNo);
			Group g=new GroupService().selectGrInfo(groupNo);
			GroupMember gm = new GroupService().selectGmInfo(groupNo,loginMember.getMemberNo());
			request.setAttribute("loginMember", loginMember);
			request.setAttribute("groupMember", gm);
			request.setAttribute("group", g);
			request.setAttribute("feed", f);
			request.getRequestDispatcher("/views/feed/insertOnlyContentFeed.jsp").forward(request, response);
			return;
		}else {
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
			if(fnr.getFeedResult()>0) {
				result=new FeedService().insertFeedFile(feedNo,groupNo, uploadFileList);
				
				if(result>0) {
					
					Feed f=new FeedService().selectFeedOne(feedNo);
					List<FeedFile> fileList=new FeedService().selectFeedFileListOne(feedNo);
					Group g=new GroupService().selectGrInfo(groupNo);
					GroupMember gm = new GroupService().selectGmInfo(groupNo,loginMember.getMemberNo());
					System.out.println("gm"+gm.getGroupMemberNickname());
					request.setAttribute("loginMember", loginMember);
					request.setAttribute("groupMember", gm);
					request.setAttribute("group", g);
					request.setAttribute("fileList", fileList);
					request.setAttribute("feed", f);
					request.getRequestDispatcher("/views/feed/InsertFeed.jsp").forward(request, response);
				}else {
					//실패하면 종료
					new Gson().toJson(result,response.getWriter());
				}
				
			}else {
				//실패하면 종료
				new Gson().toJson(result,response.getWriter());
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
