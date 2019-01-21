package com.dongle.dongleMemberJoin.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.dongle.dongleMemberJoin.model.dao.DongleMemberJoinDao;
import com.dongle.dongleMemberJoin.model.vo.DongleMember;

public class DongleMemberJoinService {
	
	public int insertdonglejoin(DongleMember b)
	{
		Connection conn=getConnection();
		int result=new DongleMemberJoinDao().insertdonglejoin(conn,b);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	};

}
