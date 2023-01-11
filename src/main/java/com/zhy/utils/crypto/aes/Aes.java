package com.zhy.utils.crypto.aes;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author qingnianren
 */
public class Aes {


    public static final String ALGORITHM = "AES";

    /**
     * 生成密钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator secretGenerator = KeyGenerator.getInstance(ALGORITHM);
        SecureRandom secureRandom = new SecureRandom();
        secretGenerator.init(secureRandom);
        SecretKey secretKey = secretGenerator.generateKey();
        return secretKey;
    }

    static Charset charset = Charset.forName("UTF-8");

    /**
     * 加密
     *
     * @param content
     * @param secretKey
     * @return
     */
    public static byte[] encrypt(String content, SecretKey secretKey) throws Exception { // 加密
        byte[] bytes = content.getBytes(charset);

        byte[] aes = aes(bytes, Cipher.ENCRYPT_MODE, secretKey);
        return aes;
    }

    /**
     * 解密
     *
     * @param contentArray
     * @param secretKey
     * @return
     */
    public static String decrypt(byte[] contentArray, SecretKey secretKey) throws Exception { // 解密
        byte[] result = aes(contentArray, Cipher.DECRYPT_MODE, secretKey);
        return new String(result, charset);
    }

    private static byte[] aes(byte[] contentArray, int mode, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(mode, secretKey);
        byte[] result = cipher.doFinal(contentArray);
        return result;
    }

    public static void main(String[] args) throws Exception {
        String content = "青年人需要认识到自己所负的责任！";

        long timeStartEncry = System.currentTimeMillis();
        // 生成密钥

        SecretKey secretKey = generateKey();

        byte[] encryptResult = encrypt(content, secretKey);

        System.out.println("encryptResult:" + encryptResult);

        long timeEndEncry = System.currentTimeMillis();
        System.out.println("加密后的结果为==" + new String(encryptResult, charset));

        String decryptResult = decrypt(encryptResult, secretKey);
        System.out.println("解密后的结果==" + decryptResult);

    }
}
