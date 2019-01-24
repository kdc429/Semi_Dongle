package com.dongle.calender.model.vo;

import java.util.Date;

public class Calendar {

	
	private int calno;
	private int groupno;
	private Date caldate;
	private String calcontent;
	private String caltitle;
	private int remaincost;
	private int totalcost;
	private int usecost;
	private String caldate_d;
	private String calorfile;
	private String refile;

	
	public Calendar() {
		// TODO Auto-generated constructor stub
	}

	

	public Calendar(int calno, int groupno, Date caldate, String calcontent, String caltitle, int remaincost,
			int totalcost, int usecost, String caldate_d, String calorfile, String refile) {
		super();
		this.calno = calno;
		this.groupno = groupno;
		this.caldate = caldate;
		this.calcontent = calcontent;
		this.caltitle = caltitle;
		this.remaincost = remaincost;
		this.totalcost = totalcost;
		this.usecost = usecost;
		this.caldate_d = caldate_d;
		this.calorfile = calorfile;
		this.refile = refile;
	}



	public int getCalno() {
		return calno;
	}


	public void setCalno(int calno) {
		this.calno = calno;
	}


	public int getGroupno() {
		return groupno;
	}


	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}


	public Date getCaldate() {
		return caldate;
	}


	public void setCaldate(Date caldate) {
		this.caldate = caldate;
	}


	public String getCalcontent() {
		return calcontent;
	}


	public void setCalcontent(String calcontent) {
		this.calcontent = calcontent;
	}


	public String getCaltitle() {
		return caltitle;
	}


	public void setCaltitle(String caltitle) {
		this.caltitle = caltitle;
	}


	public int getRemaincost() {
		return remaincost;
	}


	public void setRemaincost(int remaincost) {
		this.remaincost = remaincost;
	}


	public int getTotalcost() {
		return totalcost;
	}


	public void setTotalcost(int totalcost) {
		this.totalcost = totalcost;
	}


	public int getUsecost() {
		return usecost;
	}


	public void setUsecost(int usecost) {
		this.usecost = usecost;
	}


	public String getCaldate_d() {
		return caldate_d;
	}


	public void setCaldate_d(String caldate_d) {
		this.caldate_d = caldate_d;
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


	@Override
	public String toString() {
		return "Board [calno=" + calno + ", groupno=" + groupno + ", caldate=" + caldate + ", calcontent=" + calcontent
				+ ", caltitle=" + caltitle + ", remaincost=" + remaincost + ", totalcost=" + totalcost + ", usecost="
				+ usecost + ", caldate_d=" + caldate_d + ", calorfile=" + calorfile + ", refile=" + refile + "]";
	}

	


	
	
	
	

	
	
	
	
}
