package com.dongle.calender.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.dongle.calender.model.vo.Calendar;

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
		Statement stmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("insertBoard");
		try {

			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, b.getGroupno());
			pstmt.setString(2,b.getCaldate_d());
			pstmt.setString(3,b.getCalcontent());
			pstmt.setString(4,b.getCaltitle());
			pstmt.setString(5,b.getCalorfile());
			pstmt.setString(6,b.getRefile());
			
			result=pstmt.executeUpdate();
			if(result>0) {
				stmt=conn.createStatement();
				rs=stmt.executeQuery("SELECT SEQ_CAL_NO.CURRVAL AS NO FROM DUAL");
				
				if(rs.next()) {
					result=rs.getInt("no");
				}
				
			}
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		System.out.println("result:"+result);
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
			pstmt.setInt(1, c.getGroupno());
			pstmt.setInt(2, c.getCalno());
			pstmt.setInt(3, c.getTotalcost());
			pstmt.setInt(4, c.getUsecost());
			
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
