package com.ozstrategy.webapp.controller.system;

import com.ozstrategy.model.system.ApplicationConfig;
import com.ozstrategy.service.system.ApplicationConfigManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.system.ApplicationConfigCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-06-10.
*/
@RestController
@RequestMapping("security/applicationConfig")
public class ApplicationConfigController extends BaseController {
    @Autowired
    private ApplicationConfigManager applicationConfigManager;
    @RequestMapping("systemsetting")
    public ModelAndView list(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String, Object>();
        List<ApplicationConfig> configs= applicationConfigManager.listAll();
        if(configs!=null && configs.size()>0){
            for(ApplicationConfig model:configs){
                map.put(model.getSystemKey(),model.getSystemValue());
            }
        }
        return new ModelAndView("admin/systemsetting",map);
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<ApplicationConfigCommand> save(@RequestBody List<ApplicationConfigCommand> configCommands){
        try{
            if(configCommands!=null && configCommands.size()>0){
                List<ApplicationConfig> saves=new ArrayList<ApplicationConfig>();
                List<ApplicationConfig> updates=new ArrayList<ApplicationConfig>();
                for(ApplicationConfigCommand configCommand:configCommands){
                    ApplicationConfig config=null;
                    Map<String,Object> map=new HashMap<String, Object>();
                    map.put("Q_systemKey_EQ",configCommand.getSystemKey());
                    config=applicationConfigManager.getByParam(map);
                    if(config==null){
                        config=new ApplicationConfig();
                        saves.add(config);
                    }else{
                        updates.add(config);
                    }
                    config.setSystemKey(configCommand.getSystemKey());
                    config.setSystemValue(configCommand.getSystemValue());
                }
                if(saves.size()>0){
                    applicationConfigManager.batchSave(saves);
                }
                if(updates.size()>0){
                    applicationConfigManager.batchUpdate(updates);
                }
            }
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
}
