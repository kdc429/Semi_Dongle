package com.dongle.board.model.vo;

public class BoardPath {
	
	private int boardNo;
	private int groupNo;
	private int boardFileNo;
	private String boardFilePath;
	
	public BoardPath() {}

	public BoardPath(int boardNo, int groupNo, int boardFileNo, String boardFilePath) {
		super();
		this.boardNo = boardNo;
		this.groupNo = groupNo;
		this.boardFileNo = boardFileNo;
		this.boardFilePath = boardFilePath;
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

	public int getBoardFileNo() {
		return boardFileNo;
	}

	public void setBoardFileNo(int boardFileNo) {
		this.boardFileNo = boardFileNo;
	}

	public String getBoardFilePath() {
		return boardFilePath;
	}

	public void setBoardFilePath(String boardFilePath) {
		this.boardFilePath = boardFilePath;
	}

	@Override
	public String toString() {
		return "BoardPath [boardNo=" + boardNo + ", groupNo=" + groupNo + ", boardFileNo=" + boardFileNo
				+ ", boardFilePath=" + boardFilePath + "]";
	}
	
	
}
