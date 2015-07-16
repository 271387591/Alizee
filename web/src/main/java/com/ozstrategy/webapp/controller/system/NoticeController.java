package com.ozstrategy.webapp.controller.system;

import com.ozstrategy.model.system.Notice;
import com.ozstrategy.model.system.UserComments;
import com.ozstrategy.service.system.NoticeManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.system.NoticeCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-07-14.
*/
@RestController
@RequestMapping("notice")
public class NoticeController extends BaseController {
    @Autowired
    private NoticeManager noticeManager;
    @RequestMapping("list")
    public JsonReaderResponse<NoticeCommand> list(HttpServletRequest request){
        List<NoticeCommand> commands=new ArrayList<NoticeCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<Notice> models= noticeManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(Notice model:models){
                        NoticeCommand command=new NoticeCommand(model);
                        commands.add(command);
                    }
                }
                Integer count=noticeManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("security/save")
    public JsonReaderSingleResponse<NoticeCommand> save(@RequestBody NoticeCommand command){
        try{
            Notice notice=new Notice();
            notice.setCreateDate(new Date());
            notice.setContent(command.getContent());
            noticeManager.save(notice);

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("security/delete")
    public JsonReaderSingleResponse<NoticeCommand> delete(HttpServletRequest request){
        try{
            String ids=request.getParameter("ids");
            List<Notice> commentses=new ArrayList<Notice>();
            if(StringUtils.isNotEmpty(ids)){
                String[] ida=ids.split(",");
                for(String id:ida){
                    Notice comments=noticeManager.get(parseLong(id));
                    commentses.add(comments);
                }
            }
            noticeManager.batchDelete(commentses);

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}
