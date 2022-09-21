package sh.library.commom.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author huangqc
 * @date 2022/09/19
 */
@ApiModel("分页列表数据")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class PageList<E> {

    @ApiModelProperty(value = "记录总数", example = "100")
    private Long total;

    @ApiModelProperty(value = "页数", example = "10")
    private Integer pages;

    @ApiModelProperty(value = "当前页码", example = "1")
    private Integer pageNum;

    @ApiModelProperty(value = "每页记录数", example = "10")
    private Integer pageSize;

    @ApiModelProperty("当前页记录列表")
    private List<E> list;
}
