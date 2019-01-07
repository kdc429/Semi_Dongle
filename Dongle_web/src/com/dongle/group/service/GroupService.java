package com.dongle.group.service;

import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.util.List;

import com.dongle.group.model.dao.GroupDao;
import com.dongle.group.model.vo.Group;

public class GroupService {
	
	public List<Group> selectGroup(String id){
		
		Connection conn=getConnection();
		List<Group> list=new GroupDao().selectGroup(conn,id);
		
		close(conn);
		return list;
		
	}

}
