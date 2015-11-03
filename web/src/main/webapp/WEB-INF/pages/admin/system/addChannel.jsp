<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 6/19/15
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="breadcrumbs" id="breadcrumbs">
    <script type="text/javascript">
        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
    </script>
    <ul class="breadcrumb">
        <li>
            系统设置
        </li>
        <li><a href="<c:url value="/html/security/advert"/> ">渠道管理</a></li>
        <li class="active"><i class="icon-user"></i>${command.id==null?'添加渠道':'编辑渠道'}</li>
    </ul>
</div>
<div class="page-content">
    <div class="page-header">
        <h1>
            <i class="icon-dashboard">${command.id==null?'添加渠道':'编辑渠道'}</i>
        </h1>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <form class="form-horizontal" id="advertForm" enctype="multipart/form-data" method="post">
                <div class="form-group">
                    <input type="hidden" name="id" value="${command.id}" />
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right">编号(字母或数字)</label>
                    <div class="col-sm-4">
                        <input type="text" name="channelNo" value="${command.channelNo}"  placeholder="编号" data-validate="channel" class="width-100" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right">名称</label>
                    <div class="col-sm-4">
                        <input type="text" name="name" value="${command.name}"  placeholder="名称" data-validate="required" class="width-100" />
                    </div>
                </div>
                <div class="clearfix form-actions">
                    <div class="col-md-offset-3 col-md-9">
                        <button class="btn btn-info" type="button" id="saveBtn" onclick="saveAdvert(${command.id!=null?"'edit'":"'save'"});" >
                            <i class="icon-ok bigger-110"></i>
                            保存
                        </button>
                        &nbsp; &nbsp; &nbsp;
                        <button class="btn" type="button" onclick="reloadPage('html/security/channel')">
                            <i class="icon-undo bigger-110"></i>
                            返回
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


