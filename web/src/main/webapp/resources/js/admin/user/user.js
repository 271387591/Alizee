/**
 * Created by lihao1 on 6/20/15.
 */
function saveAdvert(type){

    if(!checkForm($('#userForm'))){
        return;
    }
    var datas=$('#userForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    obj.roles=[{id:obj.roleId}];
    var result = requestJSONData('html/user/security/save',obj);
    if(result.success){
        alertSuccess('保存成功');
        ajaxReloadPage('mainContent','html/user/security/userList');
    }else{
        alertError(result.message);
    }
}
function changeUserPwd(modal){
    var form=$('#changeUserPasswordModel').find('#updateUserPwd');
    if(!checkForm(form)){
        return;
    }
    var datas=form.serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    var result = requestJSONData('html/user/security/changeUserPwd',obj);
    if(result.success){
        alertSuccess('修改成功');
        ajaxReloadPage('mainContent','html/user/security/userList');
    }else{
        alertError(result.message);
    }
    modal.modal('hide');
}
function changeUserMobile(modal){
    var form=$('#changeUserMobileModel').find('#changeUserMobileForm');
    if(!checkForm(form)){
        return;
    }
    var datas=form.serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    var result = requestJSONData('html/user/security/changeUserMobile',obj);
    if(result.success){
        alertSuccess('修改成功');
        ajaxReloadPage('mainContent','html/user/security/userList');
    }else{
        alertError(result.message);
    }
    modal.modal('hide');
}
function changeUserMobile1(modal){
    var form=$('#changeUserMobileModel1').find('#changeUserMobileForm1');
    if(!checkForm(form)){
        return;
    }
    var datas=form.serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    var result = requestJSONData('html/user/security/changeUserMobile1',obj);
    if(result.success){
        alertSuccess('修改成功');
        ajaxReloadPage('mainContent','html/user/security/userList');
    }else{
        alertError(result.message);
    }
    modal.modal('hide');
}


var columns=[
    {
        width:200,
        name:'mobile',
        renderer:function(v,rec){
            return '<a href="#edit" onclick="edit('+rec.id+');">'+v+'</a>';
        }
    },
    {
        width:200,
        name:'nickName'
    },
    {
        width:80,
        name:'credits'
    },
    {
        width:200,
        name:'roles',
        renderer:function(v){
            if(!v){
                return '';
            }
            var roles=[];
            for(var i=0;i< v.length;i++){
                roles.push(v[i].displayName);
            }
            return roles.join(',');
        }
    },
    {
        name:'createDate',
        width:140,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        name:'enabled',
        width:140,
        renderer:function(v){
            if(v==1){
                return '<span class="label label-lg label-success">是</span>';
            }else{
                return '<span class="label label-lg label-grey"">否</span>';
            }

        }
    },
    {
        name:'channel'
    },

    {
        width:120,
        renderer:function(v,rec){
            var pTitle=(rec.enabled==1)?"'禁用用户'":"'启用用户'",pContent=(rec.enabled==1)?"'您确定要禁用该用户?'":"'您确定要启用该用户?'";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="编辑" onclick="edit('+rec.id+')">'+
                '<i class="icon-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="'+pTitle+'" onclick="createModal('+rec.id+','+pTitle+','+pContent+',removeUser);">'+
                '<i class="icon-trash bigger-130"></i>'+
                '</a>'+
                '<a class="green" href="#changeUserPasswordModel" data-rel="tooltip" userId="'+rec.id+'" title="修改密码" data-toggle="modal" data-target="#changeUserPasswordModel">'+
                '<i class="icon-edit bigger-130"></i>'+
                '</a>'+
                '<a class="blue" href="#changeUserMobileModel" userId="'+rec.id+'" mobile="'+rec.credits+'" data-rel="tooltip" title="修改游游币" data-toggle="modal" data-target="#changeUserMobileModel">'+
                '<i class="icon-user-md bigger-130"></i>'+
                '</a>'+
                    '<a class="blue" href="#changeUserMobileModel" userId="'+rec.id+'" mobile="'+rec.mobile+'" data-rel="tooltip" title="更换手机号" data-toggle="modal" data-target="#changeUserMobileModel1">'+
                '<i class="icon-phone bigger-130"></i>'+
                '</a>'+

                '</div>';
        }
    }
];
function listTable(params){
    $('#gameTable').htable({
        url:appPath+'html/user/security/list',
        params:$.extend({},params),
        columns:columns,
        pager:$('#paging')
    });

}
function removeUser(id,modal){
    var ids=[];
    ids.push(id);
    var result=requestStringData('html/user/security/delete',{ids:ids.join(',')});
    if(result.success){
        alertSuccess('操作成功');
        listTable();
    }else{
        alertError(result.message);
    }
    return true;
}
function edit(id){
    if(id){
        $('#mainContent').load(appPath+"html/user/security/addUser?id="+id);
    }else{
        $('#mainContent').load(appPath+"html/user/security/addUser")
    }
}
