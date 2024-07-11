package com.manager.system.controller;

import com.alibaba.fastjson2.JSONArray;
import com.manager.system.MyException;
import com.manager.system.entity.Response;
import com.manager.system.entity.User;
import com.manager.system.entity.UserDO;
import com.manager.system.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zpy on 2024/7/11.
 */
@Controller
@RequestMapping(value = "admin")
public class AdminController {
    @Resource
    private ManagerService managerService;

    @PostMapping( value = "addUser")
    @ResponseBody
    public Response addUser(@RequestHeader String userId, @RequestHeader String role, @RequestBody UserDO userDO){
        User user = new User();
        user.setUserId(userDO.getUserId());
        Response response = new Response();
        try {
            managerService.checkAuth(userId, role);
            managerService.addUserResource(userDO.getUserId(),userDO.getEndpoint());
            response.setMsg("success");
        } catch (MyException e) {
            response.setMsg( e.getMsg());
        }
        return response;
    }
}
