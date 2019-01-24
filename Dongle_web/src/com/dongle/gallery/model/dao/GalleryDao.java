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
import com.dongle.gallery.model.vo.GalleryCommentJoin;
import com.dongle.gallery.model.vo.GalleryPath;
import com.dongle.group.model.vo.GroupMember;

public class GalleryDao {
   //selectOneList getAllList 두개 쿼리문 확인
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
            gp=new GalleryPath();
            gp.setGroupNo(rs.getInt("group_no"));
            gp.setAlbumCode(rs.getString("album_code"));
            gp.setMemberNo(rs.getInt("member_no"));
            gp.setGalNo(rs.getInt("gal_no"));
            gp.setGalFileNo(rs.getInt("gal_file_no"));
            gp.setGalFileOldPath(rs.getString("gal_file_old_path"));
            gp.setGalFileNewPath(rs.getString("gal_file_new_path"));
            gp.setGalFileContent(rs.getString("gal_file_content"));
            gp.setGalEnrollDate(rs.getDate("gal_enroll_date"));
            gp.setGalMultiStatus(rs.getString("gal_multi_status"));
            
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
                  rs.getString("group_member_image_old_path"),
                  rs.getString("group_member_image_new_path"),
                  rs.getDate("group_member_enroll_date"),
                  rs.getString("group_blacklist_yn"),
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
   
   public List<GalleryCommentJoin> selectGalCommentList(Connection conn, int groupNo,int galFileNo,int galNo)
   {
      PreparedStatement pstmt = null;
      ResultSet rs=null;
      String sql=prop.getProperty("selectGalCommentList");
      GalleryCommentJoin gcj=null;
      List<GalleryCommentJoin> gclist = new ArrayList<GalleryCommentJoin>();
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, groupNo);
         pstmt.setInt(2, galFileNo);
         rs=pstmt.executeQuery();
         while(rs.next())
         {
            gcj=new GalleryCommentJoin(
                  rs.getInt("group_no"),
                  rs.getInt("gal_file_no"),
                  rs.getInt("gal_comment_no"),
                  rs.getInt("gal_comment_level"),
                  rs.getInt("member_no"),
                  rs.getString("gal_comment_content"),
                  rs.getDate("gal_comment_date"),
                  rs.getInt("gal_comment_ref"),
                  rs.getString("group_member_nickname"),
                  rs.getString("group_member_image_new_path"),
                  rs.getString("album_code"),
                  rs.getString("gal_file_new_path"),
                  rs.getInt("gal_no"),
                  rs.getString("gal_multi_status")
                  );
            gclist.add(gcj);
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
      return gclist;
   }
   
   public List<GalleryPath> selectOneList(Connection conn,  int groupNo,int galNo,String albumCode)
   {
      PreparedStatement pstmt= null;
      ResultSet rs=null;
      String sql=prop.getProperty("selectOneList");
      
      List<GalleryPath> gplist=new ArrayList<GalleryPath>();
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, groupNo);
         pstmt.setInt(2, galNo);
         pstmt.setString(3, albumCode);
         rs=pstmt.executeQuery();
         while(rs.next())
         {
            GalleryPath gp=new GalleryPath();
            gp.setGroupNo(rs.getInt("group_no"));
            gp.setAlbumCode(rs.getString("album_code"));
            gp.setMemberNo(rs.getInt("member_no"));
            gp.setGalNo(rs.getInt("gal_no"));
            gp.setGalFileNo(rs.getInt("gal_file_no"));
            gp.setGalFileOldPath(rs.getString("gal_file_old_path"));
            gp.setGalFileNewPath(rs.getString("gal_file_new_path"));
            gp.setGalFileContent(rs.getString("gal_file_content"));
            gp.setGalEnrollDate(rs.getDate("gal_enroll_date"));
            gp.setGalMultiStatus(rs.getString("gal_multi_status"));
            gp.setGroupMemberNickname(rs.getString("group_member_nickname"));
            gp.setGroupMemberImageNewPath(rs.getString("group_member_image_new_path"));
            
            gplist.add(gp);
         }
         System.out.println("갤러리: "+gplist);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      finally {
         close(rs);
         close(pstmt);
      }
      return gplist;
   }
   
   public int insertGallery(Connection conn, GalleryPath gp,List oldFileName, List newFileName, int imageCount)
   {
      PreparedStatement pstmt=null;
      int rs=0;
      String sql = prop.getProperty("insertGallery");
      try {
         for(int i=0;i<imageCount;i++) {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, gp.getGroupNo());
            pstmt.setString(2, gp.getAlbumCode());
            pstmt.setInt(3, gp.getMemberNo());
            pstmt.setInt(4, gp.getGalNo());
            pstmt.setString(5,(String) oldFileName.get(i));
            pstmt.setString(6,(String) newFileName.get(i));
            pstmt.setString(7, gp.getGalFileContent());
            pstmt.setString(8, gp.getGalMultiStatus());
            rs+=pstmt.executeUpdate();
         }
         
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      finally {
         close(pstmt);
      }
      return rs;
   }
   
   public int maxGalNo(Connection conn,int groupNo, String albumCode)
   {
      PreparedStatement pstmt=null;
      ResultSet result=null;
      int rs=0;
      String sql=prop.getProperty("maxGalNo");
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, groupNo);
         pstmt.setString(2, albumCode);
         result=pstmt.executeQuery();
         while(result.next())
         {
            rs=result.getInt("gal_no_cnt");
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      finally {
         close(result);
         close(pstmt);
      }
      return rs;
   }
   
   public int insertGalComment(Connection conn, GalleryCommentJoin gcj)
   {
      PreparedStatement pstmt=null;
      int rs=0;
      String sql=prop.getProperty("insetGalComment");
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, gcj.getGroupNo());
         pstmt.setInt(2, gcj.getGalFileNo());
         pstmt.setInt(3, gcj.getGalCommentLevel());
         pstmt.setInt(4, gcj.getMemberNo());
         pstmt.setString(5, gcj.getGalCommentContent());
         if(gcj.getGalCommentRef()==1) {pstmt.setInt(6, Integer.parseInt(null));}
         else {pstmt.setInt(6, gcj.getGalCommentRef());}
         rs=pstmt.executeUpdate();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      finally {
         close(pstmt);
      }
      
      return rs;
   }
   
   //galNoList 뽑아오는거 테스트 중입니다
   public List<Integer> distictGalNoList(Connection conn, String albumCode,int groupNo)
   {
      PreparedStatement pstmt = null;
      ResultSet rs=null;
      String sql=prop.getProperty("distinctGalNoList");
      List<Integer> galNoList = new ArrayList();
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, groupNo);
         pstmt.setString(2, albumCode);
         rs=pstmt.executeQuery();
         while(rs.next())
         {
            String galNo=rs.getString("gal_no_dis");
            galNoList.add(Integer.parseInt(galNo));
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
      return galNoList;
   }
   //갤러리 전체 뽑아보자
   public List<GalleryPath> getAllList(Connection conn,String albumCode, int groupNo)
   {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String sql=prop.getProperty("getAllList");
      List<GalleryPath> list = new ArrayList<GalleryPath>();
      GalleryPath gp =null;
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, groupNo);
         pstmt.setString(2, albumCode);
         rs=pstmt.executeQuery();
         while(rs.next())
         {
            gp=new GalleryPath();
            gp.setGroupNo(rs.getInt("group_no"));
            gp.setAlbumCode(rs.getString("album_code"));
            gp.setMemberNo(rs.getInt("member_no"));
            gp.setGalNo(rs.getInt("gal_no"));
            gp.setGalFileNo(rs.getInt("gal_file_no"));
            gp.setGalFileOldPath(rs.getString("gal_file_old_path"));
            gp.setGalFileNewPath(rs.getString("gal_file_new_path"));
            gp.setGalFileContent(rs.getString("gal_file_content"));
            gp.setGalEnrollDate(rs.getDate("gal_enroll_date"));
            gp.setGalMultiStatus(rs.getString("gal_multi_status"));
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
   //그룹의 앨범/갤러리 다뽑기
   public List<GalleryPath> albumAndGalList(Connection conn, int groupNo)
   {
      PreparedStatement pstmt=null;
      ResultSet rs =null;
      String sql = prop.getProperty("albumAndGalList");
      List<GalleryPath> galList = new ArrayList<GalleryPath>();
      GalleryPath gp =null;
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, groupNo);
         rs=pstmt.executeQuery();
         while(rs.next())
         {
            gp=new GalleryPath();
            gp.setGroupNo(rs.getInt("group_no"));
            gp.setAlbumCode(rs.getString("album_code"));
            gp.setMemberNo(rs.getInt("member_no"));
            gp.setGalNo(rs.getInt("gal_no"));
            gp.setGalFileNo(rs.getInt("gal_file_no"));
            gp.setGalFileOldPath(rs.getString("gal_file_old_path"));
            gp.setGalFileNewPath(rs.getString("gal_file_new_path"));
            gp.setGalFileContent(rs.getString("gal_file_content"));
            gp.setGalEnrollDate(rs.getDate("gal_enroll_date"));
            gp.setGalMultiStatus(rs.getString("gal_multi_status"));
            galList.add(gp);
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
      return galList;
   }
   
   public int deleteAlbum(Connection conn, int groupNo, String albumCode)
   {
      PreparedStatement pstmt=null;
      int rs =0;
      String sql = prop.getProperty("deleteAlbum");
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, groupNo);
         pstmt.setString(2, albumCode);
         rs=pstmt.executeUpdate();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      finally {
         close(pstmt);
      }
      return rs;
   }
   
   
   public int galleryDelete(Connection conn, int groupNo, String albumCode, int galNo)
   {
      PreparedStatement pstmt = null;
      int rs = 0;
      String sql= prop.getProperty("galleryDelete");

      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, groupNo);
         pstmt.setString(2, albumCode);
         pstmt.setInt(3, galNo);
         rs=pstmt.executeUpdate();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      finally {
         close(pstmt);
      }
      return rs;
   }
   
   public int deleteComment(Connection conn,int groupNo,int galCommentNo)
   {
	   PreparedStatement pstmt=null;
	   int rs =0;
	   String sql = prop.getProperty("deleteComment");
	   try {
		   pstmt= conn.prepareStatement(sql);
		   pstmt.setInt(1, groupNo);
		   pstmt.setInt(2, galCommentNo);
		   rs=pstmt.executeUpdate();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   finally {
		   close(pstmt);
	   }
	   return rs;
   }
}