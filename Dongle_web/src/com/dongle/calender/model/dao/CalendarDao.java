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

import com.dongle.calender.model.vo.Calendar;

import static common.JDBCTemplate.close;

public class CalendarDao {

	private Properties prop=new Properties();
	
	public CalendarDao() {
		try {
			String file=CalendarDao.class.getResource("./Calendar-query.properties").getPath();
			prop.load(new FileReader(file));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int insertcalendar(Connection conn, Calendar b)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertBoard");
		try {

			pstmt=conn.prepareStatement(sql);
//			pstmt.setInt(1, b.getGroupno());
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
	
	public int costcalendar(Connection conn, Calendar c)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("remaincost");
		System.out.println(sql);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, c.getTotalcost());
			pstmt.setInt(2, c.getUsecost());
			
			System.out.println(c.getTotalcost()+" " + c.getUsecost());
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
		System.out.println(result);
		return result;
	}

}
