package top.aikele.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.netty.util.internal.logging.Log4J2LoggerFactory;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

/**
 * @projectName: KeleBlog
 * @package: top.aikele.utils
 * @className: JwtUtils
 * @author: Kele
 * @description: TODO
 * @date: 2023/4/16 1:43
 * @version: 1.0
 */
public class JwtUtils {
    private static Logger log= LoggerFactory.getLogger(JwtUtils.class);
    private static String secret = "aaabbbcccdddeeefffggghhhiiijjjkkklllmmmnnnooopppqqqrrrssstttaaabbbcccdddeeefffggghhhiiijjjkkklllmmmnnnooopppqqqrrrsssttt";
    private static Long expirationTimeInSecond = 1209600L;

    /**
     * 从token中获取claim
     *
     * @param token token
     * @return claim
     */
    public static Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            log.error("token解析错误", e);
            throw new IllegalArgumentException("Token invalided.");
        }
    }

    /**
     * 获取token的过期时间
     *
     * @param token token
     * @return 过期时间
     */
    public static Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token)
                .getExpiration();
    }

    /**
     * 判断token是否过期
     *
     * @param token token
     * @return 已过期返回true，未过期返回false
     */
    private static Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 计算token的过期时间
     *
     * @return 过期时间
     */
    private static Date getExpirationTime() {
        return new Date(System.currentTimeMillis() + expirationTimeInSecond * 1000);
    }

    /**
     * 为指定用户生成token
     *
     * @param userId 用户信息
     * @return token
     */
    public static String generateToken(Integer userId,String username) {
        Date createdTime = new Date();
        Date expirationTime = getExpirationTime();

        return Jwts.builder()
                .setSubject("auth-user")
                .claim("userId",userId)
                .claim("username",username)
                .setIssuedAt(createdTime)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }
    public static Integer getUserId(String token){
        if(StringUtils.isEmpty(token))
            return null;
        if(isTokenExpired(token)==true)
            return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (Integer) claims.get("userId");
    }
    public static  String getUsername(String token) {

        if (StringUtils.isEmpty(token))
            return null;
        if(isTokenExpired(token)==true)
            return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("username");

    }

}
