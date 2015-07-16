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
        <li class="active"><i class="icon-user"></i>用户反馈</li>
    </ul>
</div>

<div class="page-content">
    <div class="page-header">
        <h1>
            <i class="icon-dashboard">用户反馈</i>
            <div class="pull-right">
                <button class="btn" type="button" onclick="reloadPage('html/security/userComments');">
                    <i class="icon-undo bigger-110"></i>
                    返回
                </button>
            </div>
        </h1>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                    <div class="profile-info-name">用户手机</div>
                    <div class="profile-info-value">
                        ${command.mobile}
                    </div>
                </div>

                <div class="profile-info-row">
                    <div class="profile-info-name">联系方式</div>
                    <div class="profile-info-value">
                        ${(command.contract!=null)?command.contract:"无"}
                    </div>
                </div>
                <div class="profile-info-row">
                    <div class="profile-info-name">反馈内容</div>
                    <div class="profile-info-value">
                        ${command.content}
                    </div>
                </div>
                <div class="profile-info-row">
                    <div class="profile-info-name">反馈时间</div>
                    <div class="profile-info-value">
                        <fmt:formatDate value="${command.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>

                    </div>
                </div>
                <div class="profile-info-row">
                    <div class="profile-info-name">回复内容</div>
                    <div class="profile-info-value">
                        ${(command.reply!=null)?command.reply:"无"}
                    </div>
                </div>
                <div class="profile-info-row">
                    <div class="profile-info-name">回复时间</div>
                    <div class="profile-info-value">
                        <fmt:formatDate value="${command.lastUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


