package club.vensen.movie.dao;

import club.vensen.movie.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleDao {
    /**
     * 通过id删除角色
     * @param ids 角色id
     * @return 受影响行数
     */
    int deleteByPrimaryKey(String[] ids);

    /**
     * 新建角色
     * @param record 角色实体
     * @return 受影响行数
     */
    int insertSelective(Role record);

    /**
     * 通过id 查询角色
     * @param id 角色id
     * @return 角色实体
     */
    Role selectByPrimaryKey(Integer id);

    /**
     * 查询所有账户
     * @param map 查询参数
     * @return list集合
     */
    List<Role> selectAll(Map<String, Object> map);

    /**
     * 更新数据
     * @param record 角色实体
     * @return 受影响行数
     */
    int updateByPrimaryKeySelective(Role record);

}