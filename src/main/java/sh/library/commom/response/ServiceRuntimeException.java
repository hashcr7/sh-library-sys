package sh.library.commom.response;

import lombok.Getter;

import java.text.MessageFormat;

/**
 * @author huangqc
 * @date 2022/09/19
 */
public class ServiceRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -7610488146748705981L;
    /**
     * 错误代码
     */
    @Getter
    private final ResultCode resultCode;

    /**
     * 直接使用errorCode中的消息
     * @param resultCode 错误代码
     */
    public ServiceRuntimeException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    /**
     * 使用自定义错误消息（不推荐）
     * @param resultCode 错误代码
     * @param message 错误消息
     */
    public ServiceRuntimeException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }

    /**
     * 对其他异常包装成本异常,使用错误代码中的消息
     * @param resultCode 错误代码
     * @param cause 错误异常
     */
    public ServiceRuntimeException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getMessage(), cause);
        this.resultCode = resultCode;
    }

    /**
     * 将其他异常包装成本异常,使用自定义消息
     * @param resultCode 错误代码
     * @param message 错误消息
     * @param cause 错误异常
     */
    public ServiceRuntimeException(ResultCode resultCode, String message, Throwable cause) {
        super(message, cause);
        this.resultCode = resultCode;
    }

    /**
     * 错误代码中消息是pattern，传入参数进行格式化
     * @param resultCode 错误代码
     * @param args 格式化参数
     */
    public static ServiceRuntimeException build(ResultCode resultCode, Object... args) {
        return new ServiceRuntimeException(resultCode, MessageFormat.format(resultCode.getMessage(), args));
    }

    /**
     * 将其他异常包装成本异常，错误代码中消息是一种pattern，传入参数进行格式化
     * @param resultCode 错误代码
     * @param cause 错误异常
     * @param args 格式化参数
     */
    public static ServiceRuntimeException build(ResultCode resultCode, Throwable cause, Object... args) {
        return new ServiceRuntimeException(resultCode, MessageFormat.format(resultCode.getMessage(), args), cause);
    }

    /**
     * 将其他异常包装成本异常,错误消息中消息是一种pattern，传入参数进行格式化
     * @param resultCode 错误代码
     * @param message 错误消息
     * @param args 格式化参数
     */
    public static ServiceRuntimeException build(ResultCode resultCode, String message, Object... args) {
        return new ServiceRuntimeException(resultCode, MessageFormat.format(message, args));
    }

}
