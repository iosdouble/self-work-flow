package com.nh.support.exception;

import com.nh.support.constant.CoreConstant;
import com.nh.support.model.ErrorInfo;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @Classname ExceptionFactory
 * @Description TODO
 * @Date 2020/4/2 6:12 PM
 * @Created by nihui
 */
public class ExceptionFactory {
    private MessageSource messageSource;

    public ExceptionFactory(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * 默认local
     **/
    private static final Locale DEFAULT_LOCALE = Locale.CHINA;

    private String getResource(String code, Object... arg) {
        return messageSource.getMessage(code, arg, DEFAULT_LOCALE);
    }

    public ErrorInfo createInternalError() {
        return new ErrorInfo(CoreConstant.ERROR_CODE, getResource(CoreConstant.ERROR_CODE));
    }

    public void throwInternalError() {
        throw new BaseException(CoreConstant.ERROR_CODE, getResource(CoreConstant.ERROR_CODE));
    }

    public void throwDefinedException(String code, Object... args) {
        throw new BaseException(code, getResource(code, args));
    }

    public void throwAuthError(String code, Object... args) {
        throw new AuthErrorException(code, getResource(code, args));
    }

    public void throwObjectNotFound(String code, Object... args) {
        throw new ObjectNotFoundException(code, getResource(code, args));
    }

    public void throwIllegalArgument(String code, Object... args) {
        throw new IllegalArgumentException(code, getResource(code, args));
    }

    public void throwForbidden(String code, Object... args) {
        throw new ForbiddenException(code, getResource(code, args));
    }

    public void throwConflict(String code, Object... args) {
        throw new ConflictException(code, getResource(code, args));
    }
}
