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
	private String albumCode;
	private String galFilePath;
	private int galNo;
	
	public GalleryCommentJoin() {}

	public GalleryCommentJoin(int groupNo, int galFileNo, int galCommentNo, int galCommentLevel, int memberNo,
			String galCommentContent, Date galCommentDate, int galCommentRef, String groupMemberNickname,
			String albumCode, String galFilePath,int galNo) {
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
		this.albumCode = albumCode;
		this.galFilePath = galFilePath;
		this.galNo=galNo;
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

	public int getgalCommentLevel() {
		return galCommentLevel;
	}

	public void setgalCommentLevel(int galCommentLevel) {
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

	public String getAlbumCode() {
		return albumCode;
	}

	public void setAlbumCode(String albumCode) {
		this.albumCode = albumCode;
	}

	public String getGalFilePath() {
		return galFilePath;
	}

	public void setGalFilePath(String galFilePath) {
		this.galFilePath = galFilePath;
	}
	
	public int getGalNo() {
		return galNo;
	}

	public void setGalNo(int galNo) {
		this.galNo = galNo;
	}


	@Override
	public String toString() {
		return "GalleryComment테이블 Join함 [groupNo=" + groupNo + ", galFileNo=" + galFileNo + ", galCommentNo=" + galCommentNo
				+ ", galCommentLevel=" + galCommentLevel + ", memberNo=" + memberNo + ", galCommentContent=" + galCommentContent
				+ ", galCommentDate=" + galCommentDate + ", galCommentRef=" + galCommentRef + ", groupMemberNickname="
				+ groupMemberNickname + ", albumCode=" + albumCode + ", galFilePath=" + galFilePath + "galNo= "+galNo+"]";
	}

	

}
