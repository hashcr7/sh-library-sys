package sh.library.commom.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author huangqc
 * @date 2022/09/19
 */

@Getter
@AllArgsConstructor
public enum CommonResultCode implements ResultCode{
    /**
     * 返回成功
     */
    OK("200", "请求成功"),
    CREATED("201", "资源创建成功"),
    NO_CONTENT("204", "资源已被删除"),

    /**
     * 用户请求不合法
     */
    ARGUMENT_INVALID("400", "参数不合法：{message}"),
    RESOURCE_NOT_EXISTS("404", "请求的资源不存在"),
    /**
     * 服务器错误
     */
    INTERNAL_UNKNOWN_ERROR("500", "服务器内部未知错误"),
    ;

    private String code;
    private String message;
}
