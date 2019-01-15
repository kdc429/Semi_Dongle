package com.dongle.calender.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.dongle.calender.model.dao.BoardDao;
import com.dongle.calender.model.vo.Board;



public class BoardService {

	
	public int insertBoard(Board b)
	{
		Connection conn=getConnection();
		int result=new BoardDao().insertBoard(conn,b);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	};

	
	
	
	
	
	
}




