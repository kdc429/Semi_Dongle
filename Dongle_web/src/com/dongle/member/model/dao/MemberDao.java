package com.dongle.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static common.JDBCTemplate.close;
import com.dongle.member.model.vo.Member;


public class MemberDao {
	
	Properties prop=new Properties();
	public MemberDao() {
		String fileName = MemberDao.class.getResource("./memberquery.properties").getPath();

		try {
			prop.load(new FileReader(fileName)); //properties 로드

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int updatePassword(Connection conn, Member data)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updatePassword");
		try 
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, data.getMemberPwd());
			pstmt.setString(2, data.getMemberId());
			result=pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}
		return result;
	}

	public Member selectMember(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("loginCheck");
		Member data=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberId());
//			pstmt.setString(2, pw));
			rs=pstmt.executeQuery();
			
			if(rs.next()) {//일단 아이디 패스워드만 받음 추후 수정할 예정
				data=new Member();
				data.setMemberNo(rs.getInt("MEMBER_NO"));
				data.setMemberId(rs.getString("MEMBER_ID"));
				data.setMemberPwd(rs.getString("MEMBER_PWD"));
				data.setMemberName(rs.getString("MEMBER_NAME"));
				data.setGender(rs.getString("MEMBER_GEN"));
				data.setSsn(rs.getString("MEMBER_SSN"));
				data.setPhone(rs.getString("MEMBER_PHONE"));
				data.setAddress(rs.getString("MEMBER_ADDRESS"));
				data.setEmail(rs.getString("MEMBER_EMAIL"));
				data.setEnrollDate(rs.getDate("MEMBER_ENROLL_DATE"));
				data.setBlackList(rs.getInt("BLACKLIST_YN"));
				data.setReportCount(rs.getInt("REPORT_MEMBER_COUNT"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		close(rs);
		
		return data;
	}
	
	public int insertMember(Connection conn, Member m)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertMember");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getGender());
			pstmt.setString(5, m.getSsn());
			pstmt.setString(6, m.getPhone());
			pstmt.setString(7, m.getAddress());
			pstmt.setString(8, m.getEmail());

			result=pstmt.executeUpdate();						
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		
		return result;		
		
	}
	
	public int memberUpdate (Connection conn, Member m)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("memberUpdate");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberName());
			pstmt.setString(2, m.getSsn());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getMemberId());
			
			
			result=pstmt.executeUpdate();
			System.out.println(result);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	
	public int memberDelete(Connection conn, Member m)
	{
		PreparedStatement pstmt = null;
		int result =0;
		String sql = prop.getProperty("memberDelete");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,m.getMemberId());
			
			result=pstmt.executeUpdate();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
}
