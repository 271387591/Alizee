<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="breadcrumbs" id="breadcrumbs">
    <script type="text/javascript">
        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
    </script>
    <ul class="breadcrumb">

        <li class="active"><i class="icon-user"></i>充值查询</li>
    </ul>
</div>
<div class="page-content">
    <div class="page-header">
        <h1>
            <i class="icon-dashboard">充值查询</i>
        </h1>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
                <li class="active">
                    <a data-toggle="tab" href="#rechargeSucDiv">充值成功列表</a>
                </li>

                <li>
                    <a data-toggle="tab" href="#rechargeFailDiv">充值失败列表</a>
                </li>
            </ul>
            <div class="tab-content" style="padding:2px 0px;">
                <div class="tab-pane in active" id="rechargeSucDiv">
                    <div class="query-box">
                        <div class="query-title">查询</div>
                        <div class="query-content">
                            <ul class="query-form" id="rechargeSucSearch">
                                <li>
                                    <label class="control-label">用户手机</label>
                                    <input type="text" name="advert.Q_u.username_EQ"/>
                                </li>
                            </ul>
                            <div class="btn-query">
                                <button class="btn btn-success btn-sm" onclick="searchForm($('#rechargeSucSearch'),'advert',listSucTable);">查询</button>
                                <button class="btn btn-sm" onclick="clearSearchForm($('#rechargeSucSearch'),listSucTable);">清空</button>
                            </div>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="rechargeSucTable">
                            <thead>
                            <tr>
                                <th>充值编号</th>
                                <th>用户手机</th>
                                <th>用户昵称</th>
                                <th>充值金额</th>
                                <th>充值时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <div id="rechargeSucTablePage" class="page table-bottom">
                    </div>
                </div>
                <div class="tab-pane in" id="rechargeFailDiv">
                    <div class="query-box">
                        <div class="query-title">查询</div>
                        <div class="query-content">
                            <ul class="query-form" id="rechargeFailSearch">
                                <li>
                                    <label>用户手机</label>
                                    <input type="text" name="advert.Q_u.username_EQ"/>
                                </li>
                            </ul>
                            <div class="btn-query">
                                <button class="btn btn-success btn-sm" onclick="searchForm($('#rechargeFailSearch'),'advert',listFailTable);">查询</button>
                                <button class="btn btn-sm" onclick="clearSearchForm($('#rechargeFailSearch'),listFailTable);">清空</button>
                            </div>
                        </div>
                    </div>
                    <div class="table-responsive" style="">
                        <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="rechargeFailTable">
                            <thead>
                            <tr>
                                <th>充值编号</th>
                                <th>用户手机</th>
                                <th>用户昵称</th>
                                <th>充值金额</th>
                                <th>充值时间</th>
                                <th>失败信息</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>

                    <div id="rechargeFailTablePage" class="page table-bottom">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    jQuery(function(){
        $('#myTab4 a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            var href=e.target.getAttribute("href");
            if(href=='#rechargeSucDiv'){
                listSucTable();
            }else{
                listFailTable();
            }
        });
        listSucTable();
    });
</script>
