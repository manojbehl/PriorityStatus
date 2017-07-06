package com.picsaxis.prioritystatus.model;

public class AffiliatesModel 
{
	private int affiliatesId ;
	private int affiliatesPriority;
	private int affiliatesStatusId;
	private String affiliatesStatusTitle;
	private String affiliatesName;
	private String actualAffiliatesStatusTitle;
	private EmployeeModel employeeModal;
	
	
	
	
	
	public int getAffiliatesId() {
		return affiliatesId;
	}
	public void setAffiliatesId(int affiliatesId) {
		this.affiliatesId = affiliatesId;
	}
	public int getAffiliatesPriority() {
		return affiliatesPriority;
	}
	public void setAffiliatesPriority(int affiliatesPriority) {
		this.affiliatesPriority = affiliatesPriority;
	}
	public int getAffiliatesStatusId() {
		return affiliatesStatusId;
	}
	public void setAffiliatesStatusId(int affiliatesStatusId) {
		this.affiliatesStatusId = affiliatesStatusId;
	}
	public String getAffiliatesStatusTitle() {
		return affiliatesStatusTitle;
	}
	public void setAffiliatesStatusTitle(String affiliatesStatusTitle) {
		this.affiliatesStatusTitle = affiliatesStatusTitle;
	}
	public String getAffiliatesName() {
		return affiliatesName;
	}
	public void setAffiliatesName(String affiliatesName) {
		this.affiliatesName = affiliatesName;
	}
	public String getActualAffiliatesStatusTitle() {
		return actualAffiliatesStatusTitle;
	}
	public void setActualAffiliatesStatusTitle(String actualAffiliatesStatusTitle) {
		this.actualAffiliatesStatusTitle = actualAffiliatesStatusTitle;
	}
	public EmployeeModel getEmployeeModal() {
		return employeeModal;
	}
	public void setEmployeeModal(EmployeeModel employeeModal) {
		this.employeeModal = employeeModal;
	}
	
}
