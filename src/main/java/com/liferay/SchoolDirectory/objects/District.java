package com.liferay.SchoolDirectory.objects;

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
