package com.liferay.SchoolDirectory.objects;

public class District extends Entity{
	String districtCode, districtName, administratorName;
	
	public District(String esdCode, String esdName, String addressLine1, String addressLine2, String state,
			String zipCode, String email, String phone, String districtCode, String districtName,
			String administratorName) {
		super(esdCode, esdName, addressLine1, addressLine2, state, zipCode, email, phone);
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.administratorName = administratorName;
	}
	
	public District() {
		super();
		this.districtCode="";
		this.districtName = "";
		this.administratorName = "";
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getAdministratorName() {
		return administratorName;
	}

	public void setAdministratorName(String administratorName) {
		this.administratorName = administratorName;
	}
	
	
}
