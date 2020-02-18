package club.vensen.movie.service.impl;

import club.vensen.movie.dao.RoleDao;
import club.vensen.movie.model.Role;
import club.vensen.movie.service.RoleService;
import club.vensen.movie.util.PageData;
import club.vensen.movie.util.ResultData;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author by VENSEN
 * @Classname RoleService
 * @Description TODO()
 * @Date 2020/2/18 15:17
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao dao;

    @Override
    public Role findById(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public PageData<Role> findAll(int page, int limit, Map<String, Object> params) {
        PageData<Role> pageData = new PageData<>();
        Long total = PageHelper.count(()->dao.selectAll(params));
        PageHelper.startPage(page,limit,true);
        pageData.setList(dao.selectAll(params));
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
    public ResultData create(Role role) {
        int rows = dao.insertSelective (role);
        if (rows > 0) {
            return new ResultData (1,"创建成功",null);
        }
        return new ResultData (0,"创建失败",null);
    }

    @Override
    public ResultData update(Role role) {
        int rows = dao.updateByPrimaryKeySelective (role);
        if (rows > 0) {
            return new ResultData (1,"修改成功",null);
        }
        return new ResultData (0,"修改失败",null);
    }
}
