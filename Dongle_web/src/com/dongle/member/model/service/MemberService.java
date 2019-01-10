package com.dongle.member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

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
}
