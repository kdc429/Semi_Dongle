package com.dongle.main.model.vo;

public class LocationCategory {
	
	private String locCtgCode;
	private String metroCode;
	private String locMetroName;
	private String areaCode;
	private String locAreaName;
	private String townCode;
	private String locTownName;
	
	public LocationCategory()
	{
		
	}
	public LocationCategory(String locCtgCode, String metroCode, String locMetroName, String areaCode,
			String locAreaName, String townCode, String locTownName) {
		super();
		this.locCtgCode = locCtgCode;
		this.metroCode = metroCode;
		this.locMetroName = locMetroName;
		this.areaCode = areaCode;
		this.locAreaName = locAreaName;
		this.townCode = townCode;
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
	public String getTownCode() {
		return townCode;
	}
	public void setTownCode(String townCode) {
		this.townCode = townCode;
	}
	public String getLocTownName() {
		return locTownName;
	}
	public void setLocTownName(String locTownName) {
		this.locTownName = locTownName;
	}
	@Override
	public String toString() {
		return "LocationCategory [locCtgCode=" + locCtgCode + ", metroCode=" + metroCode + ", locMetroName="
				+ locMetroName + ", areaCode=" + areaCode + ", locAreaName=" + locAreaName + ", townCode=" + townCode
				+ ", locTownName=" + locTownName + "]";
	}
	
	
	
	
	

}
