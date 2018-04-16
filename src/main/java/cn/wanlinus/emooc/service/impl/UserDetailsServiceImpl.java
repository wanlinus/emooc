package cn.wanlinus.emooc.service.impl;

import cn.wanlinus.emooc.annotation.LoginOperation;
import cn.wanlinus.emooc.domain.Admin;
import cn.wanlinus.emooc.domain.Teacher;
import cn.wanlinus.emooc.domain.User;
import cn.wanlinus.emooc.enums.EmoocRole;
import cn.wanlinus.emooc.persistence.AdminRepository;
import cn.wanlinus.emooc.persistence.TeacherRepository;
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
import java.util.Objects;

/**
 * 权限服务类
 *
 * @author wanli
 * @date 2018-02-22 11:31
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @LoginOperation(descript = "登陆")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String role = Objects.requireNonNull(CommonUtils.getRequest()).getParameter("role");
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (EmoocRole.ROLE_ADMIN.getDesc().equals(role)) {
            Admin admin = adminRepository.findByName(username);
            if (admin != null) {
                authorities.add(new SimpleGrantedAuthority(EmoocRole.ROLE_ADMIN.getDesc()));
                return new org.springframework.security.core.userdetails.User(admin.getName(), admin.getPassword(), authorities);
            }
        } else if (EmoocRole.ROLE_USER.getDesc().equals(role)) {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                authorities.add(new SimpleGrantedAuthority(EmoocRole.ROLE_USER.getDesc()));
                return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
            }
        } else if (EmoocRole.ROLE_TEACHER.getDesc().equals(role)) {
            Teacher teacher = teacherRepository.findByUsername(username);
            if (teacher != null) {
                authorities.add(new SimpleGrantedAuthority(EmoocRole.ROLE_TEACHER.getDesc()));
                return new org.springframework.security.core.userdetails.User(teacher.getUsername(), teacher.getPassword(), authorities);
            }
        }
        return null;

    }
}
