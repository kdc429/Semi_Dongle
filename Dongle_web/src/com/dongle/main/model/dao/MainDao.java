package com.dongle.main.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dongle.group.model.vo.Group;
import com.dongle.main.model.vo.LocationCategory;
import com.dongle.main.model.vo.TopicCategory;

public class MainDao {
	
	private Properties prop = new Properties(); 
	
	public MainDao()
	{
		String fileName = MainDao.class.getResource("./main-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public List<LocationCategory> selectMetroCode(Connection conn)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LocationCategory>metroCodeList = new ArrayList<LocationCategory>();
		String sql = prop.getProperty("selectMetroCode");
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				LocationCategory lc = new LocationCategory();
				lc.setMetroCode(rs.getString("metro_code"));
				lc.setLocMetroName(rs.getString("loc_metro_name"));
				metroCodeList.add(lc);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return metroCodeList;
	}
	
	public List<TopicCategory> selectTopicCategory(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectTopicCategory");
		TopicCategory tc=null;
		List<TopicCategory> topicList=new ArrayList();
		
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				tc=new TopicCategory();
				tc.setTopicCtgCode(rs.getInt("topic_ctg_code"));
				tc.setTopicCtgName(rs.getString("topic_ctg_name"));
				
				topicList.add(tc);
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return topicList;
	}
	
	public List<LocationCategory> selectLocationList(Connection conn,String locationCode){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		LocationCategory lc=null;
		String sql=prop.getProperty("selectLocationList");
		List<LocationCategory> locationList=new ArrayList();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, locationCode);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				lc=new LocationCategory();
				
				lc.setLocCtgCode(rs.getString("loc_ctg_code"));
				lc.setMetroCode(rs.getString("metro_code"));
				lc.setLocMetroName(rs.getString("loc_metro_name"));
				lc.setAreaCode(rs.getString("area_code"));
				lc.setLocAreaName(rs.getString("loc_area_name"));
				lc.setTownCode(rs.getString("town_code"));
				lc.setLocTownName(rs.getString("loc_town_name"));
				locationList.add(lc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return locationList;
	}
	
	public List<LocationCategory> selectAreaList(Connection conn,String locationCode){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		LocationCategory lc=null;
		String sql=prop.getProperty("selectAreaList");
		List<LocationCategory> locationList=new ArrayList();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, locationCode);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				lc=new LocationCategory();
				
				lc.setAreaCode(rs.getString("area_code"));
				lc.setLocAreaName(rs.getString("loc_area_name"));
				
				locationList.add(lc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return locationList;
	}
	
	public List<LocationCategory> selectTownList(Connection conn,String locationCode){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		LocationCategory lc=null;
		String sql=prop.getProperty("selectTownList");
		List<LocationCategory> locationList=new ArrayList();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, locationCode);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				lc=new LocationCategory();
				
				lc.setLocCtgCode(rs.getString("loc_ctg_code"));
				lc.setMetroCode(rs.getString("metro_code"));
				lc.setLocMetroName(rs.getString("loc_metro_name"));
				lc.setLocTownName(rs.getString("loc_town_name"));
				
				locationList.add(lc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return locationList;
	}
	
	public List<Group> selectAllCheckList(Connection conn,String locArr,String topicArr,int minAge,int maxAge, String time){
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MIN_AGE BETWEEN "+minAge+" AND "+maxAge+" AND MAX_AGE BETWEEN "+minAge+" AND "+maxAge+" AND GROUP_DATE_CTG='"+time+"') WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE LOC_CTG_CODE IN("+locArr+") AND TOPIC_CTG_CODE IN("+topicArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
		
	}
	
	public List<Group> selectLocationNullCheckList(Connection conn,String topicArr,int minAge,int maxAge, String time){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MIN_AGE BETWEEN "+minAge+" AND "+maxAge+" AND MAX_AGE BETWEEN "+minAge+" AND "+maxAge+" AND GROUP_DATE_CTG="+time+") WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE TOPIC_CTG_CODE IN("+topicArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
		
		
		
	}
	
	public List<Group> selectLocTopicNullCheckList(Connection conn,int minAge,int maxAge, String time){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM GROUP_TAB WHERE MIN_AGE BETWEEN "+minAge+" AND "+maxAge+" AND MAX_AGE BETWEEN "+minAge+" AND "+maxAge+" AND GROUP_DATE_CTG='"+time+"'";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectLocTopicMinNullCheckList(Connection conn,int maxAge, String time){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM GROUP_TAB WHERE MAX_AGE<="+maxAge+" AND GROUP_DATE_CTG='"+time+"'";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
		
		
	}
	
	public List<Group> selectLocTopicMinMaxNullCheckList(Connection conn,String time){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM GROUP_TAB WHERE GROUP_DATE_CTG='"+time+"'";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectTimeNullCheckList(Connection conn,String locArr,String topicArr,int minAge,int maxAge){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MIN_AGE BETWEEN "+minAge+" AND "+maxAge+" AND MAX_AGE BETWEEN "+minAge+" AND "+maxAge+") WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE LOC_CTG_CODE IN("+locArr+") AND TOPIC_CTG_CODE IN("+topicArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
		
	}
	
	public List<Group> selectMaxTimeNullCheckList(Connection conn,String locArr,String topicArr,int minAge){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MIN_AGE>="+minAge+") WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE LOC_CTG_CODE IN("+locArr+") AND TOPIC_CTG_CODE IN("+topicArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
		
	}
	
	public List<Group> selectMinMaxTimeNullCheckList(Connection conn,String locArr,String topicArr){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM GROUP_TAB WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE LOC_CTG_CODE IN("+locArr+") AND TOPIC_CTG_CODE IN("+topicArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
		
	}
	
	public List<Group> selectTopicMinMaxTimeNullCheckList(Connection conn,String locArr){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM GROUP_TAB WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE LOC_CTG_CODE IN("+locArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
		
	}
	
	public List<Group> selectMinTimeNullCheckList(Connection conn,String locArr,String topicArr,int maxAge){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MAX_AGE<="+maxAge+") WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE LOC_CTG_CODE IN("+locArr+") AND TOPIC_CTG_CODE IN("+topicArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
		
	}
	
	public List<Group> selectTopicCheckList(Connection conn,String topicArr){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM GROUP_TAB WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE TOPIC_CTG_CODE IN("+topicArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
		
	}
	
	public List<Group> selectLocMaxNullCheckList(Connection conn,String topicArr,int minAge,String time){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MIN_AGE>="+minAge+" AND GROUP_DATE_CTG='"+time+"') WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE TOPIC_CTG_CODE IN("+topicArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
		
	}
	
	public List<Group> selectTopicTimeCheckList(Connection conn,String topicArr,String time){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE GROUP_DATE_CTG='"+time+"') WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE TOPIC_CTG_CODE IN("+topicArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectTopicMinCheckList(Connection conn,String topicArr,int minAge){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MIN_AGE>='"+minAge+"') WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE TOPIC_CTG_CODE IN("+topicArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectLocTimeNullCheckList(Connection conn,String topicArr,int minAge,int maxAge){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MIN_AGE BETWEEN "+minAge+" AND "+maxAge+" AND MAX_AGE BETWEEN "+minAge+" AND "+maxAge+") WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE TOPIC_CTG_CODE IN("+topicArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectMinMaxCheckList(Connection conn,int minAge,int maxAge){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM GROUP_TAB WHERE MIN_AGE BETWEEN "+minAge+" AND "+maxAge+" AND MAX_AGE BETWEEN "+minAge+" AND "+maxAge;
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectMinCheckList(Connection conn,int minAge){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM GROUP_TAB WHERE MIN_AGE>="+minAge;
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectMinTimeCheckList(Connection conn,int minAge,String time){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM GROUP_TAB WHERE MIN_AGE>="+minAge+" GROUP_DATE_CTG='"+time+"'";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectMaxCheckList(Connection conn,int maxAge){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM GROUP_TAB WHERE MAX_AGE<="+maxAge;
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectTopicMaxCheckList(Connection conn,String topicArr,int maxAge){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MAX_AGE<='"+maxAge+"') WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE TOPIC_CTG_CODE IN("+topicArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectTopicTimeNullCheckList(Connection conn,String locArr,int maxAge,int minAge){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MIN_AGE BETWEEN "+minAge+" AND "+maxAge+" AND MAX_AGE BETWEEN "+minAge+" AND "+maxAge+") WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE LOC_CTG_CODE IN("+locArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectLocTimeCheckList(Connection conn,String locArr,String time){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE GROUP_DATE_CTG='"+time+"') WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE LOC_CTG_CODE IN("+locArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectTopicMinNullCheckList(Connection conn,String locArr,int maxAge,String time){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MAX_AGE<="+maxAge+" AND GROUP_DATE_CTG='"+time+"') WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE LOC_CTG_CODE IN("+locArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectMinNullCheckList(Connection conn,String locArr,String topicArr,int maxAge,String time){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MAX_AGE<="+maxAge+" AND GROUP_DATE_CTG='"+time+"') WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE LOC_CTG_CODE IN("+locArr+")";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectTopicNullCheckList(Connection conn,String locArr,int minAge,int maxAge,String time){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MIN_AGE BETWEEN "+minAge+" AND "+maxAge+" AND MAX_AGE BETWEEN "+minAge+" AND "+maxAge+" AND GROUP_DATE_CTG='"+time+"') WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE LOC_CTG_CODE IN("+locArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectTopicMaxNullCheckList(Connection conn,String locArr,int minAge,String time){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MIN_AGE>="+minAge+" AND GROUP_DATE_CTG='"+time+"') WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE LOC_CTG_CODE IN("+locArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectMinMaxNullCheckList(Connection conn,String locArr,String topicArr,String time){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE GROUP_DATE_CTG='"+time+"') WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE LOC_CTG_CODE IN("+locArr+") AND TOPIC_CTG_CODE IN("+topicArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectLocMinCheckList(Connection conn,String locArr,int minAge){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MIN_AGE>="+minAge+") WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE LOC_CTG_CODE IN("+locArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectLocMaxCheckList(Connection conn,String locArr,int maxAge){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MAX_AGE<="+maxAge+") WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE LOC_CTG_CODE IN("+locArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectMaxNullCheckList(Connection conn,String locArr,String topicArr,int minAge,String time){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MIN_AGE>="+minAge+" AND GROUP_DATE_CTG='"+time+"') WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE LOC_CTG_CODE IN("+locArr+") AND TOPIC_CTG_CODE IN("+topicArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	public List<Group> selectLocMinNullCheckList(Connection conn,String topicArr,int maxAge,String time){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM (SELECT * FROM GROUP_TAB WHERE MAX_AGE<="+maxAge+" AND GROUP_DATE_CTG='"+time+"') WHERE GROUP_NO IN (SELECT GROUP_NO FROM (SELECT GROUP_NO,LOC_CTG_CODE,TOPIC_CTG_CODE FROM MULTI_LOCATION_TAB JOIN MULTI_TOPIC_TAB USING(GROUP_NO)) WHERE AND TOPIC_CTG_CODE IN("+topicArr+"))";
		Group g=null;
		List<Group> groupList=new ArrayList();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				g=new Group();
				g.setMemberNo(rs.getInt("member_no"));
				g.setGroupNo(rs.getInt("group_no"));
				g.setGroupName(rs.getString("group_name"));
				g.setTopicCode(rs.getString("topic_ctg_code"));
				g.setLocCtgCode(rs.getString("loc_ctg_code"));
				g.setGroupDateCtg(rs.getString("group_date_ctg"));
				g.setMinAge(rs.getInt("min_age"));
				g.setMaxAge(rs.getInt("max_age"));
				g.setGroupImageOldPath(rs.getString("group_image_old_path"));
				g.setGroupImageNewPath(rs.getString("group_image_new_path"));
				g.setGroupIntro(rs.getString("group_introduce"));
				g.setGroupEnrollDate(rs.getDate("group_enroll_date"));
				g.setReportGroupCnt(rs.getInt("report_group_count"));
				g.setGroupMainOldImgPath(rs.getString("group_main_img_old_path"));
				g.setGroupMainNewImgPath(rs.getString("group_main_img_new_path"));
				
				groupList.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
	}
	
	

}
