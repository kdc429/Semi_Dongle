package com.dongle.feed.model.dao;

import static common.JDBCTemplate.close;

import java.io.File;
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

import com.dongle.feed.model.vo.Feed;
import com.dongle.feed.model.vo.FeedNoResult;
import com.dongle.feed.model.vo.FileList;

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
	
	public List<Feed> selectFeed(Connection conn,int groupNo,int startFeedNo,int endFeedNo){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Feed> feedList=new ArrayList();
		Feed feed=null;
		String sql=prop.getProperty("selectFeed");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			pstmt.setInt(2, startFeedNo);
			pstmt.setInt(3, endFeedNo);
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
	
	public FeedNoResult insertFeedContent(Connection conn,int memberNo,int groupNo,String feedContent) {
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Statement stmt=null;
		FeedNoResult fnr=null;
		String sql=prop.getProperty("insertFeedContent");
		int result=0;
		
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, feedContent);
			result=pstmt.executeUpdate();
			
			if(result>0) {
				String sql2="SELECT SEQ_BO_FEED_NO.CURRVAL AS NO FROM DUAL";
				stmt=conn.createStatement();
				rs=stmt.executeQuery(sql2);
				fnr=new FeedNoResult();
				if(rs.next()) {
					fnr.setFeedNo(rs.getInt("no"));
				}
			}
			fnr.setFeedResult(result);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {

			close(pstmt);
			
		}
		return fnr;
	
		
	}
	
	public int insertFeedFile(Connection conn,int groupNo,List<FileList> uploadFileList) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertFeedFile");
		
		for(FileList f : uploadFileList) {
			try {
			
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, f.getFeedNo());
				pstmt.setInt(2, groupNo);
				pstmt.setString(3, f.getFeedOriFilePath());
				pstmt.setString(4, f.getFeedRenameFilePath());
				result+=pstmt.executeUpdate();
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		close(pstmt);
		return result;
		
	}
	
	public int deleteFeedContent(Connection conn,int feedNo) {
		
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertFeedFile");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, feedNo);
			result=pstmt.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteFile(List<FileList> uploadFileList) {
		
		int delFileResult=0;
		File file=null;
		for(FileList f:uploadFileList) {
			
			try {
				file=new File("/images/feed_images/"+f.getFeedRenameFilePath());
				file.delete();
				delFileResult=1;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return delFileResult;
	}
	

}
