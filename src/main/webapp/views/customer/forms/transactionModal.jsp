<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Modal -->
<div class="modal fade" id="transactionModal">
	<div class="modal-dialog modal-lg">
	<input type="hidden" id="id-customer-trans">
		<!-- Modal content-->
		<div class="modal-content" style="overflow-wrap: break-word;">
			<div>
				<span style="font-size: 35px;">Quá trình CSKH
				<!-- <button id="btn_add_customer" class="ColVis_Button ColVis_MasterButton btn btn-white btn-info btn-bold"><span>
					<i class="fa fa-plus"></i></span></button> -->
				</span>
			<table id="customerCare" class="table table-striped table-bordered table-hover dataTable no-footer DTTT_selectable" role="grid">
				<thead>
					<tr>
						<th>Ngày tạo</th>
						<th style="width: 300px;">Ghi chú</th>
						<th>Người nhập</th>
					</tr>
				</thead>
				<tbody id="tbody-customerCare"></tbody>
			</table>
			<div style="display: inline-flex;width:100%;height:70px;">	
				<textarea id="customerCareNote" style="width:80%;" rows="2" class="form-control" placeholder="Nhập ghi chú"></textarea> 
				<button id="btn-addCareNote" class="btn btn-primary">
					<span>Thêm mới</span>
				</button>
			</div>
			
		</div>
			<div style="height: 100px;"></div>
			<div>
				<span style="font-size: 35px;">Dẫn đi ăn
				<!-- <button id="btn_add_customer" class="ColVis_Button ColVis_MasterButton btn btn-white btn-info btn-bold"><span>
					<i class="fa fa-plus"></i></span></button> -->
				</span>
			<table id="customerTour" class="table table-striped table-bordered table-hover dataTable no-footer DTTT_selectable" role="grid">
				<thead>
					<tr>
						<th>Ngày tạo</th>
						<th style="width: 300px;">Ghi chú</th>
						<th>Người nhập</th>
					</tr>
				</thead>
				<tbody id="tbody-customerTour"></tbody>
			</table>
			<div style="display: inline-flex;width:100%;height:70px;">	
				<textarea id="customerTourNote" style="width:80%;" rows="2" class="form-control" placeholder="Nhập ghi chú"></textarea> 
				<button id="btn-addTourNote" class="btn btn-primary">
					<span>Thêm mới</span>
				</button>
			</div>
			
		</div>
		</div>

	</div>
</div>