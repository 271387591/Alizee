package com.ozstrategy.webapp.controller.games;

import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.controller.BaseController;
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
 * Created by lihao1 on 6/12/15.
 */
@RestController
@RequestMapping("games")
public class GameController extends BaseController {
    @RequestMapping("security/list")
    public JsonReaderResponse tables(HttpServletRequest request, HttpServletResponse response) {
        Integer start=obtainStart(request);
        Integer limit=obtainLimit(request);
        List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
        for(int i=start;i<((start+limit)<2000?(start+limit):2000);i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("id",i);
            map.put("name","lihao"+i);
            list.add(map);
        }
        return  new JsonReaderResponse(list,true,2000,"");
    }
}
