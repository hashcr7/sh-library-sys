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
@ApiModel("有限列表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class LimitList<E> {
    @ApiModelProperty(value = "返回的最大数量", example = "100")
    private Integer limit;
    @ApiModelProperty(value = "返回的记录总数", example = "20")
    private Long total;
    @ApiModelProperty("当前页记录列表")
    private List<E> list;
}
