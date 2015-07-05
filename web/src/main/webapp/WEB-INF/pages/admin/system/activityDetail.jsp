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
        <li class="active"><i class="icon-user"></i>查看活动</li>
    </ul>
</div>

<div class="page-content">
    <div class="page-header">
        <h1>
            <i class="icon-dashboard">查看活动</i>
            <div class="pull-right">
                <button class="btn" type="button" onclick="reloadPage('html/security/activity');">
                    <i class="icon-undo bigger-110"></i>
                    返回
                </button>
            </div>
        </h1>

    </div>
    <div class="row">
        <div class="col-xs-12">

            <ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="activityDetailTab">
                <li class="active">
                    <a data-toggle="tab" href="#activityDetail">活动详情</a>
                </li>
                <li>
                    <a data-toggle="tab" href="#noPenddingDiv">待审核人员</a>
                </li>

                <li>
                    <a data-toggle="tab" href="#penddingDiv">审核通过人员</a>
                </li>
            </ul>

            <div class="tab-content" style="padding:2px 0px;">
                <div class="tab-pane in active" id="activityDetail">
                    <div class="profile-user-info profile-user-info-striped">
                        <div class="profile-info-row">
                            <div class="profile-info-name">活动标题</div>
                            <div class="profile-info-value">
                                ${command.title}
                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">是否发布</div>
                            <div class="profile-info-value">
                                ${command.published?'<span class="label label-lg label-success">已发布</span>':'<span class="label label-lg label-grey">未发布</span>'}
                            </div>
                        </div>
                        <c:if test="${command.published}">
                            <div class="profile-info-row">
                                <div class="profile-info-name">发布时间</div>
                                <div class="profile-info-value">
                                    <fmt:formatDate value='${command.lastUpdateDate}' pattern='yyyy-MM-dd HH:mm:ss'/>
                                </div>
                            </div>
                            <div class="profile-info-row">
                                <div class="profile-info-name">参与人数</div>
                                <div class="profile-info-value">
                                        ${command.peoples}
                                </div>
                            </div>
                        </c:if>
                        <div class="profile-info-row">
                            <div class="profile-info-name">开始时间</div>
                            <div class="profile-info-value">
                                <fmt:formatDate value='${command.startDate}' pattern='yyyy-MM-dd HH:mm:ss'/>
                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">结束时间</div>
                            <div class="profile-info-value">
                                <fmt:formatDate value='${command.endDate}' pattern='yyyy-MM-dd HH:mm:ss'/>
                            </div>
                        </div>


                        <div class="profile-info-row">
                            <div class="profile-info-name">商家信息</div>
                            <div class="profile-info-value">
                                ${command.merchant}
                            </div>
                        </div>


                        <div class="profile-info-row">
                            <div class="profile-info-name"> 商家地址 </div>

                            <div class="profile-info-value">
                                ${command.merchantAddress}
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name"> 商家电话 </div>

                            <div class="profile-info-value">
                                ${command.merchantPhone}
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">活动简介</div>

                            <div class="profile-info-value">
                                ${command.description}
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">创建时间</div>
                            <div class="profile-info-value">
                                <fmt:formatDate value='${command.createDate}' pattern='yyyy-MM-dd HH:mm:ss'/>
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">活动图片</div>
                            <div class="profile-info-value">
                                <img src="${command.url}"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane in" id="noPenddingDiv">
                    <div class="query-box">
                        <div class="query-title">人员查询</div>
                        <div class="query-content">
                            <ul class="query-form" id="noPenddingSearch">
                                <li>
                                    <label class="control-label">手机号</label>
                                    <input type="text" name="advert.Q_u.username_EQ"/>
                                </li>
                            </ul>
                            <div class="btn-query">
                                <button class="btn btn-success btn-sm" onclick="searchForm($('#noPenddingSearch'),'advert',listNopendingTable);">查询</button>
                                <button class="btn btn-sm" onclick="clearSearchForm($('#noPenddingSearch'),listNopendingTable);">清空</button>
                            </div>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="noPenddingTable">
                            <thead>
                            <tr>
                                <th>手机号</th>
                                <th>昵称</th>
                                <th>报名时间</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <div id="noPenddingTablePage" class="page table-bottom">
                    </div>
                </div>

                <div class="tab-pane in" id="penddingDiv">
                    <div class="query-box">
                        <div class="query-title">人员查询</div>
                        <div class="query-content">
                            <ul class="query-form" id="penddingSearch">
                                <li>
                                    <label>手机号</label>
                                    <input type="text" name="advert.Q_u.username_LK"/>
                                </li>
                            </ul>
                            <div class="btn-query">
                                <button class="btn btn-success btn-sm" onclick="searchForm($('#penddingSearch'),'advert',listPendingTable);">查询</button>
                                <button class="btn btn-sm" onclick="clearSearchForm($('#penddingSearch'),listPendingTable);">清空</button>
                            </div>
                        </div>
                    </div>
                    <div class="table-responsive" style="">
                        <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="penddingTable">
                            <thead>
                            <tr>
                                <th>手机号</th>
                                <th>昵称</th>
                                <th>报名时间</th>
                                <th>审核时间</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>

                    <div id="penddingTablePage" class="page table-bottom">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    jQuery(function(){
        $('#activityDetailTab a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            var href=e.target.getAttribute("href");
            if(href=='#noPenddingDiv'){
                listNopendingTable({id:'${command.id}'});
            }else{
                listPendingTable({id:'${command.id}'});
            }
        });
    });
</script>


