package com.picsaxis.prioritystatus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.picsaxis.prioritystatus.model.AdvertiserModel;
import com.picsaxis.prioritystatus.util.SqlUtil;

public class AdvertiserDao 
{
	static Connection conn;
	static PreparedStatement stmt ;	
			
				
								public List<AdvertiserModel> showAdvertiserDetailsDao ( ) 
								{
									try
									{
										conn=SqlUtil.createConnection();
										
										stmt =conn.prepareStatement("SELECT "
												+ "ad.advertiser_id, "
												+ "ad.advertiser_name, "
												+ "ad.advertiser_priority, "
												+ "ad.advertiser_status_id, "
												+ "si.status_title "
												+ "FROM "
												+ "advertisers ad, "
												+ "status_info si "
												+ "WHERE "
												+ "  ad.advertiser_priority != 0 "
												+ "AND ad.advertiser_status_id = si.status_id "
												+ "ORDER BY ad.advertiser_priority ASC ; ");
										
										ResultSet rs =stmt.executeQuery();
										List<AdvertiserModel> advertisersList = new ArrayList<AdvertiserModel>();
										while(rs.next())
										{
											
											AdvertiserModel advertiser =new AdvertiserModel();
											advertiser.setAdvertiserId(rs.getString("advertiser_id"));
											advertiser.setAdvertiserName(rs.getString("advertiser_name").toUpperCase());
											advertiser.setAdvertiserPriority(rs.getInt("advertiser_priority"));
											advertiser.setAdvertiserStatusId(rs.getInt("advertiser_status_id"));
											advertiser.setAdvertiserStatusTitle(rs.getString("status_title"));
											advertiser.setActualAdvertiserStatusTitle(rs.getString("status_title").toUpperCase());
											
											advertisersList.add(advertiser);
										}
										return advertisersList;
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
								
								
								
								public boolean setAdvertiserPriorityDao(int advertiserId[],int employeeId)
								{
									try
									{
										
										conn=SqlUtil.createConnection();
										conn.setAutoCommit(false);
										
										stmt =conn.prepareStatement(" SELECT MAX(alter_Index) + 1 FROM audit_advertisers ;");	
										ResultSet rs =stmt.executeQuery();
										int index =0 ;
											while (rs.next())
											{ 
												if (rs.getInt(1)!= 0){
													index = (rs.getInt(1));
												}
												else{
													index = 1;
												}
											}
											
										
									for (int i = 0; i < advertiserId.length; i++)
									{										
										stmt =conn.prepareStatement("UPDATE advertisers "
												+ " SET advertiser_priority = " +(i+1) 
												+ " WHERE"
												+ " advertiser_id = " + advertiserId[i] );
											if(stmt.executeUpdate()>0)
											{
												stmt=conn.prepareStatement(" Insert into audit_advertisers "
														+ " (advertiser_id,advertiser_priority ,advertisers_status_id,admin_id,alter_Index) "
														+ " values (" +advertiserId[i]+ "," +(i+1)+ "," + 0 +","+ employeeId + ","+ index + ")" );
												stmt.executeUpdate();
											}
											
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

								
								
								
						//---------------------------- check Advertiser StatusId--------------------------------------------------------
								
								
								
								public boolean checkAdvertiserStatusIdDao(AdvertiserModel advertiserModel)
								{
									try
									{
										conn=SqlUtil.createConnection();
										stmt =conn.prepareStatement("SELECT advertiser_status_id "
																	+ " FROM advertisers "
																	+ " WHERE "
																	+ " advertiser_id=?  and "
																	+ " advertiser_status_id=? ; ");
										stmt.setString(1, advertiserModel.getAdvertiserId());
										stmt.setInt(2, advertiserModel.getAdvertiserStatusId());
										ResultSet rs =stmt.executeQuery();
										while(rs.next())
										{
											return true;
											
										}
								
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
									return false ;
								}
								
							
						//---------------------------- set Advertiser StatusId--------------------------------------------------------
								
								
								
								public boolean setAdvertiserStatusIdDao(AdvertiserModel advertiserModel , int employeeId)
								{
									try
									{
										conn=SqlUtil.createConnection();
										conn.setAutoCommit(false);
										stmt =conn.prepareStatement(" SELECT MAX(alter_Index) + 1 FROM audit_advertisers ;");	
										ResultSet rs =stmt.executeQuery();
										int index =0 ;
											while (rs.next())
											{ 
												if (rs.getInt(1)!= 0){
													index = (rs.getInt(1));
												}
												else{
													index = 1;
												}
											} 
											
										
										stmt =conn.prepareStatement("UPDATE advertisers "
												+ " SET advertiser_status_id=? "
												+ " WHERE "
												+ " advertiser_id=? ; ");
										stmt.setInt(1, advertiserModel.getAdvertiserStatusId());
										stmt.setString(2, advertiserModel.getAdvertiserId());
										
										if(stmt.executeUpdate()>0)
										{
											stmt=conn.prepareStatement(" Insert into audit_advertisers "
													+ " (advertiser_id,advertiser_priority ,advertisers_status_id,admin_id,alter_Index) "
													+ " values(?,?,?,"+employeeId+","+index+"); ");
											stmt.setString(1, advertiserModel.getAdvertiserId());
											stmt.setInt(2, advertiserModel.getAdvertiserPriority());
											stmt.setInt(3, advertiserModel.getAdvertiserStatusId());
											
											stmt.executeUpdate();
											
											conn.commit();
											return true;
										}
										
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
									return false ;
								}

}
