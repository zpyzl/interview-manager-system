package com.manager.system.service;

import com.manager.system.MyException;
import com.manager.system.entity.User;

import java.util.List;

/**
 * Created by zpy on 2024/7/10.
 */
public interface ManagerService {

    void addUserResource(String userId, List<String> resourceIds) throws MyException;

    List<User> getUsers() throws MyException;


    String getResource(String userId, String resourceId) throws MyException;

    void checkAuth(String userId, String role) throws MyException;
}
