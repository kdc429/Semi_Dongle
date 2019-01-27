package com.dongle.group.model.vo;

public class LocalCtg {
	private String locCtgCode; //LOC_CTG_CODE;
	private String metroCode; //METRO_CODE;
	private String locMetroName; //LOC_METRO_NAME;
	private String areaCode; //AREA_CODE;
	private String locAreaName; //LOC_AREA_NAME;
	private String twonCode; //TOWN_CODE;
	private String locTownName; //LOC_TOWN_NAME;
	public LocalCtg() {
		super();
	}
	public LocalCtg(String locCtgCode, String metroCode, String locMetroName, String areaCode, String locAreaName,
			String twonCode, String locTownName) {
		super();
		this.locCtgCode = locCtgCode;
		this.metroCode = metroCode;
		this.locMetroName = locMetroName;
		this.areaCode = areaCode;
		this.locAreaName = locAreaName;
		this.twonCode = twonCode;
		this.locTownName = locTownName;
	}
	public String getLocCtgCode() {
		return locCtgCode;
	}
	public void setLocCtgCode(String locCtgCode) {
		this.locCtgCode = locCtgCode;
	}
	public String getMetroCode() {
		return metroCode;
	}
	public void setMetroCode(String metroCode) {
		this.metroCode = metroCode;
	}
	public String getLocMetroName() {
		return locMetroName;
	}
	public void setLocMetroName(String locMetroName) {
		this.locMetroName = locMetroName;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getLocAreaName() {
		return locAreaName;
	}
	public void setLocAreaName(String locAreaName) {
		this.locAreaName = locAreaName;
	}
	public String getTwonCode() {
		return twonCode;
	}
	public void setTwonCode(String twonCode) {
		this.twonCode = twonCode;
	}
	public String getLocTownName() {
		return locTownName;
	}
	public void setLocTownName(String locTownName) {
		this.locTownName = locTownName;
	}
	@Override
	public String toString() {
		return "LocalCtg [locCtgCode=" + locCtgCode + ", metroCode=" + metroCode + ", locMetroName=" + locMetroName
				+ ", areaCode=" + areaCode + ", locAreaName=" + locAreaName + ", twonCode=" + twonCode
				+ ", locTownName=" + locTownName + "]";
	}
	
	
}
