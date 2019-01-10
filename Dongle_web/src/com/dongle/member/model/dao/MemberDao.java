package com.dongle.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static common.JDBCTemplate.close;
import com.dongle.member.model.vo.Member;


public class MemberDao {
	
	Properties prop=new Properties();
	public MemberDao() {
		String fileName = MemberDao.class.getResource("./memberquery.properties").getPath();

		try {
			prop.load(new FileReader(fileName)); //properties 로드

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Member selectMember(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("loginCheck");
		Member data=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberId());
//			pstmt.setString(2, pw);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {//일단 아이디 패스워드만 받음 추후 수정할 예정
				data=new Member();
				data.setMemberNo(rs.getInt("member_no"));
				data.setMemberId(rs.getString("member_id"));
				data.setMemberPwd(rs.getString("member_pwd"));
				data.setMemberName(rs.getString("member_name"));
				data.setGender(rs.getString("member_gen"));
				data.setSsn(rs.getString("member_ssn"));
				data.setEmail(rs.getString("member_email"));
				data.setPhone(rs.getString("member_phone"));
				data.setAddress(rs.getString("member_address"));
				data.setEnrollDate(rs.getDate("member_enroll_date"));
				data.setBlackList(rs.getInt("blacklist_yn"));
				data.setReportCount(rs.getInt("report_member_count"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		close(rs);
		
		return data;
	}
}
