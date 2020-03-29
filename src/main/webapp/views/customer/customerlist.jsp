<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<body>


	<div class="main-content">

		<div class="main-content-inner">

			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try { ace.settings.check('breadcrumbs', 'fixed') } catch (e) { }
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

			<%@ include file = "./forms/customerModal.jsp"%>

			<%@ include file = "./forms/transactionModal.jsp"%>

			<%@ include file = "./forms/customerList.jsp"%>


		</div>


	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>	
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src='<c:url value="/common/template/assets/js/jquery-ui.custom.min.js"/>'></script>
		
		<script src='<c:url value="/common/template/assets/js/ace-elements.min.js"/>'></script>
		<script src='<c:url value="/common/template/assets/js/ace.min.js"/>'></script>

    <script type="text/javascript" src="../../common/simplePagination.js"></script>
	<script src="../../common/customer_script.js"></script>


</body>