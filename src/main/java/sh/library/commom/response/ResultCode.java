package sh.library.commom.response;

import java.io.Serializable;

/**
 * 错误代码
 * @author huangqc
 * @date 2022/09/19
 */
public interface ResultCode extends Serializable {
    /**
     * 获取错误代码
     * @return 错误代码
     */
    String getCode();

    /**
     * 获取错误消息
     * @return 错误消息
     */
    String getMessage();
}
