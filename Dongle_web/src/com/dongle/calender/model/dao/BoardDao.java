package com.dongle.calender.model.dao;

import java.io.FileReader;
import java.security.KeyStore.ProtectionParameter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dongle.calender.model.vo.Board;

import static common.JDBCTemplate.close;

public class BoardDao {

	private Properties prop=new Properties();
	
	public BoardDao() {
		try {
			String file=BoardDao.class.getResource("./board-query.properties").getPath();
			prop.load(new FileReader(file));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int insertBoard(Connection conn, Board b)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,b.getCaldate_d());
			pstmt.setString(2,b.getCalcontent());
			pstmt.setString(3,b.getCaltitle());
			pstmt.setString(4,b.getCalorfile());
			pstmt.setString(5,b.getRefile());
			
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
