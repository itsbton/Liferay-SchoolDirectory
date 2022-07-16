package com.liferay.SchoolDirectory.objects;

public class School extends Entity{
	String leaCode, leaName, schoolCode, schoolName, lowestGrade, 
	highestGrade, city, principalName, orgCategoryList, aypCode, gradeCategory;
	
	public School(String esdCode, String esdName, String addressLine1, String addressLine2, String state,
			String zipCode, String email, String phone, String leaCode, String leaName, String schoolCode,
			String schoolName, String lowestGrade, String highestGrade, String city, String principalName,
			String orgCategoryList, String aypCode, String gradeCategory) {
		super(esdCode, esdName, addressLine1, addressLine2, state, zipCode, email, phone);
		this.leaCode = leaCode;
		this.leaName = leaName;
		this.schoolCode = schoolCode;
		this.schoolName = schoolName;
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
		this.schoolCode = "";
		this.schoolName = "";
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

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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
