package com.picsaxis.prioritystatus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.picsaxis.prioritystatus.model.EmployeeModel;
import com.picsaxis.prioritystatus.util.SqlUtil;

public class EmployeeDao 
{
	static Connection conn;
	static PreparedStatement stmt ;	
	
	//------------------------------------------Employee   Login-------------------------------------------
	
		public EmployeeModel employeeLoginDao(EmployeeModel employee)
		{
			try
			{
				conn=SqlUtil.createConnection();
				conn.setAutoCommit(false);
				stmt =conn.prepareStatement(" SELECT "
						+ " employee_id, employee_name "
						+ " FROM employee "
						+ " WHERE "
						+ "  employee_email =? "
						+ " AND BINARY employee_password =? ");
				stmt.setString(1, employee.getEmployeeEmail());
				stmt.setString(2, employee.getEmployeePassword());
				ResultSet rs =stmt.executeQuery();
				
				EmployeeModel employeeModal = new EmployeeModel();
				while(rs.next())
				{
					
					employeeModal.setEmployeeId(rs.getInt("employee_id"));
					employeeModal.setEmployeeName(rs.getString("employee_name"));
				}
				if (employeeModal.getEmployeeId()!=0)
				{
					stmt =conn.prepareStatement("UPDATE employee "
												+ " SET employee_status_id = 4 "  
												+ " WHERE"
												+ " employee_id = ? ");
					stmt.setInt(1, employeeModal.getEmployeeId());
					if(stmt.executeUpdate()>0)
					{
						conn.commit();
						return employeeModal ;
					}
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
			return null;
		}
		
		//------------------------------------------Employee  Logout-------------------------------------------
		
		public void employeeLogoutDao(int employeeid)
		{
			try
			{
				conn=SqlUtil.createConnection();
				conn.setAutoCommit(false);
				
					stmt =conn.prepareStatement("UPDATE employee "
												+ " SET employee_status_id = 5 "  
												+ " WHERE"
												+ " employee_id = ? ");
					stmt.setInt(1, employeeid);
					if(stmt.executeUpdate()>0)
					{
						conn.commit();
					
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
			
		}
		
		//------------------------------------------ check Employee logged in / logged out -------------------------------------------
			
		
			
			public boolean checkEmployeeLoggedIn(int employeeid)
				{
					try
					{
						conn=SqlUtil.createConnection();
						
						stmt=conn.prepareStatement("SELECT employee_status_id  "
													+ " FROM employee "
													+ "WHERE  "
													+ " employee_status_id=4 AND "
													+ " employee_id = ? ");
						stmt.setInt(1, employeeid);
						ResultSet rs = stmt.executeQuery();
						int count=0;
						while (rs.next()) 
						{
							count++;
						}
						if(count>0)
						{
							return true;
						}
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					finally {
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
		//--------------------------------------------------------Employee list -----------------------------------------
		
			public List<EmployeeModel> showEmployeeStatusDao ( ) 
			{
				try
				{
					conn=SqlUtil.createConnection();
					
					stmt =conn.prepareStatement("SELECT "
							+ "em.employee_id, "
							+ "em.employee_name, "
							+ "em.employee_status_id "
							+ "FROM "
							+ "employee em "
							+ "WHERE "
							+ " em.employee_working_status_id = 6 "
							+ "ORDER BY em.employee_id ASC ; ");
					
					ResultSet rs =stmt.executeQuery();
					List<EmployeeModel> employeeList = new ArrayList<EmployeeModel>();
					while(rs.next())
					{
						
						EmployeeModel employee =new EmployeeModel();
						employee.setEmployeeId(rs.getInt("employee_id"));
						employee.setEmployeeName(rs.getString("employee_name"));
						employee.setEmployeeStatusId(rs.getInt("employee_status_id"));
						
						employeeList.add(employee);
					}
					return employeeList;
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
}
