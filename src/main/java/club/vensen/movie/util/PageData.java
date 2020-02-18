package club.vensen.movie.util;

import club.vensen.movie.model.Account;
import lombok.Data;

import java.util.List;

/**
 * @author by VENSEN
 * @Classname PageData
 * @Description TODO()
 * @Date 2020/2/17 18:42
 */
@Data
public class PageData<E> {

    private List<E> list;

    private long total;
}
