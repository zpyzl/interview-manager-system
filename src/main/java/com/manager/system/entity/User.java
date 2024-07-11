package com.manager.system.entity;

import java.util.Set;

/**
 * Created by zpy on 2024/7/10.
 */
public class User {
    private String userId;
    private Set<String> resourceIdSet;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<String> getResourceIdSet() {
        return resourceIdSet;
    }

    public void setResourceIdSet(Set<String> resourceIdSet) {
        this.resourceIdSet = resourceIdSet;
    }
}
