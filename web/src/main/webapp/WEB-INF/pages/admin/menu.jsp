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
        <li class="active" id="menu-home">
            <a href="<c:url value="home"/>">
                <i class="icon-dashboard"></i>
                <span class="menu-text"> 系统设置 </span>
            </a>
        </li>

        <li id="menu-table">
            <a href="<c:url value="table"/> ">
                <i class="icon-text-width"></i>
                <span class="menu-text"> 表格 </span>
            </a>
        </li>

        <li id="menu-ui">
            <a href="#" class="dropdown-toggle">
                <i class="icon-desktop"></i>
                <span class="menu-text"> UI 组件 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="elements.html">
                        <i class="icon-double-angle-right"></i>
                        组件
                    </a>
                </li>

                <li>
                    <a href="buttons.html">
                        <i class="icon-double-angle-right"></i>
                        按钮 &amp; 图表
                    </a>
                </li>

                <li>
                    <a href="treeview.html">
                        <i class="icon-double-angle-right"></i>
                        树菜单
                    </a>
                </li>

                <li>
                    <a href="jquery-ui.html">
                        <i class="icon-double-angle-right"></i>
                        jQuery UI
                    </a>
                </li>

                <li>
                    <a href="nestable-list.html">
                        <i class="icon-double-angle-right"></i>
                        可拖拽列表
                    </a>
                </li>

                <li>
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-double-angle-right"></i>

                        三级菜单
                        <b class="arrow icon-angle-down"></b>
                    </a>

                    <ul class="submenu">
                        <li>
                            <a href="#">
                                <i class="icon-leaf"></i>
                                第一级
                            </a>
                        </li>

                        <li>
                            <a href="#" class="dropdown-toggle">
                                <i class="icon-pencil"></i>

                                第四级
                                <b class="arrow icon-angle-down"></b>
                            </a>

                            <ul class="submenu">
                                <li>
                                    <a href="#">
                                        <i class="icon-plus"></i>
                                        添加产品
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <i class="icon-eye-open"></i>
                                        查看商品
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
        </li>


    </ul><!-- /.nav-list -->

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
            });
            $('#'+id).addClass('active');
        }
    </script>
</div>
