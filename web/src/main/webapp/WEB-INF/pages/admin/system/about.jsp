<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/common/taglibs.jsp" %>
    <title>关于我们</title>
</head>
<body class="navbar-fixed">
<%@include file="../header.jsp"%>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <%@include file="../menu.jsp"%>

        <div class="main-content">

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        <i class="icon-dashboard">关于我们</i>
                    </h1>
                </div>
                <div class="col-xs-12">
                    <form class="form-horizontal" id="systemForm">
                        <div id="systemContent">
                        </div>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <button class="btn btn-info" type="button" id="saveBtn" >
                                    <i class="icon-ok bigger-110"></i>
                                    保存
                                </button>
                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="button" onclick="clearForm($('#systemForm'))">
                                    <i class="icon-undo bigger-110"></i>
                                    重置
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>
<script type="text/javascript">
    $('#systemContent').load(appPath+'html/about/security/about');
    jQuery(function(){
        $('textarea[class*=autosize]').autosize({append: "\n"});
        $('textarea.limited').inputlimiter({
            remText: '你还可以输入%n个字符',
            limitText: '最多输入%n字.'
        });
        $('#saveBtn').click(function(){
            var form=$('#systemForm');
            if(checkForm(form)){
                $(this).attr('disabled','disabled');
                var datas=form.serializeArray();
                var obj={};
                for(var i=0;i<datas.length;i++){
                    obj[datas[i].name]=datas[i].value;
                }
                var result = requestJSONData('html/about/security/save',obj);
                if(result.success){
                    $('#systemContent').load(appPath+'html/about/security/about');
                    alertSuccess('操作成功');
                }else{
                    alertError(result.message);
                }
                $(this).removeAttr('disabled');
            }
        });
    });
</script>
</body>
</html>