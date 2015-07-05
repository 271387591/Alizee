package com.ozstrategy.webapp.controller.goods;

import com.ozstrategy.Constants;
import com.ozstrategy.exception.GoodsNotHaveException;
import com.ozstrategy.exception.UserCriditsNotHaveException;
import com.ozstrategy.model.goods.Goods;
import com.ozstrategy.model.goods.Merchant;
import com.ozstrategy.model.system.Activity;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.goods.GoodsManager;
import com.ozstrategy.service.goods.MerchantManager;
import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.goods.GoodsCommand;
import com.ozstrategy.webapp.command.system.ActivityCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
* Created by lihao1 on 2015-06-30.
*/
@RestController
@RequestMapping("goods")
public class GoodsController extends BaseController {
    @Autowired
    private GoodsManager goodsManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private MerchantManager merchantManager;


    @RequestMapping("list")
    public JsonReaderResponse<GoodsCommand> list(HttpServletRequest request){
        List<GoodsCommand> commands=new ArrayList<GoodsCommand>();
        Map<String,Object> map=requestMap(request);
        map.put("Q_enabled_EQ",Boolean.TRUE);
        try{
            List<Goods> models= goodsManager.list(map,obtainStart(request),obtainLimit(request));
            if(models!=null && models.size()>0){
                for(Goods model:models){
                    GoodsCommand command=new GoodsCommand(model);
                    commands.add(command);
                }
            }
            Integer count=goodsManager.count(map);
            return new JsonReaderResponse(commands,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<GoodsCommand> save(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("tenant/goodsList")
    public ModelAndView activityList(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/goods/goodsList");
    }
    @RequestMapping("tenant/addGoods")
    public ModelAndView addActivity(HttpServletRequest request){
        GoodsCommand command=null;
        try{

            String id=request.getParameter("id");
            if(id==null){
                command=new GoodsCommand();
            }else{
                command=new GoodsCommand(goodsManager.get(parseLong(id)));
            }
        }catch (Exception e){
            logger.error("addActivity fail",e);
        }
        return new ModelAndView("admin/goods/addGoods","command",command);
    }
    @RequestMapping(value = "tenant/upload")
    public ModelAndView upload(HttpServletRequest request, HttpServletResponse response) {
        File fileOnServer = null;
        String fileName=null,str=null,ext=null;
        PrintWriter writer = null;
        response.setContentType("text/html;charset=utf-8");
        try {
            writer = response.getWriter();
        } catch (IOException e) { }

        if (writer == null) {
            return null;
        }
        try {
            String username    = request.getRemoteUser();
            User user=userManager.getUserByUsername(username);
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_userId_EQ",user.getId());
            Merchant merchant=merchantManager.getByParam(map);
            if(merchant==null){
                writer.print("{\"success\":false,\"msg\":\"请先完善商家资料\"}");
                return null;
            }

            String title        = request.getParameter("name");
            String description = request.getParameter("description");
            String id = request.getParameter("id");
            String num=request.getParameter("num");
            String price=request.getParameter("price");

            String attachFilesDirStr = request.getSession().getServletContext().getRealPath("/") + "/" + Constants.updloadGoods + "/";
            attachFilesDirStr = FilenameUtils.normalize(attachFilesDirStr);



            File fileDir = new File(attachFilesDirStr);

            if (fileDir.exists() == false) {
                fileDir.mkdirs();
            }


            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator list             = multipartRequest.getFileNames();
            while (list.hasNext()) {
                String               controlName = list.next().toString();
                MultipartFile file        = multipartRequest.getFile(controlName);
                CommonsMultipartFile cmf         = (CommonsMultipartFile) file;
                DiskFileItem fileItem    = (DiskFileItem) cmf.getFileItem();
                str         = UUID.randomUUID().toString();
                fileName    = fileItem.getName();
                ext         = FilenameUtils.getExtension(fileName);
                attachFilesDirStr = attachFilesDirStr + "/" + str + "." + ext;
                attachFilesDirStr = FilenameUtils.normalize(attachFilesDirStr);
                fileOnServer      = new File(attachFilesDirStr);

                if (fileOnServer.exists()) {
                    str               = UUID.randomUUID().toString();
                    attachFilesDirStr = attachFilesDirStr + "/" + str + "." + ext;
                    attachFilesDirStr = FilenameUtils.normalize(attachFilesDirStr);
                    fileOnServer      = new File(attachFilesDirStr);
                }
                fileItem.write(fileOnServer);
                Goods advert=null;
                if(StringUtils.isEmpty(id)){
                    advert=new Goods();
                    advert.setCreateDate(new Date());
                }else{
                    advert=goodsManager.get(parseLong(id));

                }
                advert.setName(title);
                advert.setDescription(description);
                advert.setNum(parseInteger(num));
                advert.setPrice(parseDouble(price));
                advert.setUserId(user.getId());
                advert.setMerchantId(merchant.getId());
                advert.setLastUpdateDate(new Date());
                if(StringUtils.isNotEmpty(fileName)){
                    try{
                        FileUtils.forceDelete(new File(advert.getPicPath()));
                    }catch (Exception e){
                    }
                    advert.setPicName(fileName);
                    advert.setPicPath(fileOnServer.getAbsolutePath());
                    String httpPath=toHttpUrl(request,true)+Constants.updloadGoods+"/"+str+"."+ext;
                    advert.setUrl(httpPath);
                }
                goodsManager.saveOrUpdate(advert);
                writer.print("{\"success\":true,\"msg\":\"\"}");
            }

        } catch (Exception e) {
            logger.error("upload goods fail", e);
            String msg = "保存失败";
            writer.print("{\"success\":false,\"msg\":\"" + msg + "!\"}");

            if (fileOnServer != null) {
                try {
                    FileUtils.forceDelete(fileOnServer);
                } catch (IOException e1) { }
            }

        }
        return null;
    }
    @RequestMapping("tenant/delete")
    public JsonReaderSingleResponse<GoodsCommand> delete(HttpServletRequest request){
        try{
            String ids=request.getParameter("ids");
            List<Goods> commentses=new ArrayList<Goods>();
            if(StringUtils.isNotEmpty(ids)){
                String[] ida=ids.split(",");
                for(String id:ida){
                    Goods comments=goodsManager.get(parseLong(id));
                    comments.setEnabled(Boolean.FALSE);
                    commentses.add(comments);
                }
            }
            goodsManager.batchUpdate(commentses);
            return new JsonReaderSingleResponse("",Boolean.TRUE);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
    @RequestMapping("web/purchase")
    public JsonReaderSingleResponse<GoodsCommand> purchase(HttpServletRequest request){

        try {
            String id=request.getParameter("id");
            String username=request.getRemoteUser();
            User user=userManager.getUserByUsername(username);
            goodsManager.purchase(parseLong(id),user);
            return new JsonReaderSingleResponse("",Boolean.TRUE);
        } catch (GoodsNotHaveException e) {
            logger.warn(e.getMessage());
            return new JsonReaderSingleResponse(null,false,"商品已卖完");
        } catch (UserCriditsNotHaveException e) {
            logger.warn(e.getMessage());
            return new JsonReaderSingleResponse(null,false,"用户余额不足");
        }catch (Exception e){
            logger.error("purchase fail",e);
            return new JsonReaderSingleResponse(null,false,"抢购失败");
        }
    }

}
