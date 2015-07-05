package com.ozstrategy.webapp.controller.recharge;

import com.ozstrategy.model.recharge.ConsumeDetail;
import com.ozstrategy.service.recharge.ConsumeDetailManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.recharge.ConsumeDetailCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-07-02.
*/
@RestController
@RequestMapping("consumeDetail")
public class ConsumeDetailController extends BaseController {
    @Autowired
    private ConsumeDetailManager consumeDetailManager;
    @RequestMapping("list")
    public JsonReaderResponse<ConsumeDetailCommand> list(HttpServletRequest request){
        List<ConsumeDetailCommand> commands=new ArrayList<ConsumeDetailCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<ConsumeDetail> models= consumeDetailManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(ConsumeDetail model:models){
                        ConsumeDetailCommand command=new ConsumeDetailCommand(model);
                        commands.add(command);
                    }
                }
                Integer count=consumeDetailManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<ConsumeDetailCommand> save(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete")
    public JsonReaderSingleResponse<ConsumeDetailCommand> delete(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}
