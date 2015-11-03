<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/common/taglibs.jsp" %>
    <title>兑换券管理</title>
</head>
<body class="navbar-fixed">
<%@include file="../header.jsp"%>

<div class="main-container">
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
                    <li class="active"><i class="icon-user"></i>兑换券管理</li>
                </ul>
            </div>
            <div class="page-content">
                <div class="page-header">
                    <h1>
                        <i class="icon-dashboard">兑换券管理</i>
                    </h1>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
                            <li class="active">
                                <a data-toggle="tab"  href="#noPublishDiv" id="noPublishTab">未兑换</a>
                            </li>

                            <li>
                                <a data-toggle="tab" href="#publishDiv" id="publishTab">已兑换</a>
                            </li>
                        </ul>
                        <div class="tab-content" style="padding:2px 0px;">
                            <div class="tab-pane active" id="noPublishDiv">
                                <div class="query-box">
                                    <div class="query-title">兑换券查询</div>
                                    <div class="query-content">
                                        <ul class="query-form" id="tableSearch">
                                            <li>
                                                <label class="control-label">编号</label>
                                                <input type="text" name="advert.Q_r.certNo_LK"/>
                                            </li>
                                            <li>
                                                <label class="control-label">商品名称</label>
                                                <input type="text" name="advert.Q_ur.name_LK"/>
                                            </li>
                                            <li>
                                                <label class="control-label">用户手机</label>
                                                <input type="text" name="advert.Q_u.mobile_LK"/>
                                            </li>

                                        </ul>
                                        <div class="btn-query">
                                            <button class="btn btn-success btn-sm" onclick="searchForm($('#tableSearch'),'advert',listGTable);">查询</button>
                                            <button class="btn btn-sm" onclick="clearSearchForm($('#tableSearch'),listGTable);">清空</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-title">
                                    <span style="margin-left: 10px" class="green">未兑换列表</span>

                                </div>
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="gameTable">
                                        <thead>
                                        <tr>
                                            <th>编号</th>
                                            <th>商品名称</th>
                                            <th>价格(游游币)</th>
                                            <th>用户昵称</th>
                                            <th>用户手机</th>
                                            <th>购买时间</th>
                                            <th>到期时间</th>
                                            <th>是否到期</th>
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
                            <div class="tab-pane in" id="publishDiv">
                                <div class="query-box">
                                    <div class="query-title">已兑换列表</div>
                                    <div class="query-content">
                                        <ul class="query-form" id="parSearch">
                                            <li>
                                                <label class="control-label">编号</label>
                                                <input type="text" name="advert.Q_r.certNo_LK"/>
                                            </li>
                                            <li>
                                                <label class="control-label">商品名称</label>
                                                <input type="text" name="advert.Q_ur.name_LK"/>
                                            </li>
                                            <li>
                                                <label class="control-label">用户手机</label>
                                                <input type="text" name="advert.Q_u.mobile_LK"/>
                                            </li>
                                        </ul>
                                        <div class="btn-query">
                                            <button class="btn btn-success btn-sm" onclick="searchForm($('#parSearch'),'advert',listNoTable);">查询</button>
                                            <button class="btn btn-sm" onclick="clearSearchForm($('#parSearch'),listNoTable);">清空</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-title">
                                    <span style="margin-left: 10px" class="green">抢购商品列表</span>
                                </div>
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="perTable">
                                        <thead>
                                        <tr>
                                            <th>编号</th>
                                            <th>商品名称</th>
                                            <th>价格</th>
                                            <th>用户昵称</th>
                                            <th>用户手机</th>
                                            <th>购买时间</th>
                                            <th>到期时间</th>
                                            <th>兑换时间</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>

                                <div id="perPaging" class="page table-bottom">
                                </div>
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
<script type="text/javascript" src="<c:url value="/resources/js/admin/goods/cert.js"/>"></script>
<script type="text/javascript">




    jQuery(function($) {
        $('#myTab4 a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            var href=e.target.getAttribute("href");
            if(href=='#noPublishDiv'){
                listGTable();
            }else{
                listNoTable();
            }
        });
        changeNav('menu-cert');
        listGTable();
    });

</script>

</body>
</html>