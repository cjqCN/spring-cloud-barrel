package springcloud.barrel.common.util;

import java.util.List;
import java.util.Map;

public interface JwtPayLoad {

    default Long userId() {
        return (Long) payloadMap().get("user_id");
    }

    default String userName() {
        return payloadMap().get("user_name").toString();
    }

    default List<String> roles() {
        return (List<String>) payloadMap().get("authorities");
    }

    Map<String, Object> payloadMap();

}
