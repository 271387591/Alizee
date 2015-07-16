/**
 * Created by lihao1 on 6/20/15.
 */
var sucColumns=[
    {
        width:200,
        name:'rechargeNo',
        renderer:function(v,rec){
            return '<a href="javascript:void(0);" onclick="detail('+rec.id+')">'+v+'</a>';
        }
    },
    {
        width:200,
        name:'mobile'
    },

    {
        width:200,
        name:'nickName'
    },

    {
        width:140,
        name:'money'
    },
    {
        name:'createDate',
        width:140,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    }
];
var failColumns=[
    {
        width:200,
        name:'rechargeNo',
        renderer:function(v,rec){
            return '<a href="javascript:void(0);" onclick="detail('+rec.id+')">'+v+'</a>';
        }
    },
    {
        width:200,
        name:'mobile'
    },

    {
        width:200,
        name:'nickName'
    },

    {
        width:140,
        name:'money'
    },
    {
        name:'createDate',
        width:140,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        name:'details',
        width:140
    }

];
function listSucTable(params){
    $('#rechargeSucTable').htable({
        url:appPath+'html/recharge/security/list',
        params: $.extend({'Q_a.status_EQ':1},params),
        columns:sucColumns,
        pager:$('#rechargeSucTablePage')
    });
}
function listFailTable(params){
    $('#rechargeFailTable').htable({
        url:appPath+'html/recharge/security/list',
        params: $.extend({'Q_a.status_EQ':2},params),
        columns:failColumns,
        pager:$('#rechargeFailTablePage')
    });
}
function detail(id){
    ajaxReloadPage('mainContent','html/recharge/security/detail/'+id);
}

