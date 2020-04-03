<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/web-components/taglib.jsp"%>

<body>
	
	
		<div class="main-content-inner">

			<div class="breadcrumbs" id="breadcrumbs">
				<button type="button" id="sb-collapse" class="btn btn-primary">
				<i id="collapse-arrow" class="fa fa-arrow-left"></i>
				</button>	
				<ul class="breadcrumb">
					<li>
						<i class="ace-icon fa fa-home home-icon"></i>
						<a href="#">Quản trị</a>
					</li>
					<li class="active">Quản lý tòa nhà</li>
				</ul><!-- /.breadcrumb -->

				

			</div>
			<%@ include file = "./forms/search.jsp"%>

			<%@ include file = "./forms/assignStaffModal.jsp"%>

			<%@ include file = "./forms/buildingModal.jsp"%>

			<%@ include file = "./forms/buildingList.jsp"%>


		</div>


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src='<c:url value="/common/template/assets/js/jquery-ui.custom.min.js"/>'></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
	
	<script src='<c:url value="/common/template/assets/js/ace-elements.min.js"/>'></script>
	<script src='<c:url value="/common/template/assets/js/ace.min.js"/>'></script>
	<script src="https://cdn.jsdelivr.net/npm/gasparesganga-jquery-loading-overlay@2.1.7/dist/loadingoverlay.min.js" ></script>

	<script type="text/javascript" src="../../common/template/js/simplePagination.js"></script>
	<script src="../../common/custom/building/building.js"></script>


</body>