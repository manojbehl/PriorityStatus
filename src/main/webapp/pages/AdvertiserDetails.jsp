<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
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
	 <script src="js/jquery.tabletojson.min.js"></script>
	 <script src="js/jquery.tabletojson.js"></script>
  <script>
  var ContextPath = "<%= request.getContextPath()%>"
  
  $( function() {
    $( "#tbody" ).sortable();
    $( "#tbody" ).disableSelection();
  });
  
  function updateStatusId(advertiserId,advertiserStatusId,advertiserPriority)
  {
	  
	  $.ajax({url: ContextPath+"/updateStatusId?advertiserModel.advertiserId="+advertiserId+"&&advertiserModel.advertiserStatusId="+advertiserStatusId+"&&advertiserModel.advertiserPriority="+advertiserPriority
	  , success: function(value){
		  updates();
	  }});
  }
 </script>
							 
 
 <script>
 		$(document).ready(function(){
 			
 			updates();
 			var sub=$("#setPriorityButton").val();
 			
 			$("#setPriorityButton").click(function(){
 			
 				$("#formId").attr('action','<%=request.getContextPath()%>/updatePriority');
 			});
 			
 			 /*  $('#convert').click( function() {							
			  	  var table = $('#tbleData').tableToJSON(); // Convert the table into a javascript object
			  	  console.log(table);
			  	  alert(JSON.stringify(table));
			  	}); */
 		});
 		
 		function setPriority(advertiserId){
 			alert(advertiserId);
 			$.ajax({url:ContextPath+"/updatePriority", success:function(data){
 				updates();
 			}});
 		}
 		function updates(){
 			$.getJSON("advertiserList",function(data){
 				$("#tbody").empty()
 				var sno=1;
 				
 				$.each(data.listObjectAction,function(){
 					var advertiserId =   this['advertiserId'] ;
 					$("#tbody").append("<tr>"
 							+"<td>"+sno+"<input type=hidden name='advertiserId' value='"+this['advertiserId']+"'></td>"
 							+"<td>"+this['advertiserId']+"</td>"
 							+"<td>"+this['advertiserName'] +"</td>"	
 							+"<td>"+this['actualAdvertiserStatusTitle']+"</td>"
 							+"<td>"+( this['advertiserStatusTitle']=='DEACTIVATE' ?
								"<button  type='button' class='btn btn-danger' onclick='updateStatusId("+advertiserId+','+this['advertiserStatusId']+','+this['advertiserPriority']+")' value='"+this['advertiserId']+"'>"+this['advertiserStatusTitle']+"</button>" :
									"<button type='button' class='btn btn-success' onclick='updateStatusId("+advertiserId+','+this['advertiserStatusId']+','+this['advertiserPriority']+")'  value='"+this['advertiserId']+"'>"+this['advertiserStatusTitle']+"</button>"
 							)+"</td>"
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
	
		<div class="container-fluid-full">
		<div class="row-fluid">
				
			<!-- start: Main Menu -->
			<div id="sidebar-left" class="span2">
				<div class="nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li><a href="navOfferPriority"><i class="icon-bar-chart"></i><span class="hidden-tablet"> Offer_Priority </span></a></li>
						<li><a href="#"><i class="icon-book"></i><span class="hidden-tablet"> Advertiser Details </span></a></li>	
						
						<li><a href="navAffiliates"><i class="icon-book"></i><span class="hidden-tablet"> Affiliates Details</span></a></li>
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
					<a href="#">Advertiser Details</a>
				</li>
			</ul>

			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon book"></i><span class="break"></span> Advertiser Details </h2>
						
					</div>
					<div class="box-content">
						
			<form  method="post" id="formId" >
 				
				<table class="table table-striped table-bordered"  id="tbleData" >
					
								<thead>
										<tr>
										 
										  <th> S No </th>
										  
										  <th>Advertiser ID </th>
										  
										  <th>Advertiser Name</th>
										  
										   <th>Advertiser Status </th> 
										  
										  <th>Action </th> 
										</tr>
			
								</thead>
						
										 <tbody id="tbody" > 
													
										 </tbody>
								
							 
					
								
 
				</table>
				<div align="right">
					<input type="submit" value="Submit" id="setPriorityButton" class="btn btn-primary" />
				</div>
						</form>		
 			  	
					
					</div>
				</div><!--/span-->
			
			</div><!--/row-->
			
			
    

	</div><!--/.fluid-container-->
	
			<!-- end: Content -->
		</div><!--/#content.span10-->
		</div><!--/fluid-row-->
		
	
	<div class="clearfix" ></div>
	
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
