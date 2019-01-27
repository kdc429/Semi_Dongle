package com.dongle.main.model.dao;

import static common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dongle.group.model.dao.GroupDao;
import com.dongle.main.model.vo.LocationCategory;
import com.dongle.member.model.vo.Member;

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
	
	
	
	

}
