package com.ozstrategy.webapp.controller.user;

import com.ozstrategy.model.user.CreditsDetail;
import com.ozstrategy.service.user.CreditsDetailManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.user.CreditsDetailCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-08-25.
*/
@RestController
@RequestMapping("creditsDetail")
public class CreditsDetailController extends BaseController {
    @Autowired
    private CreditsDetailManager creditsDetailManager;
    @RequestMapping("security/list")
    public JsonReaderResponse<CreditsDetailCommand> list(HttpServletRequest request){
        List<CreditsDetailCommand> commands=new ArrayList<CreditsDetailCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<CreditsDetail> models= creditsDetailManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(CreditsDetail model:models){
                        CreditsDetailCommand command=new CreditsDetailCommand(model);
                        commands.add(command);
                    }
                }
                Integer count=creditsDetailManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<CreditsDetailCommand> save(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("security/delete/{id}")
    public JsonReaderSingleResponse<CreditsDetailCommand> delete(@PathVariable Long id){
        try{
            creditsDetailManager.deleteById(id);

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}
