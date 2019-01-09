package com.dongle.member.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.dongle.member.model.dao.MemberDao;
import com.dongle.member.model.vo.Member;

public class MemberService {
	
	public Member selectMember(Member m) {
		
		Connection conn=getConnection();
		Member data=new MemberDao().selectMember(conn,m);
		close(conn);
		return data;
	}
	
	public int insertMember(Member m)
	{
		Connection conn=getConnection();
		int result=new MemberDao().insertMember(conn, m);
		if(result>0)//입력성공
		{
			commit(conn);
		}
		else{
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int memberUpdate(Member m)
	{
		Connection conn=getConnection();
		int result=new MemberDao().memberUpdate(conn,m);
		if(result>0)
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
