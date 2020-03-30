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

				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
					Launch demo modal
				  </button>
				  
				  <!-- Modal -->
				  <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
					  <div class="modal-content">
						<div class="modal-header">
						  <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
						  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						  </button>
						</div>
						<div class="modal-body">
						  ...
						</div>
						<div class="modal-footer">
						  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
						  <button type="button" class="btn btn-primary">Save changes</button>
						</div>
					  </div>
					</div>
				  </div>

			</div>
			<%@ include file = "./forms/search.jsp"%>

			<%@ include file = "./forms/assignStaffModal.jsp"%>

			<%@ include file = "./forms/buildingModal.jsp"%>

			<%@ include file = "./forms/buildingList.jsp"%>


		</div>


	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src='<c:url value="/common/template/assets/js/jquery-ui.custom.min.js"/>'></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
	
	<script src='<c:url value="/common/template/assets/js/ace-elements.min.js"/>'></script>
	<script src='<c:url value="/common/template/assets/js/ace.min.js"/>'></script>

	<script type="text/javascript" src="../../common/simplePagination.js"></script>
	<script src="../../common/script.js"></script>


</body>