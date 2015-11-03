<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/common/taglibs.jsp" %>
    <title>营业员管理</title>
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
                    <li class="active"><i class="icon-user"></i>营业员管理</li>
                </ul>
            </div>
            <div class="page-content">
                <div class="page-header">
                    <h1>
                        <i class="icon-dashboard">营业员管理</i>
                    </h1>
                </div>
                <div class="row">
                    <div class="col-xs-12">

                        <div class="query-box">
                            <div class="query-title">营业员查询</div>
                            <div class="query-content">
                                <ul class="query-form" id="tableSearch">
                                    <li>
                                        <label class="control-label">用户手机</label>
                                        <input type="text" name="advert.Q_mobile_LK"/>
                                    </li>
                                </ul>
                                <div class="btn-query">
                                    <button class="btn btn-success btn-sm" onclick="searchForm($('#tableSearch'),'advert',listTable);">查询</button>
                                    <button class="btn btn-sm" onclick="clearSearchForm($('#tableSearch'),listTable);">清空</button>
                                </div>
                            </div>
                        </div>
                        <div class="table-title">
                            <span style="margin-left: 10px" class="green">营业员列表</span>
                            <div class="pull-right" style="margin-right: 10px;">
                                <a data-toggle="modal" data-target="#addShopModal" href="#addShopModal" class="btn btn-sm btn-primary"><i class="icon-angle-down"></i>添加</a>
                            </div>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="gameTable">
                                <thead>
                                <tr>
                                    <th>用户手机</th>
                                    <th>昵称</th>
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

        </div>

    </div>
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse" data-rel="tooltip" title="sss">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>
<div id="addShopModal" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
    <div class="modal-dialog width-35">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="icon-power-off"></i>添加营业员</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form class="form-horizontal" id="addShopForm">
                        <div class="form-group">
                            <label class="control-label">用户手机:</label>
                            <div>
                                <input type="text" name="mobile" class="form-control" data-validate="mobile" placeholder="用户手机">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">昵称:</label>
                            <div>
                                <input type="text" class="form-control" name="nickName" placeholder="昵称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">密码:</label>
                            <div>
                                <input type="password" id="addShopPwd" name="password" class="form-control required" data-validate="password" placeholder="新密码">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label">确认密码:</label>
                            <div>
                                <input type="password" name="passwordHit" class="form-control required" data-validate="passwordHit" data-hit="addShopPwd" placeholder="确认密码">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="changePasswordBtn" class="btn btn-primary" onclick="saveShop()">保存</button>
                <button type="button" class="btn" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<div id="updateShopModal" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
    <div class="modal-dialog width-35">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="icon-power-off"></i>编辑营业员</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form class="form-horizontal" id="updateShopForm">
                        <input type="hidden" name="id">
                        <div class="form-group">
                            <label class="control-label">用户手机:</label>
                            <div>
                                <input type="text" name="mobile" class="form-control" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">昵称:</label>
                            <div>
                                <input type="text" class="form-control" name="nickName" placeholder="昵称">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="updateShop()">保存</button>
                <button type="button" class="btn" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div id="changeUserPasswordModel" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
    <div class="modal-dialog width-35">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel"><i class="icon-cog"></i>修改密码</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form class="form-horizontal" id="updateUserPwd">

                        <div class="form-group">
                            <div>
                                <input type="hidden" name="id" id="changePwdUserId">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">新密码:</label>
                            <div>
                                <input type="password" class="form-control" name="password" data-validate="password" id="inputUserPassword" placeholder="新密码">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label">确认密码:</label>
                            <div>
                                <input type="password" class="form-control" data-validate="passwordHit" data-hit="inputUserPassword" placeholder="确认密码">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="changeUserPwd($('#changeUserPasswordModel'))">确定</button>
                <button type="button" class="btn" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<div id="changeUserMobileModel1" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
    <div class="modal-dialog width-35">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="icon-cog"></i>更换手机号</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form class="form-horizontal" id="changeUserMobileForm1">

                        <div class="form-group">
                            <div>
                                <input type="hidden" name="id" id="changeUserMobileId1">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">原手机号:</label>
                            <div>
                                <input type="text" class="form-control" id="changeUserOldMobile1" placeholder="原手机号">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label">新手机号:</label>
                            <div>
                                <input type="text" name="mobile" class="form-control" data-validate="mobile" placeholder="新手机号">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="changeUserMobile1($('#changeUserMobileModel1'))">确定</button>
                <button type="button" class="btn" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    changeNav('menu-shop');
    function saveShop(){
        if(!checkForm($('#addShopForm'))){
            return;
        }
        var obj={
            mobile:$('#addShopForm').find('input[name=mobile]').val(),
            nickName:$('#addShopForm').find('input[name=nickName]').val(),
            password:$('#addShopForm').find('input[name=password]').val()
        }
        var result=requestJSONData('html/goods/tenant/saveShop',obj);
        if(result.success){
            alertSuccess("添加成功");
            $('#addShopModal').modal('hide');
            listTable();
        }else{
            alertError(result.message);
        }
    }
    function updateShop(){
        if(!checkForm($('#updateShopForm'))){
            return;
        }
        var obj={
            id:$('#updateShopForm').find('input[name=id]').val(),
            nickName:$('#updateShopForm').find('input[name=nickName]').val(),
            mobile:$('#updateShopForm').find('input[name=mobile]').val()
        }
        var result=requestJSONData('html/goods/tenant/saveShop',obj);
        if(result.success){
            alertSuccess("添加成功");
            $('#updateShopModal').modal('hide');
            listTable();
        }else{
            alertError(result.message);
        }
    }

    function changeUserPwd(modal){
        var form=$('#changeUserPasswordModel').find('#updateUserPwd');
        if(!checkForm(form)){
            return;
        }
        var datas=form.serializeArray();
        var obj={};
        for(var i=0;i<datas.length;i++){
            obj[datas[i].name]=datas[i].value;
        }
        var result = requestJSONData('html/goods/tenant/changeUserPwd',obj);
        if(result.success){
            alertSuccess('修改成功');
            listTable();
        }else{
            alertError(result.message);
        }
        modal.modal('hide');
    }
    function changeUserMobile1(modal){
        var form=$('#changeUserMobileModel1').find('#changeUserMobileForm1');
        if(!checkForm(form)){
            return;
        }
        var datas=form.serializeArray();
        var obj={};
        for(var i=0;i<datas.length;i++){
            obj[datas[i].name]=datas[i].value;
        }
        var result = requestJSONData('html/goods/tenant/changeUserMobile1',obj);
        if(result.success){
            alertSuccess('修改成功');
            listTable();
        }else{
            alertError(result.message);
        }
        modal.modal('hide');
    }
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
            name:'createDate',
            width:140,
            renderer:function(v){
                return new Date(v).format("yyyy-MM-dd hh:mm:ss");
            }
        },
        {
            width:120,
            renderer:function(v,rec){
                return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                        '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,removeUser)">'+
                        '<i class="icon-trash bigger-130"></i>'+
                        '</a>'+
                        '<a class="green" href="#updateShopModal" userId="'+rec.id+'" nickName="'+rec.nickName+'" mobile="'+rec.mobile+'" data-toggle="modal" data-target="#updateShopModal" data-rel="tooltip" title="编辑" >'+
                        '<i class="icon-pencil bigger-130"></i>'+
                        '</a>'+
                        '<a class="green" href="#changeUserPasswordModel" data-rel="tooltip" userId="'+rec.id+'" title="修改密码" data-toggle="modal" data-target="#changeUserPasswordModel">'+
                        '<i class="icon-edit bigger-130"></i>'+
                        '</a>'+
                        '<a class="blue" href="#changeUserMobileModel" userId="'+rec.id+'" mobile="'+rec.mobile+'" data-rel="tooltip" title="更换手机号" data-toggle="modal" data-target="#changeUserMobileModel1">'+
                        '<i class="icon-phone bigger-130"></i>'+
                        '</a>'+
                        '</div>';
            }
        }
    ];
    function listTable(params){
        $('#gameTable').htable({
            url:appPath+'html/goods/tenant/listShop',
            params:$.extend({},params),
            columns:columns,
            pager:$('#advertPaging')
        });

    }
    function removeUser(id){
        var result=requestJSONData('html/goods/tenant/removeUser/'+id);
        if(result.success){
            alertSuccess("删除成功");
            listTable();
        }else{
            alertError(result.message);
        }
    }

    jQuery(function($) {
        listTable();
        $('#changeUserPasswordModel').on('show.bs.modal', function (event) {
            clearForm($('#updateUserPwd'));
            $('#changePwdUserId').val($(event.relatedTarget).attr('userId'));
        });
        $('#updateShopModal').on('show.bs.modal', function (event) {
            clearForm($('#updateShopForm'));
            $('#updateShopForm').find('input[name=id]').val($(event.relatedTarget).attr('userId'));
            $('#updateShopForm').find('input[name=mobile]').val($(event.relatedTarget).attr('mobile'));
            $('#updateShopForm').find('input[name=nickName]').val($(event.relatedTarget).attr('nickName'));
        });
        $('#changeUserMobileModel1').on('show.bs.modal', function (event) {
            clearForm($('#changeUserMobileForm1'));
            $('#changeUserMobileId1').val($(event.relatedTarget).attr('userId'));
            $('#changeUserOldMobile1').val($(event.relatedTarget).attr('mobile'));
            $('#changeUserOldMobile1').attr('readOnly',true);
        });

    });

</script>

</body>
</html>