<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/common/taglibs.jsp" %>
    <title>用户反馈</title>
</head>
<body class="navbar-fixed">
<%@include file="../header.jsp"%>

<div class="main-container" id="main-container">
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
                    <li>
                        系统设置
                    </li>
                    <li class="active"><i class="icon-user"></i>用户反馈</li>
                </ul>
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="query-box">
                            <div class="query-title">内容查询</div>
                            <div class="query-content">
                                <ul class="query-form">
                                    <li>
                                        <label class="control-label">用户手机</label>
                                        <input type="text" name="advert.Q_u.mobile_EQ"/>
                                    </li>
                                </ul>
                                <div class="btn-query">
                                    <button class="btn btn-success btn-sm" onclick="searchForm($('.query-content'),'advert',listTable);">查询</button>
                                    <button class="btn btn-sm" onclick="clearSearchForm($('.query-content'),listTable);">清空</button>
                                </div>
                            </div>
                        </div>

                        <div class="table-title">
                            <span style="margin-left: 10px" class="green">用户反馈</span>
                        </div>

                        <div class="table-responsive">
                            <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="gameTable">
                                <thead>
                                <tr>
                                    <th class="center">
                                        <label>
                                            <input type="checkbox" class="ace" />
                                            <span class="lbl"></span>
                                        </label>
                                    </th>
                                    <th>用户名</th>
                                    <th>反馈内容</th>
                                    <th>回复内容</th>
                                    <th>反馈时间</th>
                                    <th>回复时间</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div class="table-bottom">
                            <div class="pull-left"><button style="margin-top: 2px;margin-left: 2px" class="btn btn-sm btn-primary" onclick="batchReply();"><i class="icon-remove"></i>批量回复</button></div>
                            <div id="advertPaging" class="page">
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

<div id="replyComments" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
    <div class="modal-dialog width-35">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel"><i class="icon-cog"></i>回复</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form class="form-horizontal" id="replyCommentsForm">
                        <div class="form-group">
                            <label class="control-label">回复内容:</label>
                            <div>
                                <textarea name="description" id="replyContent" class="form-control autosize-transition limited" data-validate="required" maxlength="1000" placeholder="回复内容"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="saveReplyBtn" class="btn btn-primary">确定</button>
                <button type="button" class="btn" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="<c:url value="/resources/js/admin/system/usercomments.js"/>"></script>
<script type="text/javascript">
    jQuery(function($) {
        changeNav('menu-userComments');
        listTable();
    });
</script>
</body>
</html>