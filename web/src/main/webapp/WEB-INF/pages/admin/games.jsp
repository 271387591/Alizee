<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/common/taglibs.jsp" %>
    <title>游戏管理</title>
</head>
<body class="navbar-fixed">
<%@include file="header.jsp"%>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>
        <%@include file="menu.jsp"%>
        <div class="main-content">
            <div class="page-content">
                <div class="page-header">
                    <h1>
                        <i class="icon-dashboard">游戏管理</i>
                    </h1>
                </div>
                <div class="row">
                    <div class="col-xs-12">

                        <div class="table-header">
                            游戏列表
                        </div>
                        <div class="table-bordered">
                            <button class="btn btn-sm btn-primary" id="addBtn"><i class="icon-angle-down"></i>添加</button>
                            <button class="btn btn-sm btn-success" id="addBtn1">添加1</button>

                        </div>
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover" style="margin-bottom: 2px;" id="gameTable">
                                <thead>
                                <tr>
                                    <th class="center">
                                        <label>
                                            <input type="checkbox" class="ace" />
                                            <span class="lbl"></span>
                                        </label>
                                    </th>
                                    <th>姓名</th>
                                    <th>年龄</th>
                                    <th>年龄1</th>
                                    <th>年龄2</th>
                                    <th>年龄3</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div class="table-bottom">
                            <div class="pull-left"><button style="margin-top: 2px;margin-left: 2px" class="btn btn-sm btn-success" id="addBtn1">添加1</button></div>
                            <div id="paging1" class="page">
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
</div><!-- /.main-container -->
<script type="text/javascript">
    function deleteUser(id,modal){
        console.log(id);
        alertError('sdsfdsf');
//        modal.modal('hidden');
        return true;

    }

    jQuery(function($) {
        changeNav('menu-games');

        var columns=[
            {
                renderer:function(v){
                    return '<td class="center"><label>'+
                            '<input type="checkbox" class="ace" />'+
                            '<span class="lbl"></span>'+
                            '</label></td>';
                }
            },
            {
                name:'name',
                renderer:function(v){
                    return '<td><a href="#">'+v+'</a></td>';
                }
            },
            {
                name:'age'
            },
                {
                name:'age'
            },
                {
                name:'age'
            },
                {
                name:'age'
            },

            {
                renderer:function(v,rec){
                    return '<td><div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                            '<a class="blue" href="javascript:void(0);" data-rel="tooltip" title="查看">'+
                            '<i class="icon-zoom-in bigger-130"></i>'+
                            '</a>'+

                            '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="编辑" onclick="createDeleteModal('+rec.id+',deleteUser)">'+
                            '<i class="icon-pencil bigger-130"></i>'+
                            '</a>'+

                            '<a class="red" href="#" data-rel="tooltip" title="删除">'+
                            '<i class="icon-trash bigger-130"></i>'+
                            '</a>'+
                            '</div></td>';
                }

            }

        ];
        var htable = $('#gameTable').htable({
            url:appPath+'html/games/security/list',
            params:{},
            columns:columns,
            pager:$('#paging1')
        });
        $('#addBtn').click(getValues)
        function getValues(){
            var data = $('#gameTable').htable('getSelected');
            console.log(data);
        }

//        htable.getSelected();
    });

</script>

</body>
</html>