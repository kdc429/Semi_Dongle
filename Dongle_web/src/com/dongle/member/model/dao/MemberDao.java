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
			prop.load(new FileReader(fileName));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Member selectMember(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("loginCheck");
		Member data=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberId());
//			pstmt.setString(2, pw);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				data=new Member();
				data.setMemberId(rs.getString("member_id"));
				data.setMemberPwd(rs.getString("member_pwd"));
//				data.setMemberName(rs.getString("username"));
//				data.setGender(rs.getString("gender"));
//				data.setAge(rs.getInt("age"));
//				data.setEmail(rs.getString("email"));
//				data.setPhone(rs.getString("phone"));
//				data.setAddress(rs.getString("address"));
//				data.setHobby(rs.getString("hobby"));
//				data.setEnrollDate(rs.getDate("enrolldate"));
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
}
