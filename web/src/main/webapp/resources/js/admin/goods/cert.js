/**
 * Created by lihao1 on 6/20/15.
 */

var columns=[
    {
        name:'certNo'
    },
    {
        name:'name',
        width:300
    },
    {
        name:'price'
    },
    {
        name:'nickName'
    },
    {
        name:'mobile'
    },
    {
        name:'createDate',
        renderer:function(v){
            if(!v) return '';
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        name:'endDate',
        renderer:function(v){
            if(!v) return '';
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        name:'endDate',
        renderer:function(v,rec){
            if(rec.currentDate>v){
                return '<span class="label label-lg label-grey">已到期</span>';
            }else{
                return '<span class="label label-lg label-success">未过期</span>';
            }
        }
    },
    {
        renderer:function(v,rec){
            var pTitle="'商品兑换'",pContent="'您确定要将该该兑换券设为已兑换?'";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="设为已兑换" onclick="createModal('+rec.id+','+pTitle+','+pContent+',edit);">'+
                '<i class="icon-pencil bigger-130"></i>'+
                '</a>'+
                '</div>';
        }

    }
];

function listGTable(params){

    $('#gameTable').htable({
        url:appPath+'html/goodsCertificate/shop/list',
        params: $.extend({'Q_r.enabled_EQ':1},params),
        columns:columns,
        pager:$('#advertPaging')
    });
}
function edit(id){
    var result=requestJSONData('html/goodsCertificate/shop/edit/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listGTable();
    }else{
        alertError(result.message);
    }
}

var nocolumns=[
    {
        name:'certNo'
    },
    {
        name:'name',
        width:300
    },
    {
        name:'price'
    },
    {
        name:'nickName'
    },
    {
        name:'mobile'
    },
    {
        name:'createDate',
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        name:'endDate',
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    }
    ,
    {
        name:'lastUpdate',
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    }

];
function listNoTable(params){
    $('#perTable').htable({
        url:appPath+'html/goodsCertificate/shop/list',
        params: $.extend({'Q_r.enabled_EQ':0},params),
        columns:nocolumns,
        pager:$('#perPaging')
    });
}



