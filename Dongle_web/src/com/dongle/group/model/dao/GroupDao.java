package com.dongle.group.model.dao;

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

import com.dongle.gallery.model.vo.GalleryPath;
import com.dongle.group.model.vo.EditPickGroup;
import com.dongle.group.model.vo.Group;
import com.dongle.group.model.vo.GroupMember;
import com.dongle.group.model.vo.GroupMemberCount;
import com.dongle.group.model.vo.LocalCtg;
import com.dongle.group.model.vo.MultiLocation;
import com.dongle.group.model.vo.MultiLocationName;
import com.dongle.group.model.vo.MultiTopic;
import com.dongle.group.model.vo.MultiTopicName;
import com.dongle.group.model.vo.TopicCtg;

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
	
	public List<Group> selectAllGroupList(Connection conn)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Group> groupList = new ArrayList();
		Group g = null;
		String sql = prop.getProperty("selectAllGroupList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
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
		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return groupList;
	}
	
	public List<Group> selectGroup(Connection conn, String id){ //가입한 동글 리스트 데이터 dao (주소 카테고리 제외, 추가예정)
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Group> list=new ArrayList();
		Group g=null;
		String sql=prop.getProperty("selectGroup");
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
				
				list.add(g);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
		
		
	}
	
	public Group selectGrInfo(Connection conn,int groupNo) { // 동글정보 객체 dao (주소 카테고리 제외, 추가예정)
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Group g=null;
		String sql=prop.getProperty("selectGrInfo");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
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
				
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
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
				epg.setEditOldFilePath(rs.getString("edit_old_file_path"));
				epg.setEditNewFilePath(rs.getString("edit_new_file_path"));
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
	
	public List<Group> selectRank(Connection conn){
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Group g=null;
		List<Group> rankList=new ArrayList<Group>();
		String sql="SELECT * FROM GROUP_TAB JOIN(SELECT GROUP_NO,COUNT(MEMBER_NO) FROM GROUP_MEMBER_TAB GROUP BY GROUP_NO) USING(GROUP_NO) WHERE ROWNUM BETWEEN 1 AND 5";
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
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
				
				rankList.add(g);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return rankList;
	}
	
	 public GroupMember selectGmInfo(Connection conn, int groupNo,int memberNo)
     {
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        GroupMember gm = null;
        String sql=prop.getProperty("selectGmInfo");
        try {
           pstmt=conn.prepareStatement(sql);
           System.out.println("dao"+sql);
           pstmt.setInt(1, groupNo);
           pstmt.setInt(2, memberNo);
           
           rs=pstmt.executeQuery();
           
           if(rs.next()) {
              gm=new GroupMember();
              gm.setGroupNo(rs.getInt("group_no"));
              gm.setMemberNo(rs.getInt("member_no"));         
              gm.setGroupMemberNickname(rs.getString("group_member_nickname"));
              gm.setGroupMemberImageOldPath(rs.getString("group_member_image_old_path"));
              gm.setGroupMemberImageNewPath(rs.getString("group_member_image_new_path"));
              gm.setGroupMemberEnrollDate(rs.getDate("group_member_enroll_date"));
              gm.setBlackListYN(rs.getString("group_blacklist_yn"));
              gm.setReportDongleCount(rs.getInt("report_dongle_count"));
           }
        }catch(SQLException e) {
           e.printStackTrace();
        }finally {
           close(rs);
           close(pstmt);
        }
        return gm;
     }
	 
	 public int countMember(Connection conn, int groupNo)
	 {
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 int result = 0;
		 String sql = prop.getProperty("countMember");
		 try {
			 pstmt=conn.prepareStatement(sql);
			 pstmt.setInt(1,groupNo);
			 rs=pstmt.executeQuery();
			 if(rs.next())
			 {
				 result = rs.getInt("member_cnt");
			 }
		 }
		 catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
		 finally {
			 close(pstmt);
		 }
		 return result;
	}

	public List<GroupMember> selectMemberList(Connection conn, int groupNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<GroupMember> list=new ArrayList<GroupMember>();
		String sql=prop.getProperty("selectMemberList");

		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				GroupMember gm = new GroupMember();

	              gm.setGroupNo(rs.getInt("group_no"));
	              gm.setMemberNo(rs.getInt("member_no"));         
	              gm.setGroupMemberNickname(rs.getString("group_member_nickname"));
	              gm.setGroupMemberImageOldPath(rs.getString("group_member_image_old_path"));
	              gm.setGroupMemberImageNewPath(rs.getString("group_member_image_new_path"));
	              gm.setGroupMemberEnrollDate(rs.getDate("group_member_enroll_date"));
	              gm.setBlackListYN(rs.getString("group_blacklist_yn"));
	              gm.setReportDongleCount(rs.getInt("report_dongle_count"));

				
				list.add(gm);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public List<GalleryPath> selectAllGallery(Connection conn, int groupNo){
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectAllGallery");
		List<GalleryPath> galList=new ArrayList<GalleryPath>();
		GalleryPath gp = null;		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			rs=pstmt.executeQuery();
			
			
			while(rs.next()) {
				gp=new GalleryPath();
				gp.setGroupNo(rs.getInt("group_no"));
				gp.setAlbumCode(rs.getString("album_code"));
				gp.setMemberNo(rs.getInt("member_no"));
				gp.setGalNo(rs.getInt("gal_no"));
				gp.setGalFileNo(rs.getInt("gal_file_no"));
				gp.setGalFileOldPath(rs.getString("gal_file_old_path"));
				gp.setGalFileNewPath(rs.getString("gal_file_new_path"));
				gp.setGalFileContent(rs.getString("gal_file_content"));
				gp.setGalEnrollDate(rs.getDate("gal_enroll_date"));
				gp.setGalMultiStatus(rs.getString("gal_multi_status"));
				
				galList.add(gp);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return galList;
	}
	
	public List<MultiLocation> selectGrLoc(Connection conn, int groupNo)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectGrLoc");
		List<MultiLocation> list=new ArrayList<MultiLocation>();
		MultiLocation loc = null;		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			rs=pstmt.executeQuery();
			
			
			while(rs.next()) {
				loc = new MultiLocation();
				loc.setGroupNo(rs.getInt("group_no"));
				loc.setLocCtgCode(rs.getString("loc_ctg_code"));
				
				list.add(loc);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<MultiTopic> selectGrTopic(Connection conn, int groupNo)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectGrTopic");
		List<MultiTopic> list=new ArrayList<MultiTopic>();
		MultiTopic topic = null;		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			rs=pstmt.executeQuery();
			
			
			while(rs.next()) {
				topic = new MultiTopic();
				topic.setGroupNo(rs.getInt("group_no"));
				topic.setTopicCtgCode(rs.getString("topic_ctg_code"));
				
				list.add(topic);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public List<TopicCtg> selectTopicCtg(Connection conn)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectTopicCtg");
		List<TopicCtg> topicCtg=new ArrayList<TopicCtg>();
		TopicCtg topic = null;		
		try {
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			
			while(rs.next()) {
				topic = new TopicCtg();
				topic.setTopicCtgCode(rs.getString("topic_ctg_code"));
				topic.setTopicCtgName(rs.getString("topic_ctg_name"));
				
				topicCtg.add(topic);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return topicCtg;
	}
	
	public List<LocalCtg> selectLocalCtg(Connection conn)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectLocalCtg");
		List<LocalCtg> localCtg=new ArrayList<LocalCtg>();
		LocalCtg local = null;		
		try {
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				  local = new LocalCtg();
				  local.setLocCtgCode(rs.getString("loc_ctg_code"));
				  local.setMetroCode(rs.getString("metro_code"));
				  local.setLocMetroName(rs.getString("loc_metro_name"));
				  local.setAreaCode(rs.getString("area_code"));
				  local.setLocAreaName(rs.getString("loc_area_name"));
				  local.setTownCode(rs.getString("town_code"));
				  local.setLocTownName(rs.getString("loc_town_name"));
				  
				  localCtg.add(local);
				 
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return localCtg;
	}
	
	public List<MultiLocationName> selectSearchLocation(Connection conn,String groupNo){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT M.*, LOC_METRO_NAME||' '||LOC_AREA_NAME||' '||LOC_TOWN_NAME AS LOCATION_NAME FROM MULTI_LOCATION_TAB M JOIN LOCATION_CATEGORY_TABLE L ON(M.LOC_CTG_CODE=L.LOC_CTG_CODE) WHERE GROUP_NO IN("+groupNo+")";
		List<MultiLocationName> locationList=new ArrayList();
		MultiLocationName ml=null;
		try {
			
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				ml=new MultiLocationName();
				ml.setGroupNo(rs.getInt("group_no"));
				ml.setLocCtgCode(rs.getString("loc_ctg_code"));
				ml.setLocCtgName(rs.getString("location_name"));
				locationList.add(ml);
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			close(rs);
			close(stmt);
			
		}
		
		return locationList;
		
	}
	
	public List<MultiTopicName> selectSearchTopic(Connection conn,String groupNo){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT M.*, TOPIC_CTG_NAME FROM MULTI_TOPIC_TAB M JOIN TOPIC_CATEGORY_TAB L ON(M.TOPIC_CTG_CODE=L.TOPIC_CTG_CODE) WHERE GROUP_NO IN("+groupNo+")";
		List<MultiTopicName> topicList=new ArrayList();
		MultiTopicName mt=null;
		try {
			
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				mt=new MultiTopicName();
				mt.setGroupNo(rs.getInt("group_no"));
				mt.setTopicCtgCode(rs.getString("topic_ctg_code"));
				mt.setTopicCtgName(rs.getString("topic_ctg_name"));
				topicList.add(mt);
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			close(rs);
			close(stmt);
			
		}
		
		return topicList; 
	}
	
	public List<GroupMemberCount> selectMemberCount(Connection conn,String groupNo){
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT GROUP_NO,COUNT(*) AS MEMBER_COUNT FROM GROUP_MEMBER_TAB WHERE GROUP_NO IN("+groupNo+") GROUP BY GROUP_NO";
		List<GroupMemberCount> memberCountList=new ArrayList();
		GroupMemberCount gmc=null;
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()){
				gmc=new GroupMemberCount();
				gmc.setGroupNo(rs.getInt("group_no"));
				gmc.setMemberCount(rs.getInt("member_count"));
				memberCountList.add(gmc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		
		return memberCountList;
	}
		
		
}
