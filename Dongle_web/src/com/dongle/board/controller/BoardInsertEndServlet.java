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

@WebServlet("/board/boardInsertFormEnd")
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
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		System.out.println("END들어옴");
		
		//파일 기본경로
		String root=getServletContext().getRealPath("/");
		//파일경로를 설정할때 구분자!
		/*String saveDir=root+"upload/board";*/
		String saveDir=root+"upload"+File.separator+"board";
		
		//파일 크기설정
		int maxSize=1024*1024*10;//10MB
		//파일 업로드 객체 생성
		//filename rename
		MultipartRequest mr=new MultipartRequest(request, saveDir,maxSize,"UTF-8",new BoardFileRenamePolicy());
		
		BoardPath bp=new BoardPath();
		bp.setGroupNo(Integer.parseInt(mr.getParameter("groupNo")));
		bp.setBoardTitle(mr.getParameter("title"));
		bp.setBoardWriter(mr.getParameter("writer"));
		bp.setBoardContent(mr.getParameter("content"));
		bp.setBoardFileOldPath(mr.getOriginalFileName("upfile"));
		bp.setBoardFileNewPath(mr.getFilesystemName("upfile"));
		
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
		
		
		System.out.println(bp);
		int result=new BoardService().insertBoard(bp);
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
					msg="게시판 등록성공";
					loc="/board/boardList";
				}
				else 
				{
					msg="게시판 등록 실패";
					loc="/board/boardInsert";
				}
			}
			else
			{
				msg="게시판 등록 실패";
				loc="/board/boardInsert";
			}
		}
		else
		{
			msg="게시판 등록 실패";
			loc="/board/boardInsert";
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
