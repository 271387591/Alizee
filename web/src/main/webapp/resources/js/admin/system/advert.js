/**
 * Created by lihao1 on 6/20/15.
 */
function saveAdvert(type){
    var datas=$('#advertForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    if(!checkForm($('#advertForm'))){
        return
    }
    if($('#companyPhone').data('ace_input_files')==undefined){
        if(type=='save'){
            alertError('请选择附件');
            return
        }
    }
    $.ajaxFileUpload({
        url: appPath + 'html/advert/security/upload',
        secureuri:false,
        fileElementId:'companyPhone',
        dataType: 'text/html',
        data: obj,
        error: function() {
        },
        success: function(response) {
            var result=JSON.parse(response);
            if(result.success){
                reloadPage('html/security/advert')
            }else{
                alertError(result.msg);
            }
        }
    });
}
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
        name:'title',
        renderer:function(v,rec){
            return '<td><a href="javascript:void(0);" onclick="edit('+rec.id+');">'+v+'</a></td>';
        }
    },
    {
        name:'url',
        renderer:function(v,rec){
            return '<td><a target="_blank" href="'+v+'">'+rec.picName+'</a></td>';
        }
    },
    {
        name:'description',
        substr:true
    },
    {
        name:'enabled',
        renderer:function(v){
            if(v){
                return '<td><span class="label label-lg label-success">激活</span></td>';
            }else{
                return '<td><span class="label label-lg label-grey"">未激活</span></td>';
            }

        }
    },
    {
        name:'createDate',
        renderer:function(v){
            return '<td>'+new Date(v).format("yyyy-MM-dd hh:mm:ss")+'</td>';
        }
    },
    {
        renderer:function(v,rec){
            return '<td><div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="编辑" onclick="edit('+rec.id+')">'+
                '<i class="icon-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,deleteUser)">'+
                '<i class="icon-trash bigger-130"></i>'+
                '</a>'+
                '</div></td>';
        }

    }
];
function listTable(params){

    $('#gameTable').htable({
        url:appPath+'html/advert/list',
        params: $.extend({},params),
        columns:columns,
        pager:$('#advertPaging')
    });
}
function deleteUser(id,modal){
    var ids=[];
    ids.push(id);
    var result=requestStringData('html/advert/security/delete',{ids:ids.join(',')});
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
        $('#mainContent').load(appPath+"html/advert/security/addAdvert?id="+id);
    }else{
        $('#mainContent').load(appPath+"html/advert/security/addAdvert")
    }
}
function batchDelete(){
    var ids=$('#gameTable').htable('getSelected');
    if(ids.length<1){
        alertNotify('请选择数据');
        return;
    }
    createDeleteModal(ids,'您确定要删除选中的数据？',function(){
        var result=requestStringData('html/advert/security/delete',{ids:ids.join(',')});
        if(result.success){
            alertSuccess('操作成功');
            listTable();
        }else{
            alertError(result.message);
        }
    });

}