package com.ozstrategy.webapp.controller.system;

import com.ozstrategy.model.system.ActivityUser;
import com.ozstrategy.service.system.ActivityUserManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.system.ActivityUserCommand;
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
@RequestMapping("activityUser")
public class ActivityUserController extends BaseController {
    @Autowired
    private ActivityUserManager activityUserManager;
    @RequestMapping("list")
    public JsonReaderResponse<ActivityUserCommand> list(HttpServletRequest request){
        List<ActivityUserCommand> commands=new ArrayList<ActivityUserCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<ActivityUser> models= activityUserManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(ActivityUser model:models){
                        ActivityUserCommand command=new ActivityUserCommand(model);
                        commands.add(command);
                    }
                }
                Integer count=activityUserManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<ActivityUserCommand> save(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete")
    public JsonReaderSingleResponse<ActivityUserCommand> delete(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}
