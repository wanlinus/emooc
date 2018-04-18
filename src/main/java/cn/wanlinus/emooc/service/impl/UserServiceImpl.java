package cn.wanlinus.emooc.service.impl;

import cn.wanlinus.emooc.annotation.RegisterAnnotation;
import cn.wanlinus.emooc.domain.User;
import cn.wanlinus.emooc.dto.GenderPieDTO;
import cn.wanlinus.emooc.dto.UserDetailsDTO;
import cn.wanlinus.emooc.dto.UserRegisterDTO;
import cn.wanlinus.emooc.enums.Gender;
import cn.wanlinus.emooc.enums.UserStatus;
import cn.wanlinus.emooc.persistence.UserRepository;
import cn.wanlinus.emooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static cn.wanlinus.emooc.utils.CommonUtils.md5Encrypt;
import static cn.wanlinus.emooc.utils.CommonUtils.uid;

/**
 * @author wanli
 * @date 2018-03-06 16:25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<GenderPieDTO> genderPie() {
        Map<String, Integer> mm = userRepository.genderPie();
        List<GenderPieDTO> pies = new ArrayList<>();
        pies.add(new GenderPieDTO("男", mm.get("0")));
        pies.add(new GenderPieDTO("女", mm.get("1")));
        pies.add(new GenderPieDTO("未知", mm.get("2")));
        return pies;
    }

    @Override
    public Boolean checkName(String username) {
        return userRepository.findByUsername(username) != null;

    }

    @Override
    public Boolean checkEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    @RegisterAnnotation(description = "注册")
    @Transactional(rollbackFor = Exception.class)
    public User register(UserRegisterDTO dto) {
        try {
            User user = new User(uid(), dto.getUsername(), md5Encrypt(dto.getPassword()), dto.getEmail());
            user.setUserStatus(UserStatus.INACTIVE);
            user.setRegisterTime(new Date());
            user.setGender(Gender.UNDEFINE);
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<User> pageUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserDetailsDTO userDetails(String id) {
        User user = userRepository.findOne(id);
        return user != null ? new UserDetailsDTO(user) : new UserDetailsDTO();
    }

    @Override
    public Long countUsers() {
        return userRepository.count();
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
}
