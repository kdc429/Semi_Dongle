package com.dongle.board.model.vo;

import java.util.Date;

public class BoardPath {
	
	private int boardNo;
	private int groupNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private Date boardWriteDate;
	private int boardViewCount;
	private String boardStatus;
	private int boardFileNo;
	private String boardFileOldPath;
	private String boardFileNewPath;
	
	public BoardPath() {}

	public BoardPath(int boardNo, int groupNo, String boardTitle, String boardWriter, String boardContent,
			Date boardWriteDate, int boardViewCount, String boardStatus, int boardFileNo, String boardFileOldPath,
			String boardFileNewPath) {
		super();
		this.boardNo = boardNo;
		this.groupNo = groupNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
		this.boardWriteDate = boardWriteDate;
		this.boardViewCount = boardViewCount;
		this.boardStatus = boardStatus;
		this.boardFileNo = boardFileNo;
		this.boardFileOldPath = boardFileOldPath;
		this.boardFileNewPath = boardFileNewPath;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public Date getBoardWriteDate() {
		return boardWriteDate;
	}

	public void setBoardWriteDate(Date boardWriteDate) {
		this.boardWriteDate = boardWriteDate;
	}

	public int getBoardViewCount() {
		return boardViewCount;
	}

	public void setBoardViewCount(int boardViewCount) {
		this.boardViewCount = boardViewCount;
	}

	public String getBoardStatus() {
		return boardStatus;
	}

	public void setBoardStatus(String boardStatus) {
		this.boardStatus = boardStatus;
	}

	public int getBoardFileNo() {
		return boardFileNo;
	}

	public void setBoardFileNo(int boardFileNo) {
		this.boardFileNo = boardFileNo;
	}

	public String getBoardFileOldPath() {
		return boardFileOldPath;
	}

	public void setBoardFileOldPath(String boardFileOldPath) {
		this.boardFileOldPath = boardFileOldPath;
	}

	public String getBoardFileNewPath() {
		return boardFileNewPath;
	}

	public void setBoardFileNewPath(String boardFileNewPath) {
		this.boardFileNewPath = boardFileNewPath;
	}

	@Override
	public String toString() {
		return "BoardPath [boardNo=" + boardNo + ", groupNo=" + groupNo + ", boardTitle=" + boardTitle
				+ ", boardWriter=" + boardWriter + ", boardContent=" + boardContent + ", boardWriteDate="
				+ boardWriteDate + ", boardViewCount=" + boardViewCount + ", boardStatus=" + boardStatus
				+ ", boardFileNo=" + boardFileNo + ", boardFileOldPath=" + boardFileOldPath + ", boardFileNewPath="
				+ boardFileNewPath + "]";
	}

	
}

