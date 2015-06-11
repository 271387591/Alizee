<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 6/10/15
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<form class="form-horizontal"  id="systemForm">
    <div id="systemContent">
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="companyName">公司名称</label>
            <div class="col-sm-11">
                <input type="text" name="config.companyName" value="${companyName}" id="companyName" placeholder="公司名称" class="col-xs-10 col-sm-5 required" data-toggle="tooltip" data-placement="right" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="companyInfo">公司简介</label>
            <div class="col-sm-11">
                <textarea name="config.companyInfo" class="col-xs-10 col-sm-5 autosize-transition limited required" maxlength="1000" id="companyInfo" placeholder="公司简介" data-toggle="tooltip" data-placement="right">${companyInfo}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="companyProduct">产品介绍</label>
            <div class="col-sm-11">
                <textarea name="config.companyProduct" class="col-xs-10 col-sm-5 autosize-transition limited required" maxlength="1000" id="companyProduct" placeholder="产品介绍" data-toggle="tooltip" data-placement="right">${companyProduct}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="companyPhone">电话</label>
            <div class="col-sm-11">
                <input type="text" name="config.companyPhone" id="companyPhone" value="${companyPhone}" placeholder="多个请用逗号隔开" class="col-xs-10 col-sm-5 required" data-toggle="tooltip" data-placement="right" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="companyMobile">手机</label>
            <div class="col-sm-11">
                <input type="text" name="config.companyMobile" id="companyMobile" value="${companyMobile}" placeholder="多个请用逗号隔开" class="col-xs-10 col-sm-5 required" data-toggle="tooltip" data-placement="right" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="companyEmail">邮件</label>
            <div class="col-sm-11">
                <input type="text" name="config.companyEmail" id="companyEmail" value="${companyEmail}" placeholder="多个请用逗号隔开" class="col-xs-10 col-sm-5 required" data-toggle="tooltip" data-placement="right" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="companyQQ">QQ</label>
            <div class="col-sm-11">
                <input type="text" name="config.companyQQ" id="companyQQ" value="${companyQQ}" placeholder="多个请用逗号隔开" class="col-xs-10 col-sm-5 required" data-toggle="tooltip" data-placement="right" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="companyAddress">地址</label>
            <div class="col-sm-11">
                <textarea name="config.companyAddress" class="col-xs-10 col-sm-5 autosize-transition limited required" maxlength="1000" id="companyAddress" placeholder="公司地址" data-toggle="tooltip" data-placement="right">${companyAddress}</textarea>
            </div>
        </div>
    </div>
    <div class="clearfix form-actions">
        <div class="col-md-offset-3 col-md-9">
            <button class="btn btn-info" type="button" id="saveBtn" data-toggle="tooltip" data-placement="right">
                <i class="icon-ok bigger-110"></i>
                保存
            </button>

            &nbsp; &nbsp; &nbsp;
            <button class="btn" type="reset">
                <i class="icon-undo bigger-110"></i>
                重置
            </button>
        </div>
    </div>


</form>
<script type="text/javascript">
    function loadSetting(){
        $('#systemForm').load(appPath+'html/security/applicationConfig/systemsetting');
    }
    jQuery(function(){
        $('textarea[class*=autosize]').autosize({append: "\n"});
        $('textarea.limited').inputlimiter({
            remText: '你还可以输入%n个字符',
            limitText: '最多输入%n字.'
        });
        $('#saveBtn').click(function(){
            if(checkForm($('#systemForm'))){
                $(this).attr('disabled','disabled');
                var datas=[];
                $('#systemForm').find("[name^='config']").each(function (i, o) {
                    var name = $(o).attr("name").split(".")[1];
                    var obj={};
                    obj.systemKey=name;
                    obj.systemValue=$(o).val();
                    datas.push(obj);
                });
                var result = requestData('html/security/applicationConfig/save',datas);
                if(result.success){
                    loadSetting()
                    alertSuccess('操作成功');
                }else{
                    alertError(result.message);
                }
                $(this).removeAttr('disabled');
            }
        });
    });

</script>







