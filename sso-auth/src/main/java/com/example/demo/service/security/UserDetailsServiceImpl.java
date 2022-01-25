package com.example.demo.service.security;

import com.example.demo.domain.security.LoginUser;
import com.example.demo.domain.system.SysRole;
import com.example.demo.domain.system.SysUser;
import com.example.demo.enums.UserStatus;
import com.example.demo.exception.BaseException;
import com.example.demo.mapper.system.SysMenuMapper;
import com.example.demo.mapper.system.SysRoleMapper;
import com.example.demo.mapper.system.SysUserMapper;
import com.example.demo.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 用户信息处理
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 用户信息
        SysUser sysUser = userMapper.selectUserByUserName(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }

        // 角色集合
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限
        if (SysUser.isAdmin(sysUser.getUserId())) {
            roles.add("admin");
        } else {
            List<SysRole> perms = roleMapper.selectRolePermissionByUserId(sysUser.getUserId());
            Set<String> permsSet = new HashSet<>();
            for (SysRole perm : perms) {
                if (perm != null) {
                    permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
                }
            }
            roles.addAll(permsSet);
        }

        // 权限集合
        Set<String> perms = new HashSet<>();
        // 管理员拥有所有权限
        if (SysUser.isAdmin(sysUser.getUserId())) {
            perms.add("*:*:*");
        } else {
            List<String> menuPerms = menuMapper.selectMenuPermsByUserId(sysUser.getUserId());
            Set<String> permsSet = new HashSet<>();
            for (String perm : menuPerms) {
                if (StringUtils.isNotEmpty(perm)) {
                    permsSet.addAll(Arrays.asList(perm.trim().split(",")));
                }
            }
            perms.addAll(permsSet);
        }

        UserInfo userResult = new UserInfo();
        userResult.setSysUser(sysUser);
        userResult.setRoles(roles);
        userResult.setPermissions(perms);

        checkUser(userResult, username);
        return getUserDetails(userResult);
    }

    public void checkUser(UserInfo userInfo, String username) {
        if (userInfo == null) {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        } else if (UserStatus.DELETED.getCode().equals(userInfo.getSysUser().getDelFlag())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        } else if (UserStatus.DISABLE.getCode().equals(userInfo.getSysUser().getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }
    }

    private UserDetails getUserDetails(UserInfo info) {
        Set<String> dbAuthsSet = new HashSet<String>();
        if (CollectionUtils.isNotEmpty(info.getRoles())) {
            // 获取角色
            dbAuthsSet.addAll(info.getRoles());
            // 获取权限
            dbAuthsSet.addAll(info.getPermissions());
        }

        Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUser user = info.getSysUser();

        return new LoginUser(user.getUserId(), user.getUserName(), user.getPassword(), true, true, true, true,
                authorities);
    }

}
