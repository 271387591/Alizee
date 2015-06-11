<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html>
<html>

<%
    String language = response.getLocale().toString();
    if ("en_US".equalsIgnoreCase(language)) {
        language = "en";
    }
%>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>据了解</title>
    <script type="text/javascript">
        var appPath='<c:url value="/"/>';
    </script>

    <link href="<c:url value="/resources/css/bootstrap.min.css"/> " rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
    <%--<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-editable.css"/>" />--%>

    <!--[if IE 7]>
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome-ie7.min.css"/>" />
    <![endif]-->




    <link rel="stylesheet" href="<c:url value="/resources/css/ace.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/css/ace-rtl.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/css/ace-skins.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/css/jNotify.jquery.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/css/popModal.min.css"/>" />

    <!--[if lte IE 8]>
        <link rel="stylesheet" href="<c:url value="/resources/css/ace-ie.min.css"/>" />
    <![endif]-->



    <!--[if lt IE 9]>
        <script src="<c:url value="/resources/lib/html5shiv.js"/>"></script>
        <script src="<c:url value="/resources/lib/respond.min.js"/>"></script>
    <![endif]-->
    <!--[if !IE]> -->
    <script src="<c:url value="/resources/lib/jquery/jquery-2.0.3.min.js"/>"></script>
    <!-- <![endif]-->

    <!--[if IE]>
    <script src="<c:url value="/resources/lib/jquery/jquery-1.10.2.min.js"/>"></script>
    <![endif]-->


    <script src="<c:url value="/resources/lib/ace/ace-extra.min.js"/>"></script>


    <script src="<c:url value="/resources/lib/popModal.min.js"/>"></script>
    <script src="<c:url value="/resources/js/common.js"/>"></script>
</head>
<body class="navbar-fixed">
<%@ include file="header.jsp"%>

<div class="main-container" id="main-container">
    <script type="text/javascript">
//        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <div class="main-container-inner">
        <%@include file="../admin/menu.jsp"%>
        <div class="main-content">
            <div class="page-content">
                <div class="page-header">
                    <h1>
                        <i class="icon-dashboard">系统设置</i>
                    </h1>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <%@ include file="systemsetting.jsp"%>
                        <script type="text/javascript">
                            loadSetting();
                        </script>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>

<script type="text/javascript">
    if("ontouchend" in document) document.write("<script src='<c:url value="/resources/lib/jquery/jquery.mobile.custom.min.js"/> '>"+"<"+"script>");
</script>

<script src="<c:url value="/resources/lib/bootstrap/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/lib/bootstrap/typeahead-bs2.min.js"/>"></script>

<!--[if lte IE 8]>
<script src="<c:url value="/resources/lib/excanvas.min.js"/>"></script>
<![endif]-->
<%--<script src="<c:url value="/resources/lib/jquery/jquery-ui-1.10.3.custom.min.js"/>"></script>--%>
<%--<script src="<c:url value="/resources/lib/jquery/jquery.ui.touch-punch.min.js"/>"></script>--%>
<%--<script src="<c:url value="/resources/lib/jquery/chosen.jquery.min.js"/>"></script>--%>
<%--<script src="<c:url value="/resources/lib/fuelux/fuelux.spinner.min.js"/>"></script>--%>
<%--<script src="<c:url value="/resources/lib/bootstrap/bootstrap-tag.min.js"/>"></script>--%>
<%--<script src="<c:url value="/resources/lib/bootstrap/bootstrap-tagsinput.min.js"/>"></script>--%>
<%--<script src="<c:url value="/resources/lib/jquery/jquery.knob.min.js"/>"></script>--%>
<script src="<c:url value="/resources/lib/jquery/jquery.autosize.min.js"/>"></script>
<script src="<c:url value="/resources/lib/jquery/jquery.inputlimiter.1.3.1.min.js"/>"></script>
<script src="<c:url value="/resources/lib/jquery/jNotify.jquery.js"/>"></script>
<%--<script src="<c:url value="/resources/lib/jquery/jquery.maskedinput.min.js"/>"></script>--%>

<%--<script src="<c:url value="/resources/lib/bootstrap/bootstrap-editable.min.js"/>"></script>--%>
<%--<script src="<c:url value="/resources/lib/ace/ace-editable.min.js"/>"></script>--%>




<script src="<c:url value="/resources/lib/ace/ace-elements.min.js"/>"></script>
<script src="<c:url value="/resources/lib/ace/ace.min.js"/>"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
//    jQuery(function(){
//        $('textarea[class*=autosize]').autosize({append: "\n"});
//        $('textarea.limited').inputlimiter({
//            remText: '你还可以输入%n个字符',
//            limitText: '最多输入%n字.'
//        });
//    });
</script>






</body>
</html>