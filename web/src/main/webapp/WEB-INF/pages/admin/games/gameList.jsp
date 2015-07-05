<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="breadcrumbs" id="breadcrumbs">
    <script type="text/javascript">
        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
    </script>
    <ul class="breadcrumb">
        <li class="active"><i class="icon-user"></i>游戏管理</li>
    </ul>
</div>
<div class="page-content">
    <div class="page-header">
        <h1>
            <i class="icon-dashboard">游戏管理</i>
        </h1>
    </div>
    <div class="row">
        <div class="col-xs-12">

            <div class="query-box">
                <div class="query-title">游戏查询</div>
                <div class="query-content">
                    <ul class="query-form" id="gameSearch">
                        <li>
                            <label class="control-label">游戏名称</label>
                            <input type="text" name="advert.Q_name_LK"/>
                        </li>
                    </ul>
                    <div class="btn-query">
                        <button class="btn btn-success btn-sm" onclick="searchForm($('#gameSearch'),'advert',listTable);">查询</button>
                        <button class="btn btn-sm" onclick="clearSearchForm($('#gameSearch'),listTable);">清空</button>
                    </div>
                </div>
            </div>
            <div class="table-title">
                <span style="margin-left: 10px" class="green">游戏列表</span>
                <div class="pull-right" style="margin-right: 10px;">
                    <button class="btn btn-sm btn-primary" onclick="edit();"><i class="icon-angle-down"></i>添加</button>
                </div>
            </div>
            <div class="table-responsive">
                <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="gameTable">
                    <thead>
                    <tr>
                        <th>游戏名称</th>
                        <th>平台</th>
                        <th>游戏热度</th>
                        <th>游戏类型</th>
                        <th>游戏版本</th>
                        <th>创建时间</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>

            <div id="gamePager" class="page table-bottom">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    jQuery(function(){
        listTable();
    });
</script>
