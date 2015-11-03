<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 6/19/15
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

                        <div class="breadcrumbs">
                            <script type="text/javascript">
                                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                            </script>
                            <ul class="breadcrumb">
                                <li><a href="javascript:void(0);" onclick="reloadPage('html/security/appstore');">APP管理</a></li>
                                <li class="active"><i class="icon-user"></i>${command.id==null?'添加':'编辑'}</li>
                            </ul>
                        </div>

                        <div class="page-content">
                            <div class="page-header">
                                <h1>
                                    <i class="icon-dashboard">${command.id==null?'添加':'编辑'}</i>
                                </h1>
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <form class="form-horizontal" id="advertForm">
                                        <div class="form-group">
                                            <input type="hidden" name="id" value="${command.id}" />
                                            <input type="hidden" name="plat" value="Android" />
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-1 control-label no-padding-right">版本号</label>
                                            <div class="col-sm-4">
                                                <input type="text" name="version" value="${command.version}" data-validate="required" class="width-100" />
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-1 control-label no-padding-right">是否发布</label>
                                            <div class="col-sm-4">
                                                <select name="enabled">
                                                    <option value="false" ${command.enabled?"":"selected='selected'"}>否</option>
                                                    <option value="true" ${command.enabled?"selected='selected'":""}>是</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-1 control-label no-padding-right" for="activityPicture">文件安装包</label>
                                            <div class="col-sm-4">
                                                <input multiple="" type="file" name="pacName" id="activityPicture" />
                                                <span class="lbl"></span>
                                            </div>
                                        </div>
                                        <c:if test="${command.id!=null}">
                                            <div class="form-group" id="exsitPicShow">
                                                <label class="col-sm-1 control-label no-padding-right">下载文件安装包</label>
                                                <div class="col-sm-4">
                                                    <span><a target="_blank" href="${command.pacUrl}">${command.pacName}</a></span>
                                                    <span class="lbl"></span>
                                                </div>

                                            </div>
                                        </c:if>

                                        <div class="clearfix form-actions">
                                            <div class="col-md-offset-3 col-md-9">
                                                <button class="btn btn-info" type="button" id="saveBtn" onclick="saveAdvert(${command.id!=null?"'edit'":"'save'"},'N');" >
                                                    <i class="icon-ok bigger-110"></i>
                                                    保存
                                                </button>
                                                &nbsp; &nbsp; &nbsp;

                                                <button class="btn" type="button" onclick="reloadPage('html/security/appstore');">
                                                    <i class="icon-undo bigger-110"></i>
                                                    返回
                                                </button>
                                            </div>
                                        </div>
                                    </form>
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
    changeNav('menu-appstore');
    jQuery(function(){
        $('#activityPicture').ace_file_input({
            style:'well',
            btn_choose:'${command.id!=null?command.picName:"拖拽或点击此区域上传文件"}',
            no_icon:'icon-cloud-upload',
            droppable:true,
            thumbnail:'small',
            before_change:function(files, dropped) {
//                var allowed_files = [];
//                if(allowed_files.length == 0) return false;

                return true;
            },
            preview_error : function(filename, error_code) {
            }

        }).on('change', function(){
            $('#exsitPicShow').hide();
        });
    });
</script>

</body>
</html>


