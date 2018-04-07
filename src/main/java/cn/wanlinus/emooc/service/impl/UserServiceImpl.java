package cn.wanlinus.emooc.service.impl;

import cn.wanlinus.emooc.domain.Teacher;
import cn.wanlinus.emooc.domain.User;
import cn.wanlinus.emooc.domain.UserLog;
import cn.wanlinus.emooc.dto.GenderPieDTO;
import cn.wanlinus.emooc.dto.UserRegisterDTO;
import cn.wanlinus.emooc.enums.Gender;
import cn.wanlinus.emooc.enums.UserStatus;
import cn.wanlinus.emooc.persistence.UserLogRepository;
import cn.wanlinus.emooc.persistence.UserRepository;
import cn.wanlinus.emooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static cn.wanlinus.emooc.utils.CommonUtils.*;

/**
 * @author wanli
 * @date 2018-03-06 16:25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLogRepository userLogRepository;

    @Autowired
    private HttpServletRequest request;


    @Override
    public List<GenderPieDTO> genderPie() {
        int total = (int) userRepository.count();
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
    @Transactional(rollbackFor = Exception.class)
    public boolean register(UserRegisterDTO dto, String uid) {
        boolean flag;
        try {
            User user = new User(uid(), dto.getUsername(), md5Encrypt(dto.getPassword()), dto.getEmail());
            user.setUserStatus(UserStatus.INACTIVE);
            user.setRegisterTime(new Date());
            user.setGender(Gender.UNDEFINE);
            user = userRepository.save(user);
            //记录日志
            UserLog log = new UserLog();
            log.setId(userLogId());
            log.setUser(user);
            log.setDetail("用户注册");
            log.setIp(request.getRemoteAddr());
            log.setTime(new Date());
            String eq = request.getHeader("User-Agent");
            System.err.println(eq);
            log.setEquipment(eq.substring(eq.indexOf("(") + 1, eq.indexOf(")")));
            userLogRepository.save(log);
            flag = true;
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Page<User> pageUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User userDetails(String id) {
        return userRepository.findOne(id);
    }
}
