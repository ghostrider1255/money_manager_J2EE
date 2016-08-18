<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.hibernate.resource.User" %>
<!DOCTYPE HTML>
<html>
<head>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript">
addEventListener("load",
	function()
	{
		setTimeout(hideURLbar, 0);
	}, false);
	function hideURLbar()
	{
		window.scrollTo(0,1);
	}
</script>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href="css/font-awesome.css" rel="stylesheet">
<script src="jquery/jquery-1.11.1.min.js"></script>
<script src="jquery/modernizr.custom.js"></script>

<script src="jquery/metisMenu.min.js"></script>
<script src="jquery/custom.js"></script>
<link href="css/custom.css" rel="stylesheet">
</head>
<body class="cbp-spmenu-push">
	<div class="main-content">
		<!--left-fixed -navigation-->
		<div class=" sidebar" role="navigation">
            <div class="navbar-collapse">
				<nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left" id="cbp-spmenu-s1">
					<ul class="nav" id="side-menu">
						<li>
							<a href="viewDashBoard" class="active"><i class="fa fa-home nav_icon"></i>Dashboard</a>
						</li>
						<li>
							<a href="viewBudget"><i class="fa fa-bar-chart nav_icon"></i>Budget</a>
							<!-- /nav-second-level -->
						</li>
						<li class="">
							<a href="viewIncomeGrid"><i class="fa fa-book nav_icon"></i>Income</a>
							<!-- /nav-second-level -->
						</li>
						<li>
							<a href="viewExpenseGrid"><i class="fa fa-book nav_icon"></i>Expense</a>
						</li>
						<li>
							<a href="viewUserCategories"><i class="fa fa-cogs nav_icon"></i>Category</a>
						</li>
						<li>
							<a href="#"><i class="fa fa-cogs nav_icon"></i>Payee</a>
						</li>
						<li>
							<a href="#"><i class="fa fa-envelope nav_icon"></i>Mailbox<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level collapse">
								<li>
									<a href="#">Inbox <span class="nav-badge-btm">05</span></a>
								</li>
								<li>
									<a href="#">Report Issue</a>
								</li>
							</ul>
							<!-- //nav-second-level -->
						</li>
						<li>
							<a href="#"><i class="fa fa-table nav_icon"></i>Reminder <span class="nav-badge">02</span></a>
						</li>
						<li>
							<a href="#" class="chart-nav"><i class="fa fa-bar-chart nav_icon"></i>News <span class="nav-badge-btm pull-right">new</span></a>
						</li>
					</ul>
					<!-- //sidebar-collapse -->
				</nav>
			</div>
		</div>
		<!--left-fixed -navigation-->
		<!-- header-starts -->
		<div class="sticky-header header-section ">
			<div class="header-left">
				<!--toggle button start-->
				<button id="showLeftPush"><i class="fa fa-bars"></i></button>
				<!--toggle button end-->
				<!--logo -->
				<div class="logo">
					<a href=viewDashBoard>
						<h1>Money manager</h1>
						<span>tracks each coin</span>
					</a>
				</div>
				<!--//logo-->
				<div class="clearfix"> </div>
			</div>
			<div class="header-right">
				<div class="profile_details_left"><!--notifications of menu start -->
					<ul class="nofitications-dropdown">
						<li class="dropdown head-dpdn">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-envelope"></i><span class="badge">3</span></a>
							<ul class="dropdown-menu">
								<li>
									<div class="notification_header">
										<h3>You have 3 new messages</h3>
									</div>
								</li>
								<li><a href="#">
<!-- 								   <div class="user_img"><img src="images/1.png" alt=""></div> -->
								   <div class="notification_desc">
									<p>Message from UserOne</p>
									<p><span>1 hour ago</span></p>
									</div>
								   <div class="clearfix"></div>
								</a></li>
								<li class="odd"><a href="#">
								   <div class="notification_desc">
									<p>Message from User Two </p>
									<p><span>3 hour ago</span></p>
									</div>
								  <div class="clearfix"></div>
								</a></li>
								<li><a href="#">
								   <div class="notification_desc">
									<p>Message from User Three </p>
									<p><span>1 day ago</span></p>
									</div>
								   <div class="clearfix"></div>
								</a></li>
								<li>
									<div class="notification_bottom">
										<a href="#">See all messages</a>
									</div>
								</li>
							</ul>
						</li>
						<li class="dropdown head-dpdn">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-bell"></i><span class="badge blue">2</span></a>
							<ul class="dropdown-menu">
								<li>
									<div class="notification_header">
										<h3>You have 2 new Events</h3>
									</div>
								</li>
								<li><a href="#">
								   <div class="notification_desc">
									<p>Event One</p>
									<p><span>1 hour ago</span></p>
									</div>
								  <div class="clearfix"></div>
								 </a></li>
								 <li><a href="#">
								   <div class="notification_desc">
									<p>Event Two</p>
									<p><span>1 Day ago</span></p>
									</div>
								   <div class="clearfix"></div>
								 </a></li>
								 <li>
									<div class="notification_bottom">
										<a href="#">See all notifications</a>
									</div>
								</li>
							</ul>
						</li>
					</ul>
					<div class="clearfix"> </div>
				</div>
				<!--notification menu end -->
				<div class="profile_details">
					<ul>
						<li class="dropdown profile_details_drop">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
								<div class="profile_img">
									<span class="prfil-img"><!-- <img src="images/a.png" alt=""> --> </span>
									<div class="user-name">
										<p><% User tempUser=(User)session.getAttribute("userName"); 
											out.print(tempUser.getFirstName()+","+tempUser.getLastName());
										%></p>
										<span>Personal Settings</span>
									</div>
									<i class="fa fa-angle-down lnr"></i>
									<i class="fa fa-angle-up lnr"></i>
									<div class="clearfix"></div>
								</div>
							</a>
							<ul class="dropdown-menu drp-mnu">
								<li> <a href="#"><i class="fa fa-cog"></i> Settings</a> </li>
								<li> <a href="#"><i class="fa fa-user"></i> Profile</a> </li>
								<li> <a href="logoutUser"><i class="fa fa-sign-out"></i> Logout</a> </li>
							</ul>
						</li>
					</ul>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="clearfix"> </div>
		</div>
		<!-- //header-ends -->
			<div id="page-wrapper">
			<div class="main-page">
				<tiles:insertAttribute name="body" />
			</div>

			<div class="clearfix"> </div>
		</div>
		<!--footer-->
		<div class="footer">
		   <p>&copy; 2015 Money Manager. All Rights Reserved | Design by <b><i>M.v.s.s.Prasad Raju</i></b></p>
		</div>
        <!--//footer-->
	</div>
	<!-- Classie -->
		<script src="jquery/classie.js"></script>
		<script>
			var menuLeft = document.getElementById( 'cbp-spmenu-s1' ),
				showLeftPush = document.getElementById( 'showLeftPush' ),
				body = document.body;

			showLeftPush.onclick = function() {
				classie.toggle( this, 'active' );
				classie.toggle( body, 'cbp-spmenu-push-toright' );
				classie.toggle( menuLeft, 'cbp-spmenu-open' );
				disableOther( 'showLeftPush' );
			};


			function disableOther( button ) {
				if( button !== 'showLeftPush' ) {
					classie.toggle( showLeftPush, 'disabled' );
				}
			}
		</script>
	<!--scrolling js-->
	<script src="jquery/jquery.nicescroll.js"></script>
	<script src="jquery/scripts.js"></script>
	<!--//scrolling js-->
	<!-- Bootstrap Core JavaScript -->
   <script src="jquery/bootstrap.js"> </script>
</body>
</html>