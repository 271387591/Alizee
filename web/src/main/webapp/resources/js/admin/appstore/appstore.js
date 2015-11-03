/**
 * Created by lihao1 on 6/20/15.
 */
function initPage(btn_choose1,btn_choose2){
    $('#activityPicture').ace_file_input({
        style:'well',
        btn_choose:btn_choose1,
        no_icon:'icon-cloud-upload',
        droppable:true,
        thumbnail:'small',
        before_change:function(files, dropped) {
            var allowed_files = [];
            if(allowed_files.length == 0) return false;

            return allowed_files;
        },
        preview_error : function(filename, error_code) {
        }

    }).on('change', function(){
        $('#exsitPicShow').hide();
    });
}
function saveAdvert(type,publish){
    var datas=$('#advertForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    if(!checkForm($('#advertForm'))){
        return
    }
    if($('#activityPicture').data('ace_input_files')==undefined){
        if(type=='save'){
            alertError('请选择附件');
            return
        }
    }
    $.ajaxFileUpload({
        url: appPath + 'html/appStore/security/upload',
        secureuri:false,
        fileElementId:['activityPicture'],
        dataType: 'text/html',
        data: obj,
        error: function() {
        },
        success: function(response) {
            var result=JSON.parse(response);
            if(result.success){
                reloadPage('html/security/appstore')
            }else{
                alertError(result.msg);
            }
        }
    });
}
function saveios(){
    var datas=$('#advertForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    if(!checkForm($('#advertForm'))){
        return
    }
    var result=requestStringData('html/appStore/security/saveios',obj);
    if(result.success){
        reloadPage('html/security/appstore?p=ios');
    }else{
        alertError(result.message)
    }
}
var nopcolumns=[
    {
        width:200,
        name:'version'
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
                return '<span class="label label-lg label-grey">否</span>';
            }
        }
    },
    {
        width:90,
        renderer:function(v,rec){
            var p=rec.plat;
            var pTitle="'发布活动'",pContent="'您确定要发布该活动?'";
            var d=(rec.enabled==1)?"hidden":"";
            var b=(rec.enabled==1)?"hidden":"";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="编辑" onclick="edit('+rec.id+','+p+')">'+
                '<i class="icon-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="red '+d+'" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,deleteUser);">'+
                '<i class="icon-trash bigger-130"></i>'+
                '</a>'+
                '</div>';
        }

    }
];

function listATable(params){
    $('#noPublishTable').htable({
        url:appPath+'html/appStore/list',
        params: $.extend({Q_plat_EQ:0},params),
        columns:nopcolumns,
        pager:$('#noPublishPaging')
    });
}
function listITable(params){
    $('#publishTable').htable({
        url:appPath+'html/appStore/list',
        params: $.extend({Q_plat_EQ:1},params),
        columns:nopcolumns,
        pager:$('#publishPaging')
    });
}

function deleteUser(id,modal){
    var ids=[];
    ids.push(id);
    var result=requestStringData('html/appStore/security/delete/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listATable();
        listITable();
    }else{
        alertError(result.message);
    }
    return true;
}
function edit(id,plat){
    if(id){
        reloadPage('html/appStore/security/addApp?plat='+plat+'&id='+id);
    }else{
        reloadPage('html/appStore/security/addApp?plat='+plat)
    }
}
