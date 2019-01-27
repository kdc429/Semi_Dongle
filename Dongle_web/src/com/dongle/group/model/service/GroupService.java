package com.dongle.group.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.dongle.gallery.model.vo.GalleryPath;
import com.dongle.group.model.dao.GroupDao;
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

public class GroupService {
	
	public List<GalleryPath> selectAllGallery(int groupNo) {
		Connection conn=getConnection();
		List<GalleryPath> galList=new GroupDao().selectAllGallery(conn, groupNo);
		close(conn);
		return galList;
	}
	
	public List<Group> selectAllGroupList()
	{
		Connection conn = getConnection();
		List<Group> groupList = new GroupDao().selectAllGroupList(conn);
		close(conn);
		return groupList;
	}

	
	public List<Group> selectGroup(String id){// 가입한 그룹 찾기
		
		Connection conn=getConnection();
		List<Group> list=new GroupDao().selectGroup(conn,id);
		
		close(conn);
		return list;
		
	}
	
	public Group selectGrInfo(int groupNo) {// 그룹정보 데이터 출력
		
		Connection conn=getConnection();
		Group g=new GroupDao().selectGrInfo(conn,groupNo);
		close(conn);
		return g;
	}
	
	public List<EditPickGroup> selectEditGr(){// 에디터픽 그룹 찾기
		
		Connection conn=getConnection();
		List<EditPickGroup> editList=new GroupDao().selectEditGr(conn);
		
		close(conn);
		return editList;
		
	}
	public GroupMember selectGmInfo(int gNo,int memberNo)
	{
		Connection conn = getConnection();
		GroupMember gm = new GroupDao().selectGmInfo(conn,gNo,memberNo);
		close(conn);
		return gm;
	}
	
	public List<Group> selectRank(){
		
		Connection conn=getConnection();
		List<Group> rankList=new GroupDao().selectRank(conn);
		
		close(conn);
		return rankList;
	}
	   
	public int countMember(int groupNo){
		Connection conn = getConnection();
		int result = new GroupDao().countMember(conn,groupNo);
		close(conn);
		return result;
	}

	public List<GroupMember> selectMemberList(int groupNo) {
		Connection conn=getConnection();
		List<GroupMember> list=new GroupDao().selectMemberList(conn, groupNo);
		close(conn);
		return list;
	}
	
	public List<MultiLocation> selectGrLoc(int groupNo)
	{
		Connection conn=getConnection();
		List<MultiLocation> list=new GroupDao().selectGrLoc(conn, groupNo);
		close(conn);
		return list;
	}
	
	public List<MultiTopic> selectGrTopic(int groupNo)
	{
		Connection conn=getConnection();
		List<MultiTopic> list=new GroupDao().selectGrTopic(conn, groupNo);
		close(conn);
		return list;
	}
	
	public List<TopicCtg> selectTopicCtg()
	{
		Connection conn=getConnection();
		List<TopicCtg> topicCtg=new GroupDao().selectTopicCtg(conn);
		close(conn);
		return topicCtg;

	}
	
	public List<LocalCtg> selectLocalCtg()
	{
		Connection conn=getConnection();
		List<LocalCtg> localCtg=new GroupDao().selectLocalCtg(conn);
		close(conn);
		return localCtg;
	}
	
	public List<MultiLocationName> selectSearchLocation(String groupNo){
		
		Connection conn=getConnection();
		List<MultiLocationName> locationList= new GroupDao().selectSearchLocation(conn,groupNo);
		close(conn);
		return locationList;
	}
	
	public List<MultiTopicName> selectSearchTopic(String groupNo){
		
		Connection conn=getConnection();
		List<MultiTopicName> topicList= new GroupDao().selectSearchTopic(conn,groupNo);
		close(conn);
		return topicList;
	}
	
	public List<GroupMemberCount> selectMemberCount(String groupNo){
		
		Connection conn=getConnection();
		List<GroupMemberCount> memberCountList=new GroupDao().selectMemberCount(conn,groupNo);
		close(conn);
		return memberCountList;

	}

}
