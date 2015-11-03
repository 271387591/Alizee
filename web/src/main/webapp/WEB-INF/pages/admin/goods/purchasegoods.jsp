<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/common/taglibs.jsp" %>
    <title>抢购商品</title>
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
                    <li class="active"><i class="icon-user"></i>抢购商品</li>
                </ul>
            </div>
            <div class="page-content">
                <div class="page-header">
                    <h1>
                        <i class="icon-dashboard">抢购商品</i>
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
                                        <input type="text" name="advert.Q_r.name_LK"/>
                                    </li>
                                    <li>
                                        <label class="control-label">商家</label>
                                        <input type="text" name="advert.Q_ur.name_LK"/>
                                    </li>
                                </ul>
                                <div class="btn-query">
                                    <button class="btn btn-success btn-sm" onclick="searchForm($('#tableSearch'),'advert',listTable);">查询</button>
                                    <button class="btn btn-sm" onclick="clearSearchForm($('#tableSearch'),listTable);">清空</button>
                                </div>
                            </div>
                        </div>
                        <div class="table-title">
                            <span style="margin-left: 10px" class="green">抢购商品列表</span>
                            <div class="pull-right" style="margin-right: 10px;">
                                <a class="btn btn-sm btn-primary" target="_blank" href="<c:url value="/html/goods/security/exportExcel"/>"><i class="icon-angle-down"></i>导出</a>
                            </div>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="gameTable">
                                <thead>
                                <tr>
                                    <th>商家名称</th>
                                    <th>商家电话</th>
                                    <th>商品名称</th>
                                    <th>商品数量</th>
                                    <th>商品价格</th>
                                    <th>添加时间</th>
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

        </div>

    </div>
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse" data-rel="tooltip" title="sss">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>
<script type="text/javascript">
    changeNav('menu-purchasegoods');
    var columns=[
        {
            width:200,
            name:'merchantName'
        },
        {
            width:300,
            name:'phone'
        },

        {
            name:'name'
        },
            {
            name:'num',
            width:100
        }, {
            name:'price',
            width:100
        },{
            name:'lastUpdateDate',
            width:140,
            renderer:function(v){
                if(!v) return '';
                return new Date(v).format("yyyy-MM-dd hh:mm:ss");
            }
        }
    ];
    function listTable(params){
        $('#gameTable').htable({
            url:appPath+'html/goods/list',
            params:$.extend({'Q_r.purchase_EQ':1},params),
            columns:columns,
            pager:$('#advertPaging')
        });

    }
    jQuery(function($) {
        listTable();
    });

</script>

</body>
</html>