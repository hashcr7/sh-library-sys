package sh.library.commom.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huangqc
 * @date 2022/09/19
 */
@ApiModel("有限列表查询")
@Data
public class LimitQuery {
    @ApiModelProperty(value = "最多展示个数", example = "20")
    private Integer limit = 20;
    @ApiModelProperty(value = "排序的字段", example = "updatedTime")
    private String sortBy = "updatedTime";
    @ApiModelProperty(value = "排序的顺序", example = "desc")
    private String sortOder = "desc";
}
