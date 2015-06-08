package ${basepakege};

import ${modelFullName};
import ${managerFullName};
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import ${commandFullName};
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on ${date}.
*/
@RestController
@RequestMapping("${modelName?uncap_first}")
public class ${className} extends BaseController {
    @Autowired
    private ${managerName} ${managerName?uncap_first};
    @RequestMapping("list")
    public JsonReaderResponse<${commandName}> list(HttpServletRequest request){
        List<${commandName}> commands=new ArrayList<${commandName}>();
            Map<String,Object> map=requestMap(request);
            try{
            List<${modelName}> models= ${managerName?uncap_first}.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(${modelName} model:models){
                        ${commandName} command=new ${commandName}(model);
                        commands.add(command);
                    }
                }
                Integer count=${managerName?uncap_first}.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<${commandName}> save(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete")
    public JsonReaderSingleResponse<${commandName}> delete(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}
