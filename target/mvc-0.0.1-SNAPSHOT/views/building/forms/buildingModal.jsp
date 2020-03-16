<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  <!-- Modal -->
  <div class="modal fade" id="myModal">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
      
			<form id="form_save">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Thêm mới tòa nhà</h4>
        </div>
        <div class="modal-body">
									
									<div class="form-group">
											<div class="col-sm-4">
													<label for="form-field-8">Tên tòa nhà</label>
			
													<input class="form-control" name="name" id="form-field-8" placeholder=""></input>
												</div>
											<div class="col-sm-4">
													<label for="form-field-8">Diện tích sàn</label>
			
													<input class="form-control" name="" id="form-field-8" placeholder=""></input>
											</div>
											<div class="col-sm-4">
												<label for="form-field-8">Số tầng hầm</label>
		
												<input class="form-control" name="numberOfBasement" id="form-field-8" placeholder=""></input>
										</div>
		
									</div>
									<div class="form-group">
										<div class="col-sm-4">
												<label for="form-field-8">Quận,Huyện</label>
		
												<select class="form-control" name="district" id="form-field-8">
													<option value=""></option>
													<c:forEach var="district" items="${districtsMap.entrySet}">
												   		<option value="${district.getKey}">${district.getValue}</option>
												   </c:forEach>											  
												   </select>
												</div>
										<div class="col-sm-4">
												<label for="form-field-8">Phường</label>
		
												<input class="form-control" name="ward" id="form-field-8" placeholder=""></input>
										</div>
										<div class="col-sm-4">
											<label for="form-field-8">Đường</label>
	
											<input class="form-control" name="street" id="form-field-8" placeholder=""></input>
									</div>
	
								</div>

								<div class="form-group">
									<div class="col-sm-3">
											<label for="form-field-8">Kết cấu</label>
	
											<input class="form-control" name="structure" id="form-field-8" placeholder=""></input>
										</div>
																														<div class="col-sm-3">
										<label for="form-field-8">Diện tích thuê</label>

										<input class="form-control" name="rentArea" id="form-field-8" placeholder=""></input>
								</div>

							</div>

							
							<div class="form-group">
								<div class="col-sm-4">
										<label for="form-field-8">Giá thuê</label>

										<input class="form-control" name="rentCost" id="form-field-8" placeholder=""></input>
									</div>
								<div class="col-sm-4">
										<label for="form-field-8">Mô tả giá</label>

										<input class="form-control" name="costDescription" id="form-field-8" placeholder=""></input>
								</div>
								<div class="col-sm-4">
									<label for="form-field-8">Phí dịch vụ</label>

									<input class="form-control" name="serviceCost" id="form-field-8" placeholder=""></input>
							</div>

						</div>
								
						
						<div class="form-group">
							<div class="col-sm-4">
									<label for="form-field-8">Phí xe hơi</label>

									<input class="form-control" name="carCost" id="form-field-8" placeholder=""></input>
								</div>
							<div class="col-sm-4">
									<label for="form-field-8">Phí mô tô</label>

									<input class="form-control" name="motorbikeCost" id="form-field-8" placeholder=""></input>
							</div>
							<div class="col-sm-4">
								<label for="form-field-8">phí ngoài giờ</label>

								<input class="form-control" name="overtimeCost"" id="form-field-8" placeholder=""></input>
						</div>

					</div>

					
					<div class="form-group">
						
							<div class="col-sm-3">
									<label for="form-field-8">Phí môi giới</label>
	
									<input class="form-control" name="" id="form-field-8" placeholder=""></input>
								</div>
						<div class="col-sm-3">
								<label for="form-field-8">Tiền điện</label>

								<input class="form-control" name="electricityCost" id="form-field-8" placeholder=""></input>
							</div>
						<div class="col-sm-3">
								<label for="form-field-8">Đặt cọc</label>

								<input class="form-control" name="deposit" id="form-field-8" placeholder=""></input>
						</div>
						<div class="col-sm-3">
							<label for="form-field-8">Thanh toán</label>

							<input class="form-control" name="payment" id="form-field-8" placeholder=""></input>
					</div>

				</div>

				
				<div class="form-group">
					<div class="col-sm-3">
							<label for="form-field-8">Thời gian thuê</label>

							<input class="form-control" name="timeRent" id="form-field-8" placeholder=""></input>
						</div>
					<div class="col-sm-3">
							<label for="form-field-8">Thời gian trang trí</label>

							<input class="form-control" name="timeDecorator" id="form-field-8" placeholder=""></input>
					</div>
					<div class="col-sm-3">
						<label for="form-field-8">Tên quản lý</label>

						<input class="form-control" name="managerName" id="form-field-8" placeholder=""></input>
				</div>
				
				<div class="col-sm-3">
					<label for="form-field-8">SĐT quản lý</label>

					<input class="form-control" name="managerPhone" id="form-field-8" placeholder=""></input>
			</div>

			</div>
									

									<div>
									<c:forEach var="buildingType" items="${buildingTypesMap.entrySet}">
										<input type="checkbox" name="buildingType" value="${buildingType.getKey}"> ${buildingType.getValue}
									</c:forEach>
									</div>


										<div id="form-field-11" class="autosize-transition form-control" style="overflow: hidden; padding: 0px;border: 0px; height: 0px;"></div>
										
									
			
        </div>
        <div class="modal-footer">
        <button id="submit_save" type="button" class="btn btn-default">
											<span"></span>
											Thêm mới
										</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
        </div>
        
								</form>
      </div>
      
    </div>
  </div>
