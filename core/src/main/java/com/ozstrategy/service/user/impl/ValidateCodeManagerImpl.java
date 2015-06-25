package com.ozstrategy.service.user.impl;

import com.ozstrategy.Constants;
import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.system.ApplicationConfigDao;
import com.ozstrategy.dao.user.ValidateCodeDao;
import com.ozstrategy.model.system.ApplicationConfig;
import com.ozstrategy.model.user.ValidateCode;
import com.ozstrategy.model.user.ValidateCodeType;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.user.ValidateCodeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("validateCodeManager")
public class ValidateCodeManagerImpl extends BaseManagerImpl<ValidateCode> implements ValidateCodeManager {
    @Autowired
    private ValidateCodeDao validateCodeDao;
    @Autowired
    private ApplicationConfigDao applicationConfigDao;


    @Override
    public BaseDao<ValidateCode> baseDao() {
        return validateCodeDao;
    }
    public boolean sendCode(String mobile,ValidateCodeType validateCodeType) throws Exception{

//发送内容
        String content = ""+((int)((Math.random()*9+1)*100000));
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_mobile_EQ",mobile);
        map.put("Q_loseDate_GE",new Date());
        map.put("Q_type_EQ",validateCodeType.ordinal());
        ValidateCode validateCode=validateCodeDao.getByParam(map);
        if(validateCode!=null){
            content=validateCode.getCode();
        }else{
            map.remove("Q_loseDate_GE");
            validateCodeDao.deleteByParam(map);
            validateCode=new ValidateCode();
            validateCode.setMobile(mobile);
            validateCode.setCode(content);
            Calendar ca=Calendar.getInstance();
            ca.setTime(new Date());
            Integer loseMin=10;
            ca.add(Calendar.MINUTE,loseMin);
            Date loseDate = ca.getTime();
            validateCode.setLoseDate(loseDate);
            validateCode.setType(validateCodeType.ordinal());
            validateCodeDao.save(validateCode);
        }


        String sign=applicationConfigDao.getValue(Constants.messageSign);
        String messageContent=applicationConfigDao.getValue(Constants.messageContent);
        messageContent= MessageFormat.format(messageContent,content);

        // 创建StringBuffer对象用来操作字符串
        StringBuffer sb = new StringBuffer("http://web.cr6868.com/asmx/smsservice.aspx?");

        // 向StringBuffer追加用户名
        sb.append("name="+applicationConfigDao.getValue(Constants.messageUsername));

        // 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
        sb.append("&pwd="+applicationConfigDao.getValue(Constants.messagePwd));

        // 向StringBuffer追加手机号码
        sb.append("&mobile="+mobile);

        // 向StringBuffer追加消息内容转URL标准码
        sb.append("&content="+ URLEncoder.encode(messageContent, "UTF-8"));

        //追加发送时间，可为空，为空为及时发送
        sb.append("&stime=");

        //加签名
        sb.append("&sign="+URLEncoder.encode(sign,"UTF-8"));

        //type为固定值pt  extno为扩展码，必须为数字 可为空
        sb.append("&type=pt&extno=");
        // 创建url对象
        //String temp = new String(sb.toString().getBytes("GBK"),"UTF-8");
        System.out.println("sb:"+sb.toString());
        URL url = new URL(sb.toString());

        // 打开url连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置url请求方式 ‘get’ 或者 ‘post’
        connection.setRequestMethod("POST");

        // 发送
        InputStream is =url.openStream();

        //转换返回值
        String returnStr = convertStreamToString(is);
        String[] strings=returnStr.split(",");
        if(strings!=null && strings.length>3 && strings[2].equals("1")){
            return true;

        }

        // 返回结果为‘0，20140009090990,1，提交成功’ 发送成功   具体见说明文档
        //0,2015061011213829611472108,0,1,0,提交成功
        return false;
    }
    private static String convertStreamToString(InputStream is) {
        StringBuilder sb1 = new StringBuilder();
        byte[] bytes = new byte[4096];
        int size = 0;

        try {
            while ((size = is.read(bytes)) > 0) {
                String str = new String(bytes, 0, size, "UTF-8");
                sb1.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb1.toString();
    }

}
