package com.picsaxis.prioritystatus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.picsaxis.prioritystatus.model.OfferPriorityModel;
import com.picsaxis.prioritystatus.util.SqlUtil;

public class OfferPriorityDao {
	
	
	static Connection conn;
	static PreparedStatement stmt ;	
			
				
								public List<OfferPriorityModel> showOfferDao ( ) 
								{
									try
									{
										conn=SqlUtil.createConnection();
										
										stmt =conn.prepareStatement("select op.id,op.offer_id,hod.name,op.priority,adv.advertiser_name  from offer_priority op inner join hasoffer_data hod on hod.id=op.offer_id "
												+ " inner join advertisers adv on  adv.id = hod.Advertiser ORDER BY op.priority ASC");
										
										ResultSet rs =stmt.executeQuery();
										List<OfferPriorityModel> OfferList = new ArrayList<OfferPriorityModel>();
										while(rs.next())
										{
											
											OfferPriorityModel offer =new OfferPriorityModel();
											offer.setOfferId(rs.getInt("offer_id"));
											offer.setOfferName(rs.getString("name").toUpperCase());
											offer.setOfferPriority(rs.getInt("priority"));
											offer.setId(rs.getInt("id"));
											offer.setAdvertiserName(rs.getString("advertiser_name"));
											
											OfferList.add(offer);
										}
										return OfferList;
									}
									catch(SQLException e){
										e.printStackTrace();
										
									}
									catch(Exception e)
									{
									e.printStackTrace();	
									}
									finally
									{
										if (conn!=null){
											try{
												conn.close();
											}
											catch (Exception e) {
												e.printStackTrace();
											}
											
										}
										
									}
									return null;
								}
								
				
								
				//----------------------------Set priority   --------------------------------------------------------
								
								public boolean setOfferPriorityDao(int Id[])
								{
									try
									{
										
										conn=SqlUtil.createConnection();
										conn.setAutoCommit(false);
									
									for (int i = 0; i < Id.length ; i++)
									{										
										stmt =conn.prepareStatement("UPDATE offer_priority "
												+ " SET priority = " +(i+1) 
												+ " WHERE"
												+ " id = " + Id[i] );
										stmt.execute();
									}
									conn.commit();
									return true;
									}
									catch(SQLException e){
										e.printStackTrace();
										
									}
									catch(Exception e)
									{
									e.printStackTrace();	
									}
									finally
									{
										if (conn!=null){
											try{
												conn.close();
											}
											catch (Exception e) {
												e.printStackTrace();
											}
											
										}
										
									}
									return false;
								}	

}
