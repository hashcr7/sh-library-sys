package sh.library.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author huangqc
 * @date 2022/9/20
 */
@ApiModel("更新图书信息")
@Data
public class BookUpdateBody {
    @ApiModelProperty(value = "图书名称", required = true)
    @NotBlank
    private String name;
    @ApiModelProperty(value = "作者名称", required = true)
    @NotBlank
    private String author;
}
