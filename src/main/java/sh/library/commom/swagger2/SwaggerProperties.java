package sh.library.commom.swagger2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author huangqc
 * @date 2022/09/19
 */
@ConfigurationProperties("swagger")
@Data
public class SwaggerProperties {

    private Boolean enable = true;
    /**
     * 接口所在的包
     */
    private String basePackage = "sh.library";

    private ApiInfoProperties apiInfo = new ApiInfoProperties();

    @Data
    public static class ApiInfoProperties {
        /**
         * 标题
         */
        private String title;
        /**
         * 描述
         */
        private String description;
        /**
         * 版本
         */
        private String version;
    }
}
