package org.bluron.postview.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 密钥工具类
 *
 * @author JuLei
 * @since 1.0.0_2020年04月05日
 */
@Component
public class KeyUtil {

    private static final Logger LOG = LoggerFactory.getLogger(KeyUtil.class);

    @Value("${key.path.private}")
    private String privateKeyPath;

    @Value("${key.path..public}")
    private String publicKeyPath;

    /**
     * 获取私钥
     *
     * @return PrivateKey
     * @author JuLei
     * @since 1.0.0_2020年04月06日
     */
    public PrivateKey getPrivateKey() {
        PrivateKey privateKey = null;
        try {
            String keyString = this.loadKey(privateKeyPath, 1);
            byte[] keyBytes = Base64.getDecoder().decode(keyString);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            privateKey = kf.generatePrivate(spec);
        } catch (Exception e) {
            LOG.warn("获取私钥异常", e);
        }
        return privateKey;
    }

    /**
     * 获取公钥
     *
     * @return PublicKey
     * @author JuLei
     * @since 1.0.0_2020年04月06日
     */
    public PublicKey getPublicKey() {
        PublicKey publicKey = null;
        try {
            String keyString = this.loadKey(publicKeyPath, 0);
            byte[] keyBytes = Base64.getDecoder().decode(keyString);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            publicKey = kf.generatePublic(spec);
        } catch (Exception e) {
            LOG.warn("获取公钥异常", e);
        }
        return publicKey;
    }

    /**
     * 加载密钥
     *
     * @param keyPath 密钥路径
     * @param type    密钥类型 1-私钥 0-公钥
     * @return java.lang.String
     * @author JuLei
     * @since 1.0.0_2020年04月08日
     */
    public String loadKey(String keyPath, int type) {
        BufferedReader reader = null;
        String result = "";
        try {
            reader = new BufferedReader(new FileReader(keyPath));
            StringBuilder sb = new StringBuilder();
            String temp;
            while ((temp = reader.readLine()) != null) {
                sb.append(temp);
            }
            if (type == 1) {
                result = sb.toString().replace("-----BEGIN PRIVATE KEY-----", "")
                        .replace("-----END PRIVATE KEY-----", "");
            } else {
                result = sb.toString().replace("-----BEGIN PUBLIC KEY-----", "")
                        .replace("-----END PUBLIC KEY-----", "");
            }
        } catch (Exception e) {
            LOG.warn("加载密钥异常", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    LOG.warn("加载密钥关流异常", e);
                }
            }
        }
        return result;
    }

}
