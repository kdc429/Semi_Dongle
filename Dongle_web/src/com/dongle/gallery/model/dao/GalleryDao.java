package com.dongle.gallery.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dongle.gallery.model.vo.AlbumCategory;
import com.dongle.gallery.model.vo.GalleryPath;
import com.dongle.group.model.vo.GroupMember;

public class GalleryDao {
	
	Properties prop= new Properties();
	public GalleryDao() {
		String fileName=GalleryDao.class.getResource("./galleryquery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<AlbumCategory> albumGet(Connection conn,int groupNo)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("albumSelect");
		List<AlbumCategory> list = new ArrayList<AlbumCategory>();
		AlbumCategory ac=null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				ac=new AlbumCategory(
						rs.getInt("group_no"),
						rs.getString("album_code"),
						rs.getString("album_name")
						);
				list.add(ac);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public List<GalleryPath> galleryGet(String albumCode, int groupNo,Connection conn,int cPage,int numPerPage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = prop.getProperty("gallerySelectList");
		List<GalleryPath> list = new ArrayList<GalleryPath>();
		GalleryPath gp = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, albumCode);
			pstmt.setInt(2, groupNo);
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				gp=new GalleryPath(
						rs.getInt("group_no"),
						rs.getString("album_code"),
						rs.getInt("gal_file_no"),
						rs.getString("gal_file_path"),
						rs.getInt("member_no"),
						rs.getDate("gal_enroll_date")
						);
				list.add(gp);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public int seleGalleryCount(String albumCode,int groupNo,Connection conn)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectGalleryCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, albumCode);
			pstmt.setInt(2, groupNo);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				result=rs.getInt("cnt");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public AlbumCategory checkAlbumName(Connection conn, AlbumCategory ac,int groupNo)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql =prop.getProperty("checkAlbumName");
		AlbumCategory checkAc=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, ac.getAlbumName());
			pstmt.setInt(2, groupNo);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				checkAc=new AlbumCategory(
						rs.getInt("group_no"),
						rs.getString("album_code"),
						rs.getString("album_name")
						);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return checkAc;
	}
	
	public int inserAlbum(Connection conn,String albumNameP,int groupNo)
	{
		PreparedStatement pstmt=null;
		int rs=0;
		String sql=prop.getProperty("insertAlbum");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			pstmt.setString(2, albumNameP);
			rs=pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}
		return rs;
	}
	
	public GroupMember groupMemberCheck(Connection conn, int groupNo, int memberNo)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = prop.getProperty("groupMemberCheck");
		GroupMember gm = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			pstmt.setInt(2, memberNo);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				gm= new GroupMember(
						rs.getInt("group_no"),
						rs.getInt("member_no"),
						rs.getString("group_member_nickname"),
						rs.getString("group_member_image_path"),
						rs.getDate("group_member_enroll_date"),
						rs.getString("blacklist_yn"),
						rs.getInt("report_dongle_count")
						);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return gm;
	}
}
