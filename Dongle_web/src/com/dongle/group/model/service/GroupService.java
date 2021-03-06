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
	

	

}
