package com.picsaxis.prioritystatus.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import com.picsaxis.prioritystatus.dao.AffiliatesDao;
import com.picsaxis.prioritystatus.dao.EmployeeDao;
import com.picsaxis.prioritystatus.model.AffiliatesModel;
public class AffiliatesBo 
{
	private AffiliatesDao  affiliatesdao = new AffiliatesDao();
	private EmployeeDao employeeDao =new EmployeeDao();
	private ResourceBundle bundle =ResourceBundle.getBundle("database");	
	private List<AffiliatesModel> listObject = new ArrayList<AffiliatesModel>();

//-------------------------------------Show Affiliates Details---------------------------------------
								
					public List<AffiliatesModel> showAffiliatesDetailsBo()
					{
						listObject =affiliatesdao.showAffiliatesDetailsDao();
						if(listObject != null)
						{
							for (int i = 0; i < listObject.size() ; i++) 
							{
			
								listObject.get(i).setAffiliatesStatusTitle( listObject.get(i).getAffiliatesStatusTitle().equals("activated") ? "DEACTIVATE":"ACTIVATE");
								
							}
							return listObject ;
						}
						return null;
					}
					
					//------------------------------- set Affiliates StatusId ---------------------------------------------------------
					
					
					public boolean setAffiliatesStatusIdBo(AffiliatesModel affiliatesModel,int employeeId)
					{
						boolean checkEmployeeLogin =employeeDao.checkEmployeeLoggedIn(employeeId);
						if (checkEmployeeLogin)
						{
						boolean check   = affiliatesdao.checkAffiliatesStatusIdDao(affiliatesModel); // checked Advertiser Previous status
						if (check){
							
						int statusId = affiliatesModel.getAffiliatesStatusId();
						int activate = Integer.parseInt(bundle.getString("activate"));
						int deactivate = Integer.parseInt(bundle.getString("deactivate"));
						
						if (statusId!= 0)
						{
							if (statusId == activate )
							{
								affiliatesModel.setAffiliatesStatusId(deactivate);
							}
							else
							{
								affiliatesModel.setAffiliatesStatusId(activate);
							}
							
							boolean value = affiliatesdao.setAffiliatesStatusIdDao(affiliatesModel,employeeId); // assign new status
										if(value)
										{
											return true;
										}
						}
						}
						}
						return false;
					}
				
					
			//---------------------------- get Affiliates Details--------------------------------------------------------
					
					
					
					public AffiliatesModel getAffiliatesDetailsBo(AffiliatesModel affiliatesModel)
					{
						
						affiliatesModel=affiliatesdao.getAffiliatesDetailsDao(affiliatesModel);
						if (affiliatesModel!=null)
						{
							return affiliatesModel;
						}
						return null;
					}
			
				//-------------------------------update Affiliates Details---------------------------------------------
					
					public boolean updateAffiliatesDetailsBo(AffiliatesModel affiliatesModel ,int employeeId)
					{
						boolean checkEmployeeLogin =employeeDao.checkEmployeeLoggedIn(employeeId);
						if (checkEmployeeLogin)
						{
							boolean updateDetails=affiliatesdao.updateAffiliatesDetailsDao(affiliatesModel,employeeId);
							if(updateDetails)
							{
								return true;
							}
						}
						return false;
					}

}
