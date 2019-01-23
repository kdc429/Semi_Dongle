package com.dongle.main.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.dongle.main.model.dao.MainDao;
import com.dongle.main.model.vo.LocationCategory;
import com.dongle.member.model.dao.MemberDao;
import com.dongle.member.model.vo.Member;

public class MainService {
	
	public List<LocationCategory> selectMetroCode()
	{
		Connection conn = getConnection();
		List<LocationCategory>metroCodeList = new MainDao().selectMetroCode(conn);
		close(conn);
		return metroCodeList;		
	}
	


}
