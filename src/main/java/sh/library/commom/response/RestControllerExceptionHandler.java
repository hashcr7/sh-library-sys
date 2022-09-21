package sh.library.commom.response;


import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @author huangqc
 * @date 2022/09/19
 */
@RestControllerAdvice
@Slf4j
public class RestControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResult<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        return RestResult.builder().code("400")
                .message(Objects.requireNonNull(fieldError).getField() + fieldError.getDefaultMessage()).error();
    }

    @ExceptionHandler(ServiceRuntimeException.class)
    public RestResult<Object> handleServiceRuntimeException(ServiceRuntimeException ex) {
        log.warn(ex.getMessage(), ex);
        return RestResult.builder().error(ex);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public RestResult<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return RestResult.builder().code("400").message("请求方法不支持").error();
    }

    @ExceptionHandler(ClientAbortException.class)
    public RestResult<Object> handleClientAbortException(ClientAbortException ex) {
        return RestResult.builder().code("500").message(ex.getMessage()).error();
    }

    @ExceptionHandler({Throwable.class})
    public RestResult<Object> handleOtherException(Throwable throwable) {
        log.error(throwable.getMessage(), throwable);
        return RestResult.builder().message(throwable.getMessage()).error(CommonResultCode.INTERNAL_UNKNOWN_ERROR);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public RestResult<Object> handleUnauthenticatedException(UnauthenticatedException ex) {
        return RestResult.builder().code("405").message("没有认证,权限拒绝").error();
    }

    @ExceptionHandler(AuthorizationException.class)
    public RestResult<Object> handleAuthorizationException(AuthorizationException ex) {
        return RestResult.builder().code("405").message("没有认证,权限拒绝").error();
    }

}
