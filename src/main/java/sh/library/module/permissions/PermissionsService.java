package sh.library.module.permissions;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sh.library.commom.response.ServiceRuntimeException;
import sh.library.entity.AccountDO;
import sh.library.exception.ErrorCode;
import sh.library.mapper.AccountMapper;
import sh.library.utils.SessionUtils;

/**
 * @author huangqc
 * @date 2022/9/21
 */
@Slf4j
@Service
public class PermissionsService extends ServiceImpl<AccountMapper, AccountDO> {
    @Autowired
    SessionUtils sessionUtils;

    /**
     * 根据账号名获取账号信息
     *
     * @param username 账号名
     * @return 账号信息
     */
    public AccountDO getAccountByUsername(String username) {
        return this.lambdaQuery().eq(AccountDO::getUsername, username).one();
    }

    /**
     * 登录
     *
     * @param username 账号名
     * @param password 密码
     * @return 是否登录成功
     */
    public Boolean login(String username, String password) {
        //加密账号密码成token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            //把token传给shiro框架内部流程，后续进入我们Realm类中自定义的登录逻辑中
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

            //登录成功后，保存账号信息在当前会话session中
            AccountDO accountDO = (AccountDO) subject.getPrincipal();
            subject.getSession().setAttribute("account", accountDO);

            log.info("[{}-{}]登录成功", sessionUtils.getUserName(), sessionUtils.getRole());
            return true;
        } catch (UnknownAccountException e) {
            throw ServiceRuntimeException.build(ErrorCode.USER_NAME_ERROR);
        } catch (IncorrectCredentialsException e) {
            throw ServiceRuntimeException.build(ErrorCode.USER_PWD_ERROR);
        }
    }

}
