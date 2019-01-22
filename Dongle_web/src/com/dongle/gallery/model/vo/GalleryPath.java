package com.dongle.gallery.model.vo;

import java.util.Date;

public class GalleryPath {
	private int groupNo;
	private String albumCode;
	private int memberNo;
	private int galNo;
	private int galFileNo;
	private String galFileOldPath;
	private String galFileNewPath;
	private String galFileContent;
	private Date galEnrollDate;
	private String galMultiStatus;
	//조인문뽑기
	private String groupMemberNickname;
	private String groupMemberImageNewPath;

	
	public GalleryPath() {}
	

	public GalleryPath(int groupNo, String albumCode, int memberNo, int galNo, int galFileNo, String galFileOldPath,
			String galFileNewPath, String galFileContent, Date galEnrollDate, String galMultiStatus,
			String groupMemberNickname, String groupMemberImageNewPath) {
		super();
		this.groupNo = groupNo;
		this.albumCode = albumCode;
		this.memberNo = memberNo;
		this.galNo = galNo;
		this.galFileNo = galFileNo;
		this.galFileOldPath = galFileOldPath;
		this.galFileNewPath = galFileNewPath;
		this.galFileContent = galFileContent;
		this.galEnrollDate = galEnrollDate;
		this.galMultiStatus = galMultiStatus;
		this.groupMemberNickname = groupMemberNickname;
		this.groupMemberImageNewPath = groupMemberImageNewPath;
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


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	public int getGalNo() {
		return galNo;
	}


	public void setGalNo(int galNo) {
		this.galNo = galNo;
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


	public Date getGalEnrollDate() {
		return galEnrollDate;
	}


	public void setGalEnrollDate(Date galEnrollDate) {
		this.galEnrollDate = galEnrollDate;
	}


	public String getGalMultiStatus() {
		return galMultiStatus;
	}


	public void setGalMultiStatus(String galMultiStatus) {
		this.galMultiStatus = galMultiStatus;
	}


	public String getGroupMemberNickname() {
		return groupMemberNickname;
	}


	public void setGroupMemberNickname(String groupMemberNickname) {
		this.groupMemberNickname = groupMemberNickname;
	}


	public String getGroupMemberImageNewPath() {
		return groupMemberImageNewPath;
	}


	public void setGroupMemberImageNewPath(String groupMemberImageNewPath) {
		this.groupMemberImageNewPath = groupMemberImageNewPath;
	}


	@Override
	public String toString() {
		return " [동호회 번호: " + groupNo + " 앨범코드: " + albumCode + " 사진게시글 번호: " + galFileNo +" 작성자 닉네임:" +groupMemberNickname+ " 작성자 프로필"+groupMemberImageNewPath
				+ " 사진 파일 Old경로: " + galFileOldPath + "사진 파일New경로: "+galFileNewPath+" 사진 등록자 번호: " + memberNo + " 등록일: " + galEnrollDate 
				+" *사진게시물등록번호: "+galNo+" 사진게시물 내용: "+galFileContent+" 다중파일 여부: "+galMultiStatus+"] ";
	}
	

}
