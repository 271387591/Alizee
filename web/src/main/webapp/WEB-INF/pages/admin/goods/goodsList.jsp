<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="breadcrumbs" id="breadcrumbs">
    <script type="text/javascript">
        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
    </script>
    <ul class="breadcrumb">
        <li class="active"><i class="icon-user"></i>商品管理</li>
    </ul>
</div>
<div class="page-content">
    <div class="page-header">
        <h1>
            <i class="icon-dashboard">商品管理</i>
        </h1>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="query-box">
                <div class="query-title">商品查询</div>
                <div class="query-content">
                    <ul class="query-form" id="tableSearch">
                        <li>
                            <label class="control-label">名称</label>
                            <input type="text" name="advert.Q_name_LK"/>
                        </li>
                    </ul>
                    <div class="btn-query">
                        <button class="btn btn-success btn-sm" onclick="searchForm($('#tableSearch'),'advert',listTable);">查询</button>
                        <button class="btn btn-sm" onclick="clearSearchForm($('#tableSearch'),listTable);">清空</button>
                    </div>
                </div>
            </div>
            <div class="table-title">
                <span style="margin-left: 10px" class="green">商品列表</span>
                <div class="pull-right" style="margin-right: 10px;">
                    <button class="btn btn-sm btn-primary" onclick="edit();"><i class="icon-angle-down"></i>添加</button>
                </div>
            </div>
            <div class="table-responsive">
                <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="gameTable">
                    <thead>
                    <tr>
                        <th>商品名称</th>
                        <th>售价</th>
                        <th>数量</th>
                        <th>描述</th>
                        <th>创建时间</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>

            <div id="advertPaging" class="page table-bottom">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    jQuery(function(){
        listTable();
    });
</script>
