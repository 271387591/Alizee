<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 6/19/15
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="breadcrumbs" id="breadcrumbs">
    <script type="text/javascript">
        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
    </script>
    <ul class="breadcrumb">
        <li>
            用户模块
        </li>
        <li class="active"><i class="icon-user"></i>用户管理</li>
    </ul>
</div>
<div class="page-content">
    <div class="page-header">
        <h1>
            <i class="icon-dashboard">用户列表</i>
        </h1>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="query-box">
                <div class="query-title">用户查询</div>
                <div class="query-content">
                    <ul class="query-form" id="userSearch">
                        <li>
                            <label class="control-label">手机号码</label>
                            <input type="text" name="advert.Q_mobile_EQ"/>
                        </li>
                        <li>
                            <label class="control-label">用户昵称</label>
                            <input type="text" name="advert.Q_nickName_LK"/>
                        </li>

                    </ul>
                    <div class="btn-query">
                        <button class="btn btn-success btn-sm" onclick="searchForm($('#userSearch'),'advert',listTable);">查询</button>
                        <button class="btn btn-sm" onclick="clearSearchForm($('#userSearch'),listTable);">清空</button>
                    </div>
                </div>
            </div>

            <div class="table-title">
                <span style="margin-left: 10px" class="green">用户列表</span>
                <div class="pull-right" style="margin-right: 10px;">
                    <button class="btn btn-sm btn-primary" onclick="edit();"><i class="icon-angle-down"></i>添加</button>
                </div>
            </div>


            <div class="table-responsive">
                <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="gameTable">
                    <thead>
                    <tr>
                        <th>用户手机</th>
                        <th>用户昵称</th>
                        <th>用户角色</th>
                        <th>创建时间</th>
                        <th>是否可用</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div id="paging" class="page table-bottom">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    jQuery(function(){
        listTable();
    });
</script>

