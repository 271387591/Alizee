/**
 * Created by lihao1 on 6/20/15.
 */
function initPage(btn_choose1,btn_choose2){
    $('textarea[class*=autosize]').autosize({append: "\n"});
    $('textarea.limited').inputlimiter({
        remText: '你还可以输入%n个字符',
        limitText: '最多输入%n字.'
    });
    $('#activityPicture').ace_file_input({
        style:'well',
        btn_choose:btn_choose1,
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
    $('#activityLogoPicture').ace_file_input({
        style:'well',
        btn_choose:btn_choose2,
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
        $('#exsitPicLogoShow').hide();
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
        fileElementId:['activityPicture','activityLogoPicture'],
        dataType: 'text/html',
        data: obj,
        error: function() {
        },
        success: function(response) {
            var result=JSON.parse(response);
            if(result.success){
                //ajaxReloadPage('mainContent','html/goods/tenant/goodsList');
                reloadPage('html/tenant/goods')
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
        renderer:function(v,rec){
            return '<a href="javascript:void(0);" onclick="edit('+rec.id+');">'+v+'</a>';
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
        width:200
    },
    {
        name:'createDate',
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        renderer:function(v,rec){
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="编辑" onclick="edit('+rec.id+')">'+
                '<i class="icon-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" onclick="qiang('+rec.id+')" title="加入抢购商品">'+
                '<i class="icon-hand-up bigger-130"></i>'+
                '</a>'+
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,deleteUser)">'+
                '<i class="icon-trash bigger-130"></i>'+
                '</a>'+
                '</div>';
        }

    }
];

function listGTable(params){

    $('#gameTable').htable({
        url:appPath+'html/goods/tenant/list',
        params: $.extend({Q_purchase_EQ:0},params),
        columns:columns,
        pager:$('#advertPaging')
    });
}
var nocolumns=[
    {
        name:'name',
        width:200,
        renderer:function(v,rec){
            return '<a href="javascript:void(0);" onclick="edit('+rec.id+');">'+v+'</a>';
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
        width:200
    },
    {
        name:'createDate',
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    //{
    //    name:'fixedDate',
    //    renderer:function(v,rec){
    //        if(v){
    //            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
    //        }else{
    //            if(rec.trends){
    //                return rec.trends+'天';
    //            }
    //            return '';
    //        }
    //    }
    //},

    {
        renderer:function(v,rec){
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="编辑" onclick="edit('+rec.id+')">'+
                '<i class="icon-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="设为普通商品" onclick="noqiang('+rec.id+')">'+
                '<i class="icon-hand-down bigger-130"></i>'+
                '</a>'+

                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,deleteUser)">'+
                '<i class="icon-trash bigger-130"></i>'+
                '</a>'+
                '</div>';
        }

    }
];
function listNoTable(params){

    $('#perTable').htable({
        url:appPath+'html/goods/tenant/list',
        params: $.extend({Q_purchase_EQ:1},params),
        columns:nocolumns,
        pager:$('#perPaging')
    });
}

function deleteUser(id,modal){
    var ids=[];
    ids.push(id);
    var result=requestStringData('html/goods/tenant/delete',{ids:ids.join(',')});
    if(result.success){
        alertSuccess('操作成功');
        listGTable();
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
function qiang(id){
    //$('#qiangGoodsModel').on('show.bs.modal', function (event) {
    //    clearForm($('#duihuanTimeDiv form'));
    //    clearForm($('#duihuanDyTimeDiv form'));
    //    $('#certificateEnddateId').val(id);
    //    $('#certificateDyEnddateId').val(id);
    //});
    //$('#qiangGoodsModel').modal('show');
    //var sss=requestJSONData('html/certificate/list');
    //if(sss && sss.data.length>1){
    //    var result=requestJSONData('html/goods/tenant/qiang/'+id);
    //    if(result.success){
    //        alertSuccess('操作成功');
    //        listGTable();
    //    }else{
    //        alertError(result.message);
    //    }
    //}else{
    //    alertError('系统还未配置兑换券信息，不能加入抢购');
    //}
    var result=requestJSONData('html/goods/tenant/qiang/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listGTable();
    }else{
        alertError(result.message);
    }

}
function saveQiang(modal){
    var rv = $('input[name=duihuan]:checked').val();
    var fixedDate,trends,id;
    if(rv==1){
        if(!checkForm($('#duihuanTimeDiv form'))){
            return;
        }
        fixedDate=$('#certificateEnddate').val();
        id=$('#certificateEnddateId').val();
    }else{
        if(!checkForm($('#duihuanDyTimeDiv form'))){
            return;
        }
        trends=$('#certificateDyEnddate').val();
        id=$('#certificateDyEnddateId').val();
    }
    var obj={};
    obj.id=id;
    obj.fixedDateStr=fixedDate;
    obj.trends=trends;
    var result=requestJSONData('html/goods/tenant/saveQiang',obj);
    if(result.success){
        alertSuccess('操作成功');
        listGTable();
        //modal.modal('hide');
    }else{
        alertError(result.message);
    }
}
function noqiang(id){
    var result=requestJSONData('html/goods/tenant/qiang/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listNoTable();
    }else{
        alertError(result.message);
    }
}

