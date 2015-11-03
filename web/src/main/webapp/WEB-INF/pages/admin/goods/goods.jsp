<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/common/taglibs.jsp" %>
    <title>商品管理</title>
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
                    <li class="active"><i class="icon-user"></i>商品管理</li>
                </ul>
            </div>
            <div class="page-content">
                <div class="page-header">
                    <h1>
                        <c:if test="${command.type==null}">
                            <i class="icon-dashboard">商品管理(平台还未设定抢购)</i>
                        </c:if>
                        <c:if test="${command.type==1}">
                            <i class="icon-dashboard">商品管理(商品抢购时间为：${command.startDate}至${command.endDate})</i>
                        </c:if>
                        <c:if test="${command.type==2}">
                            <i class="icon-dashboard">
                                商品管理<span style="font-size: 15px;color: green">(商品抢购时间为：每天
                                <c:forEach var="cm" items="${command.goods}">
                                    <c:if test="${cm.startDate<10}">
                                        0${cm.startDate}:00
                                    </c:if>
                                    <c:if test="${cm.startDate>9}">
                                        ${cm.startDate}:00
                                    </c:if>
                                    至
                                    <c:if test="${cm.endDate<10}">
                                        0${cm.endDate}:00
                                    </c:if>
                                    <c:if test="${cm.endDate>9}">
                                        ${cm.endDate}:00
                                    </c:if>
                                    &nbsp;&nbsp;&nbsp;&nbsp;

                                </c:forEach>
                                )
                                </span>

                            </i>
                        </c:if>
                        <%--<c:if test="${command.certificate==null}">--%>
                            <%--<span style="font-size: 15px;color: green">（平台还未设置兑换券信息）</span>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${command.certificate!=null}">--%>
                            <%--<span style="font-size: 15px;color: green">（兑换券过期时间：${command.certificate.endDate}${(command.certificate.type==2)?("天"):""}）</span>--%>
                        <%--</c:if>--%>
                    </h1>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
                            <li class="active">
                                <a data-toggle="tab"  href="#noPublishDiv" id="noPublishTab">普通商品</a>
                            </li>

                            <li>
                                <a data-toggle="tab" href="#publishDiv" id="publishTab">抢购商品</a>
                            </li>
                            <div class="pull-right" style="margin-right: 10px;">
                                <button class="btn btn-sm btn-primary" onclick="edit();"><i class="icon-angle-down"></i>添加</button>
                            </div>
                        </ul>
                        <div class="tab-content" style="padding:2px 0px;">
                            <div class="tab-pane active" id="noPublishDiv">
                                <div class="query-box">
                                    <div class="query-title">商品查询</div>
                                    <div class="query-content">
                                        <ul class="query-form" id="tableSearch">
                                            <li>
                                                <label class="control-label">名称</label>
                                                <input type="text" name="advert.Q_name_LK"/>
                                            </li>
                                            <li>
                                                <label class="control-label">商品价格</label>
                                                <input type="text" name="advert.Q_price_GE" style="width:100px;"/>
                                                <input type="text" name="advert.Q_price_LE" style="width:100px;"/>
                                            </li>
                                            <li style="width: 600px;">
                                                <label class="form-horizontal">添加时间</label>
                                                <div class="input-group" style="width: 500px;float: left">
                                                    <span class="input-icon input-icon-right" style="float: left">
                                                        <input type="text" readonly name="advert.Q_createDate_GE" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})"/>
                                                        <i class="icon-calendar green"></i>
                                                    </span>
                                                                <span class="ig-txt">至</span>
                                                    <span class="input-icon input-icon-right">
                                                        <input type="text" readonly name="advert.Q_createDate_LE" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})"/>
                                                        <i class="icon-calendar green"></i>
                                                    </span>
                                                </div>
                                            </li>
                                        </ul>
                                        <div class="btn-query">
                                            <button class="btn btn-success btn-sm" onclick="searchForm($('#tableSearch'),'advert',listGTable);">查询</button>
                                            <button class="btn btn-sm" onclick="clearSearchForm($('#tableSearch'),listGTable);">清空</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-title">
                                    <span style="margin-left: 10px" class="green">普通商品列表</span>
                                    <div class="pull-right" style="margin-right: 10px;">
                                        <a class="btn btn-sm btn-primary" target="_blank" href="<c:url value="/html/goods/tenant/exportExcel/0"/>"><i class="icon-angle-down"></i>导出</a>
                                    </div>

                                </div>
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="gameTable">
                                        <thead>
                                        <tr>
                                            <th>商品名称</th>
                                            <th>售价</th>
                                            <th>数量</th>
                                            <th>描述</th>
                                            <th>创建时间</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>

                                <div id="advertPaging" class="page table-bottom">
                                </div>
                            </div>
                            <div class="tab-pane in" id="publishDiv">
                                <div class="query-box">
                                    <div class="query-title">商品查询</div>
                                    <div class="query-content">
                                        <ul class="query-form" id="parSearch">
                                            <li>
                                                <label class="control-label">名称</label>
                                                <input type="text" name="advert.Q_name_LK"/>
                                            </li>
                                            <li>
                                                <label class="control-label">商品价格</label>
                                                <input type="text" name="advert.Q_price_GE" style="width:100px;"/>
                                                <input type="text" name="advert.Q_price_LE" style="width:100px;"/>
                                            </li>
                                            <li style="width: 600px;">
                                                <label class="form-horizontal">添加时间</label>
                                                <div class="input-group" style="width: 500px;float: left">
                                                    <span class="input-icon input-icon-right" style="float: left">
                                                        <input type="text" readonly name="advert.Q_createDate_GE" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})"/>
                                                        <i class="icon-calendar green"></i>
                                                    </span>
                                                    <span class="ig-txt">至</span>
                                                    <span class="input-icon input-icon-right">
                                                        <input type="text" readonly name="advert.Q_createDate_LE" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})"/>
                                                        <i class="icon-calendar green"></i>
                                                    </span>
                                                </div>
                                            </li>
                                        </ul>
                                        <div class="btn-query">
                                            <button class="btn btn-success btn-sm" onclick="searchForm($('#parSearch'),'advert',listNoTable);">查询</button>
                                            <button class="btn btn-sm" onclick="clearSearchForm($('#parSearch'),listNoTable);">清空</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-title">
                                    <span style="margin-left: 10px" class="green">抢购商品列表</span>
                                    <div class="pull-right" style="margin-right: 10px;">
                                        <a class="btn btn-sm btn-primary" target="_blank" href="<c:url value="/html/goods/tenant/exportExcel/1"/>"><i class="icon-angle-down"></i>导出</a>
                                    </div>
                                </div>
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="perTable">
                                        <thead>
                                        <tr>
                                            <th>商品名称</th>
                                            <th>售价</th>
                                            <th>数量</th>
                                            <th>描述</th>
                                            <th>创建时间</th>
                                            <%--<th>兑换券失效</th>--%>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>

                                <div id="perPaging" class="page table-bottom">
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
<div id="qiangGoodsModel" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
    <div class="modal-dialog width-35">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="icon-cog"></i>抢购商品设置</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="radio">
                                <label>
                                    <input name="duihuan" type="radio" class="ace" value="1" checked="checked" />
                                    <span class="lbl">按固定时间</span>
                                </label>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="radio">
                                <label>
                                    <input name="duihuan" type="radio" class="ace" value="2" />
                                    <span class="lbl">按动态时间</span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="row " id="duihuanTimeDiv">
                        <div class="col-xs-12">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <input id="certificateEnddateId" name="id" type="hidden">
                                    <label class="col-sm-4 control-label no-padding-right">失效时间</label>
                                    <div class="col-sm-4">
                                    <span class="input-icon input-icon-right">
                                            <input class="width-200" name="certificateEnddate" id="certificateEnddate" type="text" data-validate="required" readonly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})"/>
                                            <i class="icon-calendar green"></i>
                                    </span>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="row hidden" id="duihuanDyTimeDiv">
                        <div class="col-xs-12">
                            <form class="form-horizontal">
                                <input id="certificateDyEnddateId" name="id" type="hidden">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label no-padding-right">从购买时间起</label>
                                    <div class="col-sm-4">
                                        <input class="width-100" id="certificateDyEnddate" name="certificateDyEnddate" type="text" data-validate="integer" />
                                    </div>
                                    <span>天失效</span>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="changePasswordBtn" class="btn btn-primary" onclick="saveQiang($('#qiangGoodsModel'))">确定</button>
                <button type="button" class="btn" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/admin/goods/goods.js"/>"></script>
<script type="text/javascript">

    jQuery(function($) {
        $('#myTab4 a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            var href=e.target.getAttribute("href");
            if(href=='#noPublishDiv'){
                listGTable();
            }else{
                listNoTable();
            }
        });
        changeNav('menu-goods');
        listGTable();
        $('input[name=duihuan]').change(function(){
            if($(this).val()==1){
                $('#duihuanTimeDiv').removeClass('hidden');
                $('#duihuanDyTimeDiv').addClass('hidden');
            }else{
                $('#duihuanDyTimeDiv').removeClass('hidden');
                $('#duihuanTimeDiv').addClass('hidden');
            }
        });

    });

</script>

</body>
</html>