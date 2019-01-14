package com.dongle.gallery.model.vo;

import java.util.Date;

public class GalleryPath {
	private int groupNo;
	private String albumCode;
	private int galFileNo;
	private String galFileOldPath;
	private int memberNo;
	private Date galEnrollDate;
	private int galNo;
	private String galFileNewPath;
	private String galFileContent;
	
	public GalleryPath() {}
	
	public GalleryPath(int groupNo, String albumCode, int galFileNo, String galFileOldPath, int memberNo,
			Date galEnrollDate, int galNo, String galFileNewPath, String galFileContent) {
		super();
		this.groupNo = groupNo;
		this.albumCode = albumCode;
		this.galFileNo = galFileNo;
		this.galFileOldPath = galFileOldPath;
		this.memberNo = memberNo;
		this.galEnrollDate = galEnrollDate;
		this.galNo = galNo;
		this.galFileNewPath = galFileNewPath;
		this.galFileContent = galFileContent;
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

	public String getGalFileOldPath() {
		return galFileOldPath;
	}

	public void setGalFileOldPath(String galFileOldPath) {
		this.galFileOldPath = galFileOldPath;
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

	public String getGalFileNewPath() {
		return galFileNewPath;
	}

	public void setGalFileNewPath(String galFileNewPath) {
		this.galFileNewPath = galFileNewPath;
	}
	
	public String getGalFileContent() {
		return galFileContent;
	}

	public void setGalFileContent(String galFileContent) {
		this.galFileContent = galFileContent;
	}
	@Override
	public String toString() {
		return "동호회 번호: " + groupNo + " 앨범코드: " + albumCode + " 사진게시글 번호: " + galFileNo
				+ " 사진 파일 Old경로: " + galFileOldPath + "사진 파일New경로: "+galFileNewPath+" 사진 등록자 번호: " + memberNo + " 등록일: " + galEnrollDate +"사진게시물등록번호: "+galNo+" 사진게시물 내용: "+galFileContent;
	}
	

}
