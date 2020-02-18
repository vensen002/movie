package club.vensen.movie.util;

import lombok.*;

/**
 * @author by VENSEN
 * @Classname ResultData
 * @Description TODO()
 * @Date 2020/2/9 15:33
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ResultData {
    /**
     * 状态码  1 表示成功
     *        0 表示失败
     */
    @NonNull
    private int code;

    /**
     * 信息
     */
    @NonNull
    private String message;

    /**
     * 数据
     */
    private Object data;
}
