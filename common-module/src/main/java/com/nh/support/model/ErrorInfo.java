package com.nh.support.model;

import lombok.Data;

/**
 * @Classname ErrorInfo
 * @Description TODO
 * @Date 2020/4/2 6:13 PM
 * @Created by nihui
 */
@Data
public class ErrorInfo {
    private String ret;
    private String msg;

    public ErrorInfo(String ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }
}
