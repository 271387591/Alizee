/**
 * Created by lihao1 on 6/20/15.
 */

var columns=[
    {
        name:'createDate',
        width:170,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        name:'content'
    },
    {
        width:100,
        renderer:function(v,rec){
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,deleteUser)">'+
                '<i class="icon-trash bigger-130"></i>'+
                '</a>'+
                '</div>';
        }

    }
];
function listTable(params){
    $('#gameTable').htable({
        url:appPath+'html/notice/list',
        params: $.extend({},params),
        columns:columns,
        pager:$('#advertPaging')
    });
}
function edit(ids){
    $('#noticeModal').modal('show');
    $('#noticeModal').on('show.bs.modal',function(){
        $('#noticeContent').val('');
    });
    $('#noticeBtn').click(function(){
        if(!checkForm($('#noticeModal'))){
            return;
        }
        var obj={};
        obj.content=$('#noticeContent').val();
        var result=requestJSONData('html/notice/security/save',obj);
        if(result.success){
            reloadPage('html/security/notice');
        }else{
            alertError(result.message);
        }
    });
}

function deleteUser(id,modal){
    var ids=[];
    ids.push(id);
    var result=requestStringData('html/notice/security/delete',{ids:ids.join(',')});
    if(result.success){
        alertSuccess('操作成功');
        listTable();
    }else{
        alertError(result.message);
    }
    return true;
}

