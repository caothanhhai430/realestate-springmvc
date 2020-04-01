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
                <form:form id="customer_form" modelAttribute="customerRequest" method="GET">


                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="form-field-8">Tên khách hàng</label>

                            <form:input class="form-control" path="name" />
                        </div>
                        <div class="col-sm-4">
                            <label for="form-field-8">Di động</label>

                            <form:input class="form-control" path="phone" />
                        </div>
                        <div class="col-sm-4">
                            <label for="form-field-8">Email</label>

                            <form:input class="form-control" path="email" />
                        </div>

                    </div>

                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="form-field-8">Nhân viên vụ trách</label>

                            <form:select class="form-control" path="staffId" >
                                <form:option value="" label="--- Select ---" />
                                <form:options items="${staffMap}" />
                            </form:select>
                        </div>

                    </div>



                    <div id="form-field-11" class="autosize-transition form-control"
                        style="overflow: hidden; padding: 0px;border: 0px; height: 0px;"></div>
                    <button id="btn_search" type="button" style="margin-top: 25px;" class="btn btn-purple btn-sm">
                        <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
                        Tìm kiếm
                    </button>


                </form:form>
            </div>

        </div>
    </div>
</div>