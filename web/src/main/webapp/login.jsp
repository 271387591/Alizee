<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta charset="utf-8" />
    <title>后台登陆</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>office</title>

    <link href="<c:url value="/resources/css/bootstrap.min.css"/> " rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />

    <!--[if IE 7]>
    <link rel="stylesheet" href="<c:url value="/resources/css/ie/font-awesome-ie7.min.css"/>" />
    <![endif]-->

    <!-- page specific plugin styles -->

    <!-- fonts -->
    <!-- ace styles -->

    <link rel="stylesheet" href="<c:url value="/resources/css/ace.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/css/ace-rtl.min.css"/>" />

    <!--[if lte IE 8]-->
    <link rel="stylesheet" href="<c:url value="/resources/css/ie/ace-ie.min.css"/>" />
    <!--[endif]-->

    <!-- inline styles related to this page -->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]-->
    <script src="<c:url value="/resources/lib/ie/html5shiv.js"/>"></script>
    <script src="<c:url value="/resources/lib/ie/respond.min.js"/>"></script>
    <!--[endif]-->
    <!--[if !IE]> -->
    <script src="<c:url value="/resources/lib/jquery/jquery-2.0.3.min.js"/>"></script>
     <!--<![endif]-->

    <!--[if IE]-->
    <script src="<c:url value="/resources/lib/jquery/jquery-1.10.2.min.js"/>"></script>
    <!--[endif]-->

</head>
<body class="login-layout">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <i class="icon-leaf green"></i>
                            <span class="red">网站</span>
                            <span class="white">后台管理系统</span>
                        </h1>
                        <h4 class="blue">&copy; Company Name</h4>
                    </div>

                    <div class="space-6"></div>

                        <div class="position-relative">
                            <div id="login-box" class="login-box visible widget-box no-border">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <h4 class="header blue lighter bigger">
                                            <i class="icon-coffee green"></i>
                                            请输入用户名和密码
                                        </h4>

                                        <div class="space-6"></div>

                                        <form action="<c:url value="/j_security_check"/> " method="post" id="loginForm">
                                            <fieldset>
                                                <input type="hidden" name="platform" value="PC">
                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="用户名" name="username" />
															<i class="icon-user"></i>
														</span>
                                                </label>

                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="密码" name="password" />
															<i class="icon-lock"></i>
														</span>
                                                </label>

                                                <div class="space"></div>

                                                <div class="clearfix">

                                                    <button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
                                                        <i class="icon-key"></i>
                                                        登陆
                                                    </button>
                                                </div>

                                                <div class="space-4"></div>
                                            </fieldset>
                                        </form>

                                    </div><!-- /widget-main -->

                                    <div class="toolbar clearfix">

                                    </div>
                                </div><!-- /widget-body -->
                            </div><!-- /login-box -->

                        </div><!-- /position-relative -->
                </div>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div>
</div><!-- /.main-container -->
<script type="text/javascript">
    $(document).ready(function (e) {
        $('input[name=username]').bind('keydown', function (e) {
            var key = e.which;
            if (key == 13) {
                $('#loginForm').submit();
            }
        });
        $('input[name=password]').bind('keydown', function (e) {
            var key = e.which;
            if (key == 13) {
                $('#loginForm').submit();
            }
        });
        $("#username").focus();
    });
</script>
</body>
</html>