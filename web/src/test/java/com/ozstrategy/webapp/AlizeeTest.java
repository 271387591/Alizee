package com.ozstrategy.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozstrategy.alipay.config.AlipayConfig;
import com.ozstrategy.alipay.util.AlipayNotify;
import com.ozstrategy.service.system.ApplicationConfigManager;
import com.ozstrategy.util.Base64Utils;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lihao on 12/30/14.
 */
public class AlizeeTest {
    /***
     * 
     */
    @Test
    public void testUsername(){
        Pattern pattern=Pattern.compile("^1(3[0-9]|5[0-35-9]|8[0235-9])\\d{8}$");
        Matcher matcher = pattern.matcher("13541287474");
        System.out.println(matcher.matches());

    }
    @Test
    public void testCalender(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);
        Integer chour=calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(calendar.getTime());

    }




    /**
     * 接口连接：http://121.42.153.185:8080/Alizee
     *
     * 后台登录地址：http://121.42.153.185:8080/Alizee/login
     * 用户名：admin密码：tomcat
     * 广告和美食故事，登录后台自己添加和修改
     *
     */






    /**
     * 获取验证码
     * 接口参数：mobile,
     * type，类型，取值：Register，表示获取注册验证码，GetBackPwd：表示获取找回密码时的验证码
     * 请求方式：POST
     * 
     * @throws Exception
     */
    @Test
    public void testGetValidateCode() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/getValidateCode";
//        String url="http://121.42.153.185:8080/Alizee/app/getValidateCode";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("mobile", "18583910581"));
        nvps.add(new BasicNameValuePair("type", "Register"));

        try {
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;

        try {
            response = httpclient.execute(httpost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        try {
            body = EntityUtils.toString(entity);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
    /**
     * 注册
     * 接口参数：mobile,password,validateCode,nickName
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testRegister() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/register";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("mobile", "18583910581"));
        nvps.add(new BasicNameValuePair("password", "tomcat"));
        nvps.add(new BasicNameValuePair("nickName", "李浩"));
        nvps.add(new BasicNameValuePair("validateCode", "639143"));

        try {
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;

        try {
            response = httpclient.execute(httpost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        try {
            body = EntityUtils.toString(entity);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }


    /**
     * 登录
     * 接口参数：username,password
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testLogin() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/login";
//        String url="http://121.42.153.185:8080/Alizee/app/login";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("username", "admin"));
        nvps.add(new BasicNameValuePair("password", "tomcat"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        Header[] headers = response.getHeaders("Set-Cookie");
        for(Header header:headers){
            System.out.println(header.getName()+"==="+header.getValue());
            HeaderElement[] elements=  header.getElements();
            for(HeaderElement element:elements){

                System.out.println(element.getName()+"===="+element.getValue());
                System.out.println("element.toString()==="+element.toString());
            }
        }
        String coockie = response.getFirstHeader("Set-Cookie").getValue();
        System.out.println("coockie==="+coockie);


        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
        
    }
    /**
     * 找回密码
     * 先调用获取验证码接口获得验证码
     * 接口参数：mobile,password，validateCode
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetBackPwd() throws Exception{
//        String url="http://120.24.234.71/im/app/login";
        String url="http://127.0.0.1:8082/Alizee/app/getBackPwd";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("mobile", "18583910581"));
        nvps.add(new BasicNameValuePair("validateCode", "333452"));
        nvps.add(new BasicNameValuePair("password", "tomcat"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

    }
    /**
     * 点赞通用接口
     * 接口参数：登录时的cookie
     * type，表示点赞的分类，目前只有广告和美食，后续会增加类型，取值：广告：Advert，美食：Food,活动：Activity,游戏：Game
     * itemId,点赞的记录ID，例如为某点广告点赞去该条广告的ID，为某条美食故事点赞取该条美食故事的ID
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testCommend() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/commend";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("type", "Advert"));
        nvps.add(new BasicNameValuePair("itemId", "1"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=1i9yfqhvcaty;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

    }
    /**
     * 检查用户是否为该条记录点赞
     * 登录时的cookie
     * type，表示点赞的分类，目前只有广告和美食，后续会增加类型，取值：广告：Advert，美食：Food,活动：Activity,游戏：Game
     * itemId,点赞的记录ID，例如为某点广告点赞去该条广告的ID，为某条美食故事点赞取该条美食故事的ID
     * 请求方式：POST
     * 该接口会返回该条赞的ID
     *
     * @throws Exception
     */
    @Test
    public void testCheckCommend() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/checkCommend";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("type", "Advert"));
        nvps.add(new BasicNameValuePair("itemId", "1"));
        httpost.addHeader("Cookie", "JSESSIONID=1i9yfqhvcaty;Path=/Alizee");
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

    }
    /**
     * 取消用户是否为该条记录点赞
     * 登录时的cookie
     * 该条赞的ID
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testCancelCommend() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/cancelCommend";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("id", "1"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=edxmvfg6ar0h;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

    }
    /**
     * 通用评价接口
     * 登录时的cookie
     * type，表示点赞的分类，目前只有广告和美食，后续会增加类型，取值：广告：Advert，美食：Food,活动：Activity,游戏：Game
     * itemId,点赞的记录ID，例如为某点广告点赞去该条广告的ID，为某条美食故事点赞取该条美食故事的ID
     * rank,评价等级，由客户端自己设计，取值为正数，例如：1、2、3、4、5个等级
     * content，评价内容
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testComment() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/comment";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("type", "Advert"));
        nvps.add(new BasicNameValuePair("itemId", "1"));
        nvps.add(new BasicNameValuePair("rank", "1"));
        nvps.add(new BasicNameValuePair("content", "1111111111"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=1i9yfqhvcaty;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
    /**
     * 检查改用户的是否评价该条记录
     * 登录时的cookie
     * type，表示点赞的分类，目前只有广告和美食，后续会增加类型，取值：广告：Advert，美食：Food,活动：Activity,游戏：Game
     * itemId,点赞的记录ID，例如为某点广告点赞去该条广告的ID，为某条美食故事点赞取该条美食故事的ID
     * 请求方式：POST
     * 返回该条评价的实体
     *
     * @throws Exception
     */
    @Test
    public void testCheckComment() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/checkComment";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("type", "Advert"));
        nvps.add(new BasicNameValuePair("itemId", "1"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=edxmvfg6ar0h;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

    }
    /**
     * 获取评论列表
     * * 登录时的cookie
     * type，表示点赞的分类，目前只有广告和美食，后续会增加类型，取值：广告：Advert，美食：Food,活动：Activity,游戏：Game
     * itemId,点赞的记录ID，例如为某点广告点赞去该条广告的ID，为某条美食故事点赞取该条美食故事的ID
     * * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     * 参数示例：比如每页显示30条数据，参数传递为：
     * 第一页：start=0&limit=30
     * 第二页：start=31&limit=30
     * 第三页：start=61&limit=30
     * .......
     *
     * 根据客户端需要获取几条，
     *
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetComments() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/getComments";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("type", "Advert"));
        nvps.add(new BasicNameValuePair("itemId", "1"));
        nvps.add(new BasicNameValuePair("start", "0"));
        nvps.add(new BasicNameValuePair("limit", "2"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

        Map map = new ObjectMapper().readValue("{\"data\":{},\"extraResData\":null,\"message\":\"\",\"success\":true,\"total\":0}", Map.class);
        System.out.println("sdfsfdsf");

    }

    /**
     * 删除评论
     * 登录时的cookie
     * id，该条评价的ID
     * 请求方式：POST
     * 返回该条评价的实体
     *
     * @throws Exception
     */
    @Test
    public void testCancelComment() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/cancelComment";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("id", "1"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=edxmvfg6ar0h;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

    }
    /**
     * 获取广告列表
     * * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     * 参数示例：比如每页显示30条数据，参数传递为：
     * 第一页：start=0&limit=30
     * 第二页：start=31&limit=30
     * 第三页：start=61&limit=30
     * .......
     *
     * 根据客户端需要获取几条，
     *
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetAdverts() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/getAdverts";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("start", "0"));
        nvps.add(new BasicNameValuePair("limit", "2"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

    }
    /**
     * 获取美食故事
     * * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     * 参数示例：比如每页显示30条数据，参数传递为：
     * 第一页：start=0&limit=30
     * 第二页：start=31&limit=30
     * 第三页：start=61&limit=30
     * .......
     *
     * 根据客户端需要获取几条，
     *
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetFoods() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/getFoods";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("start", "0"));
        nvps.add(new BasicNameValuePair("limit", "2"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

    }
    /**
     * 获取活动列表
     * 登录cookie
     * * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     * 参数示例：比如每页显示30条数据，参数传递为：
     * 第一页：start=0&limit=30
     * 第二页：start=31&limit=30
     * 第三页：start=61&limit=30
     * .......
     *
     * 根据客户端需要获取几条，
     *
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetActivity() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/getActivities";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();


        nvps.add(new BasicNameValuePair("start", "0"));
        nvps.add(new BasicNameValuePair("limit", "10"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=159eaqfp7yj9e;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

    }
    /**
     * 获取活动的参与人员列表
     * id 活动ID
     * 登录cookie
     * * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     * 参数示例：比如每页显示30条数据，参数传递为：
     * 第一页：start=0&limit=30
     * 第二页：start=31&limit=30
     * 第三页：start=61&limit=30
     * .......
     *
     * 根据客户端需要获取几条，
     *
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetActivityUser() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/getActivityUsers";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("id", "1"));
        nvps.add(new BasicNameValuePair("start", "0"));
        nvps.add(new BasicNameValuePair("limit", "10"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=1b73ahlwd7et4;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

    }

    /**
     * 用户加入活动
     * 参数：
     * id:活动ID。
     * 用户登录cookie
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testJoinActivity() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/joinActivity";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("id", "1"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie","JSESSIONID=bt3zudkcflnp;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

    }
     /**
     * 检查用户是否加入活动
     * 参数：
     * id:活动ID。
     * 用户登录cookie
     * 请求方式：POST
      * 如果加入，返回加入活动的详情，未加入，返回null
     *
     * @throws Exception
     */
    @Test
    public void testCheckJoinActivity() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/checkJoinActivity";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("id", "1"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie","JSESSIONID=bt3zudkcflnp;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

    }
    /**
     * 用户退出活动
     * 参数：
     * id:活动ID。
     * 用户登录cookie
     * 请求方式：POST
      * 如果加入，返回加入活动的详情，未加入，返回null
     *
     * @throws Exception
     */
    @Test
    public void testLogoutActivity() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/logoutActivity";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("id", "1"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie","JSESSIONID=bt3zudkcflnp;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
    /**
     * 获取游戏列表
     * * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     * 参数示例：比如每页显示30条数据，参数传递为：
     * 第一页：start=0&limit=30
     * 第二页：start=31&limit=30
     * 第三页：start=61&limit=30
     * .......
     *
     * 根据客户端需要获取几条，
     *
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetGames() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/getGames";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("start", "0"));
        nvps.add(new BasicNameValuePair("limit", "10"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }


    /**
     * 上传头像
     * 登录cookie
     *
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testPortrait() throws Exception{
//        String url="http://127.0.0.1:8082/Alizee/app/portrait";
        String url="http://121.42.153.185:8080/Alizee/app/portrait";
//        HttpPost httpost = new HttpPost(url);
//
//        FileBody bin = new FileBody(new File("/Users/lihao1/Pictures/871B841438B9CA8E34710197EB41E296.png"));
//
//        StringBody comment = new StringBody("871B841438B9CA8E34710197EB41E296.png");//文件名
//        MultipartEntity reqEntity = new MultipartEntity();
//
//        reqEntity.addPart("file", bin);
//        reqEntity.addPart("filename", comment);//filename为请求后台的普通参数;属性
//
//        httpost.setEntity(reqEntity);
//        httpost.addHeader("Cookie", "JSESSIONID=15tubnea743sv;Path=/Alizee");
//
//
//        DefaultHttpClient httpclient = new DefaultHttpClient();
//        HttpResponse response = null;
//        response = httpclient.execute(httpost);
//        HttpEntity entity = response.getEntity();
//
//        String charset = EntityUtils.getContentCharSet(entity);
//
//        String body = null;
//        body = EntityUtils.toString(entity);
//        System.out.println(body);
//        httpclient.getConnectionManager().shutdown();



        HttpPost httpost = new HttpPost(url);
        File file=new File("/Users/lihao1/Pictures/asdfasdf.png");
        FileInputStream fileInputStream=new FileInputStream(file);
        byte[] bytes = IOUtils.toByteArray(fileInputStream);
        //转成base64,请限制用户文件不能大于200K，格式为png/jpg/jpeg/bmp
        String str= Base64Utils.encode(bytes);

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("file", str));
        nvps.add(new BasicNameValuePair("fileName", "asdfasdf.png"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=vfi8vntaui7j;Path=/Alizee");

        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        int code  = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        String charset = EntityUtils.getContentCharSet(entity);
        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();


    }

    /**
     * 修改个人资料，所有参数可选
     * 登录cookie
     *nickName,昵称，不做重复判断，昵称是可以重名的如果昵称为空，你前端直接显示“游客”
     * gender，性别，取值：M,表示男，F，表示女，
     * birth，生日，格式：2015-04-23，
     * province，省
     * city，市
     * country，县
     * address，县以下的地区
     * postalCode，邮编，
     *
     * 请求方式：POST/get
     *
     * @throws Exception
     */
    @Test
    public void testUpdateUser() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/updateUser";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("gender", "F"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=eyc6nlyt4o47;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

    }
    /**
     *用户反馈
     * 登录cookie
     * content，反馈内容
     * contract，联系方式
     *
     * 请求方式：POST/get
     *
     * @throws Exception
     */
    @Test
    public void testUserComments() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/userComments";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("content", "sdfsdfsdf"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=1er2klujrrjqi;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

    }
    /**
     * 获取评论列表
     * 登录cookie
     * * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     * 参数示例：比如每页显示30条数据，参数传递为：
     * 第一页：start=0&limit=30
     * 第二页：start=31&limit=30
     * 第三页：start=61&limit=30
     * .......
     *
     * 根据客户端需要获取几条，
     *
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetUserComments() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/getUserComments";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("start", "0"));
        nvps.add(new BasicNameValuePair("limit", "10"));
        httpost.addHeader("Cookie", "JSESSIONID=1er2klujrrjqi;Path=/Alizee");
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
     /**
     * 获取管理员通知
     * * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     * 参数示例：比如每页显示30条数据，参数传递为：
     * 第一页：start=0&limit=30
     * 第二页：start=31&limit=30
     * 第三页：start=61&limit=30
     * .......
     *
     * 根据客户端需要获取几条，
     *
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetNotice() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/getNotice";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("start", "0"));
        nvps.add(new BasicNameValuePair("limit", "10"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }

//------------------------------------------------------------------------------------------------------------------------


    /**
     * 充值提交订单
     * 登录cookie
     *money,充值金额
     *
     * 请求方式：POST
     * 返回支付信息，直接调用客户端支付宝，支付成功后，重新获取一次用户信息，会返回用户积分，积分比例1元=100积分
     * 返回数据和积分项目相同，请参照之前的解析
     *
     * @throws Exception
     */
    @Test
    public void testRecharge() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/recharge";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("money", "4"));
        nvps.add(new BasicNameValuePair("channel", "10000000"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=1hu4lw0ez42nf;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

    }
     /**
     * 获取充值记录
      * 登录cookie
     * * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     * 参数示例：比如每页显示30条数据，参数传递为：
     * 第一页：start=0&limit=30
     * 第二页：start=31&limit=30
     * 第三页：start=61&limit=30
     * .......
     *
     * 根据客户端需要获取几条，返回的status，0表示未支付，1表示成功，2表示失败
     *
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetRecharge() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/getRecharge";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("start", "0"));
        nvps.add(new BasicNameValuePair("limit", "10"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=16fw828kqriqt;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
    /**
     * 获取抢购商品
     * * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     * 参数示例：比如每页显示30条数据，参数传递为：
     * 第一页：start=0&limit=30
     * 第二页：start=31&limit=30
     * 第三页：start=61&limit=30
     * .......
     *
     * 根据客户端需要获取几条，
      * 返回中得，currentDate表示服务器当前时间，endDate表示结束时间，你自己通过客户端计算倒计时
     *
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testPurchaseGoods() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/purchaseGoods";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("start", "0"));
        nvps.add(new BasicNameValuePair("limit", "10"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=16fw828kqriqt;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }

    /**
     * 获取所有商品，非抢购商品
     * 参数：
     * Q_r.purchase_EQ,取值:0表示非抢购，不传表示所有
     * * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     *
     * .......
     *
     * 根据客户端需要获取几条，
      * 返回中得，currentDate表示服务器当前时间，endDate表示结束时间，你自己通过客户端计算倒计时
     *
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetGoods() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/getGoods";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("start", "0"));
        nvps.add(new BasicNameValuePair("limit", "10"));
        nvps.add(new BasicNameValuePair("Q_r.purchase_EQ", "0"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=16fw828kqriqt;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }

    /**
     * 抢购
     *id:商品ID
     *num:数量
     * 返回订单的信息
     *
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testPurchase() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/purchase";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("id", "1"));
        nvps.add(new BasicNameValuePair("num", "1"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=1tpaxtr3nkdxd;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
    /**
     * 获取用户订单，订单包含兑换券信息，一个订单可能有多个兑换券（会返回商品的相关信息）
     * 登录cookie
     * * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     * 参数示例：比如每页显示30条数据，参数传递为：
     * 第一页：start=0&limit=30
     * 第二页：start=31&limit=30
     * 第三页：start=61&limit=30
     * .......
     *
     * 根据客户端需要获取几条，
     * 返回中得，currentDate表示服务器当前时间，endDate表示结束时间，你自己通过客户端计算倒计时
     *
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetGoodsOrder() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/getGoodsOrder";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=1047fq6cuq88c;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
     /**
     * 获取用户余额、用户基本信息
     * 登录cookie
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetCredits() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/getCredits";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=p8lzm7nak6ie;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }

    /**
     * 获取用户兑换券接口
     * 登录cookie
     * 参数：Q_r.enabled_EQ,取值：1表示未使用，0表示已使用
     * * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetUserCert() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/getUserCert";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        nvps.add(new BasicNameValuePair("start", "0"));
        nvps.add(new BasicNameValuePair("limit", "10"));
        nvps.add(new BasicNameValuePair("Q_r.enabled_EQ", "1"));
        httpost.addHeader("Cookie", "JSESSIONID=b6rqguk1681n;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }






//    ---------------

    /*
    获取最新版本
    参数:plat,0表示Android，1表示IOS
    返回版本信息，对比规则由客户端自己设定
     */

    @Test
    public void testGetLast() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/getLast";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        nvps.add(new BasicNameValuePair("plat", "0"));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;

        response = httpclient.execute(httpost);

        HttpEntity entity = response.getEntity();


        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
     /*
    第三方消费
    登录cookie
    channelNo：渠道编号
    goodsName：商品名称
    num：商品数量
    goodsPrice：商品单价

    返回版本信息，对比规则由客户端自己设定
     */

    @Test
    public void testThreeGoods() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/threeGoods";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        nvps.add(new BasicNameValuePair("channelNo", "0"));
        nvps.add(new BasicNameValuePair("goodsName", "0"));
        nvps.add(new BasicNameValuePair("num", "0"));
        nvps.add(new BasicNameValuePair("goodsPrice", "0"));
        httpost.addHeader("Cookie", "JSESSIONID=b6rqguk1681n;Path=/Alizee");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
     /*
    查询第三方个人消费消费
    登录cookie
    返回版本信息，对比规则由客户端自己设定
     */

    @Test
    public void testThreeGoodsList() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/threeGoodsList";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        httpost.addHeader("Cookie", "JSESSIONID=b6rqguk1681n;Path=/Alizee");
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }

















}
