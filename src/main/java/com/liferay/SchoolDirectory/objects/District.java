package com.liferay.SchoolDirectory.objects;

import com.liferay.SchoolDirectory.constants.SchoolDirectoryPortletKeys;

import java.sql.ResultSet;

public class District extends Entity{
	String administratorName, esdCode, esdName, city;
	
	public District(String code, String name, String addressLine1, String addressLine2, String state,
			String zipCode, String email, String phone, String primaryKey, String esdCode, String esdName,
			String administratorName, String city) {
		super(code, name, addressLine1, addressLine2, state, zipCode, email, phone, primaryKey);
		this.esdCode = esdCode;
		this.esdName = esdName;
		this.administratorName = administratorName;
		this.city = city;
	}
	
	public District() {
		super();
		this.esdCode="";
		this.esdName = "";
		this.administratorName = "";
		this.city = "";
	}
	
	public District(ResultSet rs) {
		try {
			super.primaryKey = Integer.toString(rs.getInt("iddistricts"));
			super.code = rs.getString("code");
			super.name = rs.getString("name");
			super.addressLine1 = rs.getString("addressLine1");
			super.addressLine2 = rs.getString("addressLine2");
			super.state = rs.getString("state");
			super.zipCode = rs.getString("zipCode");
			super.email = rs.getString("email");
			super.phone = rs.getString("phone");
			this.administratorName = rs.getString("administratorName");
			this.esdCode = rs.getString("esdCode");
			this.esdName = rs.getString("esdName");
			this.city = rs.getString("city");
			
			
		} catch(Exception e) {
			System.out.println("ERROR: " + this.getClass().getName() + " - Issue with getting information out of resultSet");
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructor for a CSV row to be converted into a District object
	 * The string array must be in this order
	 * ESDCode,ESDName,DistrictCode,DistrictName,AddressLine1,AddressLine2,
	 * City,State,Zipcode,AdministratorName,Phone,Email
	 * @param csvRow
	 */
	public District(String[] csvRow) {
		if(csvRow.length != SchoolDirectoryPortletKeys.DISTRICT_TABLE_CSV_COLUMN_COUNT) {
			System.out.println("ERROR: " + this.getClass().getName() + 
					" - Cannot convert csvRow since it's the incorrect length - row string = (" + Integer.toString(csvRow.length) + ")");
		} else {
			this.esdCode = csvRow[0];
			this.esdName = csvRow[1];
			super.code = csvRow[2];
			super.name = csvRow[3];
			super.addressLine1 = csvRow[4];
			super.addressLine2 = csvRow[5];
			this.city = csvRow[6];
			super.state = csvRow[7];
			super.zipCode = csvRow[8];
			this.administratorName = csvRow[9];
			super.phone = csvRow[10];
			super.email = csvRow[11];
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
}
