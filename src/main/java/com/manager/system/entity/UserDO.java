package com.manager.system.entity;

import java.util.List;

/**
 * Created by zpy on 2024/7/11.
 */
public class UserDO {
    private String userId;
    private List<String> endpoint;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(List<String> endpoint) {
        this.endpoint = endpoint;
    }
}
