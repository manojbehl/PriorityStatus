<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en" >
<head>
	
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title></title>
	<meta name="description" content="Bootstrap PriorityStatus Dashboard">
	<meta name="author" content="Sudhakar Rathi">
	<meta name="keyword" content="PriorityStatus, PriorityStatus UI, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
	<!-- end: Meta -->
	
	<!-- start: Mobile Specific -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- end: Mobile Specific -->
	
	<!-- start: CSS -->
	<link id="bootstrap-style" href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
	<link id="base-style" href="css/style.css" rel="stylesheet">
	<link id="base-style-responsive" href="css/style-responsive.css" rel="stylesheet">
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext' rel='stylesheet' type='text/css'>
	<!-- end: CSS -->
	
	<!-- start: Favicon -->
	<link rel="shortcut icon" href="img/favicon.ico">
	<!-- end: Favicon -->
	

 	 <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
 	 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 
 	 <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	 <script src="js/jquery-1.9.1.min.js"></script>
	 <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css"></link>
			<%-- 	<script>
			 $(function(){
				 $("#updateInfo").click(function(){
					
					 $("#updatediv").dialog({
							title:"Update Information" ,
							width:450,
							height:500,
							modal:true,
							button:{
								Close:
									function(){
									$(this).dialog('close');
								}
							}
					 });
				 });
			 });
			 
			 </script>  --%>

  <script>
  var ContextPath = "<%= request.getContextPath()%>"
  
  function updateStatusId(affiliatesId,affiliatesStatusId)
  {
	  $.ajax({url: ContextPath+"/updateAffiliatesStatusId?affiliatesModel.affiliatesId="+affiliatesId+"&&affiliatesModel.affiliatesStatusId="+affiliatesStatusId
	  , success:function(data){
		  updates();
	  }});
  }
  
		  
  function updateInfo(affiliatesId)
  { 
	
	  $("#updatediv").dialog({
			title:"Update Information" ,
			width:450,
			height:500,
			modal:true,			
	  });
		
	  $.ajax({url: ContextPath+"/getAffiliatesDetails?affiliatesModel.affiliatesId="+affiliatesId , success:function(data){
		  $("#updateAffiliates").empty()
		  
				$("#updateAffiliates").append("<tr>"
						+"<td>NAME</td>"
						+"<td><input type='text' name='affiliatesModel.affiliatesName' value='"+data.affiliatesModel.affiliatesName+"'/></td>"	
						+"</tr><tr>"
						+"<td><input type='hidden' name='affiliatesModel.affiliatesId' value='"+data.affiliatesModel.affiliatesId+"'/></td>"
						+"<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						+"&nbsp;&nbsp;<button type='submit' class='btn btn-primary'>UPDATE</button> </td>"
						+"</tr>");
			
				  
	  }   
	  }); 
	  
  }
 </script>
							 
 
  <script>
 	 $(document).ready(function(){
								 	
 			updates();
 	
 		});
 		function updates(){
 			$.getJSON("affiliatesList",function(data){
 				$("#tbody").empty()
 				var sno=1;
 				
 				$.each(data.listObjectAction,function(){
 					$("#tbody").append("<tr>"
 							+"<td>"+sno+"</td>"
 							+"<td>"+this['affiliatesId']+"</td>"
 							+"<td>"+this['affiliatesName'] +"</td>"	
 							+"<td>"+this['actualAffiliatesStatusTitle']+"</td>"
 							+"<td>"+( this['affiliatesStatusTitle']=='DEACTIVATE' ?
								"<button class='btn btn-danger' onclick='updateStatusId("+this['affiliatesId']+','+this['affiliatesStatusId']+")' >"+this['affiliatesStatusTitle']+"</button>" :
									"<button class='btn btn-success' onclick='updateStatusId("+this['affiliatesId']+','+this['affiliatesStatusId']+")'  >"+this['affiliatesStatusTitle']+"</button>"
 							)+"</td>"
 							+"<td><button type='button' class='btn btn-primary' onclick='updateInfo("+this['affiliatesId']+ ")'>Edit-Info</button></td>"
							+"</tr>" );
 							sno++;
 							
 				});
 			});
 		}
  </script>
 
									
	
</head>

<body>
		<!-- start: Header -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="#"><span>PriorityStatus</span></a>
								
				<!-- start: Header Menu -->
				<div class="nav-no-collapse header-nav">
					<ul class="nav pull-right">
						
						
						
						
						<!-- start: User Dropdown -->
						<li class="dropdown">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="halflings-icon white user"></i> <s:property value="#session.employee.employeeName"/>
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li class="dropdown-menu-title">
 									<span>Account Settings</span>
								</li>
								<li><a href="#"><i class="halflings-icon user"></i> Profile</a></li>
								<li><a href="navLogOut"><i class="halflings-icon off"></i> Logout</a></li>
							</ul>
						</li>
						<!-- end: User Dropdown -->
					</ul>
				</div>
				<!-- end: Header Menu -->
				
			</div>
		</div>
	</div>
	<!-- start: Header -->
	
		<div  id="divhide" class="container-fluid-full">
		<div class="row-fluid">
				
			<!-- start: Main Menu -->
			<div id="sidebar-left" class="span2">
				<div class="nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li><a href="navOfferPriority"><i class="icon-bar-chart"></i><span class="hidden-tablet"> Offer_Priority </span></a></li>
						<li><a href="navLogin"><i class="icon-book"></i><span class="hidden-tablet"> Advertiser Details </span></a></li>	
						<li><a href="#"><i class="icon-book"></i><span class="hidden-tablet"> Affiliates Details</span></a></li>
						<li><a href="navLoggedInEmployeeList"><i class="icon-bar-chart"></i><span class="hidden-tablet">LoggedIn-Employee</span></a></li>

					</ul>
				</div>
			</div>
			<!-- end: Main Menu -->
			
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			
			<!-- start: Content -->
			<div id="content" class="span10">
			
			
			<ul class="breadcrumb">
				<li>
					<i class="icon-home"></i>
					<a href="#">Home</a>
					<i class="icon-angle-right"></i> 
				</li>
				<li>
					<i class="icon-edit"></i>
					<a href="#">Affiliates Details</a>
				</li>
			</ul>

			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon book"></i><span class="break"></span> Affiliates Details </h2>
						
					</div>
					<div class="box-content">
						
	<!-- 		<form  method="post" id="formId">
	 -->			
				<table class="table table-striped table-bordered"  id="tbleData" >
					
								<thead>
										<tr>
										 
										  <th> S No </th>
										  
										  <th>Affiliates ID </th>
										  
										  <th>Affiliates Name</th>
										  
										  <th>Affiliates Status </th> 
										  
										  <th>Action Status </th> 
										  
										   <th> Update Action </th> 

										</tr>
			
								</thead>
						
										 <tbody id="tbody" > 
													
										 </tbody>
								
				</table>
		
<!-- 				</form>		
 -->						  	
					
					</div>
				</div><!--/span-->
			
			</div><!--/row-->
			
			
    

	</div><!--/.fluid-container-->
	
			<!-- end: Content -->
		</div><!--/#content.span10-->
		</div><!--/fluid-row-->

	<div class="clearfix" ></div>
	<div class="box span12" id="updatediv" style="display:none">
	
		<form method="post" action="updateAffiliatesDetails">
		  <table class="table table-striped table-bordered" id="updateAffiliates" style="margin-top: 40px">
		  </table>
		  
		</form>
		 
	</div>
	
	
	<footer>
		
		<p >
			<span style="text-align:left;float:left">&copy; 2016 <a href="#" > PriorityStatus </a></span>	
		</p>
		
	</footer>
	
	<!-- start: JavaScript-->
	
		
		
		
		
		<script src="js/jquery-migrate-1.0.0.min.js"></script>
	
		<script src="js/jquery-ui-1.10.0.custom.min.js"></script>
	
		<script src="js/jquery.ui.touch-punch.js"></script>
	
		<script src="js/modernizr.js"></script>
	
		<script src="js/bootstrap.min.js"></script>
	
		<script src="js/jquery.cookie.js"></script>
	
		<script src='js/fullcalendar.min.js'></script>
	
		<script src='js/jquery.dataTables.min.js'></script>

		<script src="js/excanvas.js"></script>
		
		<script src="js/jquery.flot.js"></script>
		
		<script src="js/jquery.flot.pie.js"></script>
		
		<script src="js/jquery.flot.stack.js"></script>
		
		<script src="js/jquery.flot.resize.min.js"></script>
		
		<script src="js/jquery.chosen.min.js"></script>
	
		<script src="js/jquery.uniform.min.js"></script>
		
		<script src="js/jquery.cleditor.min.js"></script>
	
		<script src="js/jquery.noty.js"></script>
	
		<script src="js/jquery.elfinder.min.js"></script>
	
		<script src="js/jquery.raty.min.js"></script>
	
		<script src="js/jquery.iphone.toggle.js"></script>
	
		<script src="js/jquery.uploadify-3.1.min.js"></script>
	
		<script src="js/jquery.gritter.min.js"></script>
	
		<script src="js/jquery.imagesloaded.js"></script>
	
		<script src="js/jquery.masonry.min.js"></script>
	
		<script src="js/jquery.knob.modified.js"></script>
	
		<script src="js/jquery.sparkline.min.js"></script>
	
		<script src="js/counter.js"></script>
	
		<script src="js/retina.js"></script>

		<script src="js/custom.js"></script>
	<!-- end: JavaScript-->
	
</body>
</html>
