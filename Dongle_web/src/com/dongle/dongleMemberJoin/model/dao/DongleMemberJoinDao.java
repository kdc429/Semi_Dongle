package com.dongle.dongleMemberJoin.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.dongle.dongleMemberJoin.model.vo.DongleMember;

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
	
	public int insertdonglejoin(Connection conn, DongleMember b)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertdonglejoin");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b.getNickname() );
			pstmt.setString(2, b.getCalorfile());
			pstmt.setString(3, b.getRefile());
			
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
