package club.vensen.movie.service.impl;

import club.vensen.movie.dao.AccountDao;
import club.vensen.movie.model.Account;
import club.vensen.movie.service.AccountService;
import club.vensen.movie.util.PageData;
import club.vensen.movie.util.ResultData;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author by VENSEN
 * @Classname AccountServiceImpl
 * @Description TODO()
 * @Date 2020/1/19 15:04
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao dao;


    @Override
    public ResultData create(Account account) {
        int rows = dao.insertSelective(account);
        if (rows > 0) {
            return new ResultData (1,"创建成功",null);
        }
        return new ResultData (0,"创建失败",null);
    }

    @Override
    public Account findById(String id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public PageData<Account> findAll(int pageNum, int pageSize, Map<String, Object> map) {
        PageData<Account> pageData = new PageData<>();
        Long total = PageHelper.count(()->dao.selectAll(map));
        PageHelper.startPage(pageNum,pageSize,true);
        pageData.setList(dao.selectAll(map));
        pageData.setTotal(total);
        return pageData;
    }

    @Override
    public ResultData deleteByIds(String[] ids) {
        int rows = dao.deleteByPrimaryKey (ids);
        if (rows > 0 ) {
            return new ResultData (1,"删除成功",null);
        }
        return new ResultData (0,"删除失败",null);
    }

    @Override
    public ResultData update(Account account) {
        int rows = dao.updateByPrimaryKeySelective (account);
        if (rows > 0) {
            return new ResultData (1,"修改成功",null);
        }
        return new ResultData (0,"修改失败",null);
    }

}
