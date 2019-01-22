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
	
	public GroupMember selectMember(Connection conn, GroupMember m) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("NickCheck");
		GroupMember data=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getGroupMemberNickname());
			rs=pstmt.executeQuery();
				
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		close(rs);
		
		return data;
	}
	
	

}
