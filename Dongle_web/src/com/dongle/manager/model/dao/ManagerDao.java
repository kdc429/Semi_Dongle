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

import com.dongle.group.model.vo.Group;
import com.dongle.group.model.vo.GroupMember;
import com.dongle.member.model.vo.DongleRptMember;

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
	
	public List<GroupMember> selectMemberList(Connection conn, int groupNo)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<GroupMember> memberList=new ArrayList<GroupMember>();
		String sql=prop.getProperty("selectMemberList");

		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				GroupMember gm = new GroupMember();

				gm.setGroupNo(rs.getInt("group_no"));
				gm.setMemberNo(rs.getInt("member_no"));
				gm.setGroupMemberNickname(rs.getString("group_member_nickname"));
				gm.setGroupMemberImageOldPath(rs.getString("group_member_image_old_path"));
				gm.setGroupMemberImageNewPath(rs.getString("group_member_image_new_path"));
				gm.setGroupMemberEnrollDate(rs.getDate("group_member_enroll_date"));
				gm.setBlackListYN(rs.getString("group_blacklist_yn"));
				gm.setReportDongleCount(rs.getInt("report_dongle_count"));

				memberList.add(gm);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	public int deleteMemberSubmit(Connection conn, int groupNo, int selectMemberNo)
	{
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteMemberSubmit");
		try
		{
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, selectMemberNo);
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
	
	public int updateDongle(Connection conn, Group g)
	{
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateDongle");
		try
		{
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, g.getGroupName());
			pstmt.setString(2, g.getTopicCode());
			pstmt.setString(3, g.getLocCtgCode());
			pstmt.setString(4, g.getGroupDateCtg());
			pstmt.setInt(5, g.getMinAge());
			pstmt.setInt(6, g.getMaxAge());
			pstmt.setString(7, g.getGroupImageOldPath());
			pstmt.setString(8, g.getGroupImageNewPath());
			pstmt.setString(9, g.getGroupIntro());
			pstmt.setInt(10, g.getGroupNo());
			
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
	
	public String selectLocCtgCode(Connection conn, String metroCode, String areaCode, String townCode)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String locCode = "";
		String sql = "";
		if(townCode.equals(""))
		{
			if(areaCode.equals(""))
			{
				sql=prop.getProperty("selectLocCtgCode1");
			}
			else
			{
				sql=prop.getProperty("selectLocCtgCode2");
			}
		}
		else
		{
			sql=prop.getProperty("selectLocCtgCode3");
		}
		

		try {
			pstmt=conn.prepareStatement(sql);
			if(townCode.equals(""))
			{
				if(areaCode.equals(""))
				{
					pstmt.setString(1, metroCode);
				}
				else
				{
					pstmt.setString(1, metroCode);
					pstmt.setString(2, areaCode);
				}
			}
			else
			{
				pstmt.setString(1, metroCode);
				pstmt.setString(2, areaCode);
				pstmt.setString(3, townCode);
			}
			
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				locCode = rs.getString("loc_ctg_code");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return locCode;
	}
	
	public int deleteMultiTopic(Connection conn, int groupNo)
	{
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteMultiTopic");
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
	
	public int updateMultiTopic(Connection conn, String[] topicCode, int groupNo)
	{
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateMultiTopic");
		try
		{	
			for(int i = 0; i < topicCode.length; i++)
			{
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, groupNo);
				pstmt.setString(2, topicCode[i]);
				
				
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
	
	public int deleteMultiLoc(Connection conn, int groupNo)
	{
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteMultiLoc");
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
	
	public int updateMultiLoc(Connection conn, String[] locCode, int groupNo)
	{
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateMultiLoc");
		try
		{	
			for(int i = 0; i < locCode.length; i++)
			{
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, groupNo);
				pstmt.setString(2, locCode[i]);
				
				
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
	
}

