package com.liferay.SchoolDirectory.objects;

public class School extends Entity{
	String leaCode, leaName, lowestGrade, esdCode, esdName,
	highestGrade, city, principalName, orgCategoryList, aypCode, gradeCategory;
	
	public School(String code, String name, String addressLine1, String addressLine2, String state,
			String zipCode, String email, String phone, String primaryKey, String leaCode, String leaName, String esdCode,
			String esdName, String lowestGrade, String highestGrade, String city, String principalName,
			String orgCategoryList, String aypCode, String gradeCategory) {
		super(code, name, addressLine1, addressLine2, state, zipCode, email, phone, primaryKey);
		this.leaCode = leaCode;
		this.leaName = leaName;
		this.esdCode = esdCode;
		this.esdName = esdName;
		this.lowestGrade = lowestGrade;
		this.highestGrade = highestGrade;
		this.city = city;
		this.principalName = principalName;
		this.orgCategoryList = orgCategoryList;
		this.aypCode = aypCode;
		this.gradeCategory = gradeCategory;
	}
	
	public School() {
		super();
		this.leaCode = "";
		this.leaName = "";
		this.esdCode = "";
		this.esdName = "";
		this.lowestGrade = "";
		this.highestGrade = "";
		this.city = "";
		this.principalName = "";
		this.orgCategoryList = "";
		this.aypCode = "";
		this.gradeCategory = "";
	}

	public String getLeaCode() {
		return leaCode;
	}

	public void setLeaCode(String leaCode) {
		this.leaCode = leaCode;
	}

	public String getLeaName() {
		return leaName;
	}

	public void setLeaName(String leaName) {
		this.leaName = leaName;
	}

	public String getEsdCode() {
		return esdCode;
	}

	public void setEsdCode(String esdCode) {
		this.esdCode = esdCode;
	}

	public String getEsdName() {
		return esdName;
	}

	public void setEsdName(String esdName) {
		this.esdName = esdName;
	}

	public String getLowestGrade() {
		return lowestGrade;
	}

	public void setLowestGrade(String lowestGrade) {
		this.lowestGrade = lowestGrade;
	}

	public String getHighestGrade() {
		return highestGrade;
	}

	public void setHighestGrade(String highestGrade) {
		this.highestGrade = highestGrade;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getOrgCategoryList() {
		return orgCategoryList;
	}

	public void setOrgCategoryList(String orgCategoryList) {
		this.orgCategoryList = orgCategoryList;
	}

	public String getAypCode() {
		return aypCode;
	}

	public void setAypCode(String aypCode) {
		this.aypCode = aypCode;
	}

	public String getGradeCategory() {
		return gradeCategory;
	}

	public void setGradeCategory(String gradeCategory) {
		this.gradeCategory = gradeCategory;
	}
	
	
}
