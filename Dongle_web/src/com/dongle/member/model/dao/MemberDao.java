package com.dongle.member.model.dao;

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

import com.dongle.member.model.vo.Member;
import com.dongle.member.model.vo.ReportReason;


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
				data.setMemberNo(rs.getInt("member_no"));
				data.setMemberId(rs.getString("member_id"));
				data.setMemberPwd(rs.getString("member_pwd"));
				data.setMemberName(rs.getString("member_name"));
				data.setGender(rs.getString("member_gen"));
				data.setSsn(rs.getString("member_ssn"));
				data.setEmail(rs.getString("member_email"));
				data.setPhone(rs.getString("member_phone"));
				data.setAddress(rs.getString("member_address"));
				data.setEnrollDate(rs.getDate("member_enroll_date"));
				data.setPwdHintList(rs.getString("pwd_hint_list"));
				data.setPwdHintAnswer(rs.getString("pwd_hint_answer"));
				data.setBlackList(rs.getString("blacklist_yn"));
				data.setReportCount(rs.getInt("report_member_count"));

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		close(rs);
		
		return data;
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
	
	public int resetPassword(Connection conn, Member data)
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
	
	public int insertMember(Connection conn, Member m)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertMember");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			pstmt.setString(3, m.getPwdHintList());
			pstmt.setString(4, m.getPwdHintAnswer());
			pstmt.setString(5, m.getMemberName());
			pstmt.setString(6, m.getGender());
			pstmt.setString(7, m.getSsn());
			pstmt.setString(8, m.getPhone());
			pstmt.setString(9, m.getAddress());
			pstmt.setString(10, m.getEmail());

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
	
	public Member selectId(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectId");
		Member data=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberName());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				data=new Member();
				data.setMemberId(rs.getString("member_id"));
				data.setMemberName(m.getMemberName());
				data.setEmail(rs.getString("member_email"));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		close(rs);
		
		return data;
	}
	
	public Member selectPwd(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectPwd");
		Member data = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,m.getMemberId());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				data=new Member();
				data.setMemberPwd(rs.getString("member_pwd"));				
				data.setEmail(rs.getString("member_email"));
				data.setPwdHintList(rs.getString("pwd_hint_list"));
				data.setPwdHintAnswer(rs.getString("pwd_hint_answer"));
				data.setMemberId(m.getMemberId());				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		close(rs);
		
		return data;
			
	}
	
	public List<ReportReason> selectReportCategory(Connection conn){
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectReportCategory");
		List<ReportReason> reportCategory=new ArrayList();
		ReportReason rr=null;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				rr=new ReportReason();
				rr.setReportCode(rs.getInt("report_code"));
				rr.setReportReason(rs.getString("report_reason"));
				reportCategory.add(rr);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return reportCategory;
		
	}
	
	public int insertReport(Connection conn,int groupNo,int memberNo,String reportCode) {
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("insertReport");
		int result=0;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, reportCode);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
}
