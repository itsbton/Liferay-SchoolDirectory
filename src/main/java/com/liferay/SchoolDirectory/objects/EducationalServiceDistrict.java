package com.liferay.SchoolDirectory.objects;

import com.liferay.SchoolDirectory.constants.SchoolDirectoryPortletKeys;

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
			super.primaryKey = Integer.toString(rs.getInt("idEducationalServiceDistricts"));
			super.code = rs.getString("code");
			super.name = rs.getString("name");
			super.addressLine1 = rs.getString("addressLine1");
			super.addressLine2 = rs.getString("addressLine2");
			super.state = rs.getString("state");
			super.zipCode = rs.getString("zipCode");
			super.email = rs.getString("email");
			super.phone = rs.getString("phone");
			this.administratorName = rs.getString("administratorName");
			
		} catch(Exception e) {
			System.out.println("ERROR: " + this.getClass().getName() + " - Issue with getting information out of resultSet");
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructor for a CSV row to be converted into a ESD object
	 * The string array must be in this order
	 * ESD Name,ESD Code,AddressLine1,AddressLine2,State,ZipCode,Administrator Name,Phone,Email
	 * @param csvRow
	 */
	public EducationalServiceDistrict(String[] csvRow){
		if(csvRow.length != SchoolDirectoryPortletKeys.ESD_TABLE_CSV_COLUMN_COUNT) {
			System.out.println("ERROR: " + this.getClass().getName() + 
					" - Cannot convert csvRow since it's the incorrect length - row string = (" + Integer.toString(csvRow.length) + ")");
		} else {
			super.name = csvRow[0];
			super.code = csvRow[1];
			super.addressLine1 = csvRow[2];
			super.addressLine2 = csvRow[3];
			super.state = csvRow[4];
			super.zipCode = csvRow[5];
			this.administratorName = csvRow[6];
			super.phone = csvRow[7];
			super.email = csvRow[8];
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
