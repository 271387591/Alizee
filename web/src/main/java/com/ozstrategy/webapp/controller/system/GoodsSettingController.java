package com.ozstrategy.webapp.controller.system;

import com.ozstrategy.model.system.Certificate;
import com.ozstrategy.model.system.GoodsSetting;
import com.ozstrategy.service.system.CertificateManager;
import com.ozstrategy.service.system.GoodsSettingManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.system.GoodsSettingCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-07-29.
*/
@RestController
@RequestMapping("goodsSetting")
public class GoodsSettingController extends BaseController {
    @Autowired
    private GoodsSettingManager goodsSettingManager;
    @Autowired
    private CertificateManager certificateManager;

    @RequestMapping("list")
    public JsonReaderResponse<GoodsSettingCommand> list(HttpServletRequest request){
        List<GoodsSettingCommand> commands=new ArrayList<GoodsSettingCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<GoodsSetting> models= goodsSettingManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(GoodsSetting model:models){
                        GoodsSettingCommand command=new GoodsSettingCommand(model);
                        commands.add(command);
                    }
                }
                Integer count=goodsSettingManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("security/goodsset")
    public ModelAndView goodsset(HttpServletRequest request, HttpServletResponse response) {
        List<GoodsSetting> models= goodsSettingManager.list(new HashMap<String, Object>(),0,1000);
        Map<String,Object> map=new HashMap<String, Object>();
        if(models!=null && models.size()>0){
            Integer type=models.get(0).getType();
            if(type==1){
                map.put("type",models.get(0).getType());
                map.put("startDate",models.get(0).getStartDate());
                map.put("endDate",models.get(0).getEndDate());
            }else{
                map.put("type",type);
                map.put("goods",models);
            }

        }
        List<Certificate> certificates = certificateManager.listAll(new HashMap<String, Object>());
        if(certificates!=null && certificates.size()>0){
            map.put("certificate",certificates.get(0));
        }
        return new ModelAndView("admin/system/goodsset","command",map);
    }
    @RequestMapping("security/save")
    public JsonReaderSingleResponse<GoodsSettingCommand> save(@RequestBody List<GoodsSetting> commands){
        try{
            goodsSettingManager.saveSetting(commands);

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("security/certificate")
    public JsonReaderSingleResponse<GoodsSettingCommand> certificate(@RequestBody Certificate commands){
        try{
            certificateManager.saveCer(commands);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }

    @RequestMapping("delete")
    public JsonReaderSingleResponse<GoodsSettingCommand> delete(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}
