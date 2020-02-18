package club.vensen.movie.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author by VENSEN
 * @Classname EmailUtilTest
 * @Description TODO()
 * @Date 2020/2/8 15:50
 */
public class EmailUtilTest {

    @Test
    public void sendMain() {
        EmailUtil.sendMain("1219877637@qq.com");
    }
}