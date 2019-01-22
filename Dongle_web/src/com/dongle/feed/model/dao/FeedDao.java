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
import com.dongle.feed.model.vo.FeedComment;
import com.dongle.feed.model.vo.FeedFile;
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
	
	public List<Feed> selectFeed(Connection conn,int groupNo,int startFeedNo,int endFeedNo){//피드 가져오기
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
		//피드 내용 업로드 후 피드 넘버 가져오기
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Statement stmt=null;
		FeedNoResult fnr=null;
		String sql=prop.getProperty("insertFeedContent");
		int result=0;
		System.out.println(feedContent);
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, feedContent);
			result=pstmt.executeUpdate();
			System.out.println(result+"?");
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
	
	public int insertFeedFile(Connection conn,int groupNo,List<FileList> uploadFileList) {//피드 파일 업로드
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
	
	public int deleteFeedContent(Connection conn,int feedNo) {//피드 내용 지우기
		
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
	
	public int deleteFile(List<FileList> uploadFileList) {//피드 파일지우기
		
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
	
	public List<FeedFile> selectFeedFileList(Connection conn,int groupNo){//피드 파일 가져오기
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectFeedFileList");
		FeedFile feedFile=null;
		List<FeedFile> feedFileList=new ArrayList();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				feedFile=new FeedFile();
				feedFile.setFeedNo(rs.getInt("feed_no"));
				feedFile.setGroupNo(rs.getInt("group_no"));
				feedFile.setFeedFileNo(rs.getInt("feed_file_no"));
				feedFile.setFeedOldFilePath(rs.getString("feed_old_file_path"));
				feedFile.setFeedNewFilePath(rs.getString("feed_new_file_path"));
				
				feedFileList.add(feedFile);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return feedFileList;
	}
	
	public int insertFeedComment(Connection conn,FeedComment feedComment) {//피드 댓글 작성
		int result=0;
		PreparedStatement pstmt=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("insertFeedComment");
		int feedCommentNo=0;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, feedComment.getGroupNo());
			pstmt.setInt(2, feedComment.getFeedNo());
			pstmt.setInt(3, feedComment.getFeCommentLevel());
			pstmt.setInt(4, feedComment.getMemberNo());
			pstmt.setString(5, feedComment.getFeCommentContent());
			pstmt.setInt(6, feedComment.getFeCommentRef());
			result=pstmt.executeUpdate();
			
			if(result>0) {
				
				String sql2="SELECT SEQ_FE_COMMENT_NO.CURRVAL AS NO FROM DUAL";
				stmt=conn.createStatement();
				rs=stmt.executeQuery(sql2);
				
				if(rs.next()) {
					feedCommentNo=rs.getInt("no");
				}
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			
			close(pstmt);
		}
		if(result>0) {
			return feedCommentNo;
		}else {
			return result;
		}
		
	}
	
	public List<FeedComment> selectFeedCommentList(Connection conn,int groupNo){
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<FeedComment> feedCommentList=new ArrayList();
		FeedComment feedComment=null;
		String sql=prop.getProperty("selectFeedCommentList");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				feedComment=new FeedComment();
				feedComment.setGroupNo(groupNo);
				feedComment.setFeedNo(rs.getInt("feed_no"));
				feedComment.setFeCommentNo(rs.getInt("fe_comment_no"));
				feedComment.setFeCommentLevel(rs.getInt("fe_comment_level"));
				feedComment.setMemberNo(rs.getInt("member_no"));
				feedComment.setFeCommentContent(rs.getString("fe_comment_content"));
				feedComment.setFeCommentDate(rs.getDate("fe_comment_date"));
				feedComment.setFeCommentRef(rs.getInt("fe_comment_ref"));
				
				feedCommentList.add(feedComment);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return feedCommentList;
	}
	
	public List<FeedComment> selectLevel2FeedCommentList(Connection conn){
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<FeedComment> feedLevel2CommentList=new ArrayList();
		FeedComment feedComment=null;
		String sql=prop.getProperty("selectLevel2FeedCommentList");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				feedComment=new FeedComment();
				feedComment.setGroupNo(rs.getInt("group_no"));
				feedComment.setFeedNo(rs.getInt("feed_no"));
				feedComment.setFeCommentNo(rs.getInt("fe_comment_no"));
				feedComment.setFeCommentLevel(rs.getInt("fe_comment_level"));
				feedComment.setMemberNo(rs.getInt("member_no"));
				feedComment.setFeCommentContent(rs.getString("fe_comment_content"));
				feedComment.setFeCommentDate(rs.getDate("fe_comment_date"));
				feedComment.setFeCommentRef(rs.getInt("fe_comment_ref"));
				
				feedLevel2CommentList.add(feedComment);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return feedLevel2CommentList;
	}
	
	public FeedComment selectFeedComment(Connection conn,int feedCommentNo) {
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectFeedComment");
		FeedComment fc=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, feedCommentNo);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				fc=new FeedComment();
				fc.setGroupNo(rs.getInt("group_no"));
				fc.setFeedNo(rs.getInt("feed_no"));
				fc.setFeCommentNo(rs.getInt("fe_comment_no"));
				fc.setFeCommentLevel(rs.getInt("fe_comment_level"));
				fc.setMemberNo(rs.getInt("member_no"));
				fc.setFeCommentContent(rs.getString("fe_comment_content"));
				fc.setFeCommentDate(rs.getDate("fe_comment_date"));
				fc.setFeCommentRef(rs.getInt("fe_comment_ref"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return fc;
	}
	
	public Feed selectFeedOne(Connection conn, int feedNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectFeedOne");
		Feed f=null;
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, feedNo);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				f=new Feed();
				f.setFeedNo(rs.getInt("feed_no"));
				f.setGroupNo(rs.getInt("group_no"));
				f.setMemberNo(rs.getInt("member_no"));
				f.setFeedContent(rs.getString("feed_content"));
				f.setFeedWriteDate(rs.getDate("feed_write_date"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return f;
		
	}
	
	public List<FeedFile> selectFeedFileListOne(Connection conn,int feedNo){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectFeedFileListOne");
		FeedFile fl=null;
		List<FeedFile> feedFile=new ArrayList();
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, feedNo);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				fl=new FeedFile();
				fl.setFeedNo(rs.getInt("feed_no"));
				fl.setGroupNo(rs.getInt("group_no"));
				fl.setFeedFileNo(rs.getInt("feed_file_no"));
				fl.setFeedOldFilePath(rs.getString("feed_old_file_path"));
				fl.setFeedNewFilePath(rs.getString("feed_new_file_path"));
				feedFile.add(fl);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return feedFile;
		
	}
	

}
