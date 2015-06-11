<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 6/4/15
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

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
									Jason
								</span>

                        <i class="icon-caret-down"></i>
                    </a>

                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="javascript:void(0);" id="updatePassword" data-toggle="modal" data-target="#exampleModal">
                                <i class="icon-cog"></i>
                                修改密码
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
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
<div id="changePasswordDialog" class="dialog_content" style="display:none">
    <div class="dialogModal_header">修改密码</div>
    <div class="dialogModal_content">
        <form class="form-horizontal" id="registerForm">
            <div class="form-group">
                <label for="inputMobile" class="control-label">新密码:</label>
                <input type="text" class="form-control required" id="inputNick" placeholder="新密码" data-toggle="tooltip" data-placement="bottom">
            </div>
            <div class="form-group">
                <label for="inputMobile" class="control-label">确认密码:</label>
                <input type="text" class="form-control" id="inputMobile" placeholder="确认密码" data-toggle="tooltip" data-placement="bottom">
            </div>
        </form>
    </div>
    <div class="dialogModal_footer">
        <button type="button" class="btn btn-primary" data-dialogModalBut="ok">确定</button>
        <button type="button" class="btn" data-dialogModalBut="cancel">cancel</button>
    </div>
</div>


<script type="text/javascript">
    $(function(){
        $('#updatePassword').click(function(){
            $('#changePasswordDialog').dialogModal({
                onOkBut: function() {
                    console.log('sdfsdf')
                    return false;
                },
                onCancelBut: function() {},
                onLoad: function() {},
                onClose: function() {
                }
            });
        });
    });
</script>