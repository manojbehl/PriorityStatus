package com.picsaxis.prioritystatus.actions;


import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import com.opensymphony.xwork2.ActionSupport;
import com.picsaxis.prioritystatus.bo.AffiliatesBo;
import com.picsaxis.prioritystatus.model.AffiliatesModel;
import com.picsaxis.prioritystatus.model.EmployeeModel;
@Namespace("/")
@ResultPath("/")
@ParentPackage(value = "json-default")

public class AffiliatesAction extends ActionSupport
{
	private static final long serialVersionUID = 662327913352422444L;
	

	HttpSession session = ServletActionContext.getRequest().getSession(false);
	EmployeeModel loggedInEmp = (EmployeeModel) session.getAttribute("employee");	
	    
	
	private AffiliatesBo affiliatesbo= new AffiliatesBo();
	private List<AffiliatesModel> listObjectAction = new ArrayList<AffiliatesModel>();
	private AffiliatesModel affiliatesModel;
	
	public List<AffiliatesModel> getListObjectAction() {
		return listObjectAction;
	}


	public void setListObjectAction(List<AffiliatesModel> listObjectAction) {
		this.listObjectAction = listObjectAction;
	}


	public AffiliatesModel getAffiliatesModel() {
		return affiliatesModel;
	}


	public void setAffiliatesModel(AffiliatesModel affiliatesModel) {
		this.affiliatesModel = affiliatesModel;
	}
	
	

			
			
			//-------------------------------------------Affiliates List-------------------------------------------------			
			
			@Action(value="affiliatesList" ,  results = 
					{ 
					@Result(name = SUCCESS, type="json"),
					@Result(name =ERROR , location ="pages/ERROR.jsp" )
					}) 
					public String  affiliatesList ()
					{
						if(EmployeeAction.getSessionMap()!=null && EmployeeAction.getSessionMap().containsKey("employee"))
						{
							listObjectAction=affiliatesbo.showAffiliatesDetailsBo();
							if(listObjectAction.size()>0)
							{
								return SUCCESS;
							}
						}
									return ERROR;
					}
			
			
			//-------------------------------------------Affiliates Update StatusId-------------------------------------------------			
			
			@Action(value="updateAffiliatesStatusId" ,  results = 
					{ 
					
					@Result(name = SUCCESS , location ="navAffiliates", type= "redirect" ),
					@Result(name =ERROR , location ="pages/login.jsp" )
					}) 
					public String  updateAffiliatesStatusId()
					{
					if(EmployeeAction.getSessionMap()!=null && EmployeeAction.getSessionMap().containsKey("employee"))
						{
							
							boolean value= affiliatesbo.setAffiliatesStatusIdBo(affiliatesModel,loggedInEmp.getEmployeeId());
							
							if(value){
								
								
								return SUCCESS ;
							}
						}
							return ERROR;
							
							
					}
			
			//---------------------------- get Affiliates Details--------------------------------------------------------
			
			@Action(value="getAffiliatesDetails" ,  results = 
			{ 
			
			@Result(name = SUCCESS , type= "json" ),
			@Result(name =ERROR , location ="pages/login.jsp" )
			}) 
			
			public String getAffiliatesDetails()
			{
				if(EmployeeAction.getSessionMap()!=null && EmployeeAction.getSessionMap().containsKey("employee"))
				{
				if (affiliatesModel !=null){
				affiliatesModel=affiliatesbo.getAffiliatesDetailsBo(affiliatesModel);
				if (affiliatesModel !=null)
				{
					
					
					return SUCCESS;
				}
				}
				}
				return ERROR;
			}
			
			//---------------------------- Update Affiliates Details--------------------------------------------------------
			
			@Action(value="updateAffiliatesDetails" ,  results = 
			{ 
			
			@Result(name = SUCCESS , location ="navAffiliates", type= "redirect" ),
			@Result(name =ERROR , location ="pages/login.jsp" )
			}) 
			
			public String updateAffiliatesDetails()
			{
				if (affiliatesModel !=null){
				boolean value=affiliatesbo.updateAffiliatesDetailsBo(affiliatesModel, loggedInEmp.getEmployeeId());
					if (value )
					{
						return SUCCESS;
					}
				}
				return ERROR;
			}	
}
