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
import com.picsaxis.prioritystatus.bo.OfferPriorityBo;
import com.picsaxis.prioritystatus.model.EmployeeModel;
import com.picsaxis.prioritystatus.model.OfferPriorityModel;

@Namespace("/")
@ResultPath("/")
@ParentPackage(value = "json-default")
public class OfferPriorityAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;

	
	HttpSession session = ServletActionContext.getRequest().getSession(false);
	EmployeeModel loggedInEmp = (EmployeeModel) session.getAttribute("employee");	
	    
	
	private OfferPriorityBo offerPriorityBo= new OfferPriorityBo(); 
	private List<OfferPriorityModel> listObjectAction = new ArrayList<OfferPriorityModel>();
	private int Id[];
	private OfferPriorityModel offerPriorityModel ;
					
	public List<OfferPriorityModel> getListObjectAction() {
		return listObjectAction;
	}
	public void setListObjectAction(List<OfferPriorityModel> listObjectAction) {
		this.listObjectAction = listObjectAction;
	}
	
	public OfferPriorityModel getOfferPriorityModel() {
		return offerPriorityModel;
	}
	public void setOfferPriorityModel(OfferPriorityModel offerPriorityModel) {
		this.offerPriorityModel = offerPriorityModel;
	}
	public int[] getId() {
		return Id;
	}
	public void setId(int id[]) {
		Id = id;
	}


	//-------------------------------------------Offer List-------------------------------------------------	

					@Action(value="offerList" ,  results = 
							{ 
							@Result(name = SUCCESS, type="json"),
							@Result(name =ERROR , location ="pages/login.jsp" )
							}) 
							public String  offerList ()
							{
								if(EmployeeAction.getSessionMap()!=null && EmployeeAction.getSessionMap().containsKey("employee"))
								{
									listObjectAction=offerPriorityBo.showOfferBo();
									if(listObjectAction.size()>0)
									{
										return SUCCESS;
									}
								}
											return ERROR;
							}
					
					
					
			//-------------------------------------------offer Update Priority -------------------------------------------------			
					
					@Action(value="updateOfferPriority" ,  results = 
							{ 
							
							@Result(name = SUCCESS , location ="navOfferPriority", type= "redirect" ),
							@Result(name =ERROR , location ="pages/login.jsp" )
							}) 
							public String  updateOfferPriority ()
							{
								
									boolean value= offerPriorityBo.setOfferPriorityBo(Id);
									if(value)
									{	
										return SUCCESS ;
									}
								
									return ERROR;
							}
					
}
