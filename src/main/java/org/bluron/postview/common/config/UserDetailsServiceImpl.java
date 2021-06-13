package org.bluron.postview.common.config;

import org.bluron.postview.entity.pojo.SysRoleMenu;
import org.bluron.postview.entity.pojo.SysUser;
import org.bluron.postview.entity.pojo.SysUserRole;
import org.bluron.postview.service.SysRoleMenuService;
import org.bluron.postview.service.SysUserRoleService;
import org.bluron.postview.service.SysUserService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 根据用户名加载信息
     *
     * @param username
     * @return SysUser
     * @author JuLei
     * @since 1.0.0_2019年11月16日
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        SysUserRole sysUserRole = sysUserRoleService.getRoleByUserId(sysUser.getUserId());
        if (sysUserRole != null) {
            List<SysRoleMenu> sysRoleMenu = sysRoleMenuService.getMenuByRoleId(sysUserRole.getRoleId());
            StringBuilder menus = new StringBuilder();
            for (int i = 0; i < sysRoleMenu.size(); i++) {
                if (i == sysRoleMenu.size() - 1) {
                    menus.append(sysRoleMenu.get(i).getMenuId());
                } else {
                    menus.append(sysRoleMenu.get(i).getMenuId()).append(",");
                }
            }
            sysUser.setGrantedAuthority(AuthorityUtils.commaSeparatedStringToAuthorityList(menus.toString()));
        }
        return sysUser;
    }

}
