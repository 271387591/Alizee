package com.ozstrategy.webapp.controller.system;

import com.ozstrategy.model.system.About;
import com.ozstrategy.model.system.ApplicationConfig;
import com.ozstrategy.service.system.AboutManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.system.AboutCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-06-18.
*/
@RestController
@RequestMapping("about")
public class AboutController extends BaseController {
    @Autowired
    private AboutManager aboutManager;
    @RequestMapping("security/info")
    public ModelAndView info(HttpServletRequest request, HttpServletResponse response) {
//        AboutCommand command=new AboutCommand();
//        try{
//            List<About> models= aboutManager.listAll();
//            if(models!=null && models.size()>0){
//                About about=models.get(0);
//                command=new AboutCommand(about);
//            }
//        }catch (Exception e){
//            logger.error("list fail",e);
//        }
        return new ModelAndView("admin/system/about");
    }
     @RequestMapping("security/about")
    public ModelAndView about(HttpServletRequest request, HttpServletResponse response) {
        AboutCommand command=new AboutCommand();
        try{
            List<About> models= aboutManager.listAll();
            if(models!=null && models.size()>0){
                About about=models.get(0);
                command=new AboutCommand(about);
            }
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new ModelAndView("admin/system/aboutinfo","command",command);
    }

    @RequestMapping("security/save")
    public JsonReaderSingleResponse<AboutCommand> save(@RequestBody AboutCommand command){
        try{
            About about=command.toAbout();
            aboutManager.saveOrUpdate(about);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete")
    public JsonReaderSingleResponse<AboutCommand> delete(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}
