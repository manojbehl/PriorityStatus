package com.picsaxis.prioritystatus.model;

public class AdvertiserModel {

	private String advertiserId ;
	private int advertiserPriority;
	private int advertiserStatusId;
	private String advertiserStatusTitle;
	private String advertiserName;
	private String actualAdvertiserStatusTitle;
	private EmployeeModel employeeModal;
	
	

	public EmployeeModel getEmployeeModal() {
		return employeeModal;
	}

	public void setEmployeeModal(EmployeeModel employeeModal) {
		this.employeeModal = employeeModal;
	}

	public String getAdvertiserId() {
		return advertiserId;
	}

	public void setAdvertiserId(String advertiserId) {
		this.advertiserId = advertiserId;
	}

	public int getAdvertiserPriority() {
		return advertiserPriority;
	}

	public void setAdvertiserPriority(int advertiserPriority) {
		this.advertiserPriority = advertiserPriority;
	}

	public int getAdvertiserStatusId() {
		return advertiserStatusId;
	}

	public void setAdvertiserStatusId(int advertiserStatusId) {
		this.advertiserStatusId = advertiserStatusId;
	}

	public String getAdvertiserStatusTitle() {
		return advertiserStatusTitle;
	}

	public void setAdvertiserStatusTitle(String advertiserStatusTitle) {
		this.advertiserStatusTitle = advertiserStatusTitle;
	}

	public String getAdvertiserName() {
		return advertiserName;
	}

	public void setAdvertiserName(String advertiserName) {
		this.advertiserName = advertiserName;
	}

	public String getActualAdvertiserStatusTitle() {
		return actualAdvertiserStatusTitle;
	}

	public void setActualAdvertiserStatusTitle(String actualAdvertiserStatusTitle) {
		this.actualAdvertiserStatusTitle = actualAdvertiserStatusTitle;
	}

	

}
