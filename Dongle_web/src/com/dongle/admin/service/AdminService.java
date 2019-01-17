package com.dongle.admin.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.dongle.admin.model.dao.AdminDao;
import com.dongle.member.model.vo.Member;

public class AdminService {
	public List<Member> selectMemberList()
	{
		Connection conn = getConnection();
		List<Member> memberList = new AdminDao().selectMemberList(conn);
		
		close(conn);
		return memberList;
	}
	
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
}
