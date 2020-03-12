<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
	<body>

	
			<div class="main-content">
			
				<div class="main-content-inner">

					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Home</a>
							</li>
							<li class="active">Dashboard</li>
						</ul><!-- /.breadcrumb -->

					

					</div>
					<%@ include file = "./forms/search.jsp"%>
					
					<%@ include file = "./forms/assignStaffModal.jsp"%>
					
					<%@ include file = "./forms/buildingModal.jsp"%>
					
					<%@ include file = "./forms/buildingList.jsp"%>
					

				</div>
				
				
			</div>
			<%@ include file = "./script.jsp"%>
					

	</body>
