package com.dongle.feed.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static common.JDBCTemplate.close;

import com.dongle.feed.model.vo.Feed;

public class FeedDao {
	
	Properties prop=new Properties();
	public FeedDao() {
		String fileName=FeedDao.class.getResource("./feedquery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Feed> selectFeed(Connection conn,int groupNo){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Feed> feedList=new ArrayList();
		Feed feed=null;
		String sql=prop.getProperty("selectFeed");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				feed=new Feed();
				feed.setFeedNo(rs.getInt("feed_no"));
				feed.setGroupNo(rs.getInt("group_no"));
				feed.setMemberNo(rs.getInt("member_no"));
				feed.setFeedContent(rs.getString("feed_content"));
				feed.setFeedWriteDate(rs.getDate("feed_write_date"));
				
				feedList.add(feed);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			
		}
		return feedList;
	}

}
