package com.ozstrategy.webapp.controller.system;

import com.ozstrategy.model.system.Certificate;
import com.ozstrategy.service.system.CertificateManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.system.CertificateCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-07-29.
*/
@RestController
@RequestMapping("certificate")
public class CertificateController extends BaseController {
    @Autowired
    private CertificateManager certificateManager;
    @RequestMapping("list")
    public JsonReaderResponse<CertificateCommand> list(HttpServletRequest request){
        List<CertificateCommand> commands=new ArrayList<CertificateCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<Certificate> models= certificateManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(Certificate model:models){
                        CertificateCommand command=new CertificateCommand(model);
                        commands.add(command);
                    }
                }
                Integer count=certificateManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<CertificateCommand> save(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete")
    public JsonReaderSingleResponse<CertificateCommand> delete(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}
