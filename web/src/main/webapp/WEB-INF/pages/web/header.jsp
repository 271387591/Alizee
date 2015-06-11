<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 6/4/15
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<nav class="navbar navbar-default navbar-fixed-top" >
    <div class="container">
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active" id="nav_index"><a href="index">首页<span class="sr-only">(current)</span></a></li>
                <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
                <li id="nav_about"><a href="about">关于我们</a></li>
                <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
                <li id="nav_download"><a href="#contact">App下载</a></li>
            </ul>
            <form class="navbar-form navbar-right" id="loginForm">
                <div class="form-group">
                    <input type="text" placeholder="手机号码" class="form-control">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="密码" class="form-control">
                </div>

                <button type="button" class="btn btn-primary" id="loginSubmit">登录</button>
                <button type="button" class="btn btn-primary" id="registerSubmit">注册</button>
                <a href="#">忘记密码?</a>
            </form>
            <form class="navbar-form navbar-right hidden" id="userInfo">

                <span>欢迎你：</span>
                <a href="#">13541287474</a>
                <button type="submit" class="btn btn-primary" id="logoutBtn">安全退出</button>
                <%--<div class="form-group">--%>
                    <%--<input type="text" placeholder="手机号码" class="form-control">--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<input type="password" placeholder="密码" class="form-control">--%>
                <%--</div>--%>

                <%--<button type="submit" class="btn btn-primary" id="loginSubmit">登录</button>--%>
                <%--<button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">注册</button>--%>
                <%--<a href="#">忘记密码?</a>--%>
            </form>

            <%--<form class="navbar-form navbar-right">--%>
            <%--<div class="form-group">--%>
            <%--<a href="./">都是对的</a>--%>
            <%--</div>--%>

            <%--<button type="submit" class="btn btn-success">退出</button>--%>
            <%--</form>--%>

        </div><!--/.nav-collapse -->
    </div>
</nav>





<%--<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">--%>
    <%--<div class="modal-dialog modal-sm">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
                <%--<h4 class="modal-title" id="exampleModalLabel">用户注册</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<form class="form-horizontal" id="registerForm">--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="inputMobile" class="control-label">昵称:</label>--%>
                        <%--<input type="text" class="form-control required" id="inputNick" placeholder="昵称" data-toggle="tooltip" data-placement="bottom">--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="inputMobile" class="control-label">手机号码:</label>--%>
                        <%--<input type="text" class="form-control" id="inputMobile" placeholder="手机号码" data-toggle="tooltip" data-placement="bottom">--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label for="inputPassword" class="control-label">密码:</label>--%>
                        <%--<input type="password" id="inputPassword" class="form-control required" placeholder="密码" data-toggle="tooltip" data-placement="bottom" >--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="inputPasswordHit" class="control-label">确认密码:</label>--%>
                        <%--<input type="password" id="inputPasswordHit" class="form-control" placeholder="确认密码" data-toggle="tooltip" data-placement="bottom">--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="vilCoce" class="control-label">验证码:</label>--%>
                        <%--<div>--%>
                            <%--<div class="form-group col-sm-10">--%>
                                <%--<input type="text" id="vilCoce" class="form-control" placeholder="验证码">--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-2">--%>
                                <%--<button type="button" class="btn btn-primary" id="getValidateCode">获取验证码</button>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</form>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<button type="button" class="btn btn-primary" id="registerBtn">注册</button>--%>
                <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>


<div id="registerDialog" class="dialog_content" style="display:none">
    <div class="dialogModal_header">修改密码</div>
    <div class="dialogModal_content">
        <form class="form-horizontal">
            <form class="form-horizontal" id="registerForm">
                <div class="form-group">
                    <label for="inputMobile" class="control-label">昵称:</label>
                    <input type="text" class="form-control required" id="inputNick" placeholder="昵称" data-toggle="tooltip" data-placement="bottom">
                </div>
                <div class="form-group">
                    <label for="inputMobile" class="control-label">手机号码:</label>
                    <input type="text" class="form-control" id="inputMobile" placeholder="手机号码" data-toggle="tooltip" data-placement="bottom">
                </div>

                <div class="form-group">
                    <label for="inputPassword" class="control-label">密码:</label>
                    <input type="password" id="inputPassword" class="form-control required" placeholder="密码" data-toggle="tooltip" data-placement="bottom" >
                </div>
                <div class="form-group">
                    <label for="inputPasswordHit" class="control-label">确认密码:</label>
                    <input type="password" id="inputPasswordHit" class="form-control" placeholder="确认密码" data-toggle="tooltip" data-placement="bottom">
                </div>
                <div class="form-group">
                    <label for="vilCoce" class="control-label">验证码:</label>
                    <div>
                        <div class="form-group col-sm-10">
                            <input type="text" id="vilCoce" class="form-control" placeholder="验证码">
                        </div>
                        <div class="col-sm-2">
                            <button type="button" class="btn btn-primary" id="getValidateCode" onclick="getValidate(this);">获取验证码</button>
                        </div>
                    </div>
                </div>
            </form>
        </form>
    </div>
    <div class="dialogModal_footer">
        <button type="button" class="btn btn-primary" data-dialogModalBut="ok" id="registerBtn">注册</button>
        <button type="button" class="btn btn-default" data-dialogModalBut="cancel" data-dismiss="modal">关闭</button>
    </div>
</div>




<script type="text/javascript">
    var changeNav=function(id){
        $('ul > li[id!='+id+']').each(function(){
            if($(this).hasClass('active')){
                $(this).removeClass('active')
            }
        });
        $('#'+id).addClass('active');
    }
    $('#registerSubmit').click(function(){
        $('#registerDialog').dialogModal({
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
    var getValidate=function(button){
        var btn=$(button),btnText='重新获取',defText=btn.text();
        btn.attr('disabled','disabled');
        var i=60;
        var fn=function(){
            i--;
            btn.text(btnText+'('+i+')');
            if(i==0){
                clearInterval(inter);
                btn.removeAttr('disabled');
                btn.text(defText);
            }
        }
        var inter = setInterval(fn,1000)
    }



    $('#registerBtn').click(function(){
        if(!validateData($('#inputNick'))){
            return;
        }
        if(!isPhone($('#inputMobile'))){
            return;
        }

        if(!validatePassword($('#inputPassword'))){
            return;
        }
        if(!validatePasswordHit($('#inputPassword'),$('#inputPasswordHit'))){
            return;
        }
    });
    $('#loginSubmit').click(function(){
        console.log('sdfsdf')
        $('#loginForm').hide();
        $('#userInfo').removeClass('hidden');

    });
    $(function(){

    });
</script>