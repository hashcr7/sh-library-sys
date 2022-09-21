package sh.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author huangqc
 * @date 2022/9/20
 */
@Data
@TableName("book")
public class BookDO {
    /**
     * 主键id
     */
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    /**
     * 书本名称
     */
    private String name;
    /**
     * 作者
     */
    private String author;
    /**
     * 借阅次数
     */
    private Integer borrowingNum;


}
