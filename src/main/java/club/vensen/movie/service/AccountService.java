package club.vensen.movie.service;

import club.vensen.movie.model.Account;
import club.vensen.movie.util.PageData;
import club.vensen.movie.util.ResultData;

import java.util.List;
import java.util.Map;

/**
 * @author by VENSEN
 * @Classname AccountService
 * @Description TODO()
 * @Date 2020/1/19 15:03
 */
public interface AccountService {

    /**
     * 新建账户
     * @param account 账户实体
     * @return ResultData
     */
    ResultData create(Account account);

    /**
     * 通过id查询账户
     * @param id
     * @return Account
     */
    Account findById(String id);

    /**
     * 查询所有用户
     * @return resultData
     */
    PageData<Account> findAll(int pageNum, int pageSize, Map<String, Object> map);


    /**
     * 删除数据
     * @param ids 账户id 组
     * @return ResultData
     */
    ResultData deleteByIds(String[] ids);

    /**
     * 编辑
     * @param account 账户实体
     * @return ResultData
     */
    ResultData update(Account account);
}
