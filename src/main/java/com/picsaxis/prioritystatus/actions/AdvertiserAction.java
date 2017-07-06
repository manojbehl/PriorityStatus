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
import com.picsaxis.prioritystatus.bo.AdvertiserBo;
import com.picsaxis.prioritystatus.model.AdvertiserModel;
import com.picsaxis.prioritystatus.model.EmployeeModel;




@Namespace("/")
@ResultPath("/")
@ParentPackage(value = "json-default")
public class AdvertiserAction extends ActionSupport 
{
	private static final long serialVersionUID = -9080644137433240197L;
	
	HttpSession session = ServletActionContext.getRequest().getSession(false);
	EmployeeModel loggedInEmp = (EmployeeModel) session.getAttribute("employee");	
	    
	
	private AdvertiserBo advertiserbo= new AdvertiserBo(); 
	private List<AdvertiserModel> listObjectAction = new ArrayList<AdvertiserModel>();
	private int advertiserId[];
	private AdvertiserModel advertiserModel ;


	public AdvertiserModel getAdvertiserModel() {
		return advertiserModel;
	}

	public void setAdvertiserModel(AdvertiserModel advertiserModel) {
		this.advertiserModel = advertiserModel;
	}
	public int[] getAdvertiserId() {
		return advertiserId;
	}

	public void setAdvertiserId(int[] advertiserId) {
		this.advertiserId = advertiserId;
	}

	public List<AdvertiserModel> getListObjectAction() {
		return listObjectAction;
	}

	public void setListObjectAction(List<AdvertiserModel> listObjectAction) {
		this.listObjectAction = listObjectAction;
	}

	
	
	
	//-------------------------------------------Advertiser List-------------------------------------------------			
					
					@Action(value="advertiserList" ,  results = 
							{ 
							@Result(name = SUCCESS, type="json"),
							@Result(name =ERROR , location ="pages/login.jsp" )
							}) 
							public String  advertiserList ()
							{
								if(EmployeeAction.getSessionMap()!=null && EmployeeAction.getSessionMap().containsKey("employee"))
								{
									listObjectAction=advertiserbo.showAdvertiserDetailsBo();
									if(listObjectAction.size()>0)
									{
										return SUCCESS;
									}
								}
											return ERROR;
							}
					
	
					
					
					//-------------------------------------------Advertiser List-------------------------------------------------			
									
									/*@Action(value="navlogin" ,  results = 
											{ 
											
											@Result(name = SUCCESS , location ="pages/AdvertiserDetails.jsp" )
											}) 
											public String  navadvertiserList ()
											{
													return SUCCESS ;
											}*/
											
					
				//-------------------------------------------Advertiser Update Priority -------------------------------------------------			
									
									@Action(value="updatePriority" ,  results = 
											{ 
											
											@Result(name = SUCCESS , location ="navLogin", type= "redirect" ),
											@Result(name =ERROR , location ="pages/login.jsp" )
											}) 
											public String  updatePriority ()
											{
												if(EmployeeAction.getSessionMap()!=null && EmployeeAction.getSessionMap().containsKey("employee"))
												{
													boolean value= advertiserbo.setAdvertiserPriorityBo(advertiserId,loggedInEmp.getEmployeeId());
													if(value)
													{	
														return SUCCESS ;
													}
												}
													return ERROR;
											}

				//-------------------------------------------Advertiser Update StatusId-------------------------------------------------			
									@Action(value="updateStatusId" ,  results = 
										{ 
										@Result(name = SUCCESS , location ="navLogin", type= "redirect" ),
										@Result(name =ERROR , location ="pages/login.jsp")
										})
						 
											public String  updateStatusId()
											{
											if(EmployeeAction.getSessionMap()!=null && EmployeeAction.getSessionMap().containsKey("employee"))
												{
													
													boolean value= advertiserbo.setAdvertiserStatusIdBo(advertiserModel,loggedInEmp.getEmployeeId());
													
													if(value){
														
														
														return SUCCESS ;
													}
												}
													return ERROR;
													
													
											}
								
					
					
					
					
					
					
}
