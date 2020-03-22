<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="col-xs-12">
    <h3 class="header smaller lighter blue">Danh sách tòa nhà</h3>

    <div class="clearfix">
        <div class="pull-right tableTools-container">
            <div class="btn-group btn-overlap">
                <div class="ColVis btn-group" title="" data-original-title="Show/hide columns">
                    <button id="btn_add_building"  data-toggle="modal" data-target="#myModal"
                        class="ColVis_Button ColVis_MasterButton btn btn-white btn-info btn-bold"><span>
                            <i class="fa fa-plus"></i></span></button></div>
                <a class="DTTT_button btn btn-white btn-primary  btn-bold" id="delete-selected-building" title=""
                    tabindex="0" aria-controls="dynamic-table" data-original-title="Print view"><span>
                        <i class="fa fa-trash bigger-110 grey"></i></span></a>
            </div>
        </div>
    </div>
    <div class="table-header">
    </div>

    <div>
        <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline no-footer">
            <div class="row">
                <div class="col-xs-6">
                    <div class="dataTables_length" id="dynamic-table_length"><label>Display <select
                                name="dynamic-table_length" aria-controls="dynamic-table" class="form-control input-sm">
                                <option value="10">10</option>
                                <option value="25">25</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                            </select> records</label></div>
                </div>
                <div class="col-xs-6">
                    <div id="dynamic-table_filter" class="dataTables_filter"><label>Search:<input type="search"
                                class="form-control input-sm" placeholder="" aria-controls="dynamic-table"></label>
                    </div>
                </div>
            </div>
            <table id="dynamic-table"
                class="table table-striped table-bordered table-hover dataTable no-footer DTTT_selectable" role="grid"
                aria-describedby="dynamic-table_info">
                <thead>
                    <tr role="row" >
                        <th class="center sorting_disabled" rowspan="1" colspan="1" aria-label=">">
                            <label class="pos-rel">
                                <input type="checkbox" class="ace">
                                <span class="lbl"></span>
                            </label>
                        <th class="sorting" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1"
                            aria-label="">Tên sản phẩm</th>

                        <th class="sorting" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1"
                            aria-label="">Địa chỉ</th>
                        <th class="sorting" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1"
                            aria-label="">Tên quản lý</th>

                        <th class="sorting" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1"
                            aria-label="">Số điện thoại</th>
                        <th class="sorting" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1"
                            aria-label="">Diện tích sàn</th>
                        <th class="sorting" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1"
                            aria-label="">Giá thuê</th>
                        <th class="sorting" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1"
                            aria-label="">Loại tòa nhà</th>
                        <th class="sorting" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1"
                            aria-label="">Diện tích thuê</th>
                        <th class="sorting" style="width: 165px;" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1"
                            aria-label="">Thao tác</th>
                </thead>

                <tbody id="data-building-list">
                </tbody>
            </table>
            <div id="pagination-container"></div>

        </div>
    </div>
</div>