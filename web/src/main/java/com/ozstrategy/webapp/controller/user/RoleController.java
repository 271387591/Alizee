package com.ozstrategy.webapp.controller.user;

import com.ozstrategy.model.user.Role;
import com.ozstrategy.service.user.RoleManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.user.RoleCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-06-08.
*/
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {
    @Autowired
    private RoleManager roleManager;
    @RequestMapping("security/list")
    public JsonReaderResponse<RoleCommand> list(HttpServletRequest request){
        List<RoleCommand> commands=new ArrayList<RoleCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<Role> models= roleManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(Role model:models){
                        RoleCommand command=new RoleCommand(model);
                        commands.add(command);
                    }
                }
                Integer count=roleManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<RoleCommand> save(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete")
    public JsonReaderSingleResponse<RoleCommand> delete(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}
