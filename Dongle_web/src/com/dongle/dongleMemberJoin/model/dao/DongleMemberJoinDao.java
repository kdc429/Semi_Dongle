package com.dongle.dongleMemberJoin.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


import com.dongle.group.model.vo.GroupMember;


public class DongleMemberJoinDao {
	

	private Properties prop=new Properties();
	
	public DongleMemberJoinDao() {
		try {
			String file=DongleMemberJoinDao.class.getResource("./DongleMemberJoinDao-query.properties").getPath();
			prop.load(new FileReader(file));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int insertdongleupdate(Connection conn, GroupMember b)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql = "";
		if(b.getGroupMemberImageOldPath()==null)
		{
			sql=prop.getProperty("insertdongleupdate1");
		}else {
			sql=prop.getProperty("insertdongleupdate2");
		}
		
		try {
			if(b.getGroupMemberImageOldPath()==null)
			{
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, b.getGroupMemberNickname());
				pstmt.setInt(2, b.getGroupNo());
				pstmt.setInt(3, b.getMemberNo());
			}else {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, b.getGroupMemberNickname());
				pstmt.setString(2, b.getGroupMemberImageNewPath());
				pstmt.setString(3, b.getGroupMemberImageOldPath());
				pstmt.setInt(4, b.getGroupNo());
				pstmt.setInt(5, b.getMemberNo());
			}


			result=pstmt.executeUpdate();
			
			System.out.println(result);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertdonglejoin(Connection conn, GroupMember b)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertdonglejoin");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, b.getGroupNo());
			pstmt.setInt(2, b.getMemberNo());
			pstmt.setString(3, b.getGroupMemberNickname());
			pstmt.setString(4, b.getGroupMemberImageNewPath());
			pstmt.setString(5, b.getGroupMemberImageOldPath());
			
			result=pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	
	public GroupMember selectMember(Connection conn, String nickname) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("NickCheck");
		System.out.println("sql : " + sql);
		GroupMember data=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				data = new GroupMember();
				System.out.println("fdsaf" + rs.getString("GROUP_MEMBER_NICKNAME"));
			data.setGroupMemberNickname(rs.getString("GROUP_MEMBER_NICKNAME"));
			data.setGroupNo(1);
			data.setMemberNo(rs.getInt("MEMBER_NO"));
			data.setGroupMemberImageNewPath(rs.getString("GROUP_MEMBER_IMAGE_OLD_PATH"));
			data.setGroupMemberImageOldPath(rs.getString("GROUP_MEMBER_IMAGE_NEW_PATH"));
			data.setGroupMemberEnrollDate(rs.getDate("GROUP_MEMBER_ENROLL_DATE"));
			data.setBlackListYN(rs.getString("BLACKLIST_YN"));
			data.setReportDongleCount(rs.getInt("REPORT_DONGLE_COUNT"));
			}
			System.out.println("ddd" + data);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		
		
		return data;
	}
	
	public int deleteDongleMember(Connection conn, int memberNo, int groupNo)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteDongleMember");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, groupNo);
		
			result=pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}

}
