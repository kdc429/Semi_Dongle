package com.dongle.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardDownLoadServlet
 */
@WebServlet("/board/boardFileDownLoad")
public class BoardDownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDownLoadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rName=request.getParameter("rName");
		String oName=request.getParameter("oName");
		//실제 저장되어있는 파일 경로를 가져옴
		String saveDir=getServletContext().getRealPath("/upload/board");
		String fileName=saveDir+"/"+rName;
		
		//파일을 서버의 메모리 공간으로 가져옴
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(fileName));
		
		//출력할 곳에 스트림을 연결
		ServletOutputStream sos=response.getOutputStream();
		BufferedOutputStream bos=new BufferedOutputStream(sos);
		
		//불러온 데이터를 브라우저에 전송
		String resFileName="";
		
		//요청한 클라이언트의 브라우저가 무엇인지 확인
		boolean isMSIE=request.getHeader("user-agent").indexOf("MSIE")!= -1||request.getHeader("user-agent").indexOf("Trident") !=-1;
		if(isMSIE)
		{
			resFileName=URLEncoder.encode(oName, "UTF-8");
			resFileName=resFileName.replaceAll("\\", "%20");
		}
		else
		{
			resFileName=new String(oName.getBytes("UTF-8"),"ISO-8859-1");
		}
		
		response.setContentType("application/octect-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+resFileName);
		
		int read=-1;
		while((read=bis.read())!=-1)
		{
			bos.write(read);
		}
		bis.close();
		bos.close();
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
