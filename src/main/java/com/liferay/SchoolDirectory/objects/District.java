package com.liferay.SchoolDirectory.objects;

import java.sql.ResultSet;

public class District extends Entity{
	String administratorName, esdCode, esdName;
	
	public District(String code, String name, String addressLine1, String addressLine2, String state,
			String zipCode, String email, String phone, String primaryKey, String esdCode, String esdName,
			String administratorName) {
		super(code, name, addressLine1, addressLine2, state, zipCode, email, phone, primaryKey);
		this.esdCode = esdCode;
		this.esdName = esdName;
		this.administratorName = administratorName;
	}
	
	public District() {
		super();
		this.esdCode="";
		this.esdName = "";
		this.administratorName = "";
	}
	
	public District(ResultSet rs) {
		try {
			super.primaryKey = Integer.toString(rs.getInt("iddistricts"));
			super.code = rs.getString("code");
			super.name = rs.getString("name");
			super.addressLine1 = rs.getString("addressLine1");
			super.addressLine2 = rs.getString("addressLine2");
			super.state = rs.getString("state");
			super.zipCode = rs.getString("zipcode");
			super.email = rs.getString("email");
			super.phone = rs.getString("phone");
			this.administratorName = rs.getString("administratorname");
			this.esdCode = rs.getString("esdcode");
			this.esdName = rs.getString("esdName");
			
			
		} catch(Exception e) {
			System.out.println("ERROR: " + this.getClass().getName() + " - Issue with getting information out of resultSet");
			e.printStackTrace();
		}
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

	public String getAdministratorName() {
		return administratorName;
	}

	public void setAdministratorName(String administratorName) {
		this.administratorName = administratorName;
	}
	
	
}
