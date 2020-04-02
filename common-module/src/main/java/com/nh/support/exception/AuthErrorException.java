package com.nh.support.exception;

/**
 * @Classname AuthErrorException
 * @Description TODO
 * @Date 2020/4/2 6:14 PM
 * @Created by nihui
 */
public class AuthErrorException extends BaseException {
    public AuthErrorException(String ret, String msg) {
        super(ret, msg);
    }

    public AuthErrorException(String ret, String msg, Throwable cause) {
        super(ret, msg, cause);
    }

}
