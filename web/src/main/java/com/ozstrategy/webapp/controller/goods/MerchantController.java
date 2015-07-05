package com.ozstrategy.webapp.controller.goods;

import com.ozstrategy.model.goods.Merchant;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.goods.MerchantManager;
import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.goods.MerchantCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-06-30.
*/
@RestController
@RequestMapping("merchant")
public class MerchantController extends BaseController {
    @Autowired
    private MerchantManager merchantManager;
    @Autowired
    private UserManager userManager;

    @RequestMapping("list")
    public JsonReaderResponse<MerchantCommand> list(HttpServletRequest request){
        List<MerchantCommand> commands=new ArrayList<MerchantCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<Merchant> models= merchantManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(Merchant model:models){
                        MerchantCommand command=new MerchantCommand(model);
                        commands.add(command);
                    }
                }
                Integer count=merchantManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("tenant/save")
    public JsonReaderSingleResponse<MerchantCommand> save(@RequestBody MerchantCommand command,HttpServletRequest request){
        try{
            String username=request.getRemoteUser();
            if(org.apache.commons.lang.StringUtils.isEmpty(username)){
                return new JsonReaderSingleResponse(null,false,"登录超时");
            }
            User user=userManager.getUserByUsername(username);

            Merchant merchant=null;
            if(command.getId()==null){
                merchant=new Merchant();
            }else{
                merchant=merchantManager.get(command.getId());
            }
            merchant.setAddress(command.getAddress());
            merchant.setName(command.getName());
            merchant.setPhone(command.getPhone());
            merchant.setUserId(user.getId());
            merchantManager.saveOrUpdate(merchant);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete")
    public JsonReaderSingleResponse<MerchantCommand> delete(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}
