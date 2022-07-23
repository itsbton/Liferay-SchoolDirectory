package com.liferay.SchoolDirectory.objects;

import java.sql.ResultSet;

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
	
	public School(ResultSet rs) {
		try {
			super.primaryKey = Integer.toString(rs.getInt("idschools"));
			super.code = rs.getString("code");
			super.name = rs.getString("name");
			super.addressLine1 = rs.getString("addressLine1");
			super.addressLine2 = rs.getString("addressLine2");
			super.state = rs.getString("state");
			super.zipCode = rs.getString("zipcode");
			super.email = rs.getString("email");
			super.phone = rs.getString("phone");
			
			this.leaCode = rs.getString("leacode");
			this.leaName = rs.getString("leaname");
			this.esdCode = rs.getString("esdcode");
			this.esdName = rs.getString("esdname");
			this.lowestGrade = rs.getString("lowestgrade");
			this.highestGrade = rs.getString("highestgrade");
			this.city = rs.getString("city");
			this.principalName = rs.getString("principalname");
			this.orgCategoryList = rs.getString("orgcategorylist");
			this.aypCode = rs.getString("aypcode");
			this.gradeCategory = rs.getString("gradecategory");
			
		} catch(Exception e) {
			System.out.println("ERROR: " + this.getClass().getName() + " - Issue with getting information out of resultSet");
			e.printStackTrace();
		}
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

	@Override
	public String toString() {
		return "School [leaCode=" + leaCode + ", leaName=" + leaName + ", lowestGrade=" + lowestGrade + ", esdCode="
				+ esdCode + ", esdName=" + esdName + ", highestGrade=" + highestGrade + ", city=" + city
				+ ", principalName=" + principalName + ", orgCategoryList=" + orgCategoryList + ", aypCode=" + aypCode
				+ ", gradeCategory=" + gradeCategory + ", addressLine1=" + addressLine1 + ", addressLine2="
				+ addressLine2 + ", state=" + state + ", zipCode=" + zipCode + ", email=" + email + ", phone=" + phone
				+ ", primaryKey=" + primaryKey + ", code=" + code + ", name=" + name + "]";
	}
	
}
