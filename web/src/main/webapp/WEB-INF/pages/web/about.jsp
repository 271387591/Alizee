<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 6/9/15
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>关于我们</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="<c:url value="/resources/images/favicon.ico"/> ">

    <link href="<c:url value="/resources/css/bootstrap.min.css"/> " rel="stylesheet" />
    <link href="<c:url value="/resources/css/simplePagination.css"/> " rel="stylesheet" />
    <link href="<c:url value="/resources/css/style.css"/> " rel="stylesheet" />


    <!--[if lt IE 9]><script src="<c:url value="/resources/assets/ie8-responsive-file-warning.js"/>"></script><![endif]-->
    <script src="<c:url value="/resources/assets/ie-emulation-modes-warning.js"/> "></script>
    <!--[if lt IE 9]>
    <script src="<c:url value="/resources/lib/ie/html5shiv.js"/>"></script>
    <script src="<c:url value=" / resources / lib / ie / respond.min.js
    "/>"></script>
    <![endif]-->
    <script src="<c:url value="/resources/lib/jquery/jquery-2.0.3.min.js"/>"></script>
    <script src="<c:url value="/resources/lib/bootstrap/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/lib/jplugin/jquery.simplePagination.js"/>"></script>
</head>
<body>
<%@ include file="header.jsp"%>
<script type="text/javascript">
//    $('#nav_about').addClass('active');
changeNav('nav_about');
</script>

<div class="container">

    <div>
        <h2 class="page-header text-info">
            公司
        </h2>
        <p class="text-info">${companyInfo}</p>
    </div>
    <div>
        <h2 class="page-header text-success">
            产品
        </h2>
        <p class="text-success">${companyProduct}</p>
    </div>
    <div>
        <h2 class="page-header">
            联系我们
        </h2>

        <p><label class="col-md-1" for="phone">电话:</label><span id="phone">${companyPhone}</span></p>
        <p><label class="col-md-1" for="mobile">手机:</label><span id="mobile">${companyMobile}</span></p>
        <p><label class="col-md-1" for="email">邮件:</label><span id="email">${companyEmail}</span></p>
        <p><label class="col-md-1" for="qq">QQ:</label><span id="qq">${companyQQ}</span></p>
        <p><label class="col-md-1" for="address">地址:</label><span id="address">${companyAddress}</span></p>
    </div>
</div>
<%@ include file="footer.jsp"%>
<script src="<c:url value="/resources/lib/bootstrap/typeahead-bs2.min.js"/>"></script>
<script src="<c:url value="/resources/assets/ie10-viewport-bug-workaround.js"/>"></script>
<script type="text/javascript">

</script>

</body>
</html>
