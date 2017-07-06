package com.picsaxis.prioritystatus.bo;

import java.util.ArrayList;
import java.util.List;

import com.picsaxis.prioritystatus.dao.EmployeeDao;
import com.picsaxis.prioritystatus.model.EmployeeModel;

public class EmployeeBo 
{
	List<EmployeeModel> employeeList = new ArrayList<EmployeeModel>();
	EmployeeDao employeeDao =  new EmployeeDao();
	EmployeeModel employeeModal = new EmployeeModel();
	
	//------------------------------------------------employee login -----------------------------------------
			public EmployeeModel employeeLoginBo(EmployeeModel employee)
			{
				employeeModal = employeeDao.employeeLoginDao(employee);
				
				if(employeeModal !=null)
				{
					return employeeModal;
				}
				
				return null;
			}
			
	//------------------------------------------------employee logout -----------------------------------------
			public void employeeLogoutBo(int employeeid)
			{
				 employeeDao.employeeLogoutDao( employeeid);
				
			}
			
	//-------------------------------------Show Employee Current Status---------------------------------------
			
			public List<EmployeeModel> showEmployeeStatusBo()
			{
				employeeList =employeeDao.showEmployeeStatusDao();
				if(employeeList != null)
				{
					return employeeList ;
				}
				return null;
			}


}
