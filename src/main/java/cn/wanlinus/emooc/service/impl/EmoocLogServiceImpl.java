package cn.wanlinus.emooc.service.impl;

import cn.wanlinus.emooc.domain.EmoocLog;
import cn.wanlinus.emooc.persistence.EmoocLogRepository;
import cn.wanlinus.emooc.service.EmoocLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanli
 * @date 2018-04-16 19:47
 */
@Service
public class EmoocLogServiceImpl implements EmoocLogService {

    @Autowired
    private EmoocLogRepository emoocLogRepository;

    @Override
    public List<EmoocLog> getTopUserLog(Integer startNum, Integer endNum) {
        return emoocLogRepository.getTopUserLog(startNum, endNum);
    }

    @Override
    public List<EmoocLog> getTopTeacherLog(Integer startNum, Integer endNum) {
        return emoocLogRepository.getTopTeacherLog(startNum, endNum);
    }
}
