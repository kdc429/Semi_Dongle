package com.dongle.calender.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.dongle.calender.model.dao.CalendarDao;
import com.dongle.calender.model.vo.Calendar;



public class CalendarService {

	
	public int insertcalendar(Calendar b)
	{
		Connection conn=getConnection();
		int result=new CalendarDao().insertcalendar(conn,b);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	};
	
	public int costcalendar(Calendar c) 
	{
		Connection conn=getConnection();
		System.out.println(1111);
		int result=new CalendarDao().costcalendar(conn,c);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	};

	
	
	
	
	
	
}




