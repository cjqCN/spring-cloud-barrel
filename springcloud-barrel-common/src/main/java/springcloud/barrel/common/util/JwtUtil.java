package springcloud.barrel.common.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author jqChan
 * @date 2018/3/25
 */
public class JwtUtil {
    JwtHelper jwtHelper = new JwtHelper();

    static final String OAUTH2_TOKEN_PREFIX = "Bearer ";
    static final String TOKEN_HEADER = "Authorization";

    public static Map<String, Object> parseTokenBySHA256(HttpServletResponse response, String key) throws Exception {
        final String token = response.getHeader(TOKEN_HEADER);
        Jwt jwt = parseOauth2TokenBySHA256(token, key);
        return jsonToMap(jwt.getClaims());
    }

    public static Map<String, Object> jsonToMap(String json) {
        Map<String, Object> map = JSON.parseObject(json).getInnerMap();
        return map;
    }


    public static Jwt parseOauth2TokenBySHA256(String token, String key) throws Exception {
        final String oauth2Token = token.trim();
        verifyTokenFormat(oauth2Token);
        final String tokenContext = oauth2Token.substring(OAUTH2_TOKEN_PREFIX.length()).trim();
        SignatureVerifier signatureVerifier = new MacSigner(key);
        Jwt jwt = JwtHelper.decodeAndVerify(tokenContext, signatureVerifier);
        return jwt;
    }

    private static void verifyTokenFormat(String oauth2Token) throws Exception {
        if (StringUtils.isEmpty(oauth2Token)) {
            throwTokenIsEmptyException();
        }
        if (!oauth2Token.startsWith(OAUTH2_TOKEN_PREFIX)) {
            throwIsNotOauth2TokenException();
        }
    }


    private static void throwTokenIsEmptyException() throws Exception {
        throw new Exception("Token is empty");
    }

    private static void throwIsNotOauth2TokenException() throws Exception {
        throw new Exception("Token is not a oauth2Token");
    }

    private static void throwTokenIsNotValidException() throws Exception {
        throw new Exception("Token is not valid");
    }


    public static void main(String[] args) throws Exception {
        String token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJleHAiOjE1MjIwNjM2OTUsInVzZXJfbmFtZSI6ImNoZW5qaW5x" +
                "dWFuIiwiYXV0aG9yaXRpZXMiOlsicm9vdCJdLCJqdGkiOiJjYTAwN" +
                "2U3Ni0yYjljLTRkNDUtYmUzZC00Y2UwZDAxYTAzYjUiLCJjbGllbnR" +
                "faWQiOiJhcGkiLCJzY29wZSI6WyJ4eDEiXX0" +
                ".ZMIgPow1SMdMW6d2823Nliide7BcLTz3bEXwXJ92N_g";
        Jwt jwt = parseOauth2TokenBySHA256(token, "123");
        System.out.println(jsonToMap(jwt.getClaims()));
        System.out.println(jwt.getClaims());

    }
}
