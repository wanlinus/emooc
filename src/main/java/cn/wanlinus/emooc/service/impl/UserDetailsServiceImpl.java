package cn.wanlinus.emooc.service.impl;

import cn.wanlinus.emooc.domain.Admin;
import cn.wanlinus.emooc.domain.User;
import cn.wanlinus.emooc.persistence.AdminRepository;
import cn.wanlinus.emooc.persistence.UserRepository;
import cn.wanlinus.emooc.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限服务类
 *
 * @author wanli
 * @date 2018-02-22 11:31
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";
    private static final String ROLE_TEACHER = "TEACHER";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String role = CommonUtils.getRequest().getParameter("role");
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (ROLE_ADMIN.equals(role)) {
            Admin admin = adminRepository.findByName(username);
            authorities.add(new SimpleGrantedAuthority(ROLE_ADMIN));
            return new org.springframework.security.core.userdetails.User(admin.getName(), admin.getPassword(), authorities);
        } else if (ROLE_USER.equals(role)) {
            User user = userRepository.findByUsername(username);
            authorities.add(new SimpleGrantedAuthority(ROLE_USER));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        } else if (ROLE_TEACHER.equals(role)) {

            return null;
        } else {
            return null;
        }

    }
}
