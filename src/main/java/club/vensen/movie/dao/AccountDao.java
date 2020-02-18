package club.vensen.movie.dao;

import club.vensen.movie.model.Account;

import java.util.List;
import java.util.Map;

/**
 * @author VENSEN
 * @ClassName AccountDao
 * @Description account 的 dao层
 */
public interface AccountDao {

    /**
     * 通过id删除
     * @param ids 账户id
     * @return 返回受影响行数
     */
    int deleteByPrimaryKey(String[] ids);

    /**
     * 新增账户
     * @param record 账户实体
     * @return 返回受影响行数
     */
    int insertSelective(Account record);

    /**
     * 统计数量
     * @return int类型的数
     */
    int count();

    /**
     * 通过id查找账户
     * @param id 账户id
     * @return 返回账户实体
     */
    Account selectByPrimaryKey(String id);

    /**
     * 查询所有账户
     * @param map 查询参数
     * @return list集合
     */
    List<Account> selectAll(Map<String, Object> map);

    /**
     * 更新信息
     * @param record 账户实体
     * @return 返回受影响行数
     */
    int updateByPrimaryKeySelective(Account record);

}