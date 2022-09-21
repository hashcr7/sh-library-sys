package sh.library.commom.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huangqc
 * @date 2022/09/19
 */
@Data
@ApiModel("基本分页列表的查询条件")
public class PageQuery {
    @ApiModelProperty(value = "一页的大小", example = "10")
    private Integer pageSize = 10;
    @ApiModelProperty(value = "页码，从1开始", example = "1")
    private Integer pageNum = 1;
    @ApiModelProperty(value = "排序的字段", example = "updatedTime")
    private String sortBy = "updatedTime";
    @ApiModelProperty(value = "排序的顺序", example = "desc")
    private String sortOrder = "desc";
}