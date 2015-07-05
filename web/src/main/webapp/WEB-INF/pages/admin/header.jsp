<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 6/4/15
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar navbar-default navbar-fixed-top" id="navbar">
    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="icon-leaf"></i>
                    网站后台管理系统
                </small>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->

        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="green">
                    <a data-toggle="dropdown" href="javascript:void(0);" class="dropdown-toggle">
                        <img class="nav-user-photo" src="<c:url value="/resources/images/user.jpg"/> " alt="Jason's Photo" />
								<span class="user-info">
									<small>欢迎光临,</small>
									${userinfo.username}
								</span>

                        <i class="icon-caret-down"></i>
                    </a>

                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="#" id="updatePassword" data-toggle="modal" data-target="#changePasswordModel">
                                <i class="icon-cog"></i>
                                修改密码
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#" data-toggle="modal" data-target="#logoutModel">
                                <i class="icon-power-off"></i>
                                退出
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
<div id="changePasswordModel" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
    <div class="modal-dialog width-35">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel"><i class="icon-cog"></i>修改密码</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form class="form-horizontal" id="updatePwdForm">
                        <div class="form-group">
                            <label for="inputPassword" class="control-label">新密码:</label>
                            <div>
                                <input type="password" class="form-control required" id="inputPassword" placeholder="新密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPasswordHit" class="control-label">确认密码:</label>
                            <div>
                                <input type="password" class="form-control required" id="inputPasswordHit" placeholder="确认密码">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="changePasswordBtn" class="btn btn-primary" onclick="changeAdminPassword()">确定</button>
                <button type="button" class="btn" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<div id="logoutModel" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
    <div class="modal-dialog width-35">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="icon-power-off"></i>退出系统</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <h4>你确定要退出系统?</h4>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="logout();">退出</button>
                <button type="button" class="btn" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $('#changePasswordModel').on('show.bs.modal', function (event) {
        clearForm($('#updatePwdForm'));
    });
    function changeAdminPassword(){
        var val=$('#inputPassword').val();
        if(!validatePassword($('#inputPassword'))){
            return
        }
        if(!validatePasswordHit($('#inputPassword'),$('#inputPasswordHit'))){
            return
        }
        $('#changePasswordModel').modal('hide');
        var result=requestStringData('html/user/security/changeAdminPwd',{password:val});
        if(result.success){
            alertSuccess('操作成功');
        }else{
            alertError(result.message);
        }
    }
    function logout(){
        window.location.replace(appPath+'logout.jsp?plat=security');
    }
</script>