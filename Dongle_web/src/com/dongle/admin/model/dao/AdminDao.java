package com.dongle.admin.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dongle.group.model.vo.ListGroup;
import com.dongle.member.model.vo.Member;


public class AdminDao {
	private Properties prop = new Properties();
	
	public AdminDao()
	{
		try {
			String fileName = AdminDao.class.getResource("./admin-query.properties").getPath();
			prop.load(new FileReader(fileName));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	/* 멤버리스트 dao */
	public List<Member> selectMemberList(Connection conn)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Member> memberList=new ArrayList();
		String sql=prop.getProperty("selectMemberList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
				memberList.add(m);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	public Member selectMember(Connection conn, int memberNo)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m= null;
		String sql=prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return m;
	}
	/* 멤버리스트 검색 */
	public List<Member> selectMemberId(Connection conn, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Member> memberList=new ArrayList();
		String sql=prop.getProperty("selectMemberId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
				memberList.add(m);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	public List<Member> selectMemberName(Connection conn, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Member> memberList=new ArrayList();
		String sql=prop.getProperty("selectMemberName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
				memberList.add(m);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	public List<Member> selectPhone(Connection conn, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Member> memberList=new ArrayList();
		String sql=prop.getProperty("selectPhone");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
				memberList.add(m);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	public List<Member> selectEmail(Connection conn, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Member> memberList=new ArrayList();
		String sql=prop.getProperty("selectEmail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
				memberList.add(m);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	/* 멤버리스트 정렬 */
	public List<Member> sortId(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Member> memberList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortIdAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortIdDesc");
		}
		
		else if(searchType.equals("memberId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortIdSearchIdAsc");
		}
		else if(searchType.equals("memberId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortIdSearchIdDesc");
		}
		
		else if(searchType.equals("memberName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortIdSearchNameAsc");
		}
		else if(searchType.equals("memberName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortIdSearchNameDesc");
		}
		 
		else if(searchType.equals("phone") && flag.equals("true"))
		{
			sql=prop.getProperty("sortIdSearchPhoneAsc");
		}
		else if(searchType.equals("phone") && flag.equals("false"))
		{
			sql=prop.getProperty("sortIdSearchPhoneDesc");
		}
		
		else if(searchType.equals("email") && flag.equals("true"))
		{
			sql=prop.getProperty("sortIdSearchEmailAsc");
		}
		else if(searchType.equals("email") && flag.equals("false"))
		{
			sql=prop.getProperty("sortIdSearchEmailDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
				memberList.add(m);
			}
			System.out.println("DD");
			System.out.println(memberList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	public List<Member> sortName(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Member> memberList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortNameAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortNameDesc");
		}
		
		else if(searchType.equals("memberId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortNameSearchIdAsc");
		}
		else if(searchType.equals("memberId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortNameSearchIdDesc");
		}
		
		else if(searchType.equals("memberName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortNameSearchNameAsc");
		}
		else if(searchType.equals("memberName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortNameSearchNameDesc");
		}
		 
		else if(searchType.equals("phone") && flag.equals("true"))
		{
			sql=prop.getProperty("sortNameSearchPhoneAsc");
		}
		else if(searchType.equals("phone") && flag.equals("false"))
		{
			sql=prop.getProperty("sortNameSearchPhoneDesc");
		}
		
		else if(searchType.equals("email") && flag.equals("true"))
		{
			sql=prop.getProperty("sortNameSearchEmailAsc");
		}
		else if(searchType.equals("email") && flag.equals("false"))
		{
			sql=prop.getProperty("sortNameSearchEmailDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			System.out.println(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
				memberList.add(m);
			}
			System.out.println("DD");
			System.out.println(memberList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	public List<Member> sortGender(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Member> memberList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortGenderAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortGenderDesc");
		}
		
		else if(searchType.equals("memberId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortGenderSearchIdAsc");
		}
		else if(searchType.equals("memberId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortGenderSearchIdDesc");
		}
		
		else if(searchType.equals("memberName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortGenderSearchNameAsc");
		}
		else if(searchType.equals("memberName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortGenderSearchNameDesc");
		}
		 
		else if(searchType.equals("phone") && flag.equals("true"))
		{
			sql=prop.getProperty("sortGenderSearchPhoneAsc");
		}
		else if(searchType.equals("phone") && flag.equals("false"))
		{
			sql=prop.getProperty("sortGenderSearchPhoneDesc");
		}
		
		else if(searchType.equals("email") && flag.equals("true"))
		{
			sql=prop.getProperty("sortGenderSearchEmailAsc");
		}
		else if(searchType.equals("email") && flag.equals("false"))
		{
			sql=prop.getProperty("sortGenderSearchEmailDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			System.out.println(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
				memberList.add(m);
			}
			System.out.println("DD");
			System.out.println(memberList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	public List<Member> sortSsn(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Member> memberList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortSsnAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortSsnDesc");
		}
		
		else if(searchType.equals("memberId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortSsnSearchIdAsc");
		}
		else if(searchType.equals("memberId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortSsnSearchIdDesc");
		}
		
		else if(searchType.equals("memberName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortSsnSearchNameAsc");
		}
		else if(searchType.equals("memberName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortSsnSearchNameDesc");
		}
		 
		else if(searchType.equals("phone") && flag.equals("true"))
		{
			sql=prop.getProperty("sortSsnSearchPhoneAsc");
		}
		else if(searchType.equals("phone") && flag.equals("false"))
		{
			sql=prop.getProperty("sortSsnSearchPhoneDesc");
		}
		
		else if(searchType.equals("email") && flag.equals("true"))
		{
			sql=prop.getProperty("sortSsnSearchEmailAsc");
		}
		else if(searchType.equals("email") && flag.equals("false"))
		{
			sql=prop.getProperty("sortSsnSearchEmailDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			System.out.println(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
				memberList.add(m);
			}
			System.out.println("DD");
			System.out.println(memberList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	public List<Member> sortPhone(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Member> memberList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortPhoneAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortPhoneDesc");
		}
		
		else if(searchType.equals("memberId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortPhoneSearchIdAsc");
		}
		else if(searchType.equals("memberId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortPhoneSearchIdDesc");
		}
		
		else if(searchType.equals("memberName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortPhoneSearchNameAsc");
		}
		else if(searchType.equals("memberName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortPhoneSearchNameDesc");
		}
		 
		else if(searchType.equals("phone") && flag.equals("true"))
		{
			sql=prop.getProperty("sortPhoneSearchPhoneAsc");
		}
		else if(searchType.equals("phone") && flag.equals("false"))
		{
			sql=prop.getProperty("sortPhoneSearchPhoneDesc");
		}
		
		else if(searchType.equals("email") && flag.equals("true"))
		{
			sql=prop.getProperty("sortPhoneSearchEmailAsc");
		}
		else if(searchType.equals("email") && flag.equals("false"))
		{
			sql=prop.getProperty("sortPhoneSearchEmailDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			System.out.println(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
				memberList.add(m);
			}
			System.out.println("DD");
			System.out.println(memberList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	public List<Member> sortAddress(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Member> memberList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortAddressAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortAddressDesc");
		}
		
		else if(searchType.equals("memberId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortAddressSearchIdAsc");
		}
		else if(searchType.equals("memberId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortAddressSearchIdDesc");
		}
		
		else if(searchType.equals("memberName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortAddressSearchNameAsc");
		}
		else if(searchType.equals("memberName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortAddressSearchNameDesc");
		}
		 
		else if(searchType.equals("phone") && flag.equals("true"))
		{
			sql=prop.getProperty("sortAddressSearchPhoneAsc");
		}
		else if(searchType.equals("phone") && flag.equals("false"))
		{
			sql=prop.getProperty("sortAddressSearchPhoneDesc");
		}
		
		else if(searchType.equals("email") && flag.equals("true"))
		{
			sql=prop.getProperty("sortAddressSearchEmailAsc");
		}
		else if(searchType.equals("email") && flag.equals("false"))
		{
			sql=prop.getProperty("sortAddressSearchEmailDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			System.out.println(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
				memberList.add(m);
			}
			System.out.println("DD");
			System.out.println(memberList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	public List<Member> sortEmail(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Member> memberList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortEmailAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortEmailDesc");
		}
		
		else if(searchType.equals("memberId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortEmailSearchIdAsc");
		}
		else if(searchType.equals("memberId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortEmailSearchIdDesc");
		}
		
		else if(searchType.equals("memberName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortEmailSearchNameAsc");
		}
		else if(searchType.equals("memberName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortEmailSearchNameDesc");
		}
		 
		else if(searchType.equals("phone") && flag.equals("true"))
		{
			sql=prop.getProperty("sortEmailSearchPhoneAsc");
		}
		else if(searchType.equals("phone") && flag.equals("false"))
		{
			sql=prop.getProperty("sortEmailSearchPhoneDesc");
		}
		
		else if(searchType.equals("email") && flag.equals("true"))
		{
			sql=prop.getProperty("sortEmailSearchEmailAsc");
		}
		else if(searchType.equals("email") && flag.equals("false"))
		{
			sql=prop.getProperty("sortEmailSearchEmailDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			System.out.println(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
				memberList.add(m);
			}
			System.out.println("DD");
			System.out.println(memberList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	public List<Member> sortEnrollDate(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Member> memberList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortEnrollDateAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortEnrollDateDesc");
		}
		
		else if(searchType.equals("memberId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortEnrollDateSearchIdAsc");
		}
		else if(searchType.equals("memberId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortEnrollDateSearchIdDesc");
		}
		
		else if(searchType.equals("memberName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortEnrollDateSearchNameAsc");
		}
		else if(searchType.equals("memberName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortEnrollDateSearchNameDesc");
		}
		 
		else if(searchType.equals("phone") && flag.equals("true"))
		{
			sql=prop.getProperty("sortEnrollDateSearchPhoneAsc");
		}
		else if(searchType.equals("phone") && flag.equals("false"))
		{
			sql=prop.getProperty("sortEnrollDateSearchPhoneDesc");
		}
		
		else if(searchType.equals("email") && flag.equals("true"))
		{
			sql=prop.getProperty("sortEnrollDateSearchEmailAsc");
		}
		else if(searchType.equals("email") && flag.equals("false"))
		{
			sql=prop.getProperty("sortEnrollDateSearchEmailDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			System.out.println(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
				memberList.add(m);
			}
			System.out.println("DD");
			System.out.println(memberList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	public List<Member> sortBlackList(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Member> memberList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortBlackListAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortBlackListDesc");
		}
		
		else if(searchType.equals("memberId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortBlackListSearchIdAsc");
		}
		else if(searchType.equals("memberId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortBlackListSearchIdDesc");
		}
		
		else if(searchType.equals("memberName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortBlackListSearchNameAsc");
		}
		else if(searchType.equals("memberName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortBlackListSearchNameDesc");
		}
		 
		else if(searchType.equals("phone") && flag.equals("true"))
		{
			sql=prop.getProperty("sortBlackListSearchPhoneAsc");
		}
		else if(searchType.equals("phone") && flag.equals("false"))
		{
			sql=prop.getProperty("sortBlackListSearchPhoneDesc");
		}
		
		else if(searchType.equals("email") && flag.equals("true"))
		{
			sql=prop.getProperty("sortBlackListSearchEmailAsc");
		}
		else if(searchType.equals("email") && flag.equals("false"))
		{
			sql=prop.getProperty("sortBlackListSearchEmailDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			System.out.println(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
				memberList.add(m);
			}
			System.out.println("DD");
			System.out.println(memberList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	public List<Member> sortReportCount(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Member> memberList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortReportCountAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortReportCountDesc");
		}
		
		else if(searchType.equals("memberId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortReportCountSearchIdAsc");
		}
		else if(searchType.equals("memberId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortReportCountSearchIdDesc");
		}
		
		else if(searchType.equals("memberName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortReportCountSearchNameAsc");
		}
		else if(searchType.equals("memberName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortReportCountSearchNameDesc");
		}
		 
		else if(searchType.equals("phone") && flag.equals("true"))
		{
			sql=prop.getProperty("sortReportCountSearchPhoneAsc");
		}
		else if(searchType.equals("phone") && flag.equals("false"))
		{
			sql=prop.getProperty("sortReportCountSearchPhoneDesc");
		}
		
		else if(searchType.equals("email") && flag.equals("true"))
		{
			sql=prop.getProperty("sortReportCountSearchEmailAsc");
		}
		else if(searchType.equals("email") && flag.equals("false"))
		{
			sql=prop.getProperty("sortReportCountSearchEmailDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			System.out.println(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
				memberList.add(m);
			}
			System.out.println("DD");
			System.out.println(memberList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	/* 동글리스트 dao */
	public List<ListGroup> selectDongleList(Connection conn)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ListGroup> dongleList=new ArrayList();
		String sql=prop.getProperty("selectDongleList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ListGroup g = new ListGroup();
				g.setDongleName(rs.getString("group_name"));
				g.setManagerId(rs.getString("member_id"));
				g.setTopic(rs.getString("topic_ctg_code"));
				g.setAddress(rs.getString("loc_ctg_code"));
				g.setActiveDate(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportCnt(rs.getInt("report_group_count"));
				
				dongleList.add(g);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return dongleList;
	}
	
	/* 동글리스트 검색 */
	public List<ListGroup> selectDongleName(Connection conn, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ListGroup> dongleList=new ArrayList();
		String sql=prop.getProperty("selectDongleName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ListGroup g = new ListGroup();
				g.setDongleName(rs.getString("group_name"));
				g.setManagerId(rs.getString("member_id"));
				g.setTopic(rs.getString("topic_ctg_code"));
				g.setAddress(rs.getString("loc_ctg_code"));
				g.setActiveDate(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportCnt(rs.getInt("report_group_count"));
				dongleList.add(g);	
				System.out.println(g);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return dongleList;
	}
	
	public List<ListGroup> selectManagerId(Connection conn, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ListGroup> dongleList=new ArrayList();
		String sql=prop.getProperty("selectManagerId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ListGroup g = new ListGroup();
				g.setDongleName(rs.getString("group_name"));
				g.setManagerId(rs.getString("member_id"));
				g.setTopic(rs.getString("topic_ctg_code"));
				g.setAddress(rs.getString("loc_ctg_code"));
				g.setActiveDate(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportCnt(rs.getInt("report_group_count"));
				dongleList.add(g);			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return dongleList;
	}
	
	public List<ListGroup> selectDongleEnDate(Connection conn, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ListGroup> dongleList=new ArrayList();
		String sql=prop.getProperty("selectDongleEnDate");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchKeyword);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ListGroup g = new ListGroup();
				g.setDongleName(rs.getString("group_name"));
				g.setManagerId(rs.getString("member_id"));
				g.setTopic(rs.getString("topic_ctg_code"));
				g.setAddress(rs.getString("loc_ctg_code"));
				g.setActiveDate(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportCnt(rs.getInt("report_group_count"));
				dongleList.add(g);			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return dongleList;
	}
	
	public List<ListGroup> selectMetro(Connection conn, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ListGroup> dongleList=new ArrayList();
		String sql=prop.getProperty("selectMetro");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ListGroup g = new ListGroup();
				g.setDongleName(rs.getString("group_name"));
				g.setManagerId(rs.getString("member_id"));
				g.setTopic(rs.getString("topic_ctg_code"));
				g.setAddress(rs.getString("loc_ctg_code"));
				g.setActiveDate(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportCnt(rs.getInt("report_group_count"));
				dongleList.add(g);			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return dongleList;
	}
	
	
	/* 동글리스트 정렬 */
	public List<ListGroup> sortDongleName(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ListGroup> dongleList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDongleNameAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDongleNameDesc");
		}
		
		else if(searchType.equals("dongleName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDongleNameSearchDongleNameAsc");
		}
		else if(searchType.equals("dongleName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDongleNameSearchDongleNameDesc");
		}
		
		else if(searchType.equals("managerId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDongleNameSearchManagerIdAsc");
		}
		else if(searchType.equals("managerId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDongleNameSearchManagerIdDesc");
		}
		 
		else if(searchType.equals("dongleEnDate") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDongleNameSearchEnDateAsc");
		}
		else if(searchType.equals("dongleEnDate") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDongleNameSearchEnDateDesc");
		}
		
		else if(searchType.equals("metro") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDongleNameSearchMetroAsc");
		}
		else if(searchType.equals("metro") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDongleNameSearchMetroDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(searchType.equals("dongleEnDate"))
			{
				pstmt.setString(1, searchKeyword);
			}
			else if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ListGroup g = new ListGroup();
				g.setDongleName(rs.getString("group_name"));
				g.setManagerId(rs.getString("member_id"));
				g.setTopic(rs.getString("topic_ctg_code"));
				g.setAddress(rs.getString("loc_ctg_code"));
				g.setActiveDate(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportCnt(rs.getInt("report_group_count"));
				dongleList.add(g);			
			}
			System.out.println("DD");
			System.out.println(dongleList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return dongleList;
	}
	
	public List<ListGroup> sortManagerId(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ListGroup> dongleList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortManagerIdAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortManagerIdDesc");
		}
		
		else if(searchType.equals("dongleName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortManagerIdSearchDongleNameAsc");
		}
		else if(searchType.equals("dongleName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortManagerIdSearchDongleNameDesc");
		}
		
		else if(searchType.equals("managerId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortManagerIdSearchManagerIdAsc");
		}
		else if(searchType.equals("managerId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortManagerIdSearchManagerIdDesc");
		}
		 
		else if(searchType.equals("dongleEnDate") && flag.equals("true"))
		{
			sql=prop.getProperty("sortManagerIdSearchEnDateAsc");
		}
		else if(searchType.equals("dongleEnDate") && flag.equals("false"))
		{
			sql=prop.getProperty("sortManagerIdSearchEnDateDesc");
		}
		
		else if(searchType.equals("metro") && flag.equals("true"))
		{
			sql=prop.getProperty("sortManagerIdSearchMetroAsc");
		}
		else if(searchType.equals("metro") && flag.equals("false"))
		{
			sql=prop.getProperty("sortManagerIdSearchMetroDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(searchType.equals("dongleEnDate"))
			{
				pstmt.setString(1, searchKeyword);
			}
			else if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ListGroup g = new ListGroup();
				g.setDongleName(rs.getString("group_name"));
				g.setManagerId(rs.getString("member_id"));
				g.setTopic(rs.getString("topic_ctg_code"));
				g.setAddress(rs.getString("loc_ctg_code"));
				g.setActiveDate(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportCnt(rs.getInt("report_group_count"));
				dongleList.add(g);			
			}
			System.out.println("DD");
			System.out.println(dongleList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return dongleList;
	}
	
	public List<ListGroup> sortTopic(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ListGroup> dongleList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortTopicAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortTopicDesc");
		}
		
		else if(searchType.equals("dongleName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortTopicSearchDongleNameAsc");
		}
		else if(searchType.equals("dongleName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortTopicSearchDongleNameDesc");
		}
		
		else if(searchType.equals("managerId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortTopicSearchManagerIdAsc");
		}
		else if(searchType.equals("managerId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortTopicSearchManagerIdDesc");
		}
		 
		else if(searchType.equals("dongleEnDate") && flag.equals("true"))
		{
			sql=prop.getProperty("sortTopicSearchEnDateAsc");
		}
		else if(searchType.equals("dongleEnDate") && flag.equals("false"))
		{
			sql=prop.getProperty("sortTopicSearchEnDateDesc");
		}
		
		else if(searchType.equals("metro") && flag.equals("true"))
		{
			sql=prop.getProperty("sortTopicSearchMetroAsc");
		}
		else if(searchType.equals("metro") && flag.equals("false"))
		{
			sql=prop.getProperty("sortTopicSearchMetroDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(searchType.equals("dongleEnDate"))
			{
				pstmt.setString(1, searchKeyword);
			}
			else if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ListGroup g = new ListGroup();
				g.setDongleName(rs.getString("group_name"));
				g.setManagerId(rs.getString("member_id"));
				g.setTopic(rs.getString("topic_ctg_code"));
				g.setAddress(rs.getString("loc_ctg_code"));
				g.setActiveDate(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportCnt(rs.getInt("report_group_count"));
				dongleList.add(g);			
			}
			System.out.println("DD");
			System.out.println(dongleList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return dongleList;
	}
	
	public List<ListGroup> sortMetro(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ListGroup> dongleList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortMetroAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortMetroDesc");
		}
		
		else if(searchType.equals("dongleName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortMetroSearchDongleNameAsc");
		}
		else if(searchType.equals("dongleName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortMetroSearchDongleNameDesc");
		}
		
		else if(searchType.equals("managerId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortMetroSearchManagerIdAsc");
		}
		else if(searchType.equals("managerId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortMetroSearchManagerIdDesc");
		}
		 
		else if(searchType.equals("dongleEnDate") && flag.equals("true"))
		{
			sql=prop.getProperty("sortMetroSearchEnDateAsc");
		}
		else if(searchType.equals("dongleEnDate") && flag.equals("false"))
		{
			sql=prop.getProperty("sortMetroSearchEnDateDesc");
		}
		
		else if(searchType.equals("metro") && flag.equals("true"))
		{
			sql=prop.getProperty("sortMetroSearchMetroAsc");
		}
		else if(searchType.equals("metro") && flag.equals("false"))
		{
			sql=prop.getProperty("sortMetroSearchMetroDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			if(searchType.equals("dongleEnDate"))
			{
				pstmt.setString(1, searchKeyword);
			}
			else if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ListGroup g = new ListGroup();
				g.setDongleName(rs.getString("group_name"));
				g.setManagerId(rs.getString("member_id"));
				g.setTopic(rs.getString("topic_ctg_code"));
				g.setAddress(rs.getString("loc_ctg_code"));
				g.setActiveDate(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportCnt(rs.getInt("report_group_count"));
				dongleList.add(g);			
			}
			System.out.println("DD");
			System.out.println(dongleList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return dongleList;
	}
	
	public List<ListGroup> sortDate(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ListGroup> dongleList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDateAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDateDesc");
		}
		
		else if(searchType.equals("dongleName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDateSearchDongleNameAsc");
		}
		else if(searchType.equals("dongleName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDateSearchDongleNameDesc");
		}
		
		else if(searchType.equals("managerId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDateSearchManagerIdAsc");
		}
		else if(searchType.equals("managerId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDateSearchManagerIdDesc");
		}
		 
		else if(searchType.equals("dongleEnDate") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDateSearchEnDateAsc");
		}
		else if(searchType.equals("dongleEnDate") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDateSearchEnDateDesc");
		}
		
		else if(searchType.equals("metro") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDateSearchMetroAsc");
		}
		else if(searchType.equals("metro") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDateSearchMetroDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(searchType.equals("dongleEnDate"))
			{
				pstmt.setString(1, searchKeyword);
			}
			else if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ListGroup g = new ListGroup();
				g.setDongleName(rs.getString("group_name"));
				g.setManagerId(rs.getString("member_id"));
				g.setTopic(rs.getString("topic_ctg_code"));
				g.setAddress(rs.getString("loc_ctg_code"));
				g.setActiveDate(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportCnt(rs.getInt("report_group_count"));
				dongleList.add(g);			
			}
			System.out.println("DD");
			System.out.println(dongleList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return dongleList;
	}
	
	public List<ListGroup> sortMinAge(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ListGroup> dongleList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortMinAgeAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortMinAgeDesc");
		}
		
		else if(searchType.equals("dongleName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortMinAgeSearchDongleNameAsc");
		}
		else if(searchType.equals("dongleName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortMinAgeSearchDongleNameDesc");
		}
		
		else if(searchType.equals("managerId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortMinAgeSearchManagerIdAsc");
		}
		else if(searchType.equals("managerId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortMinAgeSearchManagerIdDesc");
		}
		 
		else if(searchType.equals("dongleEnDate") && flag.equals("true"))
		{
			sql=prop.getProperty("sortMinAgeSearchEnDateAsc");
		}
		else if(searchType.equals("dongleEnDate") && flag.equals("false"))
		{
			sql=prop.getProperty("sortMinAgeSearchEnDateDesc");
		}
		
		else if(searchType.equals("metro") && flag.equals("true"))
		{
			sql=prop.getProperty("sortMinAgeSearchMetroAsc");
		}
		else if(searchType.equals("metro") && flag.equals("false"))
		{
			sql=prop.getProperty("sortMinAgeSearchMetroDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(searchType.equals("dongleEnDate"))
			{
				pstmt.setString(1, searchKeyword);
			}
			else if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ListGroup g = new ListGroup();
				g.setDongleName(rs.getString("group_name"));
				g.setManagerId(rs.getString("member_id"));
				g.setTopic(rs.getString("topic_ctg_code"));
				g.setAddress(rs.getString("loc_ctg_code"));
				g.setActiveDate(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportCnt(rs.getInt("report_group_count"));
				dongleList.add(g);			
			}
			System.out.println("DD");
			System.out.println(dongleList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return dongleList;
	}
	
	public List<ListGroup> sortMaxAge(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ListGroup> dongleList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortMaxAgeAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortMaxAgeDesc");
		}
		
		else if(searchType.equals("dongleName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortMaxAgeSearchDongleNameAsc");
		}
		else if(searchType.equals("dongleName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortMaxAgeSearchDongleNameDesc");
		}
		
		else if(searchType.equals("managerId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortMaxAgeSearchManagerIdAsc");
		}
		else if(searchType.equals("managerId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortMaxAgeSearchManagerIdDesc");
		}
		 
		else if(searchType.equals("dongleEnDate") && flag.equals("true"))
		{
			sql=prop.getProperty("sortMaxAgeSearchEnDateAsc");
		}
		else if(searchType.equals("dongleEnDate") && flag.equals("false"))
		{
			sql=prop.getProperty("sortMaxAgeSearchEnDateDesc");
		}
		
		else if(searchType.equals("metro") && flag.equals("true"))
		{
			sql=prop.getProperty("sortMaxAgeSearchMetroAsc");
		}
		else if(searchType.equals("metro") && flag.equals("false"))
		{
			sql=prop.getProperty("sortMaxAgeSearchMetroDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(searchType.equals("dongleEnDate"))
			{
				pstmt.setString(1, searchKeyword);
			}
			else if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ListGroup g = new ListGroup();
				g.setDongleName(rs.getString("group_name"));
				g.setManagerId(rs.getString("member_id"));
				g.setTopic(rs.getString("topic_ctg_code"));
				g.setAddress(rs.getString("loc_ctg_code"));
				g.setActiveDate(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportCnt(rs.getInt("report_group_count"));
				dongleList.add(g);			
			}
			System.out.println("DD");
			System.out.println(dongleList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return dongleList;
	}
	
	public List<ListGroup> sortDongleEnrollDate(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ListGroup> dongleList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDongleEnDateAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDongleEnDateDesc");
		}
		
		else if(searchType.equals("dongleName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDongleEnDateSearchDongleNameAsc");
		}
		else if(searchType.equals("dongleName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDongleEnDateSearchDongleNameDesc");
		}
		
		else if(searchType.equals("managerId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDongleEnDateSearchManagerIdAsc");
		}
		else if(searchType.equals("managerId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDongleEnDateSearchManagerIdDesc");
		}
		 
		else if(searchType.equals("dongleEnDate") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDongleEnDateSearchEnDateAsc");
		}
		else if(searchType.equals("dongleEnDate") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDongleEnDateSearchEnDateDesc");
		}
		
		else if(searchType.equals("metro") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDongleEnDateSearchMetroAsc");
		}
		else if(searchType.equals("metro") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDongleEnDateSearchMetroDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(searchType.equals("dongleEnDate"))
			{
				pstmt.setString(1, searchKeyword);
			}
			else if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ListGroup g = new ListGroup();
				g.setDongleName(rs.getString("group_name"));
				g.setManagerId(rs.getString("member_id"));
				g.setTopic(rs.getString("topic_ctg_code"));
				g.setAddress(rs.getString("loc_ctg_code"));
				g.setActiveDate(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportCnt(rs.getInt("report_group_count"));
				dongleList.add(g);			
			}
			System.out.println("DD");
			System.out.println(dongleList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return dongleList;
	}
	
	public List<ListGroup> sortDongleReportCnt(Connection conn, String searchType, String searchKeyword, String flag)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ListGroup> dongleList=new ArrayList();
		String sql = "";
		if(searchType.equals("") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDongleRptCntAsc");
		}
		else if(searchType.equals("") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDongleRptCntDesc");
		}
		
		else if(searchType.equals("dongleName") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDongleRptCntSearchDongleNameAsc");
		}
		else if(searchType.equals("dongleName") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDongleRptCntSearchDongleNameDesc");
		}
		
		else if(searchType.equals("managerId") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDongleRptCntSearchManagerIdAsc");
		}
		else if(searchType.equals("managerId") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDongleRptCntSearchManagerIdDesc");
		}
		 
		else if(searchType.equals("dongleEnDate") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDongleRptCntSearchEnDateAsc");
		}
		else if(searchType.equals("dongleEnDate") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDongleRptCntSearchEnDateDesc");
		}
		
		else if(searchType.equals("metro") && flag.equals("true"))
		{
			sql=prop.getProperty("sortDongleRptCntSearchMetroAsc");
		}
		else if(searchType.equals("metro") && flag.equals("false"))
		{
			sql=prop.getProperty("sortDongleRptCntSearchMetroDesc");
		}
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			if(searchType.equals("dongleEnDate"))
			{
				pstmt.setString(1, searchKeyword);
			}
			else if(!searchType.equals(""))
			{
				System.out.println("2");
				System.out.println(sql);
				
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ListGroup g = new ListGroup();
				g.setDongleName(rs.getString("group_name"));
				g.setManagerId(rs.getString("member_id"));
				g.setTopic(rs.getString("topic_ctg_code"));
				g.setAddress(rs.getString("loc_ctg_code"));
				g.setActiveDate(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportCnt(rs.getInt("report_group_count"));
				dongleList.add(g);			
			}
			System.out.println("DD");
			System.out.println(dongleList);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return dongleList;
	}
	
	
	/*블랙 멤버리스트 dao*/
	public List<Member> selectBlackMemberList(Connection conn, String isBlack)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Member> memberList=new ArrayList();
		String sql="";
		if(isBlack.equals("1"))
		{
			sql=prop.getProperty("selectBlackMemberList");
		}
		else if(isBlack.equals("0"))
		{
			sql=prop.getProperty("selectNonBlackMemberList");
		}
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isBlack);
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
				memberList.add(m);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	/*블랙 추가*/
	public int addBlack(Connection conn, String memberNo[])
	{
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("addBlack");
		try {
			for(int i = 0; i < memberNo.length; i++)
			{
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(memberNo[i]));
			
				result = pstmt.executeUpdate();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}
		return result;
	}
	
	/*블랙 제외*/
	public int deleteBlack(Connection conn, String memberNo[])
	{
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteBlack");
		try {
			for(int i = 0; i < memberNo.length; i++)
			{
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(memberNo[i]));
			
				result = pstmt.executeUpdate();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}
		return result;
	}
	
	/*블랙/논블랙 검색*/
	public List<Member> searchBlack(Connection conn, String searchBlack, String isBlack)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Member> blackList=new ArrayList();
		String sql;
		if(isBlack.equals("1"))
		{
			sql=prop.getProperty("searchBlack");
		}
		else
		{
			sql=prop.getProperty("searchNonBlack");
		}
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchBlack + "%");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("member_gen"));
				m.setSsn(rs.getString("member_ssn"));
				m.setPhone(rs.getString("member_phone"));
				m.setAddress(rs.getString("member_address"));
				m.setEmail(rs.getString("member_email"));
				m.setEnrollDate(rs.getDate("member_enroll_date"));
				m.setBlackList(rs.getString("blacklist_yn"));
				m.setReportCount(rs.getInt("report_member_count"));
				blackList.add(m);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return blackList;
	}
}
