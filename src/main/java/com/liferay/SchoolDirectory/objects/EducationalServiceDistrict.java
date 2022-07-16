package com.liferay.SchoolDirectory.objects;

public class EducationalServiceDistrict extends Entity{
	String administratorName;
	
	public EducationalServiceDistrict(String esdCode, String esdName, String addressLine1, String addressLine2,
			String state, String zipCode, String email, String phone, String administratorName) {
		super(esdCode, esdName, addressLine1, addressLine2, state, zipCode, email, phone);
		this.administratorName = administratorName;
	}

	public String getAdministratorName() {
		return administratorName;
	}

	public void setAdministratorName(String administratorName) {
		this.administratorName = administratorName;
	}
	
}
