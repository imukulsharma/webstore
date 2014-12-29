package com.packt.webstore.domain;

import java.io.Serializable;

public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7103153562757007564L;

	private String doorNo;
	private String streetName;
	private String areaName;
	private String state;
	private String country;
	private String zipCode;

	public Address() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != this.getClass()) {
			return false;
		}

		Address other = (Address) obj;

		if (!doorNo.equals(other.doorNo) || !streetName.equals(other.streetName) || !areaName.equals(other.areaName)
				|| !state.equals(other.state) || !country.equals(other.country) || !zipCode.equals(other.zipCode)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
