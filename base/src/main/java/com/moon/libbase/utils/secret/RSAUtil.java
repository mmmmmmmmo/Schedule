package com.moon.libbase.utils.secret;

import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAUtil {
    private static final String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyg2kPQelCkgxen29EUeR" +
            "+w3ivraWtP39U6pDsgY+ffscNHUH+0VKPNLzNpEpf0tzEJeuVmxvCDBwhuEFjHYj" +
            "K/12xDQnJPBJGh1/5ySjpnwSgOi961GdzbuqLcHRO43w0afaCCD48BeO0iG//jEk" +
            "aBPclPHe4qlVVAL9YceOA09/FwbBJXVCNead/ybU8M9YizjAX63DycnxXDMoHGnB" +
            "N9AZhc1iSOkw+UIKbPZ8hbh3xVM+F0HxarnUlvNfFHQmu7FRwlKMWWpXd2q6wqwJ" +
            "OOLXcuJ9mGXTnDOw6k8pExmIyNHet7UHaFJQpdO6YYfV29dmShT1ciDKIob9j5nQ" +
            "ZwIDAQAB";

    private static final String privateKeyStr = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDKDaQ9B6UKSDF6" +
            "fb0RR5H7DeK+tpa0/f1TqkOyBj59+xw0dQf7RUo80vM2kSl/S3MQl65WbG8IMHCG" +
            "4QWMdiMr/XbENCck8EkaHX/nJKOmfBKA6L3rUZ3Nu6otwdE7jfDRp9oIIPjwF47S" +
            "Ib/+MSRoE9yU8d7iqVVUAv1hx44DT38XBsEldUI15p3/JtTwz1iLOMBfrcPJyfFc" +
            "MygcacE30BmFzWJI6TD5Qgps9nyFuHfFUz4XQfFqudSW818UdCa7sVHCUoxZald3" +
            "arrCrAk44tdy4n2YZdOcM7DqTykTGYjI0d63tQdoUlCl07phh9Xb12ZKFPVyIMoi" +
            "hv2PmdBnAgMBAAECggEAU6rmcacyvlk6s+BRZFBgsLcFR7fQHE54kiaVNajEQ+Kx" +
            "wT8c+P2uShWIRPZDWsThduN4e60XJSr5zcBsTce1tMeWYJTACNm/S6MfjZVsdso1" +
            "10+JJWJ8l+63/46cl66lJAv/ixIsQy9eIsF7AUVUMAzZcIvfobhA7zL1vYGm2tNZ" +
            "6NQsn+5BIGxpqcQAGf0EkqJlRQcTojuOrehMpmKAq2ewGMiyioX7lvtNWI9Vr9yN" +
            "C2ofun9E8uAtlAGct43ZgjVc5RIVaLkeSitk1cJXybyuCVHY8sLYkmf08KhfySna" +
            "jOUsesfj3BkVQK4XnWx5C9mLWiaGEv7vlAgAJgDj0QKBgQDsxdHvVjs03EbFE1fO" +
            "ROJQIHYuowvOu95VEHeiiOX+GX5kcLTltcdyIP2/J5n6DEYJPLGIPRPHVj7XJh7F" +
            "/O3cLHv9BW6GsQctOKE4tnhlnf7HPVzJ4ER6GATizPAZOScZvdNr08CS4b3QomjJ" +
            "ftXstilX1mQiOGSGSrvQyvP4BQKBgQDadg1HzL84lfbDwE4+jerlSnOOoRplG/b1" +
            "pxzeKsG/ZxCt2iFXZWnLs+I5Amqel9a3vHdEzFKoUIp0zryhdSdh7zsTLCzXp06X" +
            "SBw1m8PSR7QzeHsPC+F5UEHUKkN+ZQ2pLrrRcy+m/mlSGC1WxPXh5j67iVAc9eG/" +
            "VYsG5ynuewKBgHXt6axtpLulY1JRWhCQ2q/SG0sLRpxHkmuH8i5wRS2UgRGB2Xf4" +
            "XEiKChilntmeN1vucPZMhYdpe48pm3Y9jjN1rKPPisdncQ2jARoAOGNUmEugUpDy" +
            "NOkjAXv9WzEU+Fan55/zLh16dlJuMgb6N47ZBCqNy/4HXHpOytEp5O4JAoGBAKPp" +
            "0bseuBdoBfgIUW0EJMwX2a6Z2Gkm21ykH7fekvLfDRe0xDGECSZreQDXfFI0kUnZ" +
            "IhA/0oExtUFBYS0v7vW/fy66hYblpMVDuM7MadhcLqwz6Pl4K5xPiKT9dRjgD6jy" +
            "RAAlkgqVd0a+lz40mFz/2e/ybmbcYp6iO6dZSRADAoGBAJTazxAyXbaBMIwZGrem" +
            "XlYWT5FHTZeMxxn0Edz+uGZVVDA3fYqXNxVyS8B7tejtk2QQSrGbVPe7lKmEkhEv" +
            "v8KFtbCH1qWeGcFFqDxRKjwDPDTPlCTOOQEjcnfKTcArtQUpiC4MWVRb/jLNjN9L" +
            "TqbMevqF8k+dAETh+f4rcfDe";

    private static RSAPublicKey publicKey;
    private static RSAPrivateKey privateKey;

    public static void loadPublicKey(String publicKeyStr) throws Exception {
        try {
            byte[] buffer = Base64.decode(publicKeyStr, Base64.DEFAULT);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }

    public static void loadPrivateKey(String privateKeyStr) throws Exception {
        try {
            byte[] buffer = Base64.decode(privateKeyStr, Base64.DEFAULT);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NullPointerException e) {
            throw new Exception("无此算法");
        }
    }

    public static byte[] encrypt(String data) throws Exception {
        if (publicKey == null) {
            loadPublicKey(publicKeyStr);
        }
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] decrypt(byte[] data) throws Exception {
        if (privateKey == null) {
            loadPrivateKey(privateKeyStr);
        }
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            throw new Exception("无此解密算法");
        } catch (InvalidKeyException e) {
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }
}
