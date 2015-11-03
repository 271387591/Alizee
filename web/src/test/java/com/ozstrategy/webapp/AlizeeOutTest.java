package com.ozstrategy.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozstrategy.util.Base64Utils;
import com.ozstrategy.util.ThreeDESUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lihao on 12/30/14.
 */
public class AlizeeOutTest {
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
     * 对外接口说明文档
     */


    /**
     * 获取验证码
     * 接口参数：mobile,
     * type，类型，取值：Register，表示获取注册验证码，GetBackPwd：表示获取找回密码时的验证码
     * channel，渠道编号、由后台统一分配
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
     * * channel，渠道编号、由后台统一分配
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
     * channel，渠道编号、由后台统一分配
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
     * 上传头像
     * 登录cookie
     *
     *channel，渠道编号、由后台统一分配
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testPortrait() throws Exception{
        String url="http://127.0.0.1:8082/Alizee/app/portrait";

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
        httpost.addHeader("Cookie", "JSESSIONID=1tjk95ha2givl;Path=/Alizee");

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
     *channel，渠道编号、由后台统一分配
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


//------------------------------------------------------------------------------------------------------------------------


    /**
     * 充值提交订单
     * 登录cookie
     *money,充值金额
     *channel，渠道编号、由后台统一分配
     * detail,充值商品描述（不能为空）
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
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=194t9sm4gph1g;Path=/Alizee");
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
      * channel，渠道编号、由后台统一分配
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
    @Test
    public void testss()throws Exception{
        String sq="{\"message\":\"\",\"data\":\"mhu+sbM93zXdu95pn4XCHh+za8NXjo+vW37hTt3VEoF4CDyTxl8ncyEyoRUj+qcrl0q6JP9CaHLAfCEebYznxw9iyc71IoU2QX3TXgfESAqBVDD5BIH6xhj/nwf1J44Ap+dcYkfJglR14HNpuB+ib2f+I6J8PoaBtDq+O5vjX5yPBZME5Iv8G4N3oi9D0DejJqBQhljq5lnO46q7EU0+LWkKO+32rFD8bcSf/8Gz0HbLdNk/m4TqSBxk5DzZh5NwWveqPQBOQiiP9pjD5fE6J5d6U255lKqr9aqpMCoS2eTWSh3Nxy3hY19PJSzu7Rux6OCiCKgRYfT6zsf+EdMDfBOSUJkZBODNWoC3WIOQSuJUyfZNo3gqEt5mBTEX0vLenDGNDlMPNjk4nLmsCAz7uQT0qE5GHt8GkrYYgsD3C7nxWfB2NH6WO3AEzHHzq7TFgAb9ycAMvlLWPzG/vncNpvJHqofkhdHrMbJW7nmuvy9zv9URCzQ0d9mbs5UF+DqRteBL2qMFuYqSStaXEaBNowGBnKsTV8XW1uO0Tz5GLphOBexPI49AFL766kqbmllpvxOGJnB8uHeUHdfuH86VHe4rS/WhOgPpTZkfH66tYkHwXjjEnNw6fLTf7OS9zh+SJU5wArp5I2ceDFzk+6qYkzTxgjuZpN6qZQfeEOcfZ7mgGzv4ln9qOC7TpOTYmiZtEXowOftWqDkKUqn+vGQth1ts0bDwR19hPuiSobcA3GU=\",\"success\":true}";
        Map<String,Object> map=new ObjectMapper().readValue(sq,Map.class);
        String data=map.get("data").toString();

        //解码data开始
        byte[] bb= (Base64Utils.decode(data));
        //billstr
        String billstr=new String(ThreeDESUtils.decrypt(bb));
        System.out.println("billstr=="+billstr);
        Map bill=new ObjectMapper().readValue(billstr, Map.class);
        //bills
        String ss=bill.get("bills").toString();

        String sss=new String(Base64Utils.decode(ss));


        System.out.println(sss);

    }














}
