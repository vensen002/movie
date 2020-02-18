package club.vensen.movie.util;

import club.vensen.movie.util.constan.StringConstans;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author by VENSEN
 * @Classname MD5Util
 * @Description TODO()
 * @Date 2020/2/9 15:36
 */
public class MD5Util {

    public static String encode(String password) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance ("MD5");
            byte[] bytes = md5.digest (password.getBytes ("utf-8"));
            return toString(bytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace ();
        }
        return StringConstans.EMPTY_STRING;
    }

    public static String encode(String password,String salt) {
        return encode (password + salt);
    }

    private static String toString(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer ();
        for (int i = 0; i < bytes.length; i++) {
            int val =((int) bytes[i]) & 0xff;
            if (val < 16) {
                stringBuffer.append ("0");
            }
            stringBuffer.append (Integer.toHexString (val));
        }
        return stringBuffer.toString ();
    }

}
