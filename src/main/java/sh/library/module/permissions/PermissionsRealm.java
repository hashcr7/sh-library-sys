package sh.library.module.permissions;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import sh.library.entity.AccountDO;

import java.util.HashSet;
import java.util.Set;

/**
 * 认证、授权自定义逻辑
 *
 * @author huangqc
 * @date 2022/9/21
 */

public class PermissionsRealm extends AuthorizingRealm {
    @Autowired
    PermissionsService accountService;

    /**
     * 授权 逻辑
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        Subject subject = SecurityUtils.getSubject();
        //获取当前登录对象
        AccountDO accountDO = (AccountDO) subject.getPrincipal();

        //设置角色,告知shiro框架当前登录用户的角色
        Set<String> roles = new HashSet<>();
        roles.add(accountDO.getRole());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        return info;
    }

    /**
     * 认证 逻辑
     *
     * @param authenticationToken
     * @return SimpleAuthenticationInfo
     */
    @Override
    protected SimpleAuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        //从shiro框架中拿到token(账号密码加密而成)
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        //从token中拿出账号密码和数据库中的比较
        AccountDO accountDO = accountService.getAccountByUsername(token.getUsername());
        if (accountDO != null) {
            return new SimpleAuthenticationInfo(accountDO, accountDO.getPassword(), getName());
        }
        return null;
    }

}
