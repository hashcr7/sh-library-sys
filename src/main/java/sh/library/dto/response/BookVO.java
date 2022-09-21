package sh.library.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huangqc
 * @date 2022/9/20
 */
@ApiModel("图书信息")
@Data
public class BookVO {
    @ApiModelProperty(value = "图书ID", example = "1")
    private Integer id;
    @ApiModelProperty(value = "图书名称", example = "弱点")
    private String name;
    @ApiModelProperty(value = "图书作者", example = "王五")
    private String author;
}
