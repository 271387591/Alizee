<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/common/taglibs.jsp" %>
    <title>APP管理</title>
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
                    <li class="active"><i class="icon-user"></i>APP管理</li>
                </ul>
            </div>
            <div class="page-content">
                <div class="page-header">
                    <h1>
                        <i class="icon-dashboard">APP管理</i>
                    </h1>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
                            <li class="active">
                                <a data-toggle="tab" href="#noPublishDiv">Android</a>
                            </li>

                            <li id="publishDivTab">
                                <a data-toggle="tab" href="#publishDiv">IOS</a>
                            </li>
                        </ul>
                        <div class="tab-content" style="padding:2px 0px;">
                            <div class="tab-pane in active" id="noPublishDiv">
                                <div class="table-title">
                                    <span style="margin-left: 10px" class="green">列表</span>
                                    <div class="pull-right" style="margin-right: 10px;">
                                        <button class="btn btn-sm btn-primary" onclick="edit(null,'0');"><i class="icon-angle-down"></i>添加</button>
                                    </div>
                                </div>
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="noPublishTable">
                                        <thead>
                                        <tr>
                                            <th>版本</th>
                                            <th>创建时间</th>
                                            <th>是否最新</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>

                                <div id="noPublishPaging" class="page table-bottom">
                                </div>
                            </div>
                            <div class="tab-pane in" id="publishDiv">
                                <div class="table-title">
                                    <span style="margin-left: 10px" class="green">列表</span>
                                    <div class="pull-right" style="margin-right: 10px;">
                                        <button class="btn btn-sm btn-primary" onclick="edit(null,'1');"><i class="icon-angle-down"></i>添加</button>
                                    </div>
                                </div>
                                <div class="table-responsive" style="">
                                    <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="publishTable">
                                        <thead>
                                        <tr>
                                            <th>版本</th>
                                            <th>创建时间</th>
                                            <th>是否最新</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>

                                <div id="publishPaging" class="page table-bottom">
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
<script type="text/javascript" src="<c:url value="/resources/js/admin/appstore/appstore.js"/>"></script>
<script type="text/javascript">
    $('#myTab4 a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        var href=e.target.getAttribute("href");
        if(href=='#noPublishDiv'){
            listATable();
        }else{
            listITable();
        }
    });

    jQuery(function($) {
        changeNav('menu-appstore');

        var p='${param.p}';
        if(p=='ios'){
            $('#myTab4 li[class=active]').removeClass('active');
            $(' #noPublishDiv').removeClass('active');
            $(' #publishDivTab').addClass('active');
            $('#publishDiv').addClass('active');
            listITable();
        }else{
            listATable();
        }
    });

</script>

</body>
</html>