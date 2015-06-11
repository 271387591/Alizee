function alertSuccess(msg){
    jSuccess(msg,{
        // autoHide : true,       // 是否自动隐藏提示条
        clickOverlay : false,  // 是否单击遮罩层才关闭提示条
        MinWidth : 200,        // 最小宽度
        TimeShown : 1500,      // 显示时间：毫秒
        ShowTimeEffect : 200,  // 显示到页面上所需时间：毫秒
        HideTimeEffect : 200,  // 从页面上消失所需时间：毫秒
        LongTrip : 15,         // 当提示条显示和隐藏时的位移
        HorizontalPosition : "center",// 水平位置:left, center, right
        VerticalPosition : "top",// 垂直位置：top, center, bottom
        ShowOverlay : false,     // 是否显示遮罩层
        ColorOverlay : "#000",  // 设置遮罩层的颜色
        OpacityOverlay : 0.3   // 设置遮罩层的透明度

    });
}
function alertError(msg){
    jError(msg,{
        HorizontalPosition : "center",
        VerticalPosition : "center"
    });
}
function alertNotify(msg){
    jNotify(msg,{
        HorizontalPosition : "center",
        VerticalPosition : "center"
    });
}




function requestData(url, o) {
    var result = null;
    $.ajax({
        async: false,
        url: appPath + url,
        dataType: 'json',
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(o),
        cache: false,
        type: "POST",
        success: function (data) {
            result = data;
        },
        fail: function (a, b, c) {
            alert('请求出错了');
        }
    });
    return result;
}
function requestDataForString(url, o) {
    var result = null;
    $.ajax({
        async: false,
        url: appPath+url,
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(o),
        cache: false,
        type: "POST",
        success: function (data) {
            result = data;
        },
        fail: function (a, b, c) {
            art.dialog.tips('请求出错了');
        }
    });
    return result;
}


function findPage(url, param) {
    param= $.extend(param,{pageSize:5})
    var list = requestData(url, param);
    if ((!list.objects || list.objects.length == 0) && list.pageBean.totalRecords) {
        param.pageIndex = param.pageIndex - 1;
        list = requestData(url, param);
    }

    return list;
}


function initComboData(url, param, combo, keyStr, valueStr) {
    var result = [];
    var list = requestData(url, param);
    //combo.find("option[value!=-1]").remove();
    combo.find("option").remove();
    combo.append("<option value=''>请选择</option>");
    if (list && list.objects && list.objects.length > 0) {
        var objects = result = list.objects;
        for (var i = 0; i < objects.length; i++) {
            var comboData = objects[i];
            combo.append('<option value="' + comboData[keyStr] + '">' + comboData[valueStr] + '</option>')
        }
    }
    return result;
}


function submitAjaxForm(form, objectName, url, callback) {
    var params = {};
    form.find("[name^='" + objectName + "']").each(function (i, o) {
        var name = $(o).attr("name").split(".")[1];
        params[name] = $(o).val();
    });
    callback(requestData(url, params));
}
function checkForm(form) {
    var valid = true;
    form.find("input[type='text'],input[type='password'],select,textarea").each(function (i, o) {
        if (!validateData($(o))) {
            valid = false;
        }
    });
    return valid;
}
function validateData(obj) {
    var v = obj.val();
    if(obj.hasClass('required')){
        if(!v){
            validFailure(obj, '不能为空');
            return false;
        }else{
            validSuccess(obj);
        }
    }
    return true;
}
function validatePassword(obj){
    if(!/^[0-9a-zA-Z]{6,16}$/.test(obj.val())){
        validFailure(obj,'请输入6-16位数字或字母组成的密码,不含下划线和特殊字符');
        return false;
    }
    validSuccess(obj)
    return true;
}
function validatePasswordHit(pas,hit){
    if(pas.val()!=hit.val()){
        validFailure(hit,'两次密码输入不一致');
        return false;
    }
    validSuccess(hit)
    return true;
}

function validFailure(obj, title) {
    obj.tooltip({
        title: title
    });
    obj.tooltip('show');
    obj.addClass('text-required');
}
function validSuccess(obj) {
    obj.removeClass('text-required');
    obj.tooltip('destroy');
}
function isPhone(obj) {    //移动电话
    if(!(/^1[3|4|5|8][0-9]\d{8}$/.test(obj.val()))){
        validFailure(obj,'请输入合法的手机号码');
        return false;
    }
    validSuccess(obj)
    return true;
}
function isEmail(email) {   //邮件
    var regEmail = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    return regEmail.exec(email);

}

function isFixedNumber(number) {  //固定电话
    //var regPhone = /^\d{3,4}-\d{7,8}(-\d{3,4})?$/;
    //return regPhone.exec(number);
    return true;

}
function isPostCode(number) {//邮编
    var postCode = /^[1-9]\d{5}$/;
    return postCode.exec(number);

}
function mobileAlert(title,text,callback){
    $('<div>').simpledialog2({
        mode: 'button',
        headerText: title,
        headerClose: false,
        buttonPrompt: text,
        zindex:10000,
        buttons : {
            '确定': {
                click: callback ||function () {
                },
                //icon: "delete",
                theme: "c"
            }
        }
    });
}
