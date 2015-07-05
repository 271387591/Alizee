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
        <li>
            系统设置
        </li>
        <li><a href="javascript:void(0);" onclick="ajaxReloadPage('mainContent','html/activity/security/activityList')">活动管理</a></li>
        <li class="active"><i class="icon-user"></i>${command.id==null?'添加活动':'编辑活动'}</li>
    </ul>
</div>

<div class="page-content">
    <div class="page-header">
        <h1>
            <i class="icon-dashboard">${command.id==null?'添加活动':'编辑活动'}</i>
        </h1>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <form class="form-horizontal" id="advertForm">
                <div class="form-group">
                    <input type="hidden" name="id" value="${command.id}" />
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right" for="companyName">活动标题</label>
                    <div class="col-sm-4">
                        <input type="text" name="title" value="${command.title}" id="companyName" placeholder="活动标题" data-validate="required" class="width-100" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right" for="companyName">开始时间</label>
                    <div class="col-sm-4">
                        <span class="input-icon input-icon-right">
                            <input class="width-100" name="startDate" type="text" data-validate="required"  value="<fmt:formatDate value='${command.startDate}' pattern='yyyy-MM-dd HH:mm:ss'/>" readonly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})"/>
                            <i class="icon-calendar green"></i>
                        </span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right" for="companyName">结束时间</label>
                    <div class="col-sm-4">
                        <span class="input-icon input-icon-right">
                            <input class="width-100" name="endDate" type="text" data-validate="required" value="<fmt:formatDate value='${command.endDate}' pattern='yyyy-MM-dd HH:mm:ss'/>" readonly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})"/>
                            <i class="icon-calendar green"></i>
                        </span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right" for="companyName">商家信息</label>
                    <div class="col-sm-4">
                        <input type="text" name="merchant" value="${command.merchant}" placeholder="商家信息" data-validate="required" class="width-100" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right" for="companyName">商家地址</label>
                    <div class="col-sm-4">
                        <input type="text" name="merchantAddress" value="${command.merchantAddress}" placeholder="商家地址" data-validate="required" class="width-100" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right" for="companyName">商家电话</label>
                    <div class="col-sm-4">
                        <input type="text" name="merchantPhone" value="${command.merchantPhone}" placeholder="商家电话" data-validate="required" class="width-100" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right" for="companyInfo">活动简介</label>
                    <div class="col-sm-4">
                        <textarea name="description" class="width-100 autosize-transition limited" data-validate="required" maxlength="1000" id="companyInfo" placeholder="活动简介">${command.description}</textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right" for="activityPicture">活动图片</label>
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
                            <span><a target="_blank" href="${command.url}">${command.picName}</a></span>
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
                        <button class="btn btn-info" type="button" id="saveAndPublishBtn" onclick="saveAdvert(${command.id!=null?"'edit'":"'save'"},'Y');" >
                            <i class="icon-ok bigger-110"></i>
                            保存并发布
                        </button>
                        &nbsp; &nbsp; &nbsp;

                        <button class="btn" type="button" onclick="reloadPage('html/security/activity');">
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
        initPage('${command.id!=null?command.picName:"拖拽或点击此区域上传文件"}');
    });

</script>

