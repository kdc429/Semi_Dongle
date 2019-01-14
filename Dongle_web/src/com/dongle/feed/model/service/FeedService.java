package com.dongle.feed.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.dongle.feed.model.dao.FeedDao;
import com.dongle.feed.model.vo.Feed;

public class FeedService {
	
	public List<Feed> selectFeed(int groupNo){
		Connection conn=getConnection();
		List<Feed> feedList=new FeedDao().selectFeed(conn,groupNo);
		close(conn);
		return feedList;
	}

}
