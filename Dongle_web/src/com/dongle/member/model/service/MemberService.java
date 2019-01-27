package com.dongle.member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.dongle.member.model.dao.MemberDao;
import com.dongle.member.model.vo.Member;
import com.dongle.member.model.vo.ReportReason;

public class MemberService {
	
	public Member selectMember(Member m) {
		
		Connection conn=getConnection();
		Member data=new MemberDao().selectMember(conn,m);
		close(conn);
		return data;
	}
	
	public int updatePassword(Member data)
	{
		Connection conn=getConnection();
		int result=new MemberDao().updatePassword(conn,data);
		if(result>0)
		{
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;		
	}
	public int resetPassword(Member data)
	{
		Connection conn=getConnection();
		int result=new MemberDao().resetPassword(conn,data);
		if(result>0)
		{
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;		
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
	
	public int memberDelete(Member m)
	{
		Connection conn=getConnection();
		int result=new MemberDao().memberDelete(conn,m);
		System.out.println(result);
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
	
	public Member selectId(Member m) {
		Connection conn = getConnection();
		Member data = new MemberDao().selectId(conn, m);
		close(conn);
		return data;		
	}
	
	public Member selectPwd(Member m)
	{
		Connection conn = getConnection();
		Member data = new MemberDao().selectPwd(conn,m);
		close(conn);
		return data;
	}
	
	public List<ReportReason> selectReportCategory(){
		Connection conn=getConnection();
		List<ReportReason> reportCategory=new MemberDao().selectReportCategory(conn);
		close(conn);
		return reportCategory;
	}
	
	public int insertReport(int groupNo,int memberNo,String reportCode) {
		Connection conn=getConnection();
		int result=0;
		result=new MemberDao().insertReport(conn,groupNo,memberNo,reportCode);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	
		
	}

}
