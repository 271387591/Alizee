package com.ozstrategy.webapp;

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

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
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

        nvps.add(new BasicNameValuePair("username", "13333333333"));
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
     * 获取商品列表
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
    public void testGetGoods() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/getGoods";
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
        String url="http://127.0.0.1:8082/Alizee/app/portrait";
        HttpPost httpost = new HttpPost(url);

        FileBody bin = new FileBody(new File("/Users/lihao1/Pictures/871B841438B9CA8E34710197EB41E296.png"));

        StringBody comment = new StringBody("871B841438B9CA8E34710197EB41E296.png");//文件名
        MultipartEntity reqEntity = new MultipartEntity();

        reqEntity.addPart("file", bin);
        reqEntity.addPart("filename", comment);//filename为请求后台的普通参数;属性

        httpost.setEntity(reqEntity);
        httpost.addHeader("Cookie", "JSESSIONID=15tubnea743sv;Path=/Alizee");


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
     * 充值提交订单
     * 登录cookie
     *money,充值金额
     * credits，换算成的游游币
     *
     * 请求方式：POST
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
        nvps.add(new BasicNameValuePair("credits", "4"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=dnp0hap83nrc;Path=/Alizee");
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









}
