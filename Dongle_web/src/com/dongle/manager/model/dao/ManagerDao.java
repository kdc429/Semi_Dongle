package com.dongle.manager.model.dao;

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

import com.dongle.member.model.vo.DongleRptMember;
import com.dongle.member.model.vo.Member;

public class ManagerDao {
private Properties prop = new Properties();
	
	public ManagerDao()
	{
		try {
			String fileName = ManagerDao.class.getResource("./manager-query.properties").getPath();
			prop.load(new FileReader(fileName));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	/*동글 삭제*/
	public int deleteDongle(Connection conn, int groupNo)
	{
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteDongle");
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			
			result = pstmt.executeUpdate();
			
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
	
	public List<DongleRptMember> selectRptMember(Connection conn, int groupNo)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<DongleRptMember> memberList=new ArrayList();
		String sql=prop.getProperty("selectRptMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			pstmt.setString(2, "0");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				DongleRptMember m = new DongleRptMember();
				m.setMemberNo(rs.getInt("member_no"));
				m.setGroupNo(rs.getInt("group_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberNickname(rs.getString("group_member_nickname"));
				m.setRptCode(rs.getString("report_code"));
				m.setRptReason(rs.getString("report_reason"));
				m.setRptCount(rs.getInt("report_dongle_count"));
				m.setIsBlack(rs.getString("group_blacklist_yn"));
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
	
	public int submitRptMember(Connection conn, int groupNo, int selectMember, int rptCount)
	{
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("submitRptMember");
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rptCount);
			pstmt.setInt(2, selectMember);
			pstmt.setInt(3, groupNo);
			
			result = pstmt.executeUpdate();
			
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
	
	public int blackMember(Connection conn, int groupNo, int selectMember)
	{
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("blackMember");
		try
		{
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, selectMember);
			pstmt.setInt(2, groupNo);
			
			result = pstmt.executeUpdate();
			
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
	
	public int cntUpRptMember(Connection conn, int selectMember)
	{
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("cntUpRptMember");
		try
		{
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, selectMember);
			pstmt.setInt(2, selectMember);
			
			
			result = pstmt.executeUpdate();
			
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
	
	public int deleteRptMember(Connection conn, int groupNo, int selectMember)
	{
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteRptMember");
		try
		{
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, selectMember);
			pstmt.setInt(2, groupNo);
			
			result = pstmt.executeUpdate();
			
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
	
}
