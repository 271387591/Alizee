package com.ozstrategy.webapp.controller.system;

import com.ozstrategy.model.system.Channel;
import com.ozstrategy.service.system.ChannelManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.system.AdvertCommand;
import com.ozstrategy.webapp.command.system.ChannelCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-08-20.
*/
@RestController
@RequestMapping("channel")
public class ChannelController extends BaseController {
    @Autowired
    private ChannelManager channelManager;
    @RequestMapping("security/list")
    public JsonReaderResponse<ChannelCommand> list(HttpServletRequest request){
        List<ChannelCommand> commands=new ArrayList<ChannelCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<Channel> models= channelManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(Channel model:models){
                        ChannelCommand command=new ChannelCommand(model);
                        commands.add(command);
                    }
                }
                Integer count=channelManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("security/addChannel")
    public ModelAndView addAdvert(HttpServletRequest request){
        ChannelCommand command=null;
        try{

            String id=request.getParameter("id");
            if(id==null){
                command=new ChannelCommand();
            }else{
                command=new ChannelCommand(channelManager.get(parseLong(id)));
            }
        }catch (Exception e){
            logger.error("addChannel fail",e);
        }
        return new ModelAndView("admin/system/addChannel","command",command);
    }
    @RequestMapping("security/save")
    public JsonReaderSingleResponse<ChannelCommand> save(@RequestBody ChannelCommand channelCommand){
        try{
            Long id=channelCommand.getId();
            Channel channel=null;
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_channelNo_EQ",channelCommand.getChannelNo());
            if(id==null){
                channel=channelManager.getByParam(map);
                if(channel!=null){
                    return new JsonReaderSingleResponse(null,false,"该渠道已存在");
                }
                channel=new Channel();
                channel.setCreateDate(new Date());
            }else{
                channel=channelManager.get(id);
                Channel channel1=channelManager.getByParam(map);
                if(channel1!=null && channel.getId()!=channel1.getId()){
                    return new JsonReaderSingleResponse(null,false,"该渠道已存在");
                }
            }
            channel.setChannelNo(channelCommand.getChannelNo());
            channel.setName(channelCommand.getName());
            channelManager.saveOrUpdate(channel);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("security/delete/{id}")
    public JsonReaderSingleResponse<ChannelCommand> delete(@PathVariable Long id){
        try{
            channelManager.deleteById(id);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}
