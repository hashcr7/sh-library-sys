package sh.library.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import sh.library.commom.page.PageQuery;

/**
 * @author huangqc
 * @date 2022/9/20
 */
@ApiModel("图书搜索条件")
@Data
@EqualsAndHashCode(callSuper = true)
public class BookQuery extends PageQuery {

}
