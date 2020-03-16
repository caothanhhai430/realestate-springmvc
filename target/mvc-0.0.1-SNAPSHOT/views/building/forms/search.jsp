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
                    <form:form  id="building_form" modelAttribute="buildingRequest" method="GET">
             
                
                <div class="form-group">
                        <div class="col-sm-4">
                                <label for="form-field-8">Tên tòa nhà</label>

                                <form:input class="form-control" path="name" />
                            </div>
                        <div class="col-sm-4">
                                <label for="form-field-8">Diện tích sàn</label>

                                <form:input class="form-control" path="rentArea" />
                        </div>
                        <div class="col-sm-4">
                            <label for="form-field-8">Số tầng hầm</label>

                            <form:input class="form-control" path="numberOfBasement" />
                    </div>

                </div>
                <div class="form-group">
                    <div class="col-sm-4">
                            <label for="form-field-8">Quận,Huyện</label>

                            <select class="form-control" path="district" id="form-field-8">
                                <option value=""></option>
                                <c:forEach items="${districtsMap}" var="district">
                                    <option value="${district.key}">${district.value}</option>
                                </c:forEach>
                                
                              </select>
                            </div>
                    <div class="col-sm-4">
                            <label for="form-field-8">Phường</label>

                            <form:input class="form-control" path="ward" />
                    </div>
                    <div class="col-sm-4">
                        <label for="form-field-8">Đường</label>

                        <form:input class="form-control" path="street" />
                </div>

            </div>
            
                <div class="form-group">
                        <div class="col-sm-3">
                                <label for="form-field-8">Diện tích từ</label>

                                <form:input class="form-control" path="rentAreaFrom" />
                            </div>
                        <div class="col-sm-3">
                                <label for="form-field-8">Diện tích đến</label>

                                <form:input class="form-control" path="rentAreaTo" />
                            </div>
                            <div class="col-sm-3">
                                    <label for="form-field-8">Giá thuê từ</label>

                                    <form:input class="form-control" path="rentCostFrom" />
                                </div>
                            <div class="col-sm-3">
                                    <label for="form-field-8">Giá thuê đến</label>

                                    <form:input class="form-control" path="rentCostTo" />
                                </div>

                </div>

                <div>
                    <input type="checkbox" path="buildingType" value="NOI_THAT"/> Nội thất
                    <input type="checkbox" path="buildingType" value="TANG_TRET"/> Tầng trệt
                    <input type="checkbox" path="buildingType" value="NGUYEN_CAN"/> Nguyên căn
                </div>


                    <div id="form-field-11" class="autosize-transition form-control" style="overflow: hidden; padding: 0px;border: 0px; height: 0px;"></div>
                    <button type="submit" class="btn btn-purple btn-sm">
                        <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
                        Search
                    </button>
                
                    
            </form:form>
            </div>

        </div>
    </div>
</div>