<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 6/19/15
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="breadcrumbs">
    <script type="text/javascript">
        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
    </script>
    <ul class="breadcrumb">
        <li>
            用户模块
        </li>
        <li><a href="javascript:void(0);" onclick="ajaxReloadPage('mainContent','html/user/security/userList')">用户管理</a></li>
        <li class="active"><i class="icon-user"></i>${command.id==null?'添加用户':'编辑用户'}</li>
    </ul>
</div>

<div class="page-content">
    <div class="page-header">
        <h1>
            <i class="icon-dashboard">${command.id==null?'添加用户':'编辑用户'}</i>
        </h1>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <form class="form-horizontal" id="userForm">
                <div class="form-group">
                    <input type="hidden" name="id" value="${command.id}" />
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right">手机号码</label>
                    <div class="col-sm-4">
                        <input type="text" name="mobile" value="${command.mobile}" placeholder="手机号码" data-validate="mobile" class="width-100" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right">昵称</label>
                    <div class="col-sm-4">
                        <input type="text" name="nickName" value="${command.nickName}" placeholder="昵称" data-validate="required" class="width-100" />
                    </div>
                </div>
                <c:if test="${command.id==null}">
                    <div class="form-group">
                        <label class="col-sm-1 control-label no-padding-right">密码</label>
                        <div class="col-sm-4">
                            <input type="password" name="password" class="width-100" data-validate="password" placeholder="密码" id="userPassword" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label no-padding-right">确认密码:</label>
                        <div class="col-sm-4">
                            <input type="password" class="width-100" data-validate="passwordHit" data-hit="userPassword" placeholder="确认密码" />
                        </div>
                    </div>
                </c:if>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right">分配角色</label>
                    <div class="col-sm-4">
                        <select class="width-90 chosen-select" name="roleId" id="roleSelector" data-validate="required" data-placeholder="选择角色">

                        </select>
                    </div>
                </div>

                <div class="clearfix form-actions">
                    <div class="col-md-offset-3 col-md-9">
                        <button class="btn btn-info" type="button" id="saveBtn" onclick="saveAdvert(${command.id!=null?"'edit'":"'save'"});" >
                            <i class="icon-ok bigger-110"></i>
                            保存
                        </button>
                        &nbsp; &nbsp; &nbsp;
                        <button class="btn" type="button" onclick="ajaxReloadPage('mainContent','html/user/security/userList');">
                            <i class="icon-undo bigger-110"></i>
                            返回
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    jQuery(function(){
        initComboData($("#roleSelector"),'html/role/security/list',{start:0,limit:20},'id','displayName','${command.roleId}');
        $("#roleSelector").chosen();
        if(${command.id!=null}){
            $('#userForm').find('input[name=mobile]').attr('readOnly',true);
        }
    });

</script>

