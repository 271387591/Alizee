<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/common/taglibs.jsp" %>
    <title>美食故事</title>
</head>
<body class="navbar-fixed">
<%@include file="../header.jsp"%>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>
        <%@include file="../menu.jsp"%>
        <div class="main-content" id="mainContent">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>
                <ul class="breadcrumb">
                    <li>
                        系统设置
                    </li>
                    <li class="active"><i class="icon-user"></i>美食故事</li>
                </ul>
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="query-box">
                            <div class="query-title">内容查询</div>
                            <div class="query-content">
                                <ul class="query-form">
                                    <li>
                                        <label class="control-label">标题</label>
                                        <input type="text" name="food.Q_title_LK"/>
                                    </li>
                                </ul>
                                <div class="btn-query">
                                    <button class="btn btn-success btn-sm" onclick="searchForm($('.query-content'),'food',listTable);">查询</button>
                                    <button class="btn btn-sm" onclick="clearSearchForm($('.query-content'),listTable);">清空</button>
                                </div>
                            </div>
                        </div>





                        <div class="table-title">
                            <span style="margin-left: 10px" class="green">美食故事列表</span>
                            <div class="pull-right" style="margin-right: 10px;">
                            <button class="btn btn-sm btn-primary" onclick="edit();"><i class="icon-angle-down"></i>添加</button>
                            </div>
                        </div>

                        <div class="table-responsive">
                            <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="foodTable">
                                <thead>
                                <tr>
                                    <th class="center" style="width: 60px;">
                                        <label>
                                            <input type="checkbox" class="ace" />
                                            <span class="lbl"></span>
                                        </label>
                                    </th>
                                    <th>标题</th>
                                    <th>美食图片</th>
                                    <th>描述</th>
                                    <th>创建时间</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div class="table-bottom">
                            <div class="pull-left"><button style="margin-top: 2px;margin-left: 2px" class="btn btn-sm btn-primary" onclick="batchDelete();"><i class="icon-remove"></i>批量删除</button></div>
                            <div id="advertPaging" class="page">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse" data-rel="tooltip" title="sss">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/admin/system/food.js"/>"></script>
<script type="text/javascript">
    jQuery(function($) {
        changeNav('menu-food');
        listTable();
    });
</script>
</body>
</html>