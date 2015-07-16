<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 6/4/15
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="sidebar sidebar-fixed" id="sidebar">
    <ul class="nav nav-list">
        <c:if test="${userinfo.roleName eq 'ROLE_ADMIN'}">
            <li class="active" id="menu-home">
                <a href="<c:url value="/html/security/about"/>">
                    <i class="icon-dashboard"></i>
                    <span class="menu-text"> 关于我们 </span>
                </a>
            </li>
            <li>
                <a href="#" class="dropdown-toggle">
                    <i class="icon-user"></i>
                    <span class="menu-text">用户模块</span>

                    <b class="arrow icon-angle-down"></b>
                </a>

                <ul class="submenu">
                    <li id="menu-user">
                        <a href="<c:url value="/html/security/user"/> ">
                            <i class="icon-double-angle-right"></i>
                            用户管理
                        </a>
                    </li>
                </ul>
            </li>
            <li id="menu-ui">
                <a href="#" class="dropdown-toggle">
                    <i class="icon-desktop"></i>
                    <span class="menu-text">系统设置</span>

                    <b class="arrow icon-angle-down"></b>
                </a>

                <ul class="submenu">
                    <li id="menu-advert">
                        <a href="<c:url value="/html/security/advert"/>">
                            <i class="icon-double-angle-right"></i>
                            广告管理
                        </a>
                    </li>
                    <li id="menu-food">
                        <a href="<c:url value="/html/security/food"/>">
                            <i class="icon-double-angle-right"></i>
                            美食故事
                        </a>
                    </li>
                    <li id="menu-activity">
                        <a href="<c:url value="/html/security/activity"/>">
                            <i class="icon-double-angle-right"></i>
                            活动管理
                        </a>
                    </li>
                    <li id="menu-userComments">
                        <a href="<c:url value="/html/security/userComments"/>">
                            <i class="icon-double-angle-right"></i>
                            用户反馈
                        </a>
                    </li>
                    <li id="menu-notice">
                        <a href="<c:url value="/html/security/notice"/>">
                            <i class="icon-double-angle-right"></i>
                            系统广播
                        </a>
                    </li>


                </ul>
            </li>
            <li id="menu-game">
                <a href="<c:url value="/html/security/games"/>">
                    <i class="icon-glass"></i>
                    <span class="menu-text"> 游戏管理 </span>
                </a>
            </li>
            <li id="menu-recharge">
                <a href="<c:url value="/html/security/recharge"/>">
                    <i class="icon-headphones"></i>
                    <span class="menu-text"> 充值查询 </span>
                </a>
            </li>

        </c:if>
        <c:if test="${userinfo.roleName eq 'ROLE_TENANT'}">
            <li class="active" id="menu-merchant">
                <a href="<c:url value="/html/tenant/merchantinfo"/>">
                    <i class="icon-dashboard"></i>
                    <span class="menu-text"> 信息完善 </span>
                </a>
            </li>
            <li class="active" id="menu-goods">
                <a href="<c:url value="/html/tenant/goods"/>">
                    <i class="icon-dashboard"></i>
                    <span class="menu-text"> 商品管理 </span>
                </a>
            </li>


        </c:if>



    </ul>

    <div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
    </div>

    <script type="text/javascript">
        try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
        var changeNav=function(id){
            $('ul > li[id!='+id+']').each(function(){
                if($(this).hasClass('active')){
                    $(this).removeClass('active')
                }
//                if($(this).parent().hasClass('submenu')){
//                    $(this).parent('ul').hide();
//                }
            });
            if($('#'+id).parent().hasClass('submenu')){
                $('#'+id).parent('ul').show();
            }
            $('#'+id).addClass('active');
        }
    </script>
</div>
