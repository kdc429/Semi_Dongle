package com.dongle.gallery.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.dongle.gallery.model.dao.GalleryDao;
import com.dongle.gallery.model.vo.AlbumCategory;
import com.dongle.gallery.model.vo.GalleryCommentJoin;
import com.dongle.gallery.model.vo.GalleryPath;
import com.dongle.group.model.vo.GroupMember;

public class GalleryService {
	
	public GalleryService() {}
	
	public List<AlbumCategory> albumGet(int groupNo){
		Connection conn = getConnection();
		List<AlbumCategory> list=new GalleryDao().albumGet(conn,groupNo);
		if(list.size()!=0)
		{
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		
		return list;
	}
	
	public List<GalleryPath> galleryGet(String albumCode, int groupNo,int cPage,int numPerPage)
	{
		Connection conn = getConnection();
		List<GalleryPath> list = new GalleryDao().galleryGet(albumCode,groupNo,conn,cPage,numPerPage);
		close(conn);
		
		return list;
	}
	public int selectGalleryCount(String albumCode,int groupNo)
	{
		Connection conn = getConnection();
		int result = new GalleryDao().seleGalleryCount(albumCode,groupNo,conn);
		close(conn);
		return result;
	}
	
	public AlbumCategory checkAlbumName(AlbumCategory ac, int groupNo)
	{
		Connection conn = getConnection();
		AlbumCategory oldAc = new GalleryDao().checkAlbumName(conn,ac,groupNo);
		close(conn);
		return oldAc;
	}
	public int insertAlbum(String albumNameP,int groupNo)
	{
		Connection conn = getConnection();
		int rs= new GalleryDao().inserAlbum(conn,albumNameP,groupNo);
		if(rs!=0)
		{
			commit(conn);
		}
		else {rollback(conn);}
		return rs;
	}
	
	public GroupMember groupMemberCheck(int groupNo,int memberNo)
	{
		Connection conn=getConnection();
		GroupMember gm= new GalleryDao().groupMemberCheck(conn,groupNo,memberNo);
		close(conn);
		return gm;
		
	}
	public List<GalleryCommentJoin> selectGalCommentList(int groupNo,int galFileNo,int galNo)
	{
		Connection conn = getConnection();
		List<GalleryCommentJoin> gclist=new GalleryDao().selectGalCommentList(conn,groupNo,galFileNo,galNo);
		close(conn);
		return gclist;
		
	}
	
	public List<GalleryPath> selectOneList(int groupNo,int galNo,String albumCode)
	{
		Connection conn = getConnection();
		List<GalleryPath> gplist = new GalleryDao().selectOneList(conn,groupNo,galNo,albumCode);
		close(conn);
		return gplist;
	}
	
	public int insertGallery(int groupNo,String albumCode,GalleryPath gp)
	{
		Connection conn = getConnection();
		int rs = new GalleryDao().insertGallery(conn,groupNo,albumCode,gp);
		if(rs!=0)
		{
			commit(conn);
		}
		else {
			rollback(conn);
		}
		return rs;
	}
	//다중의로 데이터 받을 경우 gal_no가 동일하게 들어가야하므로 그 그룹의 해당 앨범에서 가장 높은 gal_no를 뽑아오는 것
	public int maxGalNo(int groupNo, String albumCode)
	{
		Connection conn=getConnection();
		int rs = new GalleryDao().maxGalNo(conn, groupNo,albumCode);
		close(conn);
		return rs;
	}
	//갤러리 코멘트 등록하기
	public int insertGalComment(GalleryCommentJoin gcj)
	{
		Connection conn = getConnection();
		int rs = new GalleryDao().insertGalComment(conn, gcj);
		if(rs!=0)
		{
			commit(conn);
		}
		else {
			rollback(conn);
		}
		return rs;
	}
	//gal_no만 가지고 있는 아이 뽑으러 갑니다
	public List distictGalNoList(String albumCode,int groupNo)
	{
		Connection conn = getConnection();
		List galNoList = new GalleryDao().distictGalNoList(conn,albumCode,groupNo);
		close(conn);
		return galNoList;
	}
}
