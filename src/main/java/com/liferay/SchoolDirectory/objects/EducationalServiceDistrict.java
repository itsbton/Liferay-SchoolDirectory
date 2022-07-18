package com.liferay.SchoolDirectory.objects;

import java.sql.ResultSet;

public class EducationalServiceDistrict extends Entity{
	String administratorName;
	
	public EducationalServiceDistrict(String esdCode, String esdName, String addressLine1, String addressLine2,
			String state, String zipCode, String email, String phone, String primaryKey, String administratorName) {
		super(esdCode, esdName, addressLine1, addressLine2, state, zipCode, email, phone, primaryKey);
		this.administratorName = administratorName;
	}
	public EducationalServiceDistrict() {
		super("", "", "", "", "", "", "", "", "");
		this.administratorName = "";
	}
	
	public EducationalServiceDistrict(ResultSet rs) {
		try {
			super.primaryKey = Integer.toString(rs.getInt("idEducationServiceDistricts"));
			super.code = rs.getString("code");
			super.name = rs.getString("name");
			super.addressLine1 = rs.getString("addressLine1");
			super.addressLine2 = rs.getString("addressLine2");
			super.state = rs.getString("state");
			super.zipCode = rs.getString("zip");
			super.email = rs.getString("email");
			super.phone = rs.getString("phone");
			this.administratorName = rs.getString("administratorname");
			
		} catch(Exception e) {
			System.out.println("ERROR: " + this.getClass().getName() + " - Issue with getting information out of resultSet");
			e.printStackTrace();
		}
	}

	public String getAdministratorName() {
		return administratorName;
	}

	public void setAdministratorName(String administratorName) {
		this.administratorName = administratorName;
	}

	@Override
	public String toString() {
		return "EducationalServiceDistrict [administratorName=" + administratorName + ", code=" + code
				+ ", name=" + name + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", state=" + state + ", zipCode=" + zipCode + ", email=" + email + ", phone=" + phone
				+ ", primaryKey=" + primaryKey + "]";
	}
	
}
