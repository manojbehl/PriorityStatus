
$(document).ready(function(){

	/// Opening the Section for populating the Solution Area drop down basis on changes done in Service Line Drop Down
	$( "#stateId" ).change(function(){
		var state=$("#stateId").val();
//		alert("Change of State : "+id);		
		$.ajax({url: "<%=request.getContextPath()%>/stateWiseWorkOrders?customer.state="+state, success: function(data) {
		
			var techCounter = data.employeeList.length;
			//alert("techCounter : "+techCounter);
			var workOrderCounter = data.workOrderList.length;
			//alert("workOrderCounter : "+workOrderCounter);
			var options = $("#techId");
			options.find('option').remove().end();
			options.append($("<option />").val("-1").text("--Select--"));
			if(techCounter>0){				
				for (var i = 0; i < techCounter; i++) {
					options.append($("<option />").val(data.employeeList[i].empId).text(data.employeeList[i].empName));
				}
			}
			else 
                   {
                        var options=$("#techId");
 	                    options.find('option').remove().end();
                       // alert("Data is Empty");
                        
                   }
			
			//alert("workOrderCounter:"+workOrderCounter);
			if(workOrderCounter>0){
				var tableBody=$("#secondrow");
				tableBody.find('td').remove().end();
				for(var i=0;i<workOrderCounter;i++){ 
					//alert("dddd");
					tableBody.append('<tr><td><input type="checkbox" value='+data.workOrderList[i].workId+' name="selectedWorkOrder"></td><td>'
      			  			+data.workOrderList[i].workId +'</td><td>' 
      			  			+ data.workOrderList[i].customer.connectionId + '</td><td>' 
      			  			+ data.workOrderList[i].customer.state +  '</td><td>' 
      			  			+ data.workOrderList[i].customer.city + '</td><td>'
      			  			+ data.workOrderList[i].customer.street + '</td><td>' 
      			  			+ data.workOrderList[i].workTypeName + '</td><td>'
      			  			+ data.workOrderList[i].workStatusName + '</td></tr>');
				}
			}

			else 
                   {
                        var tableBody=$("#secondrow");
 	                    tableBody.find('tr').remove().end();
                       // alert("Data is Empty");
                        return false;
                   }


		}});
	});
	/// Closing the Section for populating the Solution Area drop down basis on changes done in Service Line Drop Down		
});

