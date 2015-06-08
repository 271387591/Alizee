package com.ozstrategy.dao.test;

import com.ozstrategy.model.user.Role;
import com.ozstrategy.model.user.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by lihao1 on 6/8/15.
 */
public class TemplateTest {
    public static String rootPath=System.getProperty("user.dir")+"/core/src/main/java/";
    public static String webRootPath=System.getProperty("user.dir")+"/web/src/main/java/";
    public static List<Class> list=new ArrayList<Class>();
    static {
        list.add(User.class);
        list.add(Role.class);
    }
    public static void main(String[] args){
//        daoTemp();
//        managerTemp();
        commandTemp();
        controllerTemp();

    }
    public static void daoTemp(){
        for(Class cl:list){
            String firstLowwer= cl.getSimpleName().substring(0,1).toLowerCase()+cl.getSimpleName().substring(1);
            String pak=cl.getPackage().getName();
            pak=pak.substring(pak.lastIndexOf(".")+1);
            String dapPakage="com.ozstrategy.dao.";
            dapPakage=dapPakage+pak;
            String daoPath=rootPath+"com/ozstrategy/dao/";
            File file=new File(daoPath+pak);
            if(!file.exists()){
                file.mkdirs();
            }
            String implPakage=dapPakage+".impl";

            String implPath=daoPath+pak+"/impl";

            File implFile=new File(implPath);
            if(!implFile.exists()){
                implFile.mkdirs();
            }


            String daoName=cl.getSimpleName()+"Dao.java";
            String implName=cl.getSimpleName()+"DaoImpl.java";
            try {
                Configuration configuration = new Configuration();
                configuration.setDefaultEncoding("utf-8");
                configuration.setClassLoaderForTemplateLoading(TemplateTest.class.getClassLoader(),"resources/template");
                Template t = null;
                //获取模板信息
                t = configuration.getTemplate("daotemp.ftl");
                File outFile = new File(file,daoName);
                Writer out = null;
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
                Map map = new HashMap<String, Object>();
                map.put("basepakege", dapPakage);
                map.put("modelNameFullName", cl.getName());
                map.put("date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
                map.put("daoName", cl.getSimpleName()+"Dao");
                map.put("modelName", cl.getSimpleName());
                //输出数据到模板中，生成文件。
                t.process(map, out);
                out.close();

                t=configuration.getTemplate("daoimpltemp.ftl");

                File imploutFile = new File(implFile,implName);
                Writer implout = null;
                implout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(imploutFile), "UTF-8"));
                map = new HashMap<String, Object>();
                map.put("pakegeName", implPakage);
                map.put("daoFullName", dapPakage+"."+cl.getSimpleName()+"Dao");
                map.put("modelFullName", cl.getName());
                map.put("firstLowwer", firstLowwer+"Dao");
                map.put("className", cl.getSimpleName()+"DaoImpl");
                map.put("date", DateFormatUtils.format(new Date(),"yyyy-MM-dd"));
                map.put("modelName", cl.getSimpleName());
                map.put("daoName", cl.getSimpleName()+"Dao");
                //输出数据到模板中，生成文件。
                t.process(map, implout);
                implout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void managerTemp(){
        for(Class cl:list){
            String firstLowwer= cl.getSimpleName().substring(0,1).toLowerCase()+cl.getSimpleName().substring(1);
            String pak=cl.getPackage().getName();
            pak=pak.substring(pak.lastIndexOf(".")+1);
            String dapPakage="com.ozstrategy.dao."+pak;
            String managerPak="com.ozstrategy.service."+pak;
            String daoPath=rootPath+"com/ozstrategy/service/";
            File file=new File(daoPath+pak);
            if(!file.exists()){
                file.mkdirs();
            }
            String implPakage=managerPak+".impl";

            String implPath=daoPath+pak+"/impl";

            File implFile=new File(implPath);
            if(!implFile.exists()){
                implFile.mkdirs();
            }


            String daoName=cl.getSimpleName()+"Manager.java";
            String implName=cl.getSimpleName()+"ManagerImpl.java";
            try {
                Configuration configuration = new Configuration();
                configuration.setDefaultEncoding("utf-8");
                configuration.setClassLoaderForTemplateLoading(TemplateTest.class.getClassLoader(),"resources/template");
                Template t = null;
                //获取模板信息
                t = configuration.getTemplate("managertemp.ftl");
                File outFile = new File(file,daoName);
                Writer out = null;
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
                Map map = new HashMap<String, Object>();
                map.put("basepakege", managerPak);
                map.put("modelNameFullName", cl.getName());
                map.put("date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
                map.put("daoName", cl.getSimpleName()+"Manager");
                map.put("modelName", cl.getSimpleName());
                //输出数据到模板中，生成文件。
                t.process(map, out);
                out.close();

                t=configuration.getTemplate("managerimpltemp.ftl");

                File imploutFile = new File(implFile,implName);
                Writer implout = null;
                implout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(imploutFile), "UTF-8"));
                map = new HashMap<String, Object>();
                map.put("pakegeName", implPakage);
                map.put("daoFullName", dapPakage+"."+cl.getSimpleName()+"Dao");
                map.put("modelFullName", cl.getName());
                map.put("managerFullName", managerPak+"."+cl.getSimpleName()+"Manager");
                map.put("firstLowwer", firstLowwer+"Manager");

                map.put("className", cl.getSimpleName()+"ManagerImpl");
                map.put("date", DateFormatUtils.format(new Date(),"yyyy-MM-dd"));
                map.put("modelName", cl.getSimpleName());
                map.put("managerName", cl.getSimpleName()+"Manager");
                map.put("daoName", cl.getSimpleName()+"Dao");
                map.put("firstLowwerDao", firstLowwer+"Dao");
                //输出数据到模板中，生成文件。
                t.process(map, implout);
                implout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void commandTemp(){
        for(Class cl:list){
            Map<String,String> fieldMap=new HashMap<String, String>();
            Field[] fields= cl.getDeclaredFields();
            for(Field field:fields){
                fieldMap.put(field.getName(),field.getType().getSimpleName());
            }
            String firstLowwer= cl.getSimpleName().substring(0,1).toLowerCase()+cl.getSimpleName().substring(1);
            String pak=cl.getPackage().getName();
            pak=pak.substring(pak.lastIndexOf(".")+1);
            String cmdPakage="com.ozstrategy.webapp.command."+pak;
            String cmdPath=webRootPath+"com/ozstrategy/webapp/command/";
            File file=new File(cmdPath+pak);
            if(!file.exists()){
                file.mkdirs();
            }
            String daoName=cl.getSimpleName()+"Command.java";
            try {
                Configuration configuration = new Configuration();
                configuration.setDefaultEncoding("utf-8");
                configuration.setClassLoaderForTemplateLoading(TemplateTest.class.getClassLoader(),"resources/template");
                Template t = null;
                //获取模板信息
                t = configuration.getTemplate("cmdtemp.ftl");
                File outFile = new File(file,daoName);
                Writer out = null;
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
                Map map = new HashMap<String, Object>();
                map.put("basepakege", cmdPakage);
                map.put("modelNameFullName", cl.getName());
                map.put("date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
                map.put("className", cl.getSimpleName()+"Command");
                map.put("modelName", cl.getSimpleName());
                map.put("fields", fieldMap.keySet());
                map.put("fieldMap", fieldMap);
                //输出数据到模板中，生成文件。
                t.process(map, out);
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void controllerTemp(){
        for(Class cl:list){
            Map<String,String> fieldMap=new HashMap<String, String>();
            Field[] fields= cl.getDeclaredFields();
            for(Field field:fields){
                fieldMap.put(field.getName(),field.getType().getSimpleName());
            }
            String firstLowwer= cl.getSimpleName().substring(0,1).toLowerCase()+cl.getSimpleName().substring(1);
            String pak=cl.getPackage().getName();
            pak=pak.substring(pak.lastIndexOf(".")+1);
            String cmdPakage="com.ozstrategy.webapp.controller."+pak;
            String cmdPath=webRootPath+"com/ozstrategy/webapp/controller/";
            File file=new File(cmdPath+pak);
            if(!file.exists()){
                file.mkdirs();
            }
            String daoName=cl.getSimpleName()+"Controller.java";
            try {
                Configuration configuration = new Configuration();
                configuration.setDefaultEncoding("utf-8");
                configuration.setClassLoaderForTemplateLoading(TemplateTest.class.getClassLoader(),"resources/template");
                Template t = null;
                //获取模板信息
                t = configuration.getTemplate("controllertemp.ftl");
                File outFile = new File(file,daoName);
                Writer out = null;
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
                Map map = new HashMap<String, Object>();
                map.put("basepakege", cmdPakage);
                map.put("modelFullName", cl.getName());
                String managerPak="com.ozstrategy.service."+pak+"."+cl.getSimpleName()+"Manager";
                map.put("managerFullName", managerPak);
                map.put("commandFullName", "com.ozstrategy.webapp.command."+pak+"."+cl.getSimpleName()+"Command");

                map.put("date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
                map.put("modelName", cl.getSimpleName());
                map.put("className", cl.getSimpleName()+"Controller");
                map.put("managerName", cl.getSimpleName()+"Manager");
                map.put("commandName", cl.getSimpleName()+"Command");
                map.put("fieldMap", fieldMap);
                //输出数据到模板中，生成文件。
                t.process(map, out);
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




}
