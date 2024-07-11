package com.manager.system.controller;

import com.manager.system.MyException;
import com.manager.system.entity.Response;
import com.manager.system.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zpy on 2024/7/11.
 */
@RestController
public class UserController {
    @Resource
    private ManagerService managerService;

    @GetMapping(value = "/user/{resource}")
    public Response getResource(@RequestHeader String userId, @RequestHeader String role, @PathVariable("resource") String resource){
        Response response = new Response();
        try {
            managerService.checkAuth(userId, role);
            String result = managerService.getResource(userId, resource);
            response.setMsg(result);
        } catch (MyException e) {
            response.setMsg( e.getMsg());
        }
        return response;
    }
}
