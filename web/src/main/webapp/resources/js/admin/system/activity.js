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
    obj.publish=publish;
    $.ajaxFileUpload({
        url: appPath + 'html/activity/security/upload',
        secureuri:false,
        fileElementId:'activityPicture',
        dataType: 'text/html',
        data: obj,
        error: function() {
        },
        success: function(response) {
            var result=JSON.parse(response);
            if(result.success){
                reloadPage('html/security/activity')
            }else{
                alertError(result.msg);
            }
        }
    });
}
var nopcolumns=[
    {
        width:200,
        name:'title',
        renderer:function(v,rec){
            return '<a href="javascript:void(0);" onclick="detail('+rec.id+')">'+v+'</a>';
        }
    },
    {
        width:200,
        name:'merchant'
    },

    {
        width:200,
        name:'merchantPhone'
    },

    {
        width:140,
        name:'startDate',
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        name:'endDate',
        width:140,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
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
        width:90,
        renderer:function(v,rec){
            var pTitle="'发布活动'",pContent="'您确定要发布该活动?'";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="编辑" onclick="edit('+rec.id+')">'+
                '<i class="icon-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,deleteUser);">'+
                '<i class="icon-trash bigger-130"></i>'+
                '</a>'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="发布" onclick="createModal('+rec.id+','+pTitle+','+pContent+',publishAct);">'+
                '<i class="icon-bullhorn bigger-130"></i>'+
                '</a>'+
                '</div>';
        }

    }
];
var pucolumns=[
    {
        name:'title',
        width:200,
        renderer:function(v,rec){
            return '<a href="javascript:void(0);" onclick="detail('+rec.id+')">'+v+'</a>';
        }
    },
    {
        width:200,
        name:'merchant'
    },
    {
        width:200,
        name:'merchantPhone'
    },
    {
        width:140,
        name:'startDate',
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        name:'endDate',
        width:140,
        renderer:function(v){
            if(!v){
                v==null;
            }
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        name:'lastUpdateDate',
        width:140,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        width:100,
        name:'peoples'
    },
    {
        width:140,
        name:'endDate',
        renderer:function(v){
            if(new Date(v)<=new Date()){
                return '<span class="label label-lg label-grey">已结束</span>';
            }else{
                return '<span class="label label-lg label-success">正在进行</span>';
            }
        }
    },
    {
        width:40,
        renderer:function(v,rec){
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="结束活动" onclick="endActivity('+rec.id+')">'+
                '<i class="icon-ban-circle bigger-130"></i>'+
                '</a>'+
                '</div>';
        }

    }
];
var nopenddingColumns=[
    {
        name:'username'
    },
    {
        name:'nickName'
    },
    {
        name:'createDate',
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        width:100,
        renderer:function(v,rec){

            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="审核通过" onclick="pendding('+rec.id+','+rec.activityId+')">'+
                '<i class="icon-ok bigger-120"></i>'+
                '</a>'+
                '</div>';
        }

    }
];
var penddingColumns=[
    {
        name:'username'
    },
    {
        name:'nickName'
    },
    {
        name:'createDate',
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        name:'lastUpdateDate',
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },

    {
        width:100,
        renderer:function(v,rec){
            var pTitle="'取消资格'",pContent="'您确定要取消该用户的参加资格?'",params='{id:'+rec.id+',aId:'+rec.activityId+'}';
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="取消资格" onclick="createModal('+params+','+pTitle+','+pContent+',nopendding);">'+
                '<i class="icon-ban-circle bigger-130"></i>'+
                '</a>'+
                '</div>';
        }

    }
];

function listNoPubTable(params){
    $('#noPublishTable').htable({
        url:appPath+'html/activity/list',
        params: $.extend({Q_published_EQ:0},params),
        columns:nopcolumns,
        pager:$('#noPublishPaging')
    });
}
function listPubTable(params){
    $('#publishTable').htable({
        url:appPath+'html/activity/list',
        params: $.extend({Q_published_EQ:1},params),
        columns:pucolumns,
        pager:$('#publishPaging')
    });
}
function listNopendingTable(params){
    $('#noPenddingTable').htable({
        url:appPath+'html/activity/web/getActivityUsers',
        params: $.extend({'Q_a.status_EQ':0},params),
        columns:nopenddingColumns,
        pager:$('#noPenddingTablePage')
    });
}
function listPendingTable(params){
    $('#penddingTable').htable({
        url:appPath+'html/activity/web/getActivityUsers',
        params: $.extend({'Q_a.status_EQ':1},params),
        columns:penddingColumns,
        pager:$('#penddingTablePage')
    });
}
function pendding(id,aId){
    var result = requestStringData('html/activity/security/pendding/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listNopendingTable({id:aId});
    }else{
        alertError(result.message);
    }
}
function nopendding(params){
    var result = requestStringData('html/activity/security/nopendding/'+params.id);
    if(result.success){
        alertSuccess('操作成功');
        listPendingTable({id:params.aId});
    }else{
        alertError(result.message);
    }
}



function endActivity(id){
    var result=requestStringData('html/activity/security/endActivity/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listPubTable();
    }else{
        alertError(result.message);
    }
}
function publishAct(id){
    var result=requestStringData('html/activity/security/publish/'+id);
    if(result.success){
        alertSuccess('操作成功');
        reloadPage('html/security/activity');
    }else{
        alertError(result.message);
    }
}
function detail(id){
    ajaxReloadPage('mainContent','html/activity/security/activityDetail?id='+id);
}


function deleteUser(id,modal){
    var ids=[];
    ids.push(id);
    var result=requestStringData('html/activity/security/delete',{ids:ids.join(',')});
    if(result.success){
        alertSuccess('操作成功');
        listNoPubTable();
    }else{
        alertError(result.message);
    }
    return true;
}
function edit(id){
    if(id){
        $('#mainContent').load(appPath+"html/activity/security/addActivity?id="+id);
    }else{
        $('#mainContent').load(appPath+"html/activity/security/addActivity")
    }
}
