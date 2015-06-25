<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/common/taglibs.jsp" %>
    <title>用户管理</title>
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

        </div>

    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse" data-rel="tooltip" title="sss">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>
<div id="changeUserPasswordModel" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
    <div class="modal-dialog width-35">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel"><i class="icon-cog"></i>修改密码</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form class="form-horizontal" id="updateUserPwd">

                        <div class="form-group">
                            <div>
                                <input type="hidden" name="id" id="changePwdUserId">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">新密码:</label>
                            <div>
                                <input type="password" class="form-control" name="password" data-validate="password" id="inputUserPassword" placeholder="新密码">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label">确认密码:</label>
                            <div>
                                <input type="password" class="form-control" data-validate="passwordHit" data-hit="inputUserPassword" placeholder="确认密码">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="changePasswordBtn" class="btn btn-primary" onclick="changeUserPwd($('#changeUserPasswordModel'))">确定</button>
                <button type="button" class="btn" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<div id="changeUserMobileModel" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
    <div class="modal-dialog width-35">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="icon-cog"></i>修改密码</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form class="form-horizontal" id="changeUserMobileForm">

                        <div class="form-group">
                            <div>
                                <input type="hidden" name="id" id="changeUserMobileId">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">原手机:</label>
                            <div>
                                <input type="text" class="form-control" id="changeUserOldMobile" placeholder="原手机">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label">新手机:</label>
                            <div>
                                <input type="text" name="mobile" class="form-control" data-validate="mobile" placeholder="新手机">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="changeUserMobile($('#changeUserMobileModel'))">确定</button>
                <button type="button" class="btn" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<c:url value="/resources/js/admin/user/user.js"/>"></script>
<script type="text/javascript">

    jQuery(function($) {
        changeNav('menu-user');
        ajaxReloadPage('mainContent','html/user/security/userList');
        $('#changeUserPasswordModel').on('show.bs.modal', function (event) {
            clearForm($('#updateUserPwd'));
            $('#changePwdUserId').val($(event.relatedTarget).attr('userId'));
        });
        $('#changeUserMobileModel').on('show.bs.modal', function (event) {
            clearForm($('#changeUserMobileForm'));
            $('#changeUserMobileId').val($(event.relatedTarget).attr('userId'));
            $('#changeUserOldMobile').val($(event.relatedTarget).attr('mobile'));
            $('#changeUserOldMobile').attr('readOnly',true);
        });

    });

</script>

</body>
</html>