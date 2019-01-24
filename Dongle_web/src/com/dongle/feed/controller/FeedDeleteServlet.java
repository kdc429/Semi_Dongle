package com.dongle.feed.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.feed.model.service.FeedService;
import com.dongle.feed.model.vo.FeedFile;
import com.google.gson.Gson;

/**
 * Servlet implementation class FeedDeleteServlet
 */
@WebServlet("/feed/feedDelete")
public class FeedDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int feedNo=Integer.parseInt(request.getParameter("feedNo"));
		int result=0;
		result=new FeedService().deleteFeedContent(feedNo);
		if(result>0) {
			
			List<FeedFile> fileList=new FeedService().selectFeedFileListOne(feedNo);
			if(fileList.size()==0) {
				new Gson().toJson(result,response.getWriter());
			}else {
				result=new FeedService().deleteFeedFile(feedNo);
				new Gson().toJson(result,response.getWriter());
			}
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
