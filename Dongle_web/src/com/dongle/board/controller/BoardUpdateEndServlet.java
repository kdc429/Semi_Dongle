package com.dongle.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.dongle.board.model.service.BoardService;
import com.dongle.board.model.vo.Board;
import com.dongle.board.model.vo.BoardPath;
import com.dongle.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

import common.BoardFileRenamePolicy;

/**
 * Servlet implementation class BoardUpdateEndServlet
 */
@WebServlet("/board/boardUpdateEnd")
public class BoardUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateEndServlet() {
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
			request.setAttribute("mag", "공지사항작성오류");
			request.setAttribute("loc", "/board/boardList");
			request.getRequestDispatcher("/views/common/msg/jsp").forward(request, response);
		}
		int maxSize=1024*1024*10;
		//경로설정
		String root=getServletContext().getRealPath("/");
		String saveDir=root+"upload/board";
		
		MultipartRequest mr=new MultipartRequest(request, saveDir,maxSize,"UTF-8",new BoardFileRenamePolicy());
		
		BoardPath bp=new BoardPath();
		bp.setGroupNo(Integer.parseInt(mr.getParameter("groupNo")));
		bp.setBoardTitle(mr.getParameter("title"));
		bp.setBoardWriter(mr.getParameter("writer"));
		bp.setBoardContent(mr.getParameter("content"));
		bp.setBoardFileOldPath(mr.getOriginalFileName("upfile"));
		bp.setBoardFileNewPath(mr.getFilesystemName("upfile"));
		
	
		
		File f=mr.getFile("upfile");
		
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
		
		int result=new BoardService().updateBoard(bp);
		
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		if(result!=0)
		{
			List<Board> blist = new BoardService().selectList(bp.getGroupNo(), loginMember.getMemberId());
			if(blist!=null) {
				System.out.println(blist);
				Board bo = blist.get(0);
				System.out.println(bo);
				int rs = new BoardService().insertBoardFile(bp,bo);

				if(rs!=0)
				{
					msg="게시판 수정성공";
					loc="/board/boardList";
				}
				else 
				{
					msg="게시판 수정 실패";
					loc="/board/boardUpdate";
				}
			}
			else
			{
				msg="게시판 수정 실패";
				loc="/board/boardUpdate";
			}
		}
		else
		{
			msg="게시판 수정 실패";
			loc="/board/boardUpdate";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	
	/*	int no=Integer.parseInt(mr.getParameter("groupNo"));
	String title=mr.getParameter("title");
	String writer=mr.getParameter("writer");
	String content=mr.getParameter("content");
	//이전파일 불러옴
	String oldFile=mr.getParameter("oldfile");
	//저장할 파일 불러옴
	String newFile=mr.getFilesystemName("upfile");*/
	
	/*if(f!=null&&f.length()>0)
	{
		File deleteFile=new File(saveDir+"/"+oldFile);
		boolean bool=deleteFile.delete();
		System.out.println(bool?"파일삭제 성공":"파일삭제 실패");
	}
	else
	{
		newFile=oldFile;
	}
	//파일 삭제하고 새로운 파일 업로드!
	
	BoardPath bp=new BoardPath();
	
	bp.setBoardNo(no);
	bp.setBoardTitle(title);
	bp.setBoardContent(content);
	bp.setBoardFileNewPath(newFile);*/
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
