package club.vensen.movie.controller;

import club.vensen.movie.model.Account;
import club.vensen.movie.service.AccountService;
import club.vensen.movie.util.MD5Util;
import club.vensen.movie.util.PageData;
import club.vensen.movie.util.ResultData;
import club.vensen.movie.util.UUIDUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by VENSEN
 * @Classname AccountController
 * @Description TODO()
 * @Date 2020/2/10 14:09
 */
@RestController
@RequestMapping("/administrators")
public class AccountController {

    @Resource
    private AccountService service;

    /**
     * 管理员页面默认为list 表格
     * @return
     */
    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("views/user/administrators/list");
    }

    /**
     *
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param order
     * @param search
     * @return
     */
//    @GetMapping("/pages")
//    public PageInfo<Account> listByPages(@RequestParam(defaultValue = "1") int pageNum,
//                                         @RequestParam(defaultValue = "5") int pageSize,
//                                         @RequestParam(defaultValue = "create_at")String sort,
//                                         String order, String search){
//        Map<String, Object> params = new HashMap<>();
//        if ("lastLoginAt".equals (sort)){
//            sort = "last_login_at";
//        }
//        if ("createAt".equals (sort)){
//            sort = "create_at";
//        }
//        params.put ("sort",sort);
//        params.put ("order",order);
//        params.put ("search",search);
//        List<Account> list = service.findAllByPages (pageNum, pageSize, params);
//        return new PageInfo<Account> (list);
//
//    }

    /**
     * 分页列表
     * @return
     */
    @GetMapping("/pages")
    public Map<String, Object> list(int page, int limit, Account account) {
        Map<String, Object> params = new HashMap<>();
        params.put("account", account.getAccount());
        params.put("realName", account.getRealName());
        params.put("tel", account.getTel());
        params.put("role", "");
        PageData<Account> pageData = service.findAll(page, limit, params);

        // layui要求的json格式
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", pageData.getTotal());
        result.put("data", pageData.getList());
        return result;
    }

    /**
     * 添加管理员页面
     * @return 返回layer
     */
    @GetMapping("/adminform")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView("views/user/administrators/adminform");
        return mv;
    }

    /**
     * 创建管理员
     * @param account
     * @return
     */
    @PostMapping("/create")
    public ResultData create(Account account) {
        account.setSalt(account.getAccount());
        account.setPassword (MD5Util.encode ("123456",account.getSalt ()));
        account.setId (UUIDUtil.createUUID ());
        return service.create (account);
    }

    /**
     * 删除功能
     * @param ids 用户id
     * @return ResultData
     */
    @PostMapping("/del")
    public ResultData del(String ...ids) {
//        System.out.println(ids);
        return service.deleteByIds (ids);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("views/user/administrators/edit");
        mv.addObject ("account",service.findById (id));
        return mv;
    }

    @PostMapping("/edit")
    public ResultData edit(Account account) {
        ResultData data = service.update(account);
        return data;
    }

    /**
     * 登录页面
     * @param request
     * @param captcha
     * @param account
     * @param password
     * @param remember
     * @param session
     * @param response
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login(HttpServletRequest request, String captcha,
                              String account, String password,
                              boolean remember, HttpSession session,
                              HttpServletResponse response,
                              RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView();
        mv.setViewName ("redirect:../index");
        return mv;
    }





}
