package com.dongle.board.model.vo;

import java.sql.Date;

/**
 * @author kjh
 *
 */
public class BoardComment {
	
	private int groupNo;
	private int boardNo;
	private int boCommentNo;
	private int memberNo;
	private String boCommentContent;
	private Date boCommentDate;
	private int boCommentLevel;
	private int boCommentRef;
	private String boCommentReportStatus;
	private String groupMemberNickname;
	private String groupMemberImageNewPath;
	
	public BoardComment() {}

	public BoardComment(int groupNo, int boardNo, int boCommentNo, int memberNo, String boCommentContent,
			Date boCommentDate, int boCommentLevel, int boCommentRef, String boCommentReportStatus,
			String groupMemberNickname, String groupMemberImageNewPath) {
		super();
		this.groupNo = groupNo;
		this.boardNo = boardNo;
		this.boCommentNo = boCommentNo;
		this.memberNo = memberNo;
		this.boCommentContent = boCommentContent;
		this.boCommentDate = boCommentDate;
		this.boCommentLevel = boCommentLevel;
		this.boCommentRef = boCommentRef;
		this.boCommentReportStatus = boCommentReportStatus;
		this.groupMemberNickname = groupMemberNickname;
		this.groupMemberImageNewPath = groupMemberImageNewPath;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getBoCommentNo() {
		return boCommentNo;
	}

	public void setBoCommentNo(int boCommentNo) {
		this.boCommentNo = boCommentNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getBoCommentContent() {
		return boCommentContent;
	}

	public void setBoCommentContent(String boCommentContent) {
		this.boCommentContent = boCommentContent;
	}

	public Date getBoCommentDate() {
		return boCommentDate;
	}

	public void setBoCommentDate(Date boCommentDate) {
		this.boCommentDate = boCommentDate;
	}

	public int getBoCommentLevel() {
		return boCommentLevel;
	}

	public void setBoCommentLevel(int boCommentLevel) {
		this.boCommentLevel = boCommentLevel;
	}

	public int getBoCommentRef() {
		return boCommentRef;
	}

	public void setBoCommentRef(int boCommentRef) {
		this.boCommentRef = boCommentRef;
	}

	public String getBoCommentReportStatus() {
		return boCommentReportStatus;
	}

	public void setBoCommentReportStatus(String boCommentReportStatus) {
		this.boCommentReportStatus = boCommentReportStatus;
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
		return "BoardComment [groupNo=" + groupNo + ", boardNo=" + boardNo + ", boCommentNo=" + boCommentNo
				+ ", memberNo=" + memberNo + ", boCommentContent=" + boCommentContent + ", boCommentDate="
				+ boCommentDate + ", boCommentLevel=" + boCommentLevel + ", boCommentRef=" + boCommentRef
				+ ", boCommentReportStatus=" + boCommentReportStatus + ", groupMemberNickname=" + groupMemberNickname
				+ ", groupMemberImageNewPath=" + groupMemberImageNewPath + "]";
	}
	
}