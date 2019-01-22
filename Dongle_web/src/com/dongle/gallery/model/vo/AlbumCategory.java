package com.dongle.gallery.model.vo;

public class AlbumCategory {
	private int groupNo;
	private String albumCode;
	private String albumName;
	private int albumNo;
	
	public AlbumCategory() {}

	public AlbumCategory(int groupNo, String albumCode, String albumName, int albumNo) {
		super();
		this.groupNo = groupNo;
		this.albumCode = albumCode;
		this.albumName = albumName;
		this.albumNo = albumNo;
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

	public int getAlbumNo() {
		return albumNo;
	}

	public void setAlbumNo(int albumNo) {
		this.albumNo = albumNo;
	}

	@Override
	public String toString() {
		return "동호회 번호: " + groupNo + "앨범코드: " + albumCode + " 앨범명: " + albumName + " 앨범정렬번호: "+albumNo;
	}
	
	
}
