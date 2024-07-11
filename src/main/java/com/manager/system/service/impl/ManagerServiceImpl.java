package com.manager.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.manager.system.Constants;
import com.manager.system.MyException;
import com.manager.system.Utils;
import com.manager.system.entity.User;
import com.manager.system.service.ManagerService;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zpy on 2024/7/10.
 */
@Component
public class ManagerServiceImpl implements ManagerService {
    @Override
    public void addUserResource(String userId, List<String> resourceIds) throws MyException {
        Set<String> resourceIdSet = new HashSet<>(resourceIds);
        List<User> users = getUsers();

        if( users.isEmpty()){
            users = new ArrayList<>();
            addUserToList(userId, resourceIdSet, users);
        }else {
            Set<String> userIds = users.stream().map(User::getUserId).collect(Collectors.toSet());
            if( userIds.contains(userId)) {
                User user = users.stream().filter(u -> u.getUserId().equals(userId)).collect(Collectors.toList()).get(0);
                user.getResourceIdSet().addAll(resourceIdSet);
            }else {//add user
                addUserToList(userId, resourceIdSet, users);
            }
        }
        String jsonString = JSON.toJSONString(users);
        try (PrintWriter out = new PrintWriter(Constants.FILE_NAME)) {
            out.println(jsonString);
        } catch (FileNotFoundException e) {
            throw new MyException("File for persistence is missing!");
        }
    }

    private void addUserToList(String userId, Set<String> resourceIdSet, List<User> users) {
        User user = new User();
        user.setResourceIdSet(resourceIdSet);
        user.setUserId(userId);
        users.add(user);
    }

    @Override
    public List<User> getUsers() throws MyException {
        String fileStr = Utils.readFile();
        if( fileStr == null ){
            return Collections.emptyList();
        }
        JSONArray jsonArray = JSON.parseArray(fileStr.trim());
        return jsonArray.toJavaList(User.class);
    }

    @Override
    public String getResource(String userId, String resourceId) throws MyException{
        List<User> users = getUsers();
        if( users.isEmpty()){
            throw new MyException("no user created!");
        }else {
            Set<String> userIds = users.stream().map(User::getUserId).collect(Collectors.toSet());
            if( userIds.contains(userId)) {
                User user = users.stream().filter(u -> u.getUserId().equals(userId)).collect(Collectors.toList()).get(0);
                if( user.getResourceIdSet().contains(resourceId)){
                    return "success";
                }else {
                    return "failure for access to " + resourceId;
                }
            }else {
                throw new MyException("the user "+userId+" does not exist!");
            }
        }
    }
    @Override
    public void checkAuth(String userId, String role) throws MyException {
        switch (role){
            case "admin":
                if( !Constants.ADMIN_ID.equals(userId)){
                    throw new MyException("incorrect user for admin");
                }
            case "user":
                return ;
            default:
                throw new MyException( "incorrect role");
        }
    }
}
