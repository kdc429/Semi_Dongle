package com.dongle.gallery.model.vo;

import java.util.Date;

public class GalleryComment {
	private int groupNo; //동호회 번호
	private int galFileNo; //사진 번호
	private int galCommentNo; //시퀀스로 생성될 사진 댓글 번호
	private int galLevel; //사진 댓글 레벨
	private int memberNo; //작성자 번호
	private String galCommentContent; //댓글 내용
	private Date galCommentDate; //댓글 작성일
	private int galCommentRef; //사진 댓글 참조번호
	
	public GalleryComment() {}

	public GalleryComment(int groupNo, int galFileNo, int galCommentNo, int galLevel, int memberNo,
			String galCommentContent, Date galCommentDate, int galCommentRef) {
		super();
		this.groupNo = groupNo;
		this.galFileNo = galFileNo;
		this.galCommentNo = galCommentNo;
		this.galLevel = galLevel;
		this.memberNo = memberNo;
		this.galCommentContent = galCommentContent;
		this.galCommentDate = galCommentDate;
		this.galCommentRef = galCommentRef;
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

	public int getGalLevel() {
		return galLevel;
	}

	public void setGalLevel(int galLevel) {
		this.galLevel = galLevel;
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

	@Override
	public String toString() {
		return "GalleryComment테이블 [groupNo=" + groupNo + ", galFileNo=" + galFileNo + ", galCommentNo=" + galCommentNo
				+ ", galLevel=" + galLevel + ", memberNo=" + memberNo + ", galCommentContent=" + galCommentContent
				+ ", galCommentDate=" + galCommentDate + ", galCommentRef=" + galCommentRef + "]";
	}
	
	
	

}
