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
        <li><a href="javascript:void(0);" onclick="ajaxReloadPage('mainContent','html/recharge/security/rechargeList');">充值查询</a></li>
        <li class="active"><i class="icon-user"></i>充值详情</li>
    </ul>
</div>

<div class="page-content">
    <div class="page-header">
        <h1>
            <i class="icon-dashboard">充值详情</i>
            <div class="pull-right">
                <button class="btn" type="button" onclick="ajaxReloadPage('mainContent','html/recharge/security/rechargeList');">
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
                    <div class="profile-info-name">充值编号</div>
                    <div class="profile-info-value">
                        ${command.rechargeNo}
                    </div>
                </div>
                <div class="profile-info-row">
                    <div class="profile-info-name">用户手机</div>
                    <div class="profile-info-value">
                        ${command.mobile}
                    </div>
                </div>


                <div class="profile-info-row">
                    <div class="profile-info-name">用户昵称</div>
                    <div class="profile-info-value">
                        ${command.nickName}
                    </div>
                </div>
                <div class="profile-info-row">
                    <div class="profile-info-name">充值金额</div>
                    <div class="profile-info-value">
                        ${command.money}
                    </div>
                </div>

                <div class="profile-info-row">
                    <div class="profile-info-name">充值时间</div>
                    <div class="profile-info-value">
                        <fmt:formatDate value='${command.createDate}' pattern='yyyy-MM-dd HH:mm:ss'/>
                    </div>
                </div>
                <c:if test="${command.status==2}">
                    <div class="profile-info-row">
                        <div class="profile-info-name">失败信息</div>
                        <div class="profile-info-value">
                            ${command.details}
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>


