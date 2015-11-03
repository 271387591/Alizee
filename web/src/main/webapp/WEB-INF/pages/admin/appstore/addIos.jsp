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
                    <li class="active"><i class="icon-user"></i>IOS管理</li>
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
                                <li><a href="javascript:void(0);" onclick="reloadPage('html/security/appstore')">APP管理</a></li>
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
                                            <input type="hidden" name="plat" value="IOS" />
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
                                            <label class="col-sm-1 control-label no-padding-right">下载地址</label>
                                            <div class="col-sm-4">
                                                <input type="text" name="iosUrl" value="${command.iosUrl}" data-validate="required" class="width-100" />
                                            </div>
                                        </div>


                                        <div class="clearfix form-actions">
                                            <div class="col-md-offset-3 col-md-9">
                                                <button class="btn btn-info" type="button" id="saveBtn" onclick="saveios();" >
                                                    <i class="icon-ok bigger-110"></i>
                                                    保存
                                                </button>
                                                &nbsp; &nbsp; &nbsp;

                                                <button class="btn" type="button" onclick="reloadPage('html/security/appstore?p=ios');">
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
</script>

</body>
</html>


