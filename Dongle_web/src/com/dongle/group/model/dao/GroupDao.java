package com.dongle.group.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dongle.group.model.vo.EditPickGroup;
import com.dongle.group.model.vo.Group;

public class GroupDao {
	
	Properties prop=new Properties();
	
	public GroupDao() {
		String fileName = GroupDao.class.getResource("./group-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Group> selectGroup(Connection conn, String id){ //가입한 동글 리스트 데이터 dao
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Group> list=new ArrayList();
		Group g=null;
		String sql=prop.getProperty("selectGroup");
		System.out.println(id);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setImgPath(rs.getString("group_image_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				
				list.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		System.out.println(list);
		return list;
		
		
	}
	
	public Group selectGrInfo(Connection conn,int gNo) { // 동글정보 객체 dao
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Group g=null;
		String sql=prop.getProperty("selectGrInfo");
		System.out.println(gNo);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, gNo);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setImgPath(rs.getString("group_image_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		System.out.println(g);
		return g;
	}
	public List<EditPickGroup> selectEditGr(Connection conn){
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<EditPickGroup> editList=new ArrayList();
		EditPickGroup epg=null;
		String sql=prop.getProperty("selectEditGr");
		
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			
			while(rs.next()) {
				epg=new EditPickGroup();
				epg.setGroupNo(rs.getInt("group_no"));
				epg.setEditFilePath(rs.getString("edit_file_path"));
				epg.setEditContent(rs.getString("edit_content"));
				
				editList.add(epg);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return editList;
	}

}
