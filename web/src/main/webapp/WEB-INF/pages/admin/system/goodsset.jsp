<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/common/taglibs.jsp" %>
    <title>商品抢购</title>
</head>
<body class="navbar-fixed">
<%@include file="../header.jsp"%>

<div class="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>
        <%@include file="../menu.jsp"%>
        <div class="main-content" id="mainContent">
            <div class="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>
                <ul class="breadcrumb">
                    <li>
                        商品抢购
                    </li>
                </ul>
            </div>
            <div class="page-content">
                <div class="page-header">
                    <h1>
                        <i class="icon-dashboard">商品抢购规则</i>
                    </h1>
                </div>
                <div class="row">
                    <div class="col-xs-2">
                        <div class="radio">
                            <label>
                                <c:if test="${command.type== 1 || command.type==null}">
                                    <input name="purchaseRadio" type="radio" class="ace" value="1" checked="checked" />
                                </c:if>
                                <c:if test="${command.type== 2}">
                                    <input name="purchaseRadio" type="radio" class="ace" value="1" />
                                </c:if>
                                <span class="lbl">按固定时间</span>
                            </label>
                        </div>
                    </div>
                    <div class="col-xs-2">
                        <div class="radio">
                            <label>
                                <c:if test="${command.type== 2}">
                                    <input name="purchaseRadio" type="radio" class="ace" value="2" checked="checked" />
                                </c:if>
                                <c:if test="${command.type== 1||command.type==null}">
                                    <input name="purchaseRadio" type="radio" class="ace" value="2" />
                                </c:if>
                                <span class="lbl">按周期时间</span>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="row ${command.type==2?"hidden":""}" id="purchaseTimeDiv">
                    <div class="col-xs-12">
                        <form class="form-horizontal" id="advertForm">
                            <div class="form-group">
                                <label class="col-sm-1 control-label no-padding-right">开始时间</label>
                                <div class="col-sm-4">
                                    <span class="input-icon input-icon-right">
                                            <input class="width-100" name="startDate" id="startDate" type="text" data-validate="required" value="${command.startDate}" readonly onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})"/>
                                            <i class="icon-calendar green"></i>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label no-padding-right">结束时间</label>
                                <div class="col-sm-4">
                                    <span class="input-icon input-icon-right">
                                        <input class="width-100" name="endDate" id="endDate" type="text" data-validate="required" value="${command.endDate}" readonly onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})"/>
                                        <i class="icon-calendar green"></i>
                                    </span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row ${(command.type==1 || command.type==null)?"hidden":""}" id="purchasePeriodDiv">
                    <div class="col-xs-12">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <table class="detail-table2 table table-bordered content-table" id="answerTable">
                                        <tbody>
                                        <tr class="tr-tit">
                                            <th class="hidden">1</th>
                                            <th class="text-center">每天开始时间(时)</th>
                                            <th class="text-center">每天结束时间(时)</th>
                                            <th class="text-center">删除</th>
                                        </tr>
                                        <tr class="tr-tit text-center">
                                            <td style="font-size: 16px;" colspan="3"><a href="javascript:void(0);" onclick="addTableTr($('#answerTable'),$(this));" style="padding: 5px 0;"><i class="icon icon-plus bigger-130 green"></i>继续添加</a></td>
                                        </tr>
                                        <c:forEach var="g" items="${command.goods}">
                                            <tr class="tr-data">
                                                <td class="text-center">
                                                    <select name="periodStart" class="form-control">
                                                        <option value="0" ${g.startDate==0?'selected="selected"':''}>00:00</option>
                                                        <option value="1" ${g.startDate==1?'selected="selected"':''}>01:00</option>
                                                        <option value="2" ${g.startDate==2?'selected="selected"':''}>02:00</option>
                                                        <option value="3" ${g.startDate==3?'selected="selected"':''}>03:00</option>
                                                        <option value="4" ${g.startDate==4?'selected="selected"':''}>04:00</option>
                                                        <option value="5" ${g.startDate==5?'selected="selected"':''}>05:00</option>
                                                        <option value="6" ${g.startDate==6?'selected="selected"':''}>06:00</option>
                                                        <option value="7" ${g.startDate==7?'selected="selected"':''}>07:00</option>
                                                        <option value="8" ${g.startDate==8?'selected="selected"':''}>08:00</option>
                                                        <option value="9" ${g.startDate==9?'selected="selected"':''}>09:00</option>
                                                        <option value="10" ${g.startDate==10?'selected="selected"':''}>10:00</option>
                                                        <option value="11" ${g.startDate==11?'selected="selected"':''}>11:00</option>
                                                        <option value="12" ${g.startDate==12?'selected="selected"':''}>12:00</option>
                                                        <option value="13" ${g.startDate==13?'selected="selected"':''}>13:00</option>
                                                        <option value="14" ${g.startDate==14?'selected="selected"':''}>14:00</option>
                                                        <option value="15" ${g.startDate==15?'selected="selected"':''}>15:00</option>
                                                        <option value="16" ${g.startDate==16?'selected="selected"':''}>16:00</option>
                                                        <option value="17" ${g.startDate==17?'selected="selected"':''}>17:00</option>
                                                        <option value="18" ${g.startDate==18?'selected="selected"':''}>18:00</option>
                                                        <option value="19" ${g.startDate==19?'selected="selected"':''}>19:00</option>
                                                        <option value="20" ${g.startDate==20?'selected="selected"':''}>20:00</option>
                                                        <option value="21" ${g.startDate==21?'selected="selected"':''}>21:00</option>
                                                        <option value="22" ${g.startDate==22?'selected="selected"':''}>22:00</option>
                                                        <option value="23" ${g.startDate==23?'selected="selected"':''}>23:00</option>
                                                    </select>
                                                </td>
                                                <td class="text-center">
                                                    <select name="periodEnd" class="form-control">
                                                        <option value="0" ${g.endDate==0?'selected="selected"':''}>00:00</option>
                                                        <option value="1" ${g.endDate==1?'selected="selected"':''}>01:00</option>
                                                        <option value="2" ${g.endDate==2?'selected="selected"':''}>02:00</option>
                                                        <option value="3" ${g.endDate==3?'selected="selected"':''}>03:00</option>
                                                        <option value="4" ${g.endDate==4?'selected="selected"':''}>04:00</option>
                                                        <option value="5" ${g.endDate==5?'selected="selected"':''}>05:00</option>
                                                        <option value="6" ${g.endDate==6?'selected="selected"':''}>06:00</option>
                                                        <option value="7" ${g.endDate==7?'selected="selected"':''}>07:00</option>
                                                        <option value="8" ${g.endDate==8?'selected="selected"':''}>08:00</option>
                                                        <option value="9" ${g.endDate==9?'selected="selected"':''}>09:00</option>
                                                        <option value="10" ${g.endDate==10?'selected="selected"':''}>10:00</option>
                                                        <option value="11" ${g.endDate==11?'selected="selected"':''}>11:00</option>
                                                        <option value="12" ${g.endDate==12?'selected="selected"':''}>12:00</option>
                                                        <option value="13" ${g.endDate==13?'selected="selected"':''}>13:00</option>
                                                        <option value="14" ${g.endDate==14?'selected="selected"':''}>14:00</option>
                                                        <option value="15" ${g.endDate==15?'selected="selected"':''}>15:00</option>
                                                        <option value="16" ${g.endDate==16?'selected="selected"':''}>16:00</option>
                                                        <option value="17" ${g.endDate==17?'selected="selected"':''}>17:00</option>
                                                        <option value="18" ${g.endDate==18?'selected="selected"':''}>18:00</option>
                                                        <option value="19" ${g.endDate==19?'selected="selected"':''}>19:00</option>
                                                        <option value="20" ${g.endDate==20?'selected="selected"':''}>20:00</option>
                                                        <option value="21" ${g.endDate==21?'selected="selected"':''}>21:00</option>
                                                        <option value="22" ${g.endDate==22?'selected="selected"':''}>22:00</option>
                                                        <option value="23" ${g.endDate==23?'selected="selected"':''}>23:00</option>
                                                    </select>
                                                </td>

                                                <td class="text-center">
                                                    <a title="删除" class="icon-action" onclick="removeTableTr(this);" href="javascript:void(0)"><i class="icon icon-remove red bigger-160"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="clearfix form-actions">
                    <div class="col-md-offset-3 col-md-9">
                        <button class="btn btn-info" type="button" id="saveBtn" onclick="saveGoods(${command.id!=null?"'edit'":"'save'"},'N');" >
                            <i class="icon-ok bigger-110"></i>
                            保存
                        </button>
                        &nbsp; &nbsp; &nbsp;

                        <button class="btn" type="button" onclick="reloadPage('html/security/activity');">
                            <i class="icon-undo bigger-110"></i>
                            返回
                        </button>
                    </div>
                </div>

                <div class="page-header hidden">
                    <h1>
                        <i class="icon-dashboard">兑换券失效设置</i>
                    </h1>
                </div>
                <div class="row hidden">
                    <div class="col-xs-2">
                        <div class="radio">
                            <label>
                                <c:if test="${command.certificate.type== 1 || command.certificate.type==null}">
                                    <input name="duihuan" type="radio" class="ace" value="1" checked="checked" />
                                </c:if>
                                <c:if test="${command.certificate.type== 2}">
                                    <input name="duihuan" type="radio" class="ace" value="1" />
                                </c:if>
                                <span class="lbl">按固定时间</span>
                            </label>
                        </div>
                    </div>
                    <div class="col-xs-2">
                        <div class="radio">
                            <label>
                                <c:if test="${command.certificate.type== 2}">
                                    <input name="duihuan" type="radio" class="ace" value="2" checked="checked" />
                                </c:if>
                                <c:if test="${command.certificate.type== 1 || command.certificate.type==null}">
                                    <input name="duihuan" type="radio" class="ace" value="2" />
                                </c:if>
                                <span class="lbl">按动态时间</span>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="hidden row ${command.certificate.type==2?"hidden":""}" id="duihuanTimeDiv">
                    <div class="col-xs-12">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-1 control-label no-padding-right">失效时间</label>
                                <div class="col-sm-4">
                                    <span class="input-icon input-icon-right">
                                            <input class="width-100" name="certificateEnddate" id="certificateEnddate" type="text" data-validate="required" value="${command.certificate.endDate}" readonly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})"/>
                                            <i class="icon-calendar green"></i>
                                    </span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="hidden row ${(command.certificate.type==1 || command.certificate==null)?"hidden":""}" id="duihuanDyTimeDiv">
                    <div class="col-xs-12">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-1 control-label no-padding-right">从购买时间起</label>
                                <div class="col-sm-4">
                                    <span class="input-icon input-icon-right">
                                            <input class="width-100" id="certificateDyEnddate" name="certificateDyEnddate" type="text" data-validate="required" value="${command.certificate.endDate}" />

                                    </span>
                                    <span>天失效</span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="hidden clearfix form-actions">
                    <div class="col-md-offset-3 col-md-9">
                        <button class="btn btn-info" type="button"  onclick="saveCert();" >
                            <i class="icon-ok bigger-110"></i>
                            保存
                        </button>
                        &nbsp; &nbsp; &nbsp;

                        <button class="btn" type="button" onclick="reloadPage('html/security/activity');">
                            <i class="icon-undo bigger-110"></i>
                            返回
                        </button>
                    </div>
                </div>

            </div>
        </div>

    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse" data-rel="tooltip" title="sss">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>
<script type="text/javascript">

    function getTimeSelect(name){
        var selectTime='<select name="'+name+'" class="form-control">';
        for(var i=0;i<24;i++){
            var value=i<10?("0"+i+":00"):(i+":00");
            selectTime+=('<option value="'+i+'">'+value+'</option>');
        }
        selectTime+='</select>';
        return selectTime;
    }

    function addTableTr(dtable,a){
        var index=dtable.find('tr').size()-2;
        $('<tr class="tr-data">'+
        '<td class="text-center">'+getTimeSelect('periodStart')+
        '</td>'+
        '<td class="text-center">'+getTimeSelect('periodEnd')+
        '</td>'+
        '<td class="text-center">'+
        '<a title="删除" class="icon-action" onclick="removeTableTr(this);" href="javascript:void(0)"><i class="icon icon-remove red bigger-200"></i></a>'+
        '</td>'+
        '</tr>').appendTo(dtable.find('tbody'));

    }
    function removeTableTr(a){
        a.closest('tr').remove();
    }
    function saveGoods(){
        var rv = $('input[name=purchaseRadio]:checked').val();
        var datas=[];
        if(rv==1){
            var startDate=$('#startDate').val();
            var endDate=$('#endDate').val();
            var obj={
                type:1,
                startDate:startDate,
                endDate:endDate
            }
            datas.push(obj);
        }else{
            $('#answerTable').find('tr:gt(1)').each(function(){
                var periodStart = $(this).find('select[name=periodStart]').val();
                var periodEnd = $(this).find('select[name=periodEnd]').val();
                var obj={
                    type:2,
                    startDate:periodStart,
                    endDate:periodEnd
                };
                datas.push(obj);
            });
        }
        var result=requestJSONData('html/goodsSetting/security/save',datas);
        if(result.success){
            alertSuccess("保存成功")
        }else{
            alertError(result.message);
        }
    }
    function saveCert(){
        var rv = $('input[name=duihuan]:checked').val();
        var obj={};
        if(rv==1){
            obj={
                type:1,
                endDate:$('#certificateEnddate').val()
            }
        }else{
            obj={
                type:2,
                endDate:$('#certificateDyEnddate').val()
            }
        }
        var result=requestJSONData('html/goodsSetting/security/certificate',obj);
        if(result.success){
            alertSuccess("保存成功")
        }else{
            alertError(result.message);
        }

    }

    jQuery(function($) {
        changeNav('menu-goodsset');
        $('input[name=purchaseRadio]').change(function(){
            if($(this).val()==1){
                $('#purchaseTimeDiv').removeClass('hidden');
                $('#purchasePeriodDiv').addClass('hidden');
            }else{
                $('#purchasePeriodDiv').removeClass('hidden');
                $('#purchaseTimeDiv').addClass('hidden');
            }
        });
        $('input[name=duihuan]').change(function(){
            if($(this).val()==1){
                $('#duihuanTimeDiv').removeClass('hidden');
                $('#duihuanDyTimeDiv').addClass('hidden');
            }else{
                $('#duihuanDyTimeDiv').removeClass('hidden');
                $('#duihuanTimeDiv').addClass('hidden');
            }
        });

    });

</script>

</body>
</html>