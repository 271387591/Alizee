<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 6/10/15
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="form-group">
    <input type="hidden" name="id" value="${command.id}" />
</div>
<div class="form-group">
    <label class="col-sm-1 control-label no-padding-right" for="companyName">公司名称</label>
    <div class="col-sm-4">
        <input type="text" name="companyName" value="${command.companyName}" id="companyName" placeholder="公司名称" data-validate="required" class="width-100" />
    </div>
</div>

<div class="form-group">
    <label class="col-sm-1 control-label no-padding-right" for="companyInfo">公司简介</label>
    <div class="col-sm-4">
        <textarea name="companyInfo" class="width-100 autosize-transition limited" data-validate="required" maxlength="1000" id="companyInfo" placeholder="公司简介">${command.companyInfo}</textarea>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-1 control-label no-padding-right" for="companyProduct">产品介绍</label>
    <div class="col-sm-4">
        <textarea name="companyProduct" class="width-100 autosize-transition limited" data-validate="required" maxlength="1000" id="companyProduct" placeholder="产品介绍" >${command.companyProduct}</textarea>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-1 control-label no-padding-right" for="companyPhone">电话</label>
    <div class="col-sm-4">
        <input type="text" name="companyPhone" id="companyPhone" value="${command.companyPhone}" data-validate="required" placeholder="多个请用逗号隔开" class="width-100"  />
    </div>
</div>
<div class="form-group">
    <label class="col-sm-1 control-label no-padding-right" for="companyMobile">手机</label>
    <div class="col-sm-4">
        <input type="text" name="companyMobile" id="companyMobile" value="${command.companyMobile}" data-validate="required" placeholder="多个请用逗号隔开" class="width-100"  />
    </div>
</div>
<div class="form-group">
    <label class="col-sm-1 control-label no-padding-right" for="companyEmail">邮件</label>
    <div class="col-sm-4">
        <input type="text" name="companyEmail" id="companyEmail" value="${command.companyEmail}" data-validate="required" placeholder="多个请用逗号隔开" class="width-100"  />
    </div>
</div>
<div class="form-group">
    <label class="col-sm-1 control-label no-padding-right" for="companyQQ">QQ</label>
    <div class="col-sm-4">
        <input type="text" name="companyQQ" id="companyQQ" value="${command.companyQQ}" data-validate="required" placeholder="多个请用逗号隔开" class="width-100"  />
    </div>
</div>
<div class="form-group">
    <label class="col-sm-1 control-label no-padding-right" for="companyAddress">地址</label>
    <div class="col-sm-4">
        <textarea name="companyAddress" class="width-100 autosize-transition limited" data-validate="required" maxlength="1000" id="companyAddress" placeholder="公司地址" >${command.companyAddress}</textarea>
    </div>
</div>
    <script type="text/javascript">
        jQuery(function(){
            $('textarea[class*=autosize]').autosize({append: "\n"});
            $('textarea.limited').inputlimiter({
                remText: '你还可以输入%n个字符',
                limitText: '最多输入%n字.'
            });
        });

    </script>








