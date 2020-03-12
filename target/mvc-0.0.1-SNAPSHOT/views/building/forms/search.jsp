<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="page-content">

    <div class="widget-box">
        <div class="widget-header">
            <h4 class="widget-title">Tìm kiếm</h4>

            <div class="widget-toolbar">
                <a href="#" data-action="collapse">
                    <i class="ace-icon fa fa-chevron-up"></i>
                </a>
<!-- 
                <a href="#" data-action="close">
                    <i class="ace-icon fa fa-times"></i>
                </a> -->
            </div>
        </div>

        <div class="widget-body" style="display: block;">
                
            <div class="widget-main">
                    <form id="building_form">
                
                <div class="form-group">
                        <div class="col-sm-4">
                                <label for="form-field-8">Tên tòa nhà</label>

                                <input class="form-control" name="name" id="form-field-8" placeholder=""></input>
                            </div>
                        <div class="col-sm-4">
                                <label for="form-field-8">Diện tích sàn</label>

                                <input class="form-control" name="rentArea" id="form-field-8" placeholder=""></input>
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
                                <option value="QUAN_1">Quận 1</option>
                                <option value="QUAN_2">Quận 2</option>
                                <option value="QUAN_3">Quận 3</option>
                                <option value="QUAN_4">Quận 4</option>
                                <option value="QUAN_5">Quận 5</option>
                                <option value="QUAN_6">Quận 6</option>
                                <option value="QUAN_7">Quận 7</option>
                                <option value="QUAN_8">Quận 8</option>
                                <option value="QUAN_9">Quận 9</option>
                                <option value="QUAN_10">Quận 10</option>
                                <option value="QUAN_11">Quận 11</option>
                                <option value="QUAN_12">Quận 12</option>
                                <option value="QUAN_THU_DUC">Quận Thủ Đức</option>
                                <option value="QUAN_GO_VAP">Quận Gò Vấp</option>
                                <option value="QUAN_BINH_THANH">Quận Bình Thạnh</option>
                                <option value="QUAN_TAN_BINH">Quận Tân Bình</option>
                                <option value="QUAN_TAN_PHU">Quận Tân Phú</option>
                                <option value="QUAN_PHU_NHUAN">Quận Phú Nhuận</option>
                                <option value="QUAN_BINH_TAN">Quận Bình Tân</option>
                                <option value="QUAN_CU_CHI">Quận Củ Chi</option>
                                <option value="QUAN_HOC_MON">Quận Hóc Môn</option>
                                <option value="QUAN_BINH_CHANH">Quận Bình Chánh</option>
                                <option value="QUAN_NHA_BE">Quận Nhà Bè</option>
                                <option value="QUAN_CAN_GIO">Quận Cần Giờ</option>
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
                                <label for="form-field-8">Diện tích từ</label>

                                <input class="form-control" name="rentAreaFrom" id="form-field-8" placeholder=""></input>
                            </div>
                        <div class="col-sm-3">
                                <label for="form-field-8">Diện tích đến</label>

                                <input class="form-control" name="rentAreaTo" id="form-field-8" placeholder=""></input>
                            </div>
                            <div class="col-sm-3">
                                    <label for="form-field-8">Giá thuê từ</label>

                                    <input class="form-control" name="costRentFrom" id="form-field-8" placeholder=""></input>
                                </div>
                            <div class="col-sm-3">
                                    <label for="form-field-8">Giá thuê đến</label>

                                    <input class="form-control" name="costRentTo" id="form-field-8" placeholder=""></input>
                                </div>

                </div>

                <div>
                    <input type="checkbox" name="buildingType" value="NOI_THAT"> Nội thất
                    <input type="checkbox" name="buildingType" value="TANG_TRET"> Tầng trệt
                    <input type="checkbox" name="buildingType" value="NGUYEN_CAN"> Nguyên căn
                </div>


                    <div id="form-field-11" class="autosize-transition form-control" style="overflow: hidden; padding: 0px;border: 0px; height: 0px;"></div>
                    <button type="submit" class="btn btn-purple btn-sm">
                        <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
                        Search
                    </button>
                
            </form>
            </div>
        </div>
    </div>
</div>