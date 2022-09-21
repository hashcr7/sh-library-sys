package sh.library.exception;

import sh.library.commom.response.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A[0-9]{4}: 前端传参不符合要求导致的错误
 * B[0-9]{4}:后端业务逻辑、系统级别报错
 *
 * @author huangqc
 * @date 2022/09/19
 */
@AllArgsConstructor
@Getter
public enum ErrorCode implements ResultCode {
    /**
     * 图书功能相关的状态码
     */
    BOOK_EXISTS("A0101", "该书籍已存在"),
    BOOK_IS_BULL("A0102","该书籍不存在"),

    /**
     * 认证授权功能相关状态码
     */
    USER_NAME_ERROR("B0203","账号错误"),
    USER_PWD_ERROR("B0204","密码错误"),
    ;

    private final String code;

    private final String message;
}
