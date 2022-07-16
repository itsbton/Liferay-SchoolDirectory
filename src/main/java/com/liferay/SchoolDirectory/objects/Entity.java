package com.liferay.SchoolDirectory.objects;

public class Entity {
	String esdCode, esdName, addressLine1, addressLine2, state, zipCode, email, phone;

	
	public Entity(String esdCode, String esdName, String addressLine1, String addressLine2, String state,
			String zipCode, String email, String phone) {
		super();
		this.esdCode = esdCode;
		this.esdName = esdName;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.state = state;
		this.zipCode = zipCode;
		this.email = email;
		this.phone = phone;
	}
	
	public Entity() {
		super();
		this.esdCode = "";
		this.esdName = "";
		this.addressLine1 = "";
		this.addressLine2 = "";
		this.state = "";
		this.zipCode = "";
		this.email = "";
		this.phone = "";
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

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
