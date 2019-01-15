package com.dongle.board.model.vo;

public class BoardPath {
	
	private int boardNo;
	private int groupNo;
	private int boardFileNo;
	private String boardFileOldPath;
	private String boardFileNewPath;
	
	public BoardPath() {}

	public BoardPath(int boardNo, int groupNo, int boardFileNo, String boardFileOldPath, String boardFileNewPath) {
		super();
		this.boardNo = boardNo;
		this.groupNo = groupNo;
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
		return "BoardPath [boardNo=" + boardNo + ", groupNo=" + groupNo + ", boardFileNo=" + boardFileNo
				+ ", boardFileOldPath=" + boardFileOldPath + ", boardFileNewPath=" + boardFileNewPath + "]";
	}
	
	
}