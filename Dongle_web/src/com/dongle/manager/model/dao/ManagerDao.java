package com.dongle.manager.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static common.JDBCTemplate.close;

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
}
