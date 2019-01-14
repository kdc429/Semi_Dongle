package com.dongle.group.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.dongle.group.model.dao.GroupDao;
import com.dongle.group.model.vo.EditPickGroup;
import com.dongle.group.model.vo.Group;

public class GroupService {
	
	public List<Group> selectGroup(String id){// 가입한 그룹 찾기
		
		Connection conn=getConnection();
		List<Group> list=new GroupDao().selectGroup(conn,id);
		
		close(conn);
		return list;
		
	}
	
	public Group selectGrInfo(int gNo) {// 그룹정보 데이터 출력
		
		Connection conn=getConnection();
		Group g=new GroupDao().selectGrInfo(conn,gNo);
		close(conn);
		return g;
	}
	
	public List<EditPickGroup> selectEditGr(){// 에디터픽 그룹 찾기
		
		Connection conn=getConnection();
		List<EditPickGroup> editList=new GroupDao().selectEditGr(conn);
		
		close(conn);
		return editList;
		
	}
	
	public List<Group> selectRank(){
		
		Connection conn=getConnection();
		List<Group> rankList=new GroupDao().selectRank(conn);
		
		close(conn);
		return rankList;
	}
	

}
