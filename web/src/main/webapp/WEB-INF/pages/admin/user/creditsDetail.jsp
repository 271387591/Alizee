<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/common/taglibs.jsp" %>
    <title>金额操作记录</title>
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
                    <li class="active"><i class="icon-user"></i>金额操作记录</li>
                </ul>
            </div>
            <div class="page-content">
                <div class="page-header">
                    <h1>
                        <i class="icon-dashboard">列表</i>
                    </h1>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="query-box">
                            <div class="query-title">查询</div>
                            <div class="query-content">
                                <ul class="query-form" id="userSearch">
                                    <li>
                                        <label class="control-label">手机号码</label>
                                        <input type="text" name="advert.Q_mobile_EQ"/>
                                    </li>
                                </ul>
                                <div class="btn-query">
                                    <button class="btn btn-success btn-sm" onclick="searchForm($('#userSearch'),'advert',listTable);">查询</button>
                                    <button class="btn btn-sm" onclick="clearSearchForm($('#userSearch'),listTable);">清空</button>
                                </div>
                            </div>
                        </div>

                        <div class="table-title">
                            <span style="margin-left: 10px" class="green">记录列表</span>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="gameTable">
                                <thead>
                                <tr>
                                    <th>用户手机</th>
                                    <th>用户昵称</th>
                                    <th>修改金额</th>
                                    <th>修改时间</th>
                                    <th>修改人</th>
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

        </div>

    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse" data-rel="tooltip" title="sss">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>

<script type="text/javascript" src="<c:url value="/resources/js/admin/user/user.js"/>"></script>
<script type="text/javascript">
    var columns=[
        {
            width:200,
            name:'mobile',
            renderer:function(v,rec){
                return '<a href="#edit" onclick="edit('+rec.id+');">'+v+'</a>';
            }
        },
        {
            width:200,
            name:'nickName'
        },
        {
            width:80,
            name:'num'
        },

        {
            name:'createDate',
            width:140,
            renderer:function(v){
                return new Date(v).format("yyyy-MM-dd hh:mm:ss");
            }
        },
        {
            width: 200,
            name: 'optNickName'
        },
        {
            width:120,
            renderer:function(v,rec){
                return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                        '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,removeUser)">'+
                        '<i class="icon-trash bigger-130"></i>'+
                        '</a>'+
                        '</div>';
            }
        }
    ];
    function listTable(params){
        $('#gameTable').htable({
            url:appPath+'html/creditsDetail/security/list',
            params:$.extend({},params),
            columns:columns,
            pager:$('#paging')
        });

    }
    function removeUser(id,modal){
        var result=requestStringData('html/creditsDetail/security/delete/'+id);
        if(result.success){
            alertSuccess('操作成功');
            listTable();
        }else{
            alertError(result.message);
        }
        return true;
    }
    jQuery(function($) {
        changeNav('menu-creditDetail');
        listTable();
    });

</script>

</body>
</html>