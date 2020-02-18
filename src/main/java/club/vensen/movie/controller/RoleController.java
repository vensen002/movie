package club.vensen.movie.controller;

import club.vensen.movie.model.Account;
import club.vensen.movie.model.Role;
import club.vensen.movie.service.RoleService;
import club.vensen.movie.util.MD5Util;
import club.vensen.movie.util.PageData;
import club.vensen.movie.util.ResultData;
import club.vensen.movie.util.UUIDUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by VENSEN
 * @Classname RoleController
 * @Description TODO()
 * @Date 2020/2/12 14:23
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService service;

    @GetMapping("/")
    public ModelAndView role() {
        return new ModelAndView("views/user/administrators/role");
    }

    /**
     * 分页查询
     * @param page 第几页
     * @param limit 页面大小
     * @param role 角色实体
     * @return 封装好的json
     */
    @GetMapping("/pages")
    public Map<String, Object> list(int page, int limit, Role role) {
        Map<String, Object> params = new HashMap<>();
        PageData<Role> pageData = service.findAll(page, limit, params);

        // layui要求的json格式
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", pageData.getTotal());
        result.put("data", pageData.getList());
        return result;
    }

    /**
     * 删除功能
     * @param ids 用户id
     * @return ResultData
     */
    @PostMapping("/del")
    public ResultData del(String ...ids) {
        return service.deleteByIds (ids);
    }

    /**
     * 添加角色页面
     * @return view
     */
    @GetMapping("/create")
    public ModelAndView roleInfo() {
        ModelAndView mv = new ModelAndView("views/user/administrators/roleform");
        return mv;
    }

    /**
     * 添加角色
     * @param role 角色实体
     * @return ResultData
     */
    @PostMapping("/create")
    public ResultData create(Role role) {
        return service.create (role);
    }

    /**
     * 编辑角色页面
     * @param id 角色id
     * @return view
     */
    @GetMapping("/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("views/user/administrators/roleform");
        mv.addObject ("role",service.findById (id));
        return mv;
    }

    /**
     * 更新角色信息
     * @param role 角色实体
     * @return ResultData
     */
    @PostMapping("/edit")
    public ResultData edit(Role role) {
        ResultData data = service.update(role);
        return data;
    }

}
