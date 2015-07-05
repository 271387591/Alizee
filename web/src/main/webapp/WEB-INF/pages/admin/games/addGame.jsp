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

<div class="breadcrumbs">
    <script type="text/javascript">
        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
    </script>
    <ul class="breadcrumb">
        <li><a href="javascript:void(0);" onclick="ajaxReloadPage('mainContent','html/game/security/gameList')">游戏管理</a></li>
        <li class="active"><i class="icon-user"></i>${command.id==null?'添加游戏':'编辑游戏'}</li>
    </ul>
</div>

<div class="page-content">
    <div class="page-header">
        <h1>
            <i class="icon-dashboard">${command.id==null?'添加游戏':'编辑游戏'}</i>
        </h1>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <form class="form-horizontal" id="advertForm">
                <div class="form-group">
                    <input type="hidden" name="id" value="${command.id}" />
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right">游戏名称</label>
                    <div class="col-sm-4">
                        <input type="text" name="name" value="${command.name}" placeholder="游戏名称" data-validate="required" class="width-100" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right">适用平台</label>
                    <div class="col-sm-4">
                        <select id="gamePlatform" class="width-90 chosen-select" name="platform" data-validate="required" data-placeholder="适用平台">
                            <option value="Android">Android</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right">游戏类型</label>
                    <div class="col-sm-4">
                        <input type="text" name="type" value="${command.type}" placeholder="游戏类型" data-validate="required" class="width-100" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right">游戏版本</label>
                    <div class="col-sm-4">
                        <input type="text" name="version" value="${command.version}" placeholder="游戏版本" data-validate="required" class="width-100" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right">游戏热度</label>
                    <div class="col-sm-4">
                        <input type="text" name="popularity" value="${command.popularity}" placeholder="游戏热度" data-validate="integer" class="width-100" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right" for="companyInfo">游戏简介</label>
                    <div class="col-sm-4">
                        <textarea name="description" class="width-100 autosize-transition limited" data-validate="required" maxlength="1000" id="companyInfo" placeholder="游戏简介">${command.description}</textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right" for="activityPicture">游戏图片</label>
                    <div class="col-sm-4">
                        <input multiple="" type="file" name="picName" id="activityPicture" />
                        <span class="lbl"></span>
                    </div>
                    <div class="col-sm-reset inline" style="color: #d16e6c">
                        <i class="icon-warning-sign">仅支持jpeg、jpg、gif、png格式的图片，且大小不能超过1M,请选择合适的图片上传</i>
                    </div>
                </div>
                <c:if test="${command.id!=null}">
                    <div class="form-group" id="exsitPicShow">
                        <label class="col-sm-1 control-label no-padding-right">预览图片</label>
                        <div class="col-sm-4">
                            <span><a target="_blank" href="${command.picUrl}">${command.picName}</a></span>
                            <span class="lbl"></span>
                        </div>

                    </div>
                </c:if>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right" for="activityPicture">游戏安装包</label>
                    <div class="col-sm-4">
                        <input multiple="" type="file" name="gameName" id="gameName" />
                        <span class="lbl"></span>
                    </div>
                    <div class="col-sm-reset inline" style="color: #d16e6c">
                    </div>
                </div>
                <c:if test="${command.id!=null}">
                    <div class="form-group" id="exsitGameShow">
                        <label class="col-sm-1 control-label no-padding-right">下载游戏</label>
                        <div class="col-sm-4">
                            <span><a target="_blank" href="${command.gameUrl}">${command.gameName}</a></span>
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

                        <button class="btn" type="button" onclick="reloadPage('html/security/games');">
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
        initPage('${command.id!=null?command.picName:"拖拽或点击此区域上传文件"}','${command.id!=null?command.gameName:"拖拽或点击此区域上传文件"}');
    });

</script>

