package com.dongle.dongleMemberJoin.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.dongle.dongleMemberJoin.model.dao.DongleMemberJoinDao;
import com.dongle.group.model.vo.GroupMember;


public class DongleMemberJoinService {
	
	public int insertdonglejoin(GroupMember b)
	{
		Connection conn=getConnection();
		int result=new DongleMemberJoinDao().insertdonglejoin(conn,b);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	};
	
	public GroupMember selectMember(String nickname)
	{	
		//contrller가 전달한 정보와 DB접속정보를 DAO에게 전달
		//DB접속정보(Connection)에 대한 관리 : 객체반환(close())
		//insert,update,delete한 후 rollback, commit관리!
		Connection conn=getConnection();
		GroupMember data=new DongleMemberJoinDao().selectMember(conn,nickname);
		close(conn);
		return data;
		
	}

}
