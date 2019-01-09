package com.dongle.group.model.service;

import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.util.List;

import com.dongle.group.model.dao.GroupDao;
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

}