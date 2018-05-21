package cn.tangtj.clouddisk.security;

import cn.tangtj.clouddisk.entity.User;
import cn.tangtj.clouddisk.service.UserService;
import cn.tangtj.clouddisk.utils.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author tang
 */
@Component
public class SystemRealm extends AuthorizingRealm {

    private final UserService userService;

    @Autowired
    public SystemRealm(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        User user = userService.findByName(username);
        if (user == null){
            throw new UnknownAccountException();
        }else {
            User now = UserUtil.getPrincipal();
            if (now != null){
                SecurityUtils.getSubject().logout();
            }
            if (user.getPassword().equals(password)){
                return new SimpleAuthenticationInfo(user,password,getName());
            }
        }
        return null;
    }
}
