package com.dongle.gallery.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongle.gallery.model.service.GalleryService;
import com.dongle.gallery.model.vo.GalleryPath;
import com.dongle.group.model.vo.GroupMember;
import com.dongle.member.model.vo.Member;

/**
 * Servlet implementation class GalleryGetServlet
 */
@WebServlet("/gallery/galleryGet")
public class GalleryGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GalleryGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String albumCode = request.getParameter("albumCode");
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
/*		System.out.println(albumCode+" "+groupNo+" : "+loginMember.getMemberId());*/
		//동호회 회원인지 아닌지 group_member_tab에서 확인
		GroupMember gm = new GalleryService().groupMemberCheck(groupNo,loginMember.getMemberNo());
		System.out.println(gm);
		if(gm.getMemberNo()==0||!loginMember.getMemberId().equals("admin"))
		{
			request.setAttribute("msg", "회원만 열람 가능합니다. 동글에 가입해주세요.");
			request.setAttribute("loc", "/communityJoin?groupNo="+groupNo);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}		
		
		//페이징 처리하기
		int cPage; //현재 페이지를 의미함 (너가 지금 뭘 보고있는지!)
		try{
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}
		catch(NumberFormatException e)
		{
			//예외처리하기 (null이거나 문자가 들어온 경우 1로 초기화한다)
			cPage=1;
		}
		int numPerPage=12; //페이지당 보여줄 자료 수 

		//test : gal_no만 distinct된 dao에 갔다오기
		List<Integer> galNoList = new GalleryService().distictGalNoList(albumCode,groupNo); //7,6,5,4,3,2,1
		System.out.println(galNoList);
		//test : 전체 list뽑아오기
		List<GalleryPath> list =new GalleryService().getAllList(albumCode,groupNo);
		//##테스트 중입니다.
		ArrayList<GalleryPath> tList= new ArrayList<GalleryPath>();
		//같은 galNo끼리 저장된 ArrayList tList뽑아내기
		for(int i=0; i<galNoList.size();i++) //7회 (인덱스는 0부터 6)
		{
			List<GalleryPath> t2List= new ArrayList<GalleryPath>();
			for(int j=0;j<list.size();j++) //36 (인덱스는 0부터 35)
			{
				if(galNoList.get(i)==list.get(j).getGalNo())
				{
					
					t2List.add(list.get(j));
				}
			}
			tList.add(i, t2List.get(0));
		}
		//tList의 length가 totalMember와 같음
		
		
		//페이지 수 만큼 데이터를 불러오기
		List<GalleryPath> allList = new GalleryService().galleryGet(albumCode,groupNo,cPage,numPerPage); //cPage와 numPerPage가 있으면 공식에 의해서 페이징 처리가 가능함
		//실질적인 페이지를 구성해보자
		//전체 자료 수 확인하기
		int totalMember = new GalleryService().selectGalleryCount(albumCode,groupNo);//tList랑 같음
		//전체 페이지 수 
		/*int totalPage=(int)Math.ceil((double) totalMember/numPerPage); // 한페이지가 다 차지 않는 나머지 자료들도 보여져야하기 때문에 무조건 올림해야함*/
		int totalPage=(int)Math.ceil((double) tList.size()/numPerPage);
		//페이지바 html코드 누적변수(버튼을 구현하는 것-> 코드를 작성해주고 그 텍스트를 그대로 넘겨줄 것)
		String pageBar=" ";
		//페이지바 길이(숫자 몇개까지 보일 것인지)
		int pageBarSize=5;
		//시작 페이지의 위치를 나타냄(공식이있음) -> 다음페이지로 넘겼을 경우 숫자가 커져야하는데 그 기준을 잡는 공식
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1; //끝페이지
		//페이지 바를 구성
		//[이전]
		if(pageNo==1) //첫번째를 의미
		{
			pageBar+="<span>＜&nbsp;</span>";
		}
		else
		{
			pageBar+="<li><a href='"+request.getContextPath()+"/galleryGet?groupNo="+groupNo+"&albumCode="+albumCode+"&cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"'>＜ &nbsp;</a>";
		}
		//선택페이지 만들기
		while(!(pageNo>pageEnd||pageNo>totalPage))
		{
			if(cPage==pageNo)//현재 페이지가 시작페이지랑 같은 경우 고정되어야함
			{
				pageBar+="<span class='cPage'>&nbsp;"+pageNo+"&nbsp;</span>";
			}
			else
			{
				pageBar+="<a href='"+request.getContextPath()+"/galleryGet?groupNo="+groupNo+"&albumCode="+albumCode+"&cPage="+pageNo+"&numPerPage="+numPerPage+"'>"+"&nbsp;"+pageNo+"&nbsp;"+"</a>";
			}
			pageNo++;
		}
		//[다음]
		if(pageNo>totalPage)
		{
			pageBar+="<span> &nbsp;＞</span>";
		}
		else {
			pageBar+="<a href='"+request.getContextPath()+"/galleryGet?groupNo="+groupNo+"&albumCode="+albumCode+"&cPage="+pageNo+"&numPerPage="+numPerPage+"'>&nbsp;＞</a></li>";
		}
/*		request.setAttribute("allList", allList);
		request.setAttribute("list", list);*/
		request.setAttribute("tList", tList);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("groupNo", groupNo);
		request.setAttribute("albumCode", albumCode);
		request.setAttribute("groupMember", gm);
		request.getRequestDispatcher("/views/gallery/galleryView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
