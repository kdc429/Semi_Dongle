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
import com.dongle.member.model.vo.ReportReason;

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
   
   public GroupMember checkGroupMember(int groupNo, int memberNo)
   {
      Connection conn=getConnection();
      GroupMember gm= new GalleryDao().checkGroupMember(conn,groupNo,memberNo);
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
   
   public int insertGallery(GalleryPath gp,List oldFileName, List newFileName, int imageCount)
   {
      Connection conn = getConnection();
      int rs = new GalleryDao().insertGallery(conn,gp,oldFileName,newFileName,imageCount);
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
   public List<Integer> distictGalNoList(String albumCode,int groupNo)
   {
      Connection conn = getConnection();
      List<Integer> galNoList = new GalleryDao().distictGalNoList(conn,albumCode,groupNo);
      close(conn);
      return galNoList;
   }
   //일단 그 그룹 그 앨범 전체 갤러리 뽑아봅시다
   public List<GalleryPath> getAllList(String albumCode,int groupNo)
   {
      Connection conn=getConnection();
      List<GalleryPath> list = new GalleryDao().getAllList(conn,albumCode,groupNo);
      close(conn);
      return list;
   }
   //그룹에 있는 모든 앨범 /사진 다뽑기
   public List<GalleryPath> albumAndGalList(int groupNo)
   {
      Connection conn = getConnection();
      List<GalleryPath> galList = new GalleryDao().albumAndGalList(conn, groupNo);
      close(conn);
      return galList;
   }
   
   //앨범 삭제하기
   public int deleteAlbum(int groupNo, String albumCode)
   {
      Connection conn = getConnection();
      int rs = new GalleryDao().deleteAlbum(conn,groupNo,albumCode);
      if(rs!=0) {commit(conn);}
      else {rollback(conn);}
      return rs;
   }
   //사진 삭제하기
   public int galleryDelete(int groupNo,String albumCode, int galNo)
   {
      Connection conn = getConnection();
      int rs = new GalleryDao().galleryDelete(conn, groupNo, albumCode, galNo);
      if(rs!=0) {commit(conn);}
      else {rollback(conn);}
      return rs;
   }
   //코멘트 삭제하기
   public int deleteComment(int groupNo,int galCommentNo)
   {
	   Connection conn = getConnection();
	   int rs = new GalleryDao().deleteComment(conn,groupNo,galCommentNo);
	      if(rs!=0) {commit(conn);}
	      else {rollback(conn);}
	      return rs;
   }
   //신고 카테고리 테이블 뽑아오기
   public List<ReportReason> selectReportReason()
   {
	   Connection conn = getConnection();
	   List<ReportReason> relist = new GalleryDao().selectReportReason(conn);
	   close(conn);
	   return relist;
   }
   
   //갤러리 신고하기
   public int insertReport(int groupNo,int memberNo,String reportCode) {
	   Connection conn = getConnection();
	   int rs = new GalleryDao().insertReport(conn, groupNo, memberNo, reportCode);
	   if(rs!=0) {commit(conn);}
	   else {rollback(conn);}
	   return rs;
   }
   //신고 후 갤러리 신고여부 변경
   public int updateGalleryReport(int groupNo,String albumCode,int galNo)
   {
	   Connection conn = getConnection();
	   int rs = new GalleryDao().updateGalleryReport(conn,groupNo,albumCode,galNo);
	   if(rs!=0) {commit(conn);}
	   else {rollback(conn);}
	   return rs;
   }
   //신고 후 갤러리 코멘트 신고여부 변경
   public int updateGalleryCommentReport(int groupNo,int galNo,int galCommentNo) {
	   Connection conn = getConnection();
	   int rs = new GalleryDao().updateGalleryCommentReport(conn,groupNo,galNo,galCommentNo);
	   if(rs!=0) {commit(conn);}
	   else {rollback(conn);}
	   return rs;
   }
   
}