package com.dongle.gallery.model.vo;

public class AlbumCategory {
	private int groupNo;
	private String albumCode;
	private String albumName;
	
	public AlbumCategory() {}

	public AlbumCategory(int groupNo, String albumCode, String albumName) {
		super();
		this.groupNo = groupNo;
		this.albumCode = albumCode;
		this.albumName = albumName;
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

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	@Override
	public String toString() {
		return "동호회 번호: " + groupNo + "앨범코드: " + albumCode + " 앨범명: " + albumName;
	}
	
	
}
