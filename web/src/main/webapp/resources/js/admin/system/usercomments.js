/**
 * Created by lihao1 on 6/20/15.
 */

var columns=[
    {
        cls:'center',
        width:60,
        renderer:function(v){
            return '<label>'+
                '<input type="checkbox" class="ace" />'+
                '<span class="lbl"></span>'+
                '</label>';
        }

    },
    {
        name:'mobile',
        width:200,
        renderer:function(v,rec){
            return '<a href="javascript:void(0);" onclick="detail('+rec.id+');">'+v+'</a>';
        }
    },

    {
        name:'content',
        width:300
    },
    {
        name:'reply',
        width:300
    },
    {
        name:'createDate',
        width:170,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        name:'lastUpdateDate',
        width:170,
        renderer:function(v){
            if(v){
                return new Date(v).format("yyyy-MM-dd hh:mm:ss");
            }
        }
    },
    {
        width:100,
        renderer:function(v,rec){
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="回复" onclick="reply(['+rec.id+'])">'+
                '<i class="icon-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,deleteUser)">'+
                '<i class="icon-trash bigger-130"></i>'+
                '</a>'+
                '</div>';
        }

    }
];
function listTable(params){

    $('#gameTable').htable({
        url:appPath+'html/userComments/list',
        params: $.extend({},params),
        columns:columns,
        pager:$('#advertPaging')
    });
}
function reply(ids){
    $('#replyComments').modal('show');
    $('#replyComments').on('show.bs.modal',function(){
        $('#replyContent').val('');
    });
    //return;
    $('#saveReplyBtn').click(function(){
        if(!checkForm($('#replyComments'))){
            return;
        }
        var result=requestStringData('html/userComments/security/reply',{ids:ids.join(','),reply:$('#replyContent').val()});
        if(result.success){
            reloadPage('html/security/userComments');
        }else{
            alertError(result.message);
        }
    });
}
function batchReply(){
    var ids=$('#gameTable').htable('getSelected');
    if(ids.length<1){
        alertNotify('请选择数据');
        return;
    }
    reply(ids);
}
function deleteUser(id,modal){
    var ids=[];
    ids.push(id);
    var result=requestStringData('html/userComments/security/delete',{ids:ids.join(',')});
    if(result.success){
        alertSuccess('操作成功');
        listTable();
    }else{
        alertError(result.message);
    }
    return true;
}
function detail(id){
    ajaxReloadPage('mainContent','html/userComments/security/detail/'+id);
}
