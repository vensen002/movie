package club.vensen.movie.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author VENSEN
 * @Classname Account
 * @Description 管理员账户表
 * @Date 2020/2/18 15:02
 */
@Data
public class Role implements Serializable {

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 是否删除
     * 逻辑删除，1删除 0未删除
     */
    private Boolean del;

    /**
     * 状态
     */
    private Short status;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 更新时间
     */
    private Date updateAt;

    private static final long serialVersionUID = 1L;

}