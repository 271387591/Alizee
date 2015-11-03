<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/common/taglibs.jsp" %>
    <title>渠道管理</title>
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
                    <li class="active"><i class="icon-user"></i>渠道管理</li>
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
                                        <label class="control-label">名称</label>
                                        <input type="text" name="advert.Q_name_LK" />
                                    </li>
                                    <li>
                                        <label class="control-label">编号</label>
                                        <input type="text" name="advert.Q_channelNo_LK" />
                                    </li>
                                </ul>
                                <div class="btn-query">
                                    <button class="btn btn-success btn-sm" onclick="searchForm($('.query-content'),'advert',listTable);">查询</button>
                                    <button class="btn btn-sm" onclick="clearSearchForm($('.query-content'),listTable);">清空</button>
                                </div>
                            </div>
                        </div>

                        <div class="table-title">
                            <span style="margin-left: 10px" class="green">渠道列表</span>
                            <div class="pull-right" style="margin-right: 10px;">
                            <button class="btn btn-sm btn-primary" onclick="edit();"><i class="icon-angle-down"></i>添加</button>
                            </div>
                        </div>

                        <div class="table-responsive">
                            <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="gameTable">
                                <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>名称</th>
                                    <th>创建时间</th>
                                    <th>类型</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div class="table-bottom">
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
<script type="text/javascript" src="<c:url value="/resources/js/admin/system/channel.js"/>"></script>
<script type="text/javascript">
    jQuery(function($) {
        changeNav('menu-channel');
        listTable();
    });
</script>
</body>
</html>