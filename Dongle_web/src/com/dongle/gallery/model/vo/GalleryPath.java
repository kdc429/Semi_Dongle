package com.dongle.gallery.model.vo;

import java.util.Date;

public class GalleryPath {
	private int groupNo;
	private String albumCode;
	private int galFileNo;
	private String galFilePath;
	private int memberNo;
	private Date galEnrollDate;
	private int galNo;
	
	public GalleryPath() {}
	public GalleryPath(int groupNo, String albumCode, int galFileNo, String galFilePath, int memberNo,
			Date galEnrollDate, int galNo) {
		super();
		this.groupNo = groupNo;
		this.albumCode = albumCode;
		this.galFileNo = galFileNo;
		this.galFilePath = galFilePath;
		this.memberNo = memberNo;
		this.galEnrollDate = galEnrollDate;
		this.galNo=galNo;
		
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public String getAlbumCode() {
		return albumCode;
	}
	public void setAlbumCode(String albumCode) {
		this.albumCode = albumCode;
	}
	public int getGalFileNo() {
		return galFileNo;
	}
	public void setGalFileNo(int galFileNo) {
		this.galFileNo = galFileNo;
	}
	public String getGalFilePath() {
		return galFilePath;
	}
	public void setGalFilePath(String galFilePath) {
		this.galFilePath = galFilePath;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public Date getGalEnrollDate() {
		return galEnrollDate;
	}
	public void setGalEnrollDate(Date galEnrollDate) {
		this.galEnrollDate = galEnrollDate;
	}
	public int getGalNo() {
		return galNo;
	}
	public void setGalNo(int galNo) {
		this.galNo = galNo;
	}
	@Override
	public String toString() {
		return "동호회 번호: " + groupNo + " 앨범코드: " + albumCode + " 사진게시글 번호: " + galFileNo
				+ " 사진 파일 경로: " + galFilePath + " 사진 등록자 번호: " + memberNo + " 등록일: " + galEnrollDate +"사진게시물등록번호: "+galNo;
	}
	

}
