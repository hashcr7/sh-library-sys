package sh.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author huangqc
 * @date 2022/9/21
 */
@Data
@TableName("account")
public class AccountDO {
    /**
     * 主键id
     */
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 权限
     */
    private String perms;
    /**
     * 角色
     */
    private String role;
}
