package com.picsaxis.prioritystatus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.picsaxis.prioritystatus.model.AffiliatesModel;
import com.picsaxis.prioritystatus.util.SqlUtil;
public class AffiliatesDao 
{
	static Connection conn;
	static PreparedStatement stmt ;	
			
		//---------------------------------Affiliates List-----------------------------------------------
	
	
								public List<AffiliatesModel> showAffiliatesDetailsDao ( ) 
								{
									try
									{
										conn=SqlUtil.createConnection();
										
										stmt =conn.prepareStatement("SELECT "
												+ "af.affiliates_id, "
												+ "af.affiliates_name, "
												+ "af.affiliates_status_id, "
												+ "si.status_title "
												+ "FROM "
												+ "affiliates af, "
												+ "status_info si "
												+ "WHERE "
												//+ "af.affiliates_status_id IN (1,2) "
												+ " af.affiliates_status_id = si.status_id "
												+ "ORDER BY af.affiliates_status_id ASC  ");
										
										ResultSet rs =stmt.executeQuery();
										List<AffiliatesModel> affiliatesList = new ArrayList<AffiliatesModel>();
										while(rs.next())
										{
											
											AffiliatesModel affiliates =new AffiliatesModel();
											affiliates.setAffiliatesId(rs.getInt("affiliates_id"));
											affiliates.setAffiliatesName(rs.getString("affiliates_name").toUpperCase());
											affiliates.setAffiliatesStatusId(rs.getInt("affiliates_status_id"));
											affiliates.setAffiliatesStatusTitle(rs.getString("status_title"));
											affiliates.setActualAffiliatesStatusTitle(rs.getString("status_title").toUpperCase());
											
											affiliatesList.add(affiliates);
										}
										return affiliatesList;
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

			//---------------------------- check Affiliates StatusId--------------------------------------------------------
								
								
								
								public boolean checkAffiliatesStatusIdDao(AffiliatesModel affiliatesModel)
								{
									try
									{
										conn=SqlUtil.createConnection();
										stmt =conn.prepareStatement("SELECT affiliates_status_id "
																	+ " FROM affiliates "
																	+ " WHERE "
																	+ " affiliates_id= ?  and "
																	+ " affiliates_status_id=? ; ");
										stmt.setInt(1, affiliatesModel.getAffiliatesId());
										stmt.setInt(2, affiliatesModel.getAffiliatesStatusId());
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
								
							
						//---------------------------- set Affiliates StatusId--------------------------------------------------------
								
								
								
								public boolean setAffiliatesStatusIdDao(AffiliatesModel affiliatesModel , int employeeId)
								{
									try
									{
										conn=SqlUtil.createConnection();
										
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
											
									conn.setAutoCommit(false);
										stmt =conn.prepareStatement("UPDATE affiliates "
												+ " SET affiliates_status_id=? "
												+ " WHERE "
												+ " affiliates_id=? ; ");
										stmt.setInt(1, affiliatesModel.getAffiliatesStatusId());
										stmt.setInt(2, affiliatesModel.getAffiliatesId());
										
										if(stmt.executeUpdate()>0)
										{
											stmt=conn.prepareStatement(" Insert into audit_advertisers "
													+ " (affiliates_id,affiliates_status_id,admin_id,alter_Index) "
													+ " values(?,?,"+employeeId+","+index+"); ");
											stmt.setInt(1, affiliatesModel.getAffiliatesId());
											stmt.setInt(2, affiliatesModel.getAffiliatesStatusId());
											
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
								
								
				//---------------------------- get Affiliates Details--------------------------------------------------------
								
								
								
								public AffiliatesModel getAffiliatesDetailsDao(AffiliatesModel affiliatesModel)
								{
									try
									{
										conn=SqlUtil.createConnection();
										stmt =conn.prepareStatement("SELECT affiliates_name , affiliates_id "
														+ " FROM affiliates "
														+ " WHERE "
														+ " affiliates_id= ? ; ");
										stmt.setInt(1, affiliatesModel.getAffiliatesId());
										ResultSet rs =stmt.executeQuery();
										AffiliatesModel affiliateModel= new AffiliatesModel() ;
										while(rs.next())
										{
											affiliateModel.setAffiliatesName(rs.getString("affiliates_name"));
											affiliateModel.setAffiliatesId(rs.getInt("affiliates_id"));	
											
										}
										return affiliateModel;
								
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
									return null ;
								}
								
		//-------------------------------------------update Affiliates Details------------------------------------
								
								
					public boolean updateAffiliatesDetailsDao(AffiliatesModel affiliatesModel,int employeeId)
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
									
							stmt =conn.prepareStatement("UPDATE affiliates "
														+ " SET affiliates_name=? "
														+ " WHERE "
														+ " affiliates_id= ? ; ");
							stmt.setString(1, affiliatesModel.getAffiliatesName());
							stmt.setInt(2, affiliatesModel.getAffiliatesId());
							if(stmt.executeUpdate()>0)
							{
								stmt=conn.prepareStatement(" Insert into audit_advertisers "
									+ " (affiliates_id,affiliates_status_id,admin_id,alter_Index) "
									+ " values(?,0,"+employeeId+","+index+"); ");
								stmt.setInt(1, affiliatesModel.getAffiliatesId());
								
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
						return false;
					}
								

}
