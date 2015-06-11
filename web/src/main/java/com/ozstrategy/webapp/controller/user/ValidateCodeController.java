package com.ozstrategy.webapp.controller.user;

import com.ozstrategy.model.user.ValidateCode;
import com.ozstrategy.service.user.ValidateCodeManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.user.ValidateCodeCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-06-10.
*/
@RestController
@RequestMapping("validateCode")
public class ValidateCodeController extends BaseController {
    @Autowired
    private ValidateCodeManager validateCodeManager;
    @RequestMapping("getCode")
    public JsonReaderSingleResponse<ValidateCodeCommand> save(HttpServletRequest request){
        try{





            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
}
