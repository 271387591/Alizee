<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 6/19/15
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="breadcrumbs" id="breadcrumbs">
    <script type="text/javascript">
        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
    </script>
    <ul class="breadcrumb">
        <li>
            系统设置
        </li>
        <li><a href="<c:url value="/html/security/food"/> ">美食故事</a></li>
        <li class="active"><i class="icon-user"></i>${command.id==null?'添加美食':'编辑美食'}</li>
    </ul>
</div>



<div class="page-content">
    <div class="page-header">
        <h1>
            <i class="icon-dashboard">${command.id==null?'添加美食':'编辑美食'}</i>
        </h1>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <form class="form-horizontal" id="advertForm">
                <div class="form-group">
                    <input type="hidden" name="id" value="${command.id}" />
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right" for="companyName">标题</label>
                    <div class="col-sm-4">
                        <input type="text" name="title" value="${command.title}" id="companyName" placeholder="美食标题" data-validate="required" class="width-100" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right" for="companyInfo">故事简介</label>
                    <div class="col-sm-4">
                        <textarea name="description" class="width-100 autosize-transition limited" data-validate="required" maxlength="1000" id="companyInfo" placeholder="故事简介">${command.description}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right" for="companyPhone">美食图片</label>
                    <div class="col-sm-4">
                        <input multiple="" type="file" name="picName" id="companyPhone" />
                        <span class="lbl"></span>
                    </div>
                    <div class="col-sm-reset inline" style="color: #d16e6c">
                        <i class="icon-warning-sign">仅支持jpeg、jpg、gif、png格式的图片，且大小不能超过1M,请选择合适的图片上传</i>
                    </div>
                </div>
                <c:if test="${command.id!=null}">
                    <div class="form-group" id="exsitPicShow">
                        <label class="col-sm-1 control-label no-padding-right" for="companyPhone">预览图片</label>
                        <div class="col-sm-4">
                            <span><a target="_blank" href="${command.url}">${command.picName}</a></span>
                            <span class="lbl"></span>
                        </div>

                    </div>
                </c:if>
                <div class="clearfix form-actions">
                    <div class="col-md-offset-3 col-md-9">
                        <button class="btn btn-info" type="button" id="saveBtn" onclick="saveAdvert(${command.id!=null?"'edit'":"'save'"});" >
                            <i class="icon-ok bigger-110"></i>
                            保存
                        </button>
                        &nbsp; &nbsp; &nbsp;
                        <button class="btn" type="button" onclick="reloadPage('html/security/advert')">
                            <i class="icon-undo bigger-110"></i>
                            返回
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">


    jQuery(function(){
        $('textarea[class*=autosize]').autosize({append: "\n"});
        $('textarea.limited').inputlimiter({
            remText: '你还可以输入%n个字符',
            limitText: '最多输入%n字.'
        });
        $('#companyPhone').ace_file_input({
            style:'well',
            btn_choose:'${command.id!=null?command.picName:"拖拽或点击此区域上传文件"}',
            btn_change:null,
            no_icon:'icon-cloud-upload',
            droppable:true,
            thumbnail:'small',
            before_change:function(files, dropped) {
                var allowed_files = [];
                for(var i = 0 ; i < files.length; i++) {
                    var file = files[i];
                    if(file.size>1024*1024){
                        return allowed_files;
                    }
                    if(typeof file === "string") {
                        if(! (/\.(jpe?g|png|gif|bmp)$/i).test(file) ) return false;
                    }
                    else {
                        var type = $.trim(file.type);
                        if( ( type.length > 0 && ! (/^image\/(jpe?g|png|gif)$/i).test(type) )
                                || ( type.length == 0 && ! (/\.(jpe?g|png|gif)$/i).test(file.name) )
                        ) continue;
                    }

                    allowed_files.push(file);
                }
                if(allowed_files.length == 0) return false;

                return allowed_files;
            },
            preview_error : function(filename, error_code) {
            }

        }).on('change', function(){
            $('#exsitPicShow').hide();
        });
    });

</script>

