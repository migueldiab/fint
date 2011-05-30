package edu.ort.common.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;
import sun.misc.BASE64Encoder;

/**
 *
 * @author migueldiab
 */
public class EncryptUtils {
  protected static final Logger LOGGER = Logger.getLogger(EncryptUtils.class);

  public static String encodeSha256(String s) throws UnsupportedEncodingException, NoSuchAlgorithmException {
    MessageDigest m = null;
    String result = null;
    m = MessageDigest.getInstance("SHA-256");
    m.reset();
    byte[] hash = m.digest(s.getBytes("UTF-8"));
    result = byteToBase64(hash);
    return result;
  }

   /**
    * From a byte[] returns a base 64 representation
    * @param data byte[]
    * @return String
    * @throws IOException
    */
   public static String byteToBase64(byte[] data){
       BASE64Encoder endecoder = new BASE64Encoder();
       return endecoder.encode(data);
   }

  public static String encodeMD5(String s) throws NoSuchAlgorithmException {
    MessageDigest m = null;
    String result = null;
    m = MessageDigest.getInstance("MD5");
    m.update(s.getBytes(),0,s.length());
    result = new BigInteger(1,m.digest()).toString(16);
    if (result.length()==31) { result = "0" + result; }
    return result;
  }

}
