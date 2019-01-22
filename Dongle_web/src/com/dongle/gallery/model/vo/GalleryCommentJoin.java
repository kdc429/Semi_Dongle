package com.dongle.gallery.model.vo;

import java.util.Date;

public class GalleryCommentJoin {
	private int groupNo; //동호회 번호
	private int galFileNo; //사진 번호
	private int galCommentNo; //시퀀스로 생성될 사진 댓글 번호
	private int galCommentLevel; //사진 댓글 레벨
	private int memberNo; //작성자 번호
	private String galCommentContent; //댓글 내용
	private Date galCommentDate; //댓글 작성일
	private int galCommentRef; //사진 댓글 참조번호
	//join문 이용하여 뽑을 내용
	private String groupMemberNickname;
	private String groupMemberImageNewPath;// GROUP_MEMBER_IMAGE_NEW_PATH
	private String albumCode;
	private String galFileNewPath;
	private int galNo;

	private String galMultiStatus;
	
	public GalleryCommentJoin() {}

	public GalleryCommentJoin(int groupNo, int galFileNo, int galCommentNo, int galCommentLevel, int memberNo,
			String galCommentContent, Date galCommentDate, int galCommentRef, String groupMemberNickname,
			String groupMemberImageNewPath, String albumCode, String galFileNewPath, int galNo, String galMultiStatus) {
		super();
		this.groupNo = groupNo;
		this.galFileNo = galFileNo;
		this.galCommentNo = galCommentNo;
		this.galCommentLevel = galCommentLevel;
		this.memberNo = memberNo;
		this.galCommentContent = galCommentContent;
		this.galCommentDate = galCommentDate;
		this.galCommentRef = galCommentRef;
		this.groupMemberNickname = groupMemberNickname;
		this.groupMemberImageNewPath = groupMemberImageNewPath;
		this.albumCode = albumCode;
		this.galFileNewPath = galFileNewPath;
		this.galNo = galNo;
		this.galMultiStatus = galMultiStatus;
	}


	public int getGroupNo() {
		return groupNo;
	}


	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}


	public int getGalFileNo() {
		return galFileNo;
	}


	public void setGalFileNo(int galFileNo) {
		this.galFileNo = galFileNo;
	}


	public int getGalCommentNo() {
		return galCommentNo;
	}


	public void setGalCommentNo(int galCommentNo) {
		this.galCommentNo = galCommentNo;
	}


	public int getGalCommentLevel() {
		return galCommentLevel;
	}


	public void setGalCommentLevel(int galCommentLevel) {
		this.galCommentLevel = galCommentLevel;
	}


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	public String getGalCommentContent() {
		return galCommentContent;
	}


	public void setGalCommentContent(String galCommentContent) {
		this.galCommentContent = galCommentContent;
	}


	public Date getGalCommentDate() {
		return galCommentDate;
	}


	public void setGalCommentDate(Date galCommentDate) {
		this.galCommentDate = galCommentDate;
	}


	public int getGalCommentRef() {
		return galCommentRef;
	}


	public void setGalCommentRef(int galCommentRef) {
		this.galCommentRef = galCommentRef;
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


	public String getAlbumCode() {
		return albumCode;
	}


	public void setAlbumCode(String albumCode) {
		this.albumCode = albumCode;
	}


	public String getGalFileNewPath() {
		return galFileNewPath;
	}


	public void setGalFileNewPath(String galFileNewPath) {
		this.galFileNewPath = galFileNewPath;
	}


	public int getGalNo() {
		return galNo;
	}


	public void setGalNo(int galNo) {
		this.galNo = galNo;
	}


	public String getGalMultiStatus() {
		return galMultiStatus;
	}


	public void setGalMultiStatus(String galMultiStatus) {
		this.galMultiStatus = galMultiStatus;
	}


	@Override
	public String toString() {
		return "GalleryCommentJoin테이블 조인함 [groupNo=" + groupNo + ", galFileNo=" + galFileNo + ", galCommentNo=" + galCommentNo
				+ ", galCommentLevel=" + galCommentLevel + ", memberNo=" + memberNo + ", galCommentContent="
				+ galCommentContent + ", galCommentDate=" + galCommentDate + ", galCommentRef=" + galCommentRef
				+ ", groupMemberNickname=" + groupMemberNickname + ", groupMemberImageNewPath="
				+ groupMemberImageNewPath + ", albumCode=" + albumCode + ", galFileNewPath=" + galFileNewPath 
				+ ", galNo=" + galNo + " galMultiStatus= "+galMultiStatus+"]";
	}


	

}
