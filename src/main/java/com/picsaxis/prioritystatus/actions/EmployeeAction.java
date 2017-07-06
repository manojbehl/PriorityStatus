package com.picsaxis.prioritystatus.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.picsaxis.prioritystatus.bo.EmployeeBo;
import com.picsaxis.prioritystatus.model.EmployeeModel;

@Namespace("/")
@ResultPath("/")
@ParentPackage(value = "json-default")


public class EmployeeAction extends ActionSupport implements SessionAware
{
	List<EmployeeModel> employeeList = new ArrayList<EmployeeModel>();
	private static final long serialVersionUID = 2695223981375503802L;
	
	private static SessionMap < String, Object > sessionMap;
	 EmployeeBo employeeBo =new EmployeeBo();
	 private EmployeeModel employeeModal ;
	
	 
	 public static SessionMap < String, Object > getSessionMap() {
			return sessionMap;
		}


		public static void setSessionMap(SessionMap < String, Object > sessionMap) {
			EmployeeAction.sessionMap = sessionMap;
		}
	 public EmployeeModel getEmployeeModal() {
			return employeeModal;
		}


		public void setEmployeeModal(EmployeeModel employeeModal) {
			this.employeeModal = employeeModal;
		}
		

		public List<EmployeeModel> getEmployeeList() {
			return employeeList;
		}


		public void setEmployeeList(List<EmployeeModel> employeeList) {
			this.employeeList = employeeList;
		}

	
		//-------------------------------------------Employee List-------------------------------------------------			
		

				@Action(value="employeeStatusList" ,  results = 
						{ 
						@Result(name = SUCCESS, type="json"),
						@Result(name =ERROR , location ="pages/login.jsp" )
						}) 
						public String  employeeStatusList ()
						{
							
								employeeList=employeeBo.showEmployeeStatusBo();
								if(employeeList.size()>0)
								{
									return SUCCESS;
								}
							
										return ERROR;
						}
		
		//-------------------------------------------Employee Login-------------------------------------------------			
	

					@Action(value="navLogin" ,  results = 
							{ 
							
							@Result(name = SUCCESS , location ="pages/AdvertiserDetails.jsp" ),
							@Result(name = ERROR , location ="pages/login.jsp" )
							}) 
							public String  employeeLogin ()
							{
								if(EmployeeAction.getSessionMap()!=null && EmployeeAction.getSessionMap().containsKey("employee"))
									{
									return SUCCESS;
									}
								if(employeeModal!= null)
								{
									employeeModal=employeeBo.employeeLoginBo(employeeModal);
								if(employeeModal!= null)
									{
										sessionMap.put("employee",employeeModal );
										
										return SUCCESS;
									}
								}
									return ERROR ;
							}

					//-------------------------------------------Employee Logout-------------------------------------------------			
					

					@Action(value="navLogOut" ,  results = 
							{ 
							
							@Result(name = SUCCESS , location ="navLogin" , type="redirect" )
							}) 
							public String  employeeLogout ()
							{
								if(EmployeeAction.getSessionMap()!=null && EmployeeAction.getSessionMap().containsKey("employee"))
								{
									HttpSession session = ServletActionContext.getRequest().getSession(false);
									EmployeeModel loggedEmp = (EmployeeModel) session.getAttribute("employee");	
									employeeBo.employeeLogoutBo(loggedEmp.getEmployeeId());
									
								}
									
									sessionMap.invalidate();
									return SUCCESS;
								
								
							}
					//------------------------------------------navLoggedInEmployeeList-------------------------
					@Action(value="navLoggedInEmployeeList" ,  results = 
						{ 
						
						@Result(name = SUCCESS , location ="pages/LoggedInEmployeeList.jsp" ),
						@Result(name = ERROR , location ="pages/login.jsp" )
						}) 
					public String  navLoggedInEmployeeList ()
					{
						if(EmployeeAction.getSessionMap()!=null && EmployeeAction.getSessionMap().containsKey("employee"))
						{
							
								return SUCCESS;
							
						}
									return ERROR;
					}
			
					//-------------------------------------------navigate to Affiliates.jsp-------------------------------------------------			
					

					@Action(value="navAffiliates" ,  results = 
					{ 
							@Result(name = SUCCESS , location ="pages/AffiliatesDetails.jsp" ),
							@Result(name =ERROR , location ="pages/login.jsp" )
							
					}) 
					public String  navAffiliates ()
					{
						if(EmployeeAction.getSessionMap()!=null && EmployeeAction.getSessionMap().containsKey("employee"))
						{
							return SUCCESS ;
						}
						return ERROR;
					}
					
					//-------------------------------------------navigate to Offer_Priority.jsp-------------------------------------------------			
					

					@Action(value="navOfferPriority" ,  results = 
					{ 
							@Result(name = SUCCESS , location ="pages/offerpriority.jsp" ),
							@Result(name =ERROR , location ="pages/login.jsp" )
							
					}) 
					public String  navOfferPriority ()
					{
						if(EmployeeAction.getSessionMap()!=null && EmployeeAction.getSessionMap().containsKey("employee"))
						{
							return SUCCESS ;
						}
						return ERROR;
					}
					

					//----------------------------set Session Map-------------------------------
					
					
					public void setSession(Map < String, Object > map) {
					   setSessionMap((SessionMap<String,Object>)map);
					  
					 }		

					
			

}
