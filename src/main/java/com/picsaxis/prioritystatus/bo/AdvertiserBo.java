package com.picsaxis.prioritystatus.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.picsaxis.prioritystatus.dao.AdvertiserDao;
import com.picsaxis.prioritystatus.dao.EmployeeDao;
import com.picsaxis.prioritystatus.model.AdvertiserModel;

public class AdvertiserBo 
{
	private List<AdvertiserModel> listObject = new ArrayList<AdvertiserModel>();
	
	private AdvertiserDao  advertiserdao = new AdvertiserDao();
	private EmployeeDao employeeDao =new EmployeeDao();
	private ResourceBundle bundle =ResourceBundle.getBundle("database");				
	
	
	
	//-------------------------------------Show Advertiser Details---------------------------------------
	
						public List<AdvertiserModel> showAdvertiserDetailsBo()
						{
							listObject =advertiserdao.showAdvertiserDetailsDao();
							if(listObject != null)
							{
								for (int i = 0; i < listObject.size() ; i++) 
								{
				
									listObject.get(i).setAdvertiserStatusTitle( listObject.get(i).getAdvertiserStatusTitle().equals("activated") ? "DEACTIVATE":"ACTIVATE");
									
								}
								return listObject ;
							}
							return null;
						}
						
	    
		//-------------------------------set priority ---------------------------------------------------------
						
						public boolean setAdvertiserPriorityBo(int advertiserId[],int employeeId)
						{
							boolean checkEmployeeLogin =employeeDao.checkEmployeeLoggedIn(employeeId);
							if (checkEmployeeLogin)
							{
							boolean value = advertiserdao.setAdvertiserPriorityDao(advertiserId,employeeId);
							if (value)
							{
								return true;
							}
							}
							return false;
						}
	
						
		//------------------------------- set Advertiser StatusId ---------------------------------------------------------
						
						
						public boolean setAdvertiserStatusIdBo(AdvertiserModel advertiserModel,int employeeId)
						{
							boolean checkEmployeeLogin =employeeDao.checkEmployeeLoggedIn(employeeId);
							System.err.println("checkEmployeeLogin :"+ checkEmployeeLogin);
							if (checkEmployeeLogin)
							{
							boolean check   = advertiserdao.checkAdvertiserStatusIdDao(advertiserModel); // checked Advertiser Previous status
							System.err.println("check :"+ check);
							if (check){
								
							int statusId = advertiserModel.getAdvertiserStatusId();
							int activate = Integer.parseInt(bundle.getString("activate"));
							int deactivate = Integer.parseInt(bundle.getString("deactivate"));
							
							if (statusId!= 0)
							{
								if (statusId == activate )
								{
									advertiserModel.setAdvertiserStatusId(deactivate);
								}
								else
								{
									advertiserModel.setAdvertiserStatusId(activate);
								}
								
								boolean value = advertiserdao.setAdvertiserStatusIdDao(advertiserModel,employeeId); // assign new status
											if(value)
											{
												return true;
											}
							}
							}
							}
							return false;
						}

}
