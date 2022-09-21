package sh.library.commom.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author huangqc
 * @date 2022/09/19
 */
@ApiModel("响应主体")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RestResult<T> {
    /**
     * 是否成功请求
     */
    @ApiModelProperty(value = "请求是否被正确处理(错误数)", allowableValues = "{0, 1}", example = "0")
    private Integer success;
    /**
     * 错误代码或状态码
     */
    @ApiModelProperty(value = "错误代码或状态码", example = "200")
    private String code;
    /**
     * 错误消息
     */
    @ApiModelProperty(value = "错误消息", example = "success")
    private String message;
    /**
     * 成功返回的数据
     */
    @ApiModelProperty("成功返回的数据")
    private T data;


    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {

        private String code;

        private String message;

        private T data;

        private Integer success;

        public Builder<T> code(String code) {
            this.code = code;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> success(int success) {
            this.success = success;
            return this;
        }

        public RestResult<T> build() {
            return new RestResult<T>().setCode(code)
                    .setMessage(message)
                    .setData(data).setSuccess(success);
        }

        /**
          * 请求成功
          * @param data 数据内容
          * @return RestResult
          */
        public RestResult<T> ok(T data) {
            return this.success(0)
                    .code(code != null? code: CommonResultCode.OK.getCode())
                    .message(message != null? message: CommonResultCode.OK.getMessage())
                    .data(data).build();
        }
        /**
         * 创建成功
         * @param data 数据内容
         * @return RestResult
         */
        public RestResult<T> created(T data) {
            return this.success(0)
                    .code(code != null? code: CommonResultCode.CREATED.getCode())
                    .message(message != null? message: CommonResultCode.CREATED.getMessage())
                    .data(data).build();
        }
        /**
         * 删除成功
         * @param data 数据内容
         * @return RestResult
         */
        public RestResult<T> deleted(T data) {
            return this.success(0)
                    .code(code != null? code: CommonResultCode.NO_CONTENT.getCode())
                    .message(message != null? message: CommonResultCode.NO_CONTENT.getMessage())
                    .data(data).build();
        }

        /**
         * 返回错误信息
         * @param resultCode 错误码
         * @return RestResult
         */
        public RestResult<T> error(ResultCode resultCode) {
            return this.code(resultCode.getCode())
                    .message(resultCode.getMessage()).error();
        }

        public RestResult<T> error(ServiceRuntimeException ex) {
            return this.code(ex.getResultCode().getCode())
                    .message(ex.getMessage()).error();
        }

        public RestResult<T> error() {
            return this.code(code).message(message).success(1).build();
        }

    }
}
