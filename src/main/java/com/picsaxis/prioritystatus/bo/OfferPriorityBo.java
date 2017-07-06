package com.picsaxis.prioritystatus.bo;

import java.util.ArrayList;
import java.util.List;
import com.picsaxis.prioritystatus.dao.OfferPriorityDao;
import com.picsaxis.prioritystatus.model.OfferPriorityModel;

public class OfferPriorityBo 
{
private List<OfferPriorityModel> listObject = new ArrayList<OfferPriorityModel>();
	
	private OfferPriorityDao offerPriorityDao = new OfferPriorityDao();
	
	
	
			
			//-------------------------------------Show Advertiser Details---------------------------------------
			
			public List<OfferPriorityModel> showOfferBo()
			{
				listObject =offerPriorityDao.showOfferDao();
				if(listObject != null)
				{
					return listObject ;
				}
				return null;
			}
			
			
			//-------------------------------set priority ---------------------------------------------------------
			
			public boolean setOfferPriorityBo(int Id[])
			{
				
				boolean value = offerPriorityDao.setOfferPriorityDao(Id);
				if (value)
				{
					return true;
				}
				
				return false;
			}


}
