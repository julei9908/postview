package org.bluron.postview.common.util;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bluron.postview.entity.pojo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

/**
 * jwt签发者
 *
 * @author JuLei
 * @since 1.0.0_2020年04月06日
 */
@Component
public class JwtTokenBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(JwtTokenBuilder.class);

    private static final String ISSUER = "postview";

    @Value("${expireMinutes}")
    private long expireMinutes;

    @Resource
    private KeyUtil keyUtil;

    /**
     * 创建TOKEN
     *
     * @param sysUser
     * @return String
     * @author JuLei
     * @since 1.0.0_2020年04月05日
     */
    public String createToken(SysUser sysUser) {
        String encoded = "";
        try {
            encoded = Jwts.builder()
                    .setIssuer(ISSUER)
                    .setSubject(sysUser.getUsername())
                    .setAudience(sysUser.getUserId().toString())
                    .setExpiration(Date.from(LocalDateTime.now().plusMinutes(expireMinutes).atZone(ZoneId.systemDefault()).toInstant()))
                    .setNotBefore(new Date())
                    .setIssuedAt(new Date())
                    .setId(UUID.randomUUID().toString())
                    .compressWith(CompressionCodecs.DEFLATE)
                    .signWith(keyUtil.getPrivateKey(), SignatureAlgorithm.RS256)
                    .compact();
        } catch (Exception e) {
            LOG.warn("创建令牌失败", e);
        }
        return encoded;
    }

}
