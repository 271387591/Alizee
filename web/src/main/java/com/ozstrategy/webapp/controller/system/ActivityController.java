package com.ozstrategy.webapp.controller.system;

import com.ozstrategy.model.system.Activity;
import com.ozstrategy.service.system.ActivityManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.system.ActivityCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-06-25.
*/
@RestController
@RequestMapping("activity")
public class ActivityController extends BaseController {
    @Autowired
    private ActivityManager activityManager;
    @RequestMapping("list")
    public JsonReaderResponse<ActivityCommand> list(HttpServletRequest request){
        List<ActivityCommand> commands=new ArrayList<ActivityCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<Activity> models= activityManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(Activity model:models){
                        ActivityCommand command=new ActivityCommand(model);
                        commands.add(command);
                    }
                }
                Integer count=activityManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<ActivityCommand> save(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete")
    public JsonReaderSingleResponse<ActivityCommand> delete(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}
