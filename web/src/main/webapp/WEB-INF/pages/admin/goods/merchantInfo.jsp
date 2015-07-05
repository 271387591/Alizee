<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/common/taglibs.jsp" %>
    <title>信息完善</title>
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
                        <i class="icon-dashboard">信息完善</i>
                    </h1>
                </div>
                <div class="col-xs-12">
                    <form class="form-horizontal" id="systemForm">
                        <div class="form-group">
                            <input type="hidden" name="id" value="${command.id}" />
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label no-padding-right"> 商家名称</label>
                            <div class="col-sm-4">
                                <input type="text" name="name" value="${command.name}" placeholder="商家名称" data-validate="required" class="width-100" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label no-padding-right"> 商家电话</label>
                            <div class="col-sm-4">
                                <input type="text" name="phone" value="${command.phone}" placeholder="商家电话" data-validate="required" class="width-100" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-1 control-label no-padding-right" for="companyInfo">商家地址</label>
                            <div class="col-sm-4">
                                <textarea name="address" class="width-100 autosize-transition limited" data-validate="required" maxlength="1000" id="companyInfo" placeholder="商家地址">${command.address}</textarea>
                            </div>
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
    changeNav('menu-merchant');
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
                var result = requestJSONData('html/merchant/tenant/save',obj);
                if(result.success){
                    reloadPage("html/tenant/merchantinfo")
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