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
        <li class="active"><i class="icon-user"></i>活动管理</li>
    </ul>
</div>
<div class="page-content">
    <div class="page-header">
        <h1>
            <i class="icon-dashboard">活动管理</i>
        </h1>
    </div>
    <div class="row">
        <div class="col-xs-12">

            <ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
                <li class="active">
                    <a data-toggle="tab" href="#noPublishDiv">未发布活动</a>
                </li>

                <li>
                    <a data-toggle="tab" href="#publishDiv">已发布活动<span class="badge badge-danger">${command.count}</span></a>
                </li>
            </ul>
            <div class="tab-content" style="padding:2px 0px;">
                <div class="tab-pane in active" id="noPublishDiv">
                    <div class="query-box">
                        <div class="query-title">内容查询</div>
                        <div class="query-content">
                            <ul class="query-form" id="noPublishSearch">
                                <li>
                                    <label class="control-label">标题</label>
                                    <input type="text" name="advert.Q_title_LK" exportParam="EXPORT_S_LK_name"/>
                                </li>
                            </ul>
                            <div class="btn-query">
                                <button class="btn btn-success btn-sm" onclick="searchForm($('#noPublishSearch'),'advert',listNoPubTable);">查询</button>
                                <button class="btn btn-sm" onclick="clearSearchForm($('#noPublishSearch'),listNoPubTable);">清空</button>
                            </div>
                        </div>
                    </div>
                    <div class="table-title">
                        <span style="margin-left: 10px" class="green">活动列表</span>
                        <div class="pull-right" style="margin-right: 10px;">
                            <button class="btn btn-sm btn-primary" onclick="edit();"><i class="icon-angle-down"></i>添加</button>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="noPublishTable">
                            <thead>
                            <tr>
                                <th>活动标题</th>
                                <th>商家</th>
                                <th>商家电话</th>
                                <th>开始时间</th>
                                <th>结束时间</th>
                                <th>创建时间</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>

                    <div id="noPublishPaging" class="page table-bottom">
                    </div>
                </div>
                <div class="tab-pane in" id="publishDiv">
                    <div class="query-box">
                        <div class="query-title">内容查询</div>
                        <div class="query-content">
                            <ul class="query-form" id="publishSearch">
                                <li>
                                    <label>标题</label>
                                    <input type="text" name="advert.Q_title_LK" exportParam="EXPORT_S_LK_name"/>
                                </li>


                                <li style="width: 600px;">
                                    <label class="form-horizontal">发布时间</label>
                                    <div class="input-group" style="width: 500px;float: left">
                                        <span class="input-icon input-icon-right" style="float: left">
                                            <input type="text" readonly name="advert.Q_lastUpdateDate_GE" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})"/>
                                            <i class="icon-calendar green"></i>
                                        </span>
                                        <span class="ig-txt">至</span>
                                        <span class="input-icon input-icon-right">
                                            <input type="text" readonly name="advert.Q_lastUpdateDate_LE" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})"/>
                                            <i class="icon-calendar green"></i>
                                        </span>

                                    </div>
                                </li>

                            </ul>
                            <div class="btn-query">
                                <button class="btn btn-success btn-sm" onclick="searchForm($('#publishSearch'),'advert',listPubTable);">查询</button>
                                <button class="btn btn-sm" onclick="clearSearchForm($('#publishSearch'),listPubTable);">清空</button>
                            </div>
                        </div>
                    </div>
                    <div class="table-responsive" style="">
                        <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="publishTable">
                            <thead>
                            <tr>
                                <th>活动标题</th>
                                <th>商家</th>
                                <th>商家电话</th>
                                <th>开始时间</th>
                                <th>结束时间</th>
                                <th>发布时间</th>
                                <th>参与人数</th>
                                <th>状态</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>

                    <div id="publishPaging" class="page table-bottom">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    jQuery(function(){
        $('#myTab4 a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            var href=e.target.getAttribute("href");
            if(href=='#publishDiv'){
                listPubTable();
            }else{
                listNoPubTable();
            }
        });
        listNoPubTable();
    });
</script>
