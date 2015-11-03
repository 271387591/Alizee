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
    var result=requestJSONData('html/channel/security/save',obj);
    if(result.success){
        reloadPage('html/security/channel');
    }else{
        alertError(result.message);
    }
}
var columns=[
    {
        name:'channelNo',
        renderer:function(v,rec){
            return '<a href="javascript:void(0);" onclick="edit('+rec.id+');">'+v+'</a>';
        }
    },
    {
        name:'name'
    },
    {
        name:'createDate',
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        name:'type',
        renderer:function(v){
            if(v==1){
                return '本系统内置';
            }else{
                return '外来渠道';
            }
        }
    },

    {
        width:60,
        renderer:function(v,rec){
            var ehi=(rec.type==1)?'hidden':'';
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="编辑" onclick="edit('+rec.id+')">'+
                '<i class="icon-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="red '+ehi+'" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,deleteUser)">'+
                '<i class="icon-trash bigger-130"></i>'+
                '</a>'+
                '</div>';
        }

    }
];
function listTable(params){

    $('#gameTable').htable({
        url:appPath+'html/channel/security/list',
        params: $.extend({},params),
        columns:columns,
        pager:$('#advertPaging')
    });
}
function deleteUser(id,modal){
    var ids=[];
    ids.push(id);
    var result=requestStringData('html/channel/security/delete/'+id);
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
        $('#mainContent').load(appPath+"html/channel/security/addChannel?id="+id);
    }else{
        $('#mainContent').load(appPath+"html/channel/security/addChannel")
    }
}