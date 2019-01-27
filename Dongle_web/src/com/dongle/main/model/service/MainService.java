package com.dongle.main.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.dongle.group.model.vo.Group;
import com.dongle.main.model.dao.MainDao;
import com.dongle.main.model.vo.LocationCategory;
import com.dongle.main.model.vo.TopicCategory;

public class MainService {
	

	public List<TopicCategory> selectTopicCategory(){
		
		Connection conn=getConnection();
		List<TopicCategory> topicList=new MainDao().selectTopicCategory(conn);
		close(conn);
		return topicList;
	}
	
	public List<LocationCategory> selectLocationList(String locationCode){
		
		Connection conn=getConnection();
		List<LocationCategory> locationList=new MainDao().selectLocationList(conn,locationCode);
		close(conn);
		return locationList;
	}
	
	public List<LocationCategory> selectAreaList(String locationCode){
		
		Connection conn=getConnection();
		List<LocationCategory> locationList=new MainDao().selectAreaList(conn,locationCode);
		close(conn);
		return locationList;
	}
	public List<LocationCategory> selectTownList(String locationCode){
		
		Connection conn=getConnection();
		List<LocationCategory> locationList=new MainDao().selectTownList(conn,locationCode);
		close(conn);
		return locationList;
	}
	
	public List<Group> selectAllCheckList(String locArr,String topicArr,int minAge,int maxAge, String time){
		
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectAllCheckList(conn,locArr,topicArr,minAge,maxAge,time);
		close(conn);
		return groupList;
	}
	
	public List<Group> selectLocationNullCheckList(String topicArr,int minAge,int maxAge,String time){
		
		//locArr==null
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectLocationNullCheckList(conn,topicArr,minAge,maxAge,time);
		close(conn);
		return groupList;
	}
	
	public List<Group> selectLocTopicNullCheckList(int minAge,int maxAge,String time){
		
		//locArr==null && topicArr==null
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectLocTopicNullCheckList(conn,minAge,maxAge,time);
		close(conn);
		return groupList;
	}
	
	public List<Group> selectLocTopicMinNullCheckList(int maxAge,String time){
		
		//locArr==null && topicArr==null && minAge==0
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectLocTopicMinNullCheckList(conn,maxAge,time);
		close(conn);
		return groupList;
	}
	
	public List<Group> selectLocTopicMinMaxNullCheckList(String time){
		
		// locArr==null,topicArr==null,minAge==0,maxAge==0,
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectLocTopicMinMaxNullCheckList(conn,time);
		close(conn);
		return groupList;
	}
	
	public List<Group> selectTimeNullCheckList(String locArr,String topicArr,int minAge,int maxAge){
		
		//time==null
		
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectTimeNullCheckList(conn,locArr,topicArr,minAge,maxAge);
		close(conn);
		return groupList;
		
		
	}
	
	public List<Group> selectMaxTimeNullCheckList(String locArr,String topicArr,int minAge){
		
		//maxAge==null time==null
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectMaxTimeNullCheckList(conn,locArr,topicArr,minAge);
		close(conn);
		return groupList;
	}
	
	public List<Group> selectMinMaxTimeNullCheckList(String locArr,String topicArr){
		
		//maxAge==null time==null min==0 
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectMinMaxTimeNullCheckList(conn,locArr,topicArr);
		close(conn);
		return groupList;
	}
	
	public List<Group> selectTopicMinMaxTimeNullCheckList(String locArr){
		
		//maxAge==null time==null minage==0 topic==null
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectTopicMinMaxTimeNullCheckList(conn,locArr);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectMinTimeNullCheckList(String locArr,String topicArr,int maxAge){
		
		//maxAge==null time==null minage==0 topic==null
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectMinTimeNullCheckList(conn,locArr,topicArr,maxAge);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectTopicCheckList(String topicArr){
		
		//maxAge==null time==null minage==0 locArr==null
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectTopicCheckList(conn,topicArr);
		close(conn);
		return groupList;
		
	}
	public List<Group> selectLocMaxNullCheckList(String topicArr,int minAge,String time){
		
		//maxAge==null locArr==null
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectLocMaxNullCheckList(conn,topicArr,minAge,time);
		close(conn);
		return groupList;
		
	}
	public List<Group> selectTopicTimeCheckList(String topicArr,String time){
		
		//max,minAge==null locArr==null 
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectTopicTimeCheckList(conn,topicArr,time);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectTopicMinCheckList(String topicArr,int minAge){
		
		//max,minAge==null locArr==null 
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectTopicMinCheckList(conn,topicArr,minAge);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectLocTimeNullCheckList(String topicArr,int minAge,int maxAge){
		
		//max,minAge==null locArr==null 
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectLocTimeNullCheckList(conn,topicArr,minAge,maxAge);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectMinMaxCheckList(int minAge,int maxAge){
		
		//time,topic, locArr==null 
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectMinMaxCheckList(conn,minAge,maxAge);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectMinCheckList(int minAge){
		
		//time,topic, locArr==null 
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectMinCheckList(conn,minAge);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectMinTimeCheckList(int minAge,String time){
		
		//time,topic, locArr==null 
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectMinTimeCheckList(conn,minAge,time);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectMaxCheckList(int maxAge){
		
		//time,topic, locArr==null 
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectMaxCheckList(conn,maxAge);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectTopicMaxCheckList(String topicArr,int maxAge){
		
		//time,topic, locArr==null 
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectTopicMaxCheckList(conn,topicArr,maxAge);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectTopicTimeNullCheckList(String locArr,int maxAge, int minAge){
		
		
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectTopicTimeNullCheckList(conn,locArr,maxAge,minAge);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectLocTimeCheckList(String locArr,String time){
		
		
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectLocTimeCheckList(conn,locArr,time);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectTopicMinNullCheckList(String locArr,int maxAge ,String time){
		
		
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectTopicMinNullCheckList(conn,locArr,maxAge,time);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectMinNullCheckList(String locArr,String topicArr,int maxAge ,String time){
		
		
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectMinNullCheckList(conn,locArr,topicArr,maxAge,time);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectTopicNullCheckList(String locArr,int minAge,int maxAge ,String time){
		
		
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectTopicNullCheckList(conn,locArr,minAge,maxAge,time);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectTopicMaxNullCheckList(String locArr,int minAge,String time){
		
		
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectTopicMaxNullCheckList(conn,locArr,minAge,time);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectMinMaxNullCheckList(String locArr,String topicArr,String time){
		
		
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectMinMaxNullCheckList(conn,locArr,topicArr,time);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectLocMinCheckList(String locArr,int minAge){
		
		
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectLocMinCheckList(conn,locArr,minAge);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectLocMaxCheckList(String locArr,int maxAge){
		
		
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectLocMaxCheckList(conn,locArr,maxAge);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectMaxNullCheckList(String locArr,String topicArr,int minAge,String time){
		
		
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectMaxNullCheckList(conn,locArr,topicArr,minAge,time);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectLocMinNullCheckList(String topicArr,int maxAge,String time){
		
		
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectLocMinNullCheckList(conn,topicArr,maxAge,time);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectKeywordCheckList(String keyword){
		
		
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectKeywordCheckList(conn,keyword);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectAllNullCheckList(){
		
		
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectAllNullCheckList(conn);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectGroupSortDate(String groupNo,int cPage,int numPerPage){
		
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectGroupSortDate(conn,groupNo,cPage,numPerPage);
		close(conn);
		return groupList;
		
	}
	
	public List<Group> selectGroupSortMemberCnt(String groupNo,int cPage,int numPerPage){
		
		Connection conn=getConnection();
		List<Group> groupList=new MainDao().selectGroupSortMemberCnt(conn,groupNo,cPage,numPerPage);
		close(conn);
		return groupList;
		
	}

}
