package club.vensen.movie.service.impl;

import club.vensen.movie.dao.AccountDao;
import club.vensen.movie.model.Account;
import club.vensen.movie.service.AccountService;
import com.github.pagehelper.PageHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author by VENSEN
 * @Classname AccountServiceImplTest
 * @Description TODO()
 * @Date 2020/1/19 15:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationConfig.xml")
public class AccountServiceImplTest {

    @Resource
    private AccountService service;

    @Resource
    private AccountDao dao;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void save() {
        Account account = new Account();
        account.setId("003");
        account.setAccount("admin");
        account.setPassword("123456");
        account.setRealName("张三");
        System.out.println(service.create(account));
    }

    @Test
    public void findAll() {
        Map<String , Object> map = new HashMap<>();
//        List<Account> list = service.findAll(map);
//        System.out.println(list);
    }

    @Test
    public void testFindAll() {
        Map<String, Object> params = new HashMap<>();
        params.put("account", "ad");
        PageHelper.startPage(1,10,true);
        Long total = PageHelper.count(()->dao.selectAll(params));
//        List<Account> list = dao.selectAll(params);
        System.out.println(total);
        System.out.println();
    }
}