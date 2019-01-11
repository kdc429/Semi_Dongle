package com.dongle.gallery.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.dongle.gallery.model.dao.GalleryDao;
import com.dongle.gallery.model.vo.AlbumCategory;
import com.dongle.gallery.model.vo.GalleryPath;

public class GalleryService {
	
	public GalleryService() {}
	
	public List<AlbumCategory> albumGet(String groupNo){
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
	
	public AlbumCategory checkAlbumName(AlbumCategory ac)
	{
		Connection conn = getConnection();
		AlbumCategory oldAc = new GalleryDao().checkAlbumName(conn,ac);
		close(conn);
		return oldAc;
	}
}
