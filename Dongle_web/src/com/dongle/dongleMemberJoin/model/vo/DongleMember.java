package com.dongle.dongleMemberJoin.model.vo;

import java.util.Date;

public class DongleMember {
	
	private int groupno;
	private int memberno;
	private String nickname;
	private String calorfile;
	private String refile;
	private Date enrollDate;
	private int blacklist;
	private int reportcount;
	
	
	public DongleMember () {}

	
	
	public DongleMember(int groupno, int memberno, String nickname, String calorfile, String refile, Date enrollDate,
			int blacklist, int reportcount) {
		super();
		this.groupno = groupno;
		this.memberno = memberno;
		this.nickname = nickname;
		this.calorfile = calorfile;
		this.refile = refile;
		this.enrollDate = enrollDate;
		this.blacklist = blacklist;
		this.reportcount = reportcount;
	}



	public int getGroupno() {
		return groupno;
	}


	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}


	public int getMemberno() {
		return memberno;
	}


	public void setMemberno(int memberno) {
		this.memberno = memberno;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getCalorfile() {
		return calorfile;
	}


	public void setCalorfile(String calorfile) {
		this.calorfile = calorfile;
	}


	public String getRefile() {
		return refile;
	}


	public void setRefile(String refile) {
		this.refile = refile;
	}


	public Date getEnrollDate() {
		return enrollDate;
	}


	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}


	public int getBlacklist() {
		return blacklist;
	}


	public void setBlacklist(int blacklist) {
		this.blacklist = blacklist;
	}


	public int getReportcount() {
		return reportcount;
	}


	public void setReportcount(int reportcount) {
		this.reportcount = reportcount;
	}


	@Override
	public String toString() {
		return "DongleMember [groupno=" + groupno + ", memberno=" + memberno + ", nickname=" + nickname + ", calorfile="
				+ calorfile + ", refile=" + refile + ", enrollDate=" + enrollDate + ", blacklist=" + blacklist
				+ ", reportcount=" + reportcount + "]";
	}

	
	

	

	
	
	
	

}
