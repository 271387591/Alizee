/**
 * Created by lihao1 on 6/20/15.
 */
function initPage(btn_choose){
    $('textarea[class*=autosize]').autosize({append: "\n"});
    $('textarea.limited').inputlimiter({
        remText: '你还可以输入%n个字符',
        limitText: '最多输入%n字.'
    });
    $('#activityPicture').ace_file_input({
        style:'well',
        btn_choose:btn_choose,
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
}
function saveAdvert(type){
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
        url: appPath + 'html/goods/tenant/upload',
        secureuri:false,
        fileElementId:'activityPicture',
        dataType: 'text/html',
        data: obj,
        error: function() {
        },
        success: function(response) {
            var result=JSON.parse(response);
            if(result.success){
                ajaxReloadPage('mainContent','html/goods/tenant/goodsList');
                alertSuccess("保存成功");
            }else{
                alertError(result.msg);
            }
        }
    });
}
var columns=[
    {
        name:'name',
        substr:10,
        renderer:function(v,rec){
            return '<td><a href="javascript:void(0);" onclick="edit('+rec.id+');">'+v+'</a></td>';
        }
    },
    {
        name:'price'
    },
    {
        name:'num'
    },
    {
        name:'description',
        substr:10
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
        url:appPath+'html/goods/list',
        params: $.extend({},params),
        columns:columns,
        pager:$('#advertPaging')
    });
}
function deleteUser(id,modal){
    var ids=[];
    ids.push(id);
    var result=requestStringData('html/goods/tenant/delete',{ids:ids.join(',')});
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
        $('#mainContent').load(appPath+"html/goods/tenant/addGoods?id="+id);
    }else{
        $('#mainContent').load(appPath+"html/goods/tenant/addGoods")
    }
}
