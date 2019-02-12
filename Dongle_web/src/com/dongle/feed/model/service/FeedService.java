package com.dongle.feed.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.dongle.feed.model.dao.FeedDao;
import com.dongle.feed.model.vo.Feed;
import com.dongle.feed.model.vo.FeedComment;
import com.dongle.feed.model.vo.FeedFile;
import com.dongle.feed.model.vo.FeedNoResult;
import com.dongle.feed.model.vo.FileList;

public class FeedService {
	
	public List<Feed> selectFeed(int groupNo,int startFeedNo,int endFeedNo){//피드 가져오기
		Connection conn=getConnection();
		List<Feed> feedList=new FeedDao().selectFeed(conn,groupNo,startFeedNo,endFeedNo);
		close(conn);
		return feedList;
	}
	public FeedNoResult insertFeedContent(int memberNo, int groupNo, String feedContent) {
		//피드 컨텐트 업로드
		Connection conn=getConnection();
		
		FeedNoResult fnr=null;
		fnr=new FeedDao().insertFeedContent(conn,memberNo,groupNo,feedContent);
		if(fnr.getFeedResult()>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return fnr;
	}
	
	public int insertFeedFile(int feedNo,int groupNo,List<FileList> uploadFileList) {
		//피드 파일 업로드
		Connection conn=getConnection();
		int result=0;
		int delResult=0;
		int delFileResult=0;
		result=new FeedDao().insertFeedFile(conn,groupNo, uploadFileList);
		System.out.println("insert?");
		if(result>0) {
			commit(conn);
		}else {
			//실패하면 피드 컨텐드를 지운다
			rollback(conn);
			delResult=new FeedDao().deleteFeedContent(conn,feedNo);
			//업로드 된 파일도 지운다
			delFileResult=new FeedDao().deleteFile(uploadFileList);
			if(delResult>0&&delFileResult>0) {
				commit(conn);
			}else {
				rollback(conn);
			}
		}
		
		close(conn);
		return result;
		
	}
	
	public List<FeedFile> selectFeedFileList(int groupNo){
		Connection conn=getConnection();
		List<FeedFile> feedFileList=new FeedDao().selectFeedFileList(conn,groupNo);
		
		close(conn);
		return feedFileList;
	}
	
	public FeedComment insertFeedComment(FeedComment feedComment) {
		
		Connection conn=getConnection();
		int result=0;
		result=new FeedDao().insertFeedComment(conn,feedComment);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		FeedComment fc=new FeedDao().selectFeedComment(conn, result);
		close(conn);
		return fc;
	}
	
	public List<FeedComment> selectFeedCommentList(int groupNo){
		
		Connection conn=getConnection();
		List<FeedComment> feedCommentList=new FeedDao().selectFeedCommentList(conn,groupNo);
		
		close(conn);
		return feedCommentList;
		
	}
	
	public List<FeedComment> selectLevel2FeedCommentList(){
		Connection conn=getConnection();
		List<FeedComment> feedLevel2CommentList=new FeedDao().selectLevel2FeedCommentList(conn);
		
		close(conn);
		return feedLevel2CommentList;

	}
	
	public Feed selectFeedOne(int feedNo) {
		Connection conn=getConnection();
		Feed f=new FeedDao().selectFeedOne(conn,feedNo);
		
		close(conn);
		return f;
	}
	
	public List<FeedFile> selectFeedFileListOne(int feedNo){
		Connection conn=getConnection();
		List<FeedFile> feedFile=new FeedDao().selectFeedFileListOne(conn,feedNo);
		
		close(conn);
		return feedFile;
	}
	public int updateFeed(int feedNo,String content,String[] fileNums) {
		Connection conn=getConnection();
		int resultContent=0;
		int resultFile=0;
		int totalResult=0;
		resultContent=new FeedDao().updateFeedContent(conn,feedNo,content);
		List<FileList> fileList=new FeedDao().deleteFileList(conn,fileNums);
		resultFile=new FeedDao().updateFeedFile(conn,fileNums);
		if(resultContent>0&&resultFile>0) {
			totalResult=new FeedDao().deleteFile(fileList);
			
			if(totalResult>0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			close(conn);
			return totalResult;

		}
		return totalResult;
		
	}
	
	public int updateFeedContent(int feedNo,String content) {
		Connection conn=getConnection();
		int resultContent=0;
		resultContent=new FeedDao().updateFeedContent(conn,feedNo,content);
		
		if(resultContent>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return resultContent;
	}
	
	public int deleteFeedContent(int feedNo) {
		Connection conn=getConnection();
		int result=0;
		result=new FeedDao().deleteFeedContent(conn, feedNo);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int deleteFeedFile(int feedNo) {
		Connection conn=getConnection();
		int result=0;
		List<FileList> fileList=new FeedDao().deleteFileList2(conn, feedNo);
		result=new FeedDao().deleteFeedFile(conn,feedNo);
		
		if(result>0) {
			commit(conn);
			result=new FeedDao().deleteFile(fileList);
		
		}else {
			rollback(conn);
		}
		
		return result;
	}
	public int feedStatusUpdate(int feedNo) {
		Connection conn=getConnection();
		int result=0;
		result=new FeedDao().feedStatusUpdate(conn,feedNo);
		
		if(result>0) {
			commit(conn);
			
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int feedCommentStatusUpdate(int feedNo) {
		Connection conn=getConnection();
		int result=0;
		result=new FeedDao().feedCommentStatusUpdate(conn,feedNo);
		
		if(result>0) {
			commit(conn);
			
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public FeedFile selectFeedFile(int feedFileNo) {
		
		Connection conn=getConnection();
		FeedFile ff=new FeedDao().selectFeedFile(conn, feedFileNo);
		close(conn);
		return ff;
		
	}
	
	public int deleteComment(int feedCommentNo) {
		
		Connection conn=getConnection();
		int result=new FeedDao().deleteComment(conn,feedCommentNo);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}

}
