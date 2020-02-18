package club.vensen.movie.service;

import club.vensen.movie.model.Account;
import club.vensen.movie.model.Role;
import club.vensen.movie.util.PageData;
import club.vensen.movie.util.ResultData;

import java.util.Map;

/**
 * @author by VENSEN
 * @Classname RoleService
 * @Description TODO()
 * @Date 2020/2/18 15:17
 */
public interface RoleService {

    /**
     * 通过id 查找角色
     * @param id 角色id
     * @return Role
     */
    Role findById(Integer id);
    /**
     * 查找所有角色
     * @param page 第几页
     * @param limit 页面大小
     * @param params 其他参数
     * @return PageData
     */
    PageData<Role> findAll(int page, int limit, Map<String, Object> params);

    /**
     * 删除数据
     * @param ids 账户id 组
     * @return ResultData
     */
    ResultData deleteByIds(String[] ids);

    /**
     * 添加角色
     * @param role 角色实体
     * @return ResultData
     */
    ResultData create(Role role);


    /**
     * 更新角色
     * @param role 角色实体
     * @return ResultData
     */
    ResultData update(Role role);
}
