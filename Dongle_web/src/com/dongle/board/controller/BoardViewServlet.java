package com.dongle.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.board.model.service.BoardService;
import com.dongle.board.model.vo.Board;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));
		int groupNo=Integer.parseInt(request.getParameter("groupNo"));
		System.out.println(boardNo+" : "+groupNo);
		
		Cookie[] cookies=request.getCookies();
		String boardCookieVal="";
		boolean hasRead=false;//false : 안읽음 / true : 읽음
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
		
		if(cookies!=null)
		{
			outer:
				for(Cookie c : cookies)
				{
					String name=c.getName();
					String value=c.getValue();
					if("boardCookie".equals(name))
					{
						boardCookieVal=value;
						if(value.contains("|"+boardNo+"|"))
						{
							hasRead=true;
						}
						break outer;	
					}
				}
		}
		
		if(!hasRead)
		{
			Cookie boardCookie=new Cookie("boardCookie", boardCookieVal+"|"+boardNo+"|");
			boardCookie.setMaxAge(-1);//브라우저종료시 삭제
			response.addCookie(boardCookie);//쿠키추가							
		}
		
		Board b=new BoardService().selectOne(boardNo,groupNo,hasRead);
		System.out.println("b가 무엇? : "+b);
		System.out.println("읽긴 읽는거니??  "+hasRead);
		
		String view="";
		if(b!=null)
		{
			request.setAttribute("board", b);
			view="/views/board/boardView.jsp";
			request.setAttribute("groupNo", groupNo);
			/*request.setAttribute("boardNo", boardNo);*/
		}
		else
		{
			request.setAttribute("msg", "조회한 공지사항이 존재하지 않습니다.");
			request.setAttribute("loc", "/board/boardList?groupNo"+groupNo);
			request.setAttribute("groupNo", groupNo);
			view="/views/common/msg.jsp";
		}
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
