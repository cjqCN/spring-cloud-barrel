package springcloud.barrel.common.util;

import com.alibaba.fastjson.JSON;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author jqChan
 * @date 2018/3/25
 */
public class JwtUtil {

    static final String OAUTH2_TOKEN_PREFIX = "Bearer ";
    static final String TOKEN_HEADER = "Authorization";


    public static JwtPayLoad parseTokenBySHA256(HttpServletRequest request, String key) {
        final String token = request.getHeader(TOKEN_HEADER);
        return parseTokenBySHA256(token, key);
    }

    public static JwtPayLoad parseTokenBySHA256(String token, String key) {
        Jwt jwt = parseOauth2TokenBySHA256(token, key);
        final Map<String, Object> map = jsonToMap(jwt.getClaims());
        return new JwtPayLoad() {
            @Override
            public Map<String, Object> payloadMap() {
                return map;
            }
        };
    }

    public static Map<String, Object> jsonToMap(String json) {
        Map<String, Object> map = JSON.parseObject(json);
        return map;
    }


    public static Jwt parseOauth2TokenBySHA256(String token, String key) {
        final String oauth2Token = token.trim();
        verifyTokenFormat(oauth2Token);
        final String tokenContext = oauth2Token.substring(OAUTH2_TOKEN_PREFIX.length()).trim();
        SignatureVerifier signatureVerifier = new MacSigner(key);
        Jwt jwt = JwtHelper.decodeAndVerify(tokenContext, signatureVerifier);
        return jwt;
    }

    private static void verifyTokenFormat(String oauth2Token) {
        if (StringUtils.isEmpty(oauth2Token)) {
            throwTokenIsEmptyException();
        }
        if (!oauth2Token.startsWith(OAUTH2_TOKEN_PREFIX)) {
            throwIsNotOauth2TokenException();
        }
    }


    private static void throwTokenIsEmptyException() {
        throw new RuntimeException("Token is empty");
    }

    private static void throwIsNotOauth2TokenException() {
        throw new RuntimeException("Token is not a oauth2Token");
    }


}
