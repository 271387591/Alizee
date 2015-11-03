package com.ozstrategy.util;
 
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
 
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
 
 
/**
 * SecretUtils {3DES加密解密的工具类 }
 * @author William
 * @date 2013-04-19
 */
public class SecretUtils {

    public static String encryptThreeDESECB(String src) throws Exception
    {
        DESedeKeySpec dks = new DESedeKeySpec(PASSWORD_CRYPT_KEY.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey securekey = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, securekey);
        byte[] b=cipher.doFinal(src.getBytes());

        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b).replaceAll("\r", "").replaceAll("\n", "");
    }

    public static String decryptThreeDESECB(String src) throws Exception
    {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytesrc = decoder.decodeBuffer(src);
        DESedeKeySpec dks = new DESedeKeySpec(PASSWORD_CRYPT_KEY.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey securekey = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        byte[] retByte = cipher.doFinal(bytesrc);

        return new String(retByte);
    }
    private static final String PASSWORD_CRYPT_KEY = "D6D2402F1C98E208FF2E863AA29334BD65AE1932A821502D9E5673CDE3C713ACFE53E2103CD40ED6BEBB101B484CAE83D537806C6CB611AEE86ED2CA8C97BBE95CF8476066D419E8E833376B850172107844D394016715B2E47E0A6EECB3E83A361FA75FA44693F90D38C6F62029FCD8EA395ED868F9D718293E9C0E63194E87";
    public static void main(String[] args) {
        try {
            String sss="lkasjfljdlfj离开的法律记录记录";
            String en=encryptThreeDESECB(sss);
            System.out.println(en);
            String kkk=decryptThreeDESECB(en);
            System.out.println(kkk);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}