package club.vensen.movie.util;

import java.util.UUID;

/**
 * @author by VENSEN
 * @Classname UUIDUtil
 * @Description TODO()
 * @Date 2020/2/9 15:35
 */
public class UUIDUtil {

    private static String UUID_SPLIT = "-";

    private static String EMPTY_STRING = "";

    /**
     * 生成36位UUID
     * UUID中包含字符"-"
     * @return uuid
     */
    public static String create() {
        return UUID.randomUUID ().toString ();
    }

    /**
     * 生成32位的UUID
     * 不包含字符"-"
     * @return
     */
    public static String createUUID() {
        return UUID.randomUUID ().toString ().replace (UUID_SPLIT,EMPTY_STRING);
    }
}
