package com.picsaxis.prioritystatus.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;


public class SqlUtil 
	{
				private	static Connection con;
					
							public	static	Connection createConnection()
									{
								
									try
									{
										
										ResourceBundle rb =ResourceBundle.getBundle("database");
										Class.forName(rb.getString("driver"));
										con= DriverManager.getConnection(rb.getString("url"),rb.getString("user"),rb.getString("password"));
										
									}
									catch(Exception ex)
									{
										ex.printStackTrace();
									}
									return con;
									}
							
							
					
	}
