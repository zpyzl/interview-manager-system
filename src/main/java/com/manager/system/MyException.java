package com.manager.system;

/**
 * Created by zpy on 2024/7/10.
 */
public class MyException extends Exception{
    private String msg;

    public MyException(String msg){
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
