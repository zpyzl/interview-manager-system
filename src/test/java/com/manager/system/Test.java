package com.manager.system;

import com.manager.system.entity.User;
import com.manager.system.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Sets;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by zpy on 2024/3/16.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test {
    @Resource
    private ManagerService managerService;

    @org.junit.Test
    public void testAddUser() throws MyException {
        List<String> resourceIds = Arrays.asList("ra","rb","rc");
        managerService.addUserResource("123",resourceIds);
    }
    @org.junit.Test
    public void testAddUser2() throws MyException {
        List<String> resourceIds = Arrays.asList("ra","rb");
        managerService.addUserResource("124",resourceIds);
    }
    @org.junit.Test
    public void testGetUser() throws MyException {
        List<User> users = managerService.getUsers();
        Assert.notEmpty(users, "users empty");
        assertEquals(users.get(0).getUserId(),"123");
        assertEquals(users.get(0).getResourceIdSet(), Sets.newSet("ra", "rb", "rc"));
    }
    @org.junit.Test
    public void testAddUserNewRes() throws MyException {
        managerService.addUserResource("123456",Arrays.asList("resource A","resource B","resource C"));
        managerService.addUserResource("123456",Arrays.asList("resource D"));
        String resourceARes = managerService.getResource("123456", "resource A");
        assertEquals(resourceARes, "success" );
        String resourceDRes = managerService.getResource("123456", "resource D");
        assertEquals(resourceDRes, "success" );
    }
    @org.junit.Test
    public void testAddUserNewRes2() throws MyException {
        managerService.addUserResource("123456",Arrays.asList("resource A","resource B","resource C"));
        managerService.addUserResource("123456",Arrays.asList("resource A","resource D"));
        String resourceARes = managerService.getResource("123456", "resource B");
        assertEquals(resourceARes, "success" );
        String resourceDRes = managerService.getResource("123456", "resource D");
        assertEquals(resourceDRes, "success" );
    }
    @org.junit.Test
    public void testGetInvalidResource()  {
        try {
            managerService.getResource("123456","resource E");
        } catch (MyException e) {
            assertEquals(e.getMessage(),"failure for access to resource E" );
        }
    }
    @org.junit.Test
    public void testGetResource(){
        try {
            String res = managerService.getResource("123456", "resource A");
            assertEquals(res, "success");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

}
