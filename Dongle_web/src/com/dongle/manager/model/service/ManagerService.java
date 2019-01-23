package com.dongle.manager.model.service;

import java.sql.Connection;

import com.dongle.manager.model.dao.ManagerDao;

import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;
import static common.JDBCTemplate.close;

public class ManagerService {

	public int deleteDongle(int groupNo)
	{
		Connection conn = getConnection();
		int result = new ManagerDao().deleteDongle(conn, groupNo);
		
		if(result > 0)
		{
			commit(conn);
		}
		else
		{
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
