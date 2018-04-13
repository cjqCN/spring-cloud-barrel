package springcloud.barrel.common.util;

import com.alibaba.fastjson.JSON;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Map;

/**
 * @author jqChan
 * @date 2018/3/25
 */
public class JwtUtil {

    static final String OAUTH2_TOKEN_PREFIX = "Bearer ";
    static final String TOKEN_HEADER = "Authorization";


    /**
     * 解析JWT
     *
     * @param request
     * @param key
     * @return
     */
    public static JwtPayLoad parseTokenBySHA256(HttpServletRequest request, String key) {
        final String token = request.getHeader(TOKEN_HEADER);
        return parseTokenBySHA256(token, key);
    }

    /**
     * 解析JWT
     *
     * @param token
     * @param key
     * @return
     */
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

    /**
     * 不经过验证，获得JWTPayLoad
     *
     * @param request
     * @return
     */
    public static JwtPayLoad parseTokenWithNotVerify(HttpServletRequest request) {
        final String token = request.getHeader(TOKEN_HEADER);
        return parseTokenWithNotVerify(token);
    }

    /**
     * 不经过验证，获得JWTPayLoad
     *
     * @param token
     * @return
     */
    public static JwtPayLoad parseTokenWithNotVerify(String token) {
        verifyTokenFormat(token);
        final String tokenContext = token.substring(OAUTH2_TOKEN_PREFIX.length()).trim();
        String payLoadStr = parseBase64(tokenContext.split("\\.")[1]);
        final Map<String, Object> map = jsonToMap(payLoadStr);
        return new JwtPayLoad() {
            @Override
            public Map<String, Object> payloadMap() {
                return map;
            }
        };
    }


    private static Map<String, Object> jsonToMap(String json) {
        Map<String, Object> map = JSON.parseObject(json);
        return map;
    }


    private static Jwt parseOauth2TokenBySHA256(String token, String key) {
        final String oauth2Token = token.trim();
        verifyTokenFormat(oauth2Token);
        final String tokenContext = oauth2Token.substring(OAUTH2_TOKEN_PREFIX.length()).trim();
        SignatureVerifier signatureVerifier = new MacSigner(key);
        Jwt jwt = JwtHelper.decodeAndVerify(tokenContext, signatureVerifier);
        return jwt;
    }

    /**
     * 验证JWT格式
     *
     * @param oauth2Token
     */
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


    private static String parseBase64(String base64) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(base64);
        String parseBase64 = new String(decode);
        return parseBase64;
    }


    public static void main(String[] args) {
        //      System.out.println(parseBase64("eyJ1c2VyX2lkIjpudWxsLCJ1c2VyX25hbWUiOiJjanEiLCJzY29wZSI6WyJ4eDIiXSwiYXRpIjoiYTZmNWFiNjYtNzRhZi00NzQwLWFkNTQtZDg0MzNiYzc4MDNiIiwicmVhbF9uYW1lIjpudWxsLCJleHAiOjE1MjQ4MjE4NTgsImF1dGhvcml0aWVzIjpbInVzZXIiXSwianRpIjoiMDJjZDE2MmUtMmYwaMC00ZWE1LThmNGMtYTk0OTcyNGI4MmQyIiwiY2xpZW50X2lkIjoiYXBpIn0"));

        String base64 = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjpudWxsLCJ1c2VyX25hbWUiOiJjanEiLCJzY29wZSI6WyJ4eDIiXSwicmVhbF9uYW1lIjpudWxsLCJleHAiOjE1MjIxNDI2MDcsImF1dGhvcml0aWVzIjpbInVzZXIiXSwianRpIjoiYzZhNDgzZGEtZWZhNC00Y2YzLTlhNmYtYzU4MWMyZTY1YzIxIiwiY2xpZW50X2lkIjoiYXBpIn0.8NpfqJYtkS-8FRk_2AfCGd7cGFXmcu-eFnSkV6qLRkg";
        System.out.println(parseTokenWithNotVerify(base64).payloadMap());

    }


}
