package com.ozstrategy.webapp.controller.goods;

import com.ozstrategy.Constants;
import com.ozstrategy.exception.GoodsNotHaveException;
import com.ozstrategy.exception.UserCriditsNotHaveException;
import com.ozstrategy.model.goods.Goods;
import com.ozstrategy.model.goods.Merchant;
import com.ozstrategy.model.recharge.RechargeStatus;
import com.ozstrategy.model.system.Activity;
import com.ozstrategy.model.system.Certificate;
import com.ozstrategy.model.system.GoodsSetting;
import com.ozstrategy.model.user.Role;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.goods.GoodsCertificateManager;
import com.ozstrategy.service.goods.GoodsManager;
import com.ozstrategy.service.goods.MerchantManager;
import com.ozstrategy.service.system.CertificateManager;
import com.ozstrategy.service.system.GoodsSettingManager;
import com.ozstrategy.service.user.RoleManager;
import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.util.ExcelUtils;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.goods.GoodsCertificateCommand;
import com.ozstrategy.webapp.command.goods.GoodsCommand;
import com.ozstrategy.webapp.command.system.ActivityCommand;
import com.ozstrategy.webapp.command.user.RoleCommand;
import com.ozstrategy.webapp.command.user.UserCommand;
import com.ozstrategy.webapp.controller.BaseController;
import com.ozstrategy.webapp.utils.ExportExcelUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    private GoodsSettingManager goodsSettingManager;
    @Autowired
    private RoleManager roleManager;
    @Autowired
    private GoodsCertificateManager goodsCertificateManager;
    @Autowired
    private CertificateManager certificateManager;




    @RequestMapping("list")
    public JsonReaderResponse<GoodsCommand> weblist(HttpServletRequest request){
        Map<String,Object> map=requestMap(request);
        map.put("Q_r.createDate","DESC");
        try{
            List<Map<String,Object>> models= goodsManager.findByNamedQuery("getGoods", map, obtainStart(request), obtainLimit(request));
            Integer count=goodsManager.findByNamedQueryClass("getGoodsCount", Integer.class, map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(new ArrayList(),false,"请求错误");
    }
    @RequestMapping("security/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        ExcelUtils.Columns columns1=new ExcelUtils.Columns();
        ExcelUtils.Column r1_c0=new ExcelUtils.Column(0,"商家名称",0,0,"merchantName",5000);
        ExcelUtils.Column r1_c1=new ExcelUtils.Column(1,"商家电话",0,0,"phone");
        ExcelUtils.Column r1_c2=new ExcelUtils.Column(2,"商品名称",0,0,"name",5000);
        ExcelUtils.Column r1_c3=new ExcelUtils.Column(3,"商品数量",0,0,"num");
        ExcelUtils.Column r1_c4=new ExcelUtils.Column(4,"商品价格（游游币）",0,0,"price",7000);
        ExcelUtils.Column r1_c5=new ExcelUtils.Column(5,"添加时间",0,0,"lastUpdateDate",5000);
        columns1.addColumn(r1_c0)
                .addColumn(r1_c1)
                .addColumn(r1_c2)
                .addColumn(r1_c3)
                .addColumn(r1_c4)
                .addColumn(r1_c5);
        String sheetName="抢购商品列表";
        ExcelUtils.Row row1=new ExcelUtils.Row(1,columns1);
        ExcelUtils.Rows rows=new ExcelUtils.Rows();
        rows.addRow(row1);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_r.createDate","DESC");
        map.put("Q_r.purchase_EQ","1");
        List<Map<String,Object>> models= goodsManager.findByNamedQuery("getGoods", map);
        ExportExcelUtils.export(sheetName, rows, sheetName, models, response, request);
    }
    @RequestMapping("tenant/exportExcel/{purchase}")
    public void tenantexportExcel(@PathVariable Integer purchase,HttpServletRequest request, HttpServletResponse response) {
        ExcelUtils.Columns columns1=new ExcelUtils.Columns();
        ExcelUtils.Column r1_c0=new ExcelUtils.Column(0,"商品名称",0,0,"name",5000);
        ExcelUtils.Column r1_c1=new ExcelUtils.Column(1,"商品数量",0,0,"num");
        ExcelUtils.Column r1_c2=new ExcelUtils.Column(2,"商品价格（游游币）",0,0,"price",7000);
        ExcelUtils.Column r1_c3=new ExcelUtils.Column(3,"描述",0,0,"description",7000*2);
        ExcelUtils.Column r1_c4=new ExcelUtils.Column(4,"添加时间",0,0,"createDate",5000);
        ExcelUtils.Column r1_c5=new ExcelUtils.Column(5,"兑换券失效(固定时间)",0,0,"fixedDate",10000);
        ExcelUtils.Column r1_c6=new ExcelUtils.Column(6,"添加时间(动态时间[天])",0,0,"trends",10000);
        columns1.addColumn(r1_c0)
                .addColumn(r1_c1)
                .addColumn(r1_c2)
                .addColumn(r1_c3)
                .addColumn(r1_c4);
        String sheetName="普通商品列表";
        if(purchase==1){
            sheetName="抢购商品列表";
            columns1.addColumn(r1_c5).addColumn(r1_c6);
        }
        ExcelUtils.Row row1=new ExcelUtils.Row(1,columns1);
        ExcelUtils.Rows rows=new ExcelUtils.Rows();
        rows.addRow(row1);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_r.createDate","DESC");
        map.put("Q_r.purchase_EQ",purchase);
        List<Map<String,Object>> models= goodsManager.findByNamedQuery("getGoods", map);
        ExportExcelUtils.export(sheetName, rows, sheetName, models, response, request);
    }

    @RequestMapping("tenant/goodssaleList")
    public JsonReaderResponse<GoodsCommand> goodssale(HttpServletRequest request){
        try{
            User user=userManager.getUserByUsername(request.getRemoteUser());
            Map<String,Object> objectMap=new HashMap<String, Object>();
            objectMap.put("Q_userId_EQ",user.getId());
            Merchant merchant=merchantManager.getByParam(objectMap);
            Map<String,Object> map=requestMap(request);
            map.put("merchantId",merchant.getId());
            map.put("Q_r.createDate","DESC");
            List<Map<String,Object>> models= goodsCertificateManager.findByNamedQuery("getCerts", map, obtainStart(request), obtainLimit(request));
            Integer count=goodsCertificateManager.findByNamedQueryClass("getCertsCount",Integer.class,map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(new ArrayList(),false,"请求错误");
    }
    @RequestMapping("security/goodssaleList")
    public JsonReaderResponse<GoodsCommand> securityList(HttpServletRequest request){
        try{
            Map<String,Object> map=requestMap(request);
            map.put("Q_r.createDate","DESC");
            List<Map<String,Object>> models= goodsCertificateManager.findByNamedQuery("getAllCerts", map, obtainStart(request), obtainLimit(request));
            Integer count=goodsCertificateManager.findByNamedQueryClass("getAllCertsCount",Integer.class,map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(null,false,"请求错误");
    }
    @RequestMapping("tenant/goodssaleListExcel")
    public void goodssaleListExcel(HttpServletRequest request, HttpServletResponse response) {
        ExcelUtils.Columns columns1=new ExcelUtils.Columns();
        ExcelUtils.Column r1_c0=new ExcelUtils.Column(0,"编号",0,0,"certNo",5000);
        ExcelUtils.Column r1_c1=new ExcelUtils.Column(1,"商品名称",0,0,"name");
        ExcelUtils.Column r1_c2=new ExcelUtils.Column(2,"商品价格（游游币）",0,0,"price",7000);
        ExcelUtils.Column r1_c3=new ExcelUtils.Column(3,"用户昵称",0,0,"nickName",7000);
        ExcelUtils.Column r1_c4=new ExcelUtils.Column(4,"用户手机",0,0,"mobile",5000);
        ExcelUtils.Column r1_c5=new ExcelUtils.Column(5,"出售时间",0,0,"createDate",7000);
        ExcelUtils.Column r1_c6=new ExcelUtils.Column(6,"到期时间",0,0,"endDate",7000);
        ExcelUtils.Column r1_c7=new ExcelUtils.Column(7,"是否兑换",0,0,"endDate",7000).addColumnRenderListener(new ExcelUtils.ColumnRenderListener() {
            public String renderer(String data) {
                if(new Date().after(parseDate(data))){
                    return "未到期";
                }
                return "已到期";
            }
        });
        ExcelUtils.Column r1_c8=new ExcelUtils.Column(8,"是否到期",0,0,"enabled",7000).addColumnRenderListener(new ExcelUtils.ColumnRenderListener() {
            public String renderer(String data) {
                if(StringUtils.equals("1",data)){
                    return "未兑换";
                }
                return "已兑换";
            }
        });

        columns1.addColumn(r1_c0)
                .addColumn(r1_c1)
                .addColumn(r1_c2)
                .addColumn(r1_c3)
                .addColumn(r1_c4)
                .addColumn(r1_c5)
                .addColumn(r1_c6)
                .addColumn(r1_c7)
                .addColumn(r1_c8);
        String sheetName="商品出售列表";

        ExcelUtils.Row row1=new ExcelUtils.Row(1,columns1);
        ExcelUtils.Rows rows=new ExcelUtils.Rows();
        rows.addRow(row1);
        User user=userManager.getUserByUsername(request.getRemoteUser());
        Map<String,Object> objectMap=new HashMap<String, Object>();
        objectMap.put("Q_userId_EQ",user.getId());
        Merchant merchant=merchantManager.getByParam(objectMap);
        Map<String,Object> map=requestMap(request);
        map.put("merchantId",merchant.getId());
        map.put("Q_r.createDate","DESC");
        List<Map<String,Object>> models= goodsCertificateManager.findByNamedQuery("getCerts", map);
        ExportExcelUtils.export(sheetName, rows, sheetName, models, response, request);
    }

    @RequestMapping("tenant/list")
    public JsonReaderResponse list(HttpServletRequest request){
        List<GoodsCommand> commands=new ArrayList<GoodsCommand>();
        Map<String,Object> map=requestMap(request);
        map.put("Q_enabled_EQ",Boolean.TRUE);
        User user=userManager.getUserByUsername(request.getRemoteUser());
        map.put("Q_userId_EQ",user.getId());

        try{
            List<Goods> models= goodsManager.list(map, obtainStart(request), obtainLimit(request));
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

    @RequestMapping("tenant/listShop")
    public JsonReaderResponse<GoodsCommand> listShop(HttpServletRequest request){
        User user=userManager.getUserByUsername(request.getRemoteUser());
        Map<String,Object> map=requestMap(request);
        map.put("Q_parentId_EQ",user.getId());
        List<UserCommand> commands=new ArrayList<UserCommand>();

        try{
            List<User> models= userManager.list(map,obtainStart(request),obtainLimit(request));
            if(models!=null && models.size()>0){
                for(User model:models){
                    UserCommand command=new UserCommand(model);
                    commands.add(command);
                }
            }
            Integer count=userManager.count(map);
            return new JsonReaderResponse(commands,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(commands,false,"请求错误");
    }

    @RequestMapping("tenant/saveShop")
    public JsonReaderSingleResponse<GoodsCommand> save(@RequestBody UserCommand userCommand,HttpServletRequest request){
        try{
            User pUser=userManager.getUserByUsername(request.getRemoteUser());
            User user=null;

            if(userCommand.getId()==null){
                Map<String,Object> map=new HashMap<String, Object>();
                map.put("Q_mobile_EQ",userCommand.getMobile());
                map.put("Q_enabled_EQ",Boolean.TRUE);
                user=userManager.getByParam(map);
                if(user!=null){
                    return new JsonReaderSingleResponse(null,false,"该用户已存在");
                }
                user=new User();
                user.setCreateDate(new Date());
                user.setPassword(userCommand.getPassword());
            }else{
                user=userManager.get(userCommand.getId());
            }
            user.setLastUpdateDate(new Date());
            user.setMobile(userCommand.getMobile());
            user.setNickName(userCommand.getNickName());
            user.setUsername(userCommand.getMobile());
            user.setParentId(pUser.getId());
            Map<String,Object> roleMap=new HashMap<String, Object>();
            roleMap.put("Q_name_EQ","ROLE_SHOP");

            Role role=roleManager.getByParam(roleMap);
            user.setRoleId(role.getId());

            Set<Role> roles=new HashSet<Role>();
            roles.add(role);

            user.getRoles().clear();
            user.getRoles().addAll(roles);
            userManager.saveUser(user);

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("tenant/removeUser/{id}")
    public JsonReaderSingleResponse<GoodsCommand> removeUser(@PathVariable Long id){
        try{
            User pUser=userManager.get(id);
            userManager.deleteUser(pUser);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
    @RequestMapping("tenant/changeUserPwd")
    public JsonReaderSingleResponse<GoodsCommand> changeUserPwd(@RequestBody UserCommand userCommand){
        try{
            User user=userManager.get(userCommand.getId());
            userManager.changePassword(user, userCommand.getPassword(), null, true);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"修改失败");
    }
    @RequestMapping("tenant/changeUserMobile1")
    public JsonReaderSingleResponse<UserCommand> changeUserMobile1(@RequestBody UserCommand userCommand){
        try{
            User user=userManager.get(userCommand.getId());
            user.setLastUpdateDate(new Date());
            user.setMobile(userCommand.getMobile());
            user.setUsername(userCommand.getMobile());
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_mobile_EQ",userCommand.getMobile());
            map.put("Q_enabled_EQ",Boolean.TRUE);
            User exuser=userManager.getByParam(map);
            if(exuser!=null && exuser.getId()!=user.getId()){
                return new JsonReaderSingleResponse(null,false,"该手机号已存在");
            }
            userManager.update(user);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"修改失败");
    }


    @RequestMapping("tenant/goodsList")
    public ModelAndView activityList(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/goods/goodsList");
    }
    @RequestMapping("tenant/shop")
    public ModelAndView shop(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/goods/shop");
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
            String trends=request.getParameter("trends");

            String attachFilesDirStr = request.getSession().getServletContext().getRealPath("/") + "/" + Constants.updloadGoods + "/";
            attachFilesDirStr = FilenameUtils.normalize(attachFilesDirStr);



            File fileDir = new File(attachFilesDirStr);

            if (fileDir.exists() == false) {
                fileDir.mkdirs();
            }
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
            advert.setTrends(parseInteger(trends));

            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator list             = multipartRequest.getFileNames();
            while (list.hasNext()) {
                String               controlName = list.next().toString();
                MultipartFile file        = multipartRequest.getFile(controlName);
                CommonsMultipartFile cmf         = (CommonsMultipartFile) file;
                DiskFileItem fileItem    = (DiskFileItem) cmf.getFileItem();
                str         = UUID.randomUUID().toString();
                fileName    = fileItem.getName();
                if(StringUtils.isEmpty(fileName))continue;
                ext         = FilenameUtils.getExtension(fileName);
                String attachFilesDir = attachFilesDirStr + "/" + str + "." + ext;
                attachFilesDir = FilenameUtils.normalize(attachFilesDir);
                fileOnServer      = new File(attachFilesDir);

                fileItem.write(fileOnServer);

                if(StringUtils.equals("picName", controlName)){
                    try{
                        FileUtils.forceDelete(new File(advert.getPicPath()));
                    }catch (Exception e){
                    }
                    advert.setPicName(fileName);
                    advert.setPicPath(fileOnServer.getAbsolutePath());
                    String httpPath=toHttpUrl(request,true)+Constants.updloadGoods+"/"+str+"."+ext;
                    advert.setUrl(httpPath);
                }else if(StringUtils.equals("logoName",controlName)){
                    try{
                        FileUtils.forceDelete(new File(advert.getLogoPath()));
                    }catch (Exception e){
                    }
                    advert.setLogoName(fileName);
                    advert.setLogoPath(fileOnServer.getAbsolutePath());
                    String httpPath=toHttpUrl(request,true)+Constants.updloadGoods+"/"+str+"."+ext;
                    advert.setLogoUrl(httpPath);
                }

            }
            goodsManager.saveOrUpdate(advert);
            writer.print("{\"success\":true,\"msg\":\"\"}");


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
    @RequestMapping("tenant/qiang/{id}")
    public JsonReaderSingleResponse<GoodsCommand> qiang(@PathVariable Long id){
        try{
            Goods goods=goodsManager.get(id);
            if(goods.getPurchase()){
                goods.setPurchase(Boolean.FALSE);
            }else{
                goods.setPurchase(Boolean.TRUE);
            }
            goodsManager.update(goods);
            return new JsonReaderSingleResponse("",Boolean.TRUE);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"设置失败");
    }
     @RequestMapping("tenant/saveQiang")
    public JsonReaderSingleResponse<GoodsCommand> saveQiang(@RequestBody GoodsCommand command){
        try{
            Long id=command.getId();
            Goods goods=goodsManager.get(id);
            if(goods.getPurchase()){
                goods.setPurchase(Boolean.FALSE);
            }else{
                goods.setPurchase(Boolean.TRUE);
            }
            goods.setTrends(command.getTrends());
            goods.setFixedDate(parseDate(command.getFixedDateStr()));
            goodsManager.update(goods);
            return new JsonReaderSingleResponse("",Boolean.TRUE);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"设置失败");
    }


    @RequestMapping("web/purchase")
    public JsonReaderSingleResponse purchase(HttpServletRequest request){

        try {
            String id=request.getParameter("id");
            String num=request.getParameter("num");
            String username=request.getRemoteUser();
            User user=userManager.getUserByUsername(username);
            Goods goods=goodsManager.get(parseLong(id));
            Date fixDate=goods.getFixedDate();
            Integer trends=goods.getTrends();
//            List<Certificate> certificates = certificateManager.listAll();
//
//
//            if(certificates==null || certificates.size()<1){
//                return new JsonReaderSingleResponse(null,false,"商家未设置兑换券功能，无法抢购");
//            }
            if(trends==null){
                return new JsonReaderSingleResponse(null,false,"商家未设置兑换券功能，无法抢购");
            }

            Map<String,Object> map = goodsManager.purchase(goods,parseInteger(num), user);
            return new JsonReaderSingleResponse(map,Boolean.TRUE);
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
     @RequestMapping("web/purchaseGoods")
    public JsonReaderResponse<GoodsCommand> purchaseGoods(HttpServletRequest request){
        try {
            List<GoodsSetting> goodsSettings=goodsSettingManager.listAll();
            if(goodsSettings==null || goodsSettings.isEmpty()){
                return new JsonReaderResponse(null,false,"当前未配置抢购商品");
            }
            GoodsSetting goodsSetting=goodsSettings.get(0);
            Integer type=goodsSetting.getType();
            if(type==1){
                Date startDate=parseDate(goodsSetting.getStartDate());
                Date endDate=parseDate(goodsSetting.getEndDate());

                Map<String,Object> map=requestMap(request);
                map.put("Q_r.enabled_EQ",Boolean.TRUE);
                map.put("Q_r.purchase_EQ",Boolean.TRUE);
                map.put("Q_r.createDate","DESC");
                List<Map<String,Object>> models= goodsManager.findByNamedQuery("getGoods", map, obtainStart(request), obtainLimit(request));
                Integer count=goodsManager.findByNamedQueryClass("getGoodsCount", Integer.class, map);
                if(models!=null && models.size()>0){
                    for(Map<String,Object> model:models){
                        model.put("endDate",endDate);
                        model.put("currentDate",new Date());
                        model.put("startDate",startDate);
                    }
                }
                return new JsonReaderResponse(models,true,count,"");

            }else{

                List<Integer> integerList=new ArrayList<Integer>();
                Map<Integer,GoodsSetting> goodsSettingMap=new HashMap<Integer, GoodsSetting>();
                for(GoodsSetting setting:goodsSettings){
                    Integer startDate=parseInteger(setting.getStartDate());
                    integerList.add(startDate);
                    goodsSettingMap.put(startDate,setting);
                }
                Collections.sort(integerList);
                Integer min=integerList.get(0);
                Date startDateTime=null;
                Date endDateTime=null;
                for(Integer integer:integerList){
                    GoodsSetting setting=goodsSettingMap.get(integer);
                    Calendar calendar=Calendar.getInstance();
                    Calendar calendar1=Calendar.getInstance();
                    Calendar calendar2=Calendar.getInstance();
                    calendar2.setTime(new Date());
                    Integer startDate=parseInteger(setting.getStartDate());
                    Integer endDate=parseInteger(setting.getEndDate());
                    Integer ch=calendar2.get(Calendar.HOUR_OF_DAY);
                    if(ch<=endDate){
                        calendar.setTime(new Date());
                        calendar.set(Calendar.HOUR_OF_DAY,endDate);
                        calendar.set(Calendar.MINUTE,0);
                        calendar.set(Calendar.SECOND,0);
                        calendar1.setTime(new Date());
                        calendar1.set(Calendar.HOUR_OF_DAY,startDate);
                        calendar1.set(Calendar.MINUTE,0);
                        calendar1.set(Calendar.SECOND,0);
                        startDateTime=calendar1.getTime();
                        endDateTime=calendar.getTime();
                        break;
                    }
                }
                if(startDateTime==null || endDateTime==null){
                    GoodsSetting setting=goodsSettingMap.get(min);
                    Integer startDate=parseInteger(setting.getStartDate());
                    Integer endDate=parseInteger(setting.getEndDate());
                    Calendar calendar=Calendar.getInstance();
                    Calendar calendar1=Calendar.getInstance();
                    calendar.setTime(new Date());
                    calendar.add(Calendar.DAY_OF_YEAR, 1);
                    calendar.set(Calendar.HOUR_OF_DAY,endDate);
                    calendar.set(Calendar.MINUTE,0);
                    calendar.set(Calendar.SECOND,0);

                    calendar1.add(Calendar.DAY_OF_YEAR, 1);
                    calendar1.setTime(new Date());
                    calendar1.set(Calendar.HOUR_OF_DAY,startDate);
                    calendar1.set(Calendar.MINUTE,0);
                    calendar1.set(Calendar.SECOND,0);
                    startDateTime=calendar1.getTime();
                    endDateTime=calendar.getTime();
                }
                Map<String,Object> map=requestMap(request);
                map.put("Q_r.enabled_EQ",Boolean.TRUE);
                map.put("Q_r.purchase_EQ",Boolean.TRUE);
                map.put("Q_r.createDate","DESC");
                List<Map<String,Object>> models= goodsManager.findByNamedQuery("getGoods", map, obtainStart(request), obtainLimit(request));
                Integer count=goodsManager.findByNamedQueryClass("getGoodsCount", Integer.class, map);
                if(models!=null && models.size()>0){
                    for(Map<String,Object> model:models){
                        model.put("endDate",endDateTime);
                        model.put("currentDate",new Date());
                        model.put("startDate",startDateTime);
                    }
                }
                return new JsonReaderResponse(models,true,count,"");
            }
        } catch (Exception e){
            logger.error("purchase fail",e);
            return new JsonReaderResponse(null,false,"获取失败");
        }
    }


}
