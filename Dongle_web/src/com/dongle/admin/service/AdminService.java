package com.dongle.admin.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.dongle.admin.model.dao.AdminDao;
import com.dongle.group.model.vo.ListGroup;
import com.dongle.member.model.vo.Member;

public class AdminService {
	/* 멤버리스트 서비스 */
	public List<Member> selectMemberList()
	{
		Connection conn = getConnection();
		List<Member> memberList = new AdminDao().selectMemberList(conn);
		
		close(conn);
		return memberList;
	}
	
	/* 멤버 검색 */
	public List<Member> selectMemberId(String searchKeyword)
	{
		Connection conn = getConnection();
		List<Member> memberList = new AdminDao().selectMemberId(conn, searchKeyword);
		
		close(conn);
		return memberList;
	}

	public List<Member> selectMemberName(String searchKeyword)
	{
		Connection conn = getConnection();
		List<Member> memberList = new AdminDao().selectMemberName(conn, searchKeyword);
		
		close(conn);
		return memberList;
	}

	public List<Member> selectPhone(String searchKeyword)
	{
		Connection conn = getConnection();
		List<Member> memberList = new AdminDao().selectPhone(conn, searchKeyword);
		
		close(conn);
		return memberList;
	}
	
	public List<Member> selectEmail(String searchKeyword)
	{
		Connection conn = getConnection();
		List<Member> memberList = new AdminDao().selectEmail(conn, searchKeyword);
		
		close(conn);
		return memberList;
	}
	
	/* 멤버리스트 정렬 */
	public List<Member> sortId(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<Member> memberList = new AdminDao().sortId(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return memberList;
	}
	
	public List<Member> sortName(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<Member> memberList = new AdminDao().sortName(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return memberList;
	}
	
	public List<Member> sortGender(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<Member> memberList = new AdminDao().sortGender(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return memberList;
	}
	
	public List<Member> sortSsn(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<Member> memberList = new AdminDao().sortSsn(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return memberList;
	}
	
	public List<Member> sortPhone(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<Member> memberList = new AdminDao().sortPhone(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return memberList;
	}

	public List<Member> sortAddress(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<Member> memberList = new AdminDao().sortAddress(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return memberList;
	}
	
	public List<Member> sortEmail(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<Member> memberList = new AdminDao().sortEmail(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return memberList;
	}
	
	public List<Member> sortEnrollDate(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<Member> memberList = new AdminDao().sortEnrollDate(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return memberList;
	}
	
	public List<Member> sortBlackList(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<Member> memberList = new AdminDao().sortBlackList(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return memberList;
	}
	
	public List<Member> sortReportCount(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<Member> memberList = new AdminDao().sortReportCount(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return memberList;
	}
	
	/* 동글리스트 서비스 */
	public List<ListGroup> selectDongleList() 
	{
		Connection conn = getConnection();
		List<ListGroup> dongleList = new AdminDao().selectDongleList(conn);
		
		close(conn);
		return dongleList;
	}
	
	/* 동글 검색 */
	public List<ListGroup> selectDongleName(String searchKeyword)
	{
		Connection conn = getConnection();
		List<ListGroup> dongleList = new AdminDao().selectDongleName(conn, searchKeyword);
		
		close(conn);
		return dongleList;
	}

	public List<ListGroup> selectManagerId(String searchKeyword)
	{
		Connection conn = getConnection();
		List<ListGroup> dongleList = new AdminDao().selectManagerId(conn, searchKeyword);
		
		close(conn);
		return dongleList;
	}

	public List<ListGroup> selectDongleEnDate(String searchKeyword)
	{
		Connection conn = getConnection();
		System.out.println("여기2");
		List<ListGroup> dongleList = new AdminDao().selectDongleEnDate(conn, searchKeyword);
		
		close(conn);
		return dongleList;
	}
	
	public List<ListGroup> selectMetro(String searchKeyword)
	{
		Connection conn = getConnection();
		List<ListGroup> dongleList = new AdminDao().selectMetro(conn, searchKeyword);
		
		close(conn);
		return dongleList;
	}
	
	
	/* 동글리스트 정렬 */
	public List<ListGroup> sortDongleName(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<ListGroup> dongleList = new AdminDao().sortDongleName(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return dongleList;
	}
	
	public List<ListGroup> sortManagerId(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<ListGroup> dongleList = new AdminDao().sortManagerId(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return dongleList;
	}
	
	public List<ListGroup> sortTopic(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<ListGroup> dongleList = new AdminDao().sortTopic(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return dongleList;
	}
	
	public List<ListGroup> sortMetro(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<ListGroup> dongleList = new AdminDao().sortMetro(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return dongleList;
	}
	
	public List<ListGroup> sortDate(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<ListGroup> dongleList = new AdminDao().sortDate(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return dongleList;
	}
	
	public List<ListGroup> sortMinAge(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<ListGroup> dongleList = new AdminDao().sortMinAge(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return dongleList;
	}
	
	public List<ListGroup> sortMaxAge(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<ListGroup> dongleList = new AdminDao().sortMaxAge(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return dongleList;
	}
	
	public List<ListGroup> sortDongleEnrollDate(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<ListGroup> dongleList = new AdminDao().sortDongleEnrollDate(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return dongleList;
	}
	
	public List<ListGroup> sortDongleReportCnt(String searchType, String searchKeyword, String flag)
	{
		Connection conn = getConnection();
		List<ListGroup> dongleList = new AdminDao().sortDongleReportCnt(conn, searchType, searchKeyword, flag);
		
		close(conn);
		return dongleList;
	}
	
	/*블랙멤버리스트 서비스*/
	public List<Member> selectBlackMemberList(String isBlack)
	{
		Connection conn = getConnection();
		List<Member> memberList = new AdminDao().selectBlackMemberList(conn, isBlack);
		
		close(conn);
		return memberList;
	}
	
}