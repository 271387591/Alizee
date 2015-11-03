<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/common/taglibs.jsp" %>
    <title>商品出售记录</title>
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
                    <li class="active"><i class="icon-user"></i>商品出售记录</li>
                </ul>
            </div>
            <div class="page-content">
                <div class="page-header">
                    <h1>
                        <i class="icon-dashboard">商品出售记录</i>
                    </h1>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="query-box">
                            <div class="query-title">查询</div>
                            <div class="query-content">
                                <ul class="query-form" id="tableSearch">
                                    <li>
                                        <label class="control-label">兑换券编号</label>
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
                                    <li>
                                        <label class="control-label">出售价格</label>
                                        <input type="text" name="advert.Q_ur.price_GE" style="width:100px;"/>
                                        <input type="text" name="advert.Q_ur.price_LE" style="width:100px;"/>
                                    </li>
                                    <li style="width: 600px;">
                                        <label class="form-horizontal">出售时间</label>
                                        <div class="input-group" style="width: 500px;float: left">
                                                    <span class="input-icon input-icon-right" style="float: left">
                                                        <input type="text" readonly name="advert.Q_r.createDate_GE" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})"/>
                                                        <i class="icon-calendar green"></i>
                                                    </span>
                                            <span class="ig-txt">至</span>
                                                    <span class="input-icon input-icon-right">
                                                        <input type="text" readonly name="advert.Q_r.createDate_LE" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})"/>
                                                        <i class="icon-calendar green"></i>
                                                    </span>
                                        </div>
                                    </li>
                                    <li>
                                        <label class="control-label">商户</label>
                                        <input type="text" name="advert.Q_m.name_LK"/>
                                    </li>
                                    <li>
                                        <label class="control-label">是否兑换</label>
                                        <select name="advert.Q_r.enabled_LK">
                                            <option value="">请选择</option>
                                            <option value="1">未兑换</option>
                                            <option value="0">已兑换</option>
                                        </select>
                                    </li>

                                </ul>
                                <div class="btn-query">
                                    <button class="btn btn-success btn-sm" onclick="searchForm($('#tableSearch'),'advert',listGTable);">查询</button>
                                    <button class="btn btn-sm" onclick="clearSearchForm($('#tableSearch'),listGTable);">清空</button>
                                </div>
                            </div>
                        </div>
                        <div class="table-title">
                            <span style="margin-left: 10px" class="green">出售列表</span>
                            <div class="pull-right" style="margin-right: 10px;">
                                <a class="btn btn-sm btn-primary" target="_blank" href="<c:url value="/html/goods/tenant/goodssaleListExcel"/>"><i class="icon-angle-down"></i>导出</a>
                            </div>
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
                                    <th>出售时间</th>
                                    <th>到期时间</th>
                                    <th>是否到期</th>
                                    <th>是否兑换</th>
                                    <th>商户</th>
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
<%--<script type="text/javascript" src="<c:url value="/resources/js/admin/goods/cert.js"/>"></script>--%>
<script type="text/javascript">
    changeNav('menu-goodssaleadmin');
    var columns=[
        {
            name:'certNo'
        },
        {
            name:'name',
            width:200
        },
        {
            name:'price'
        },
        {
            name:'nickName'
        },
        {
            name:'mobile'
        },
        {
            name:'createDate',
            renderer:function(v){
                if(!v)return '';
                return new Date(v).format("yyyy-MM-dd hh:mm:ss");
            }
        },
        {
            name:'endDate',
            renderer:function(v){
                if(!v)return '';
                return new Date(v).format("yyyy-MM-dd hh:mm:ss");
            }
        },
        {
            name:'endDate',
            renderer:function(v,rec){
                if(rec.currentDate>v){
                    return '<span class="label label-lg label-grey">已到期</span>';
                }else{
                    return '<span class="label label-lg label-success">未过期</span>';
                }
            }
        },
            {
            name:'enabled',
            renderer:function(v,rec){
                if(v==1){
                    return '<span class="label label-lg label-success">未兑换</span>';
                }else{
                    return '<span class="label label-lg label-grey">已兑换</span>';
                }
            }
        },
            {
            name:'merchantName'
        }


    ];

    function listGTable(params){

        $('#gameTable').htable({
            url:appPath+'html/goods/security/goodssaleList',
            params: $.extend({},params),
            columns:columns,
            pager:$('#advertPaging')
        });
    }
    jQuery(function(){
        listGTable();
    })
</script>

</body>
</html>