package sh.library.utils;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;
import sh.library.entity.AccountDO;

/**
 * 管理session的工具
 *
 * @author huangqc
 * @date 2022/9/21
 */
@Component
public class SessionUtils {
    /**
     * 从当前session中 获取账号名
     *
     * @return 账号名
     */
    public String getUserName() {
        AccountDO accountDO = (AccountDO) SecurityUtils
                .getSubject()
                .getSession()
                .getAttribute("account");

        return accountDO.getUsername();
    }

    /**
     * 从当前session中 获取账号角色
     *
     * @return 账号角色
     */
    public String getRole() {
        AccountDO accountDO = (AccountDO) SecurityUtils
                .getSubject()
                .getSession()
                .getAttribute("account");

        return accountDO.getRole();
    }
}
