package cn.wanlinus.emooc.service.impl;

import cn.wanlinus.emooc.domain.EmoocLog;
import cn.wanlinus.emooc.dto.LayuiPaginationDataDTO;
import cn.wanlinus.emooc.dto.LoggerTeacherDTO;
import cn.wanlinus.emooc.persistence.EmoocLogRepository;
import cn.wanlinus.emooc.service.EmoocLogService;
import cn.wanlinus.emooc.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LayuiPaginationDataDTO<LoggerTeacherDTO> pageTeacherLogger(int page, Integer limit) {
        LayuiPaginationDataDTO<LoggerTeacherDTO> dto = new LayuiPaginationDataDTO<>();
        try {
            dto.setCount(emoocLogRepository.countTeacherLog());

            List<EmoocLog> logs = emoocLogRepository.pageTeacherLogger(limit * page, limit);
            List<LoggerTeacherDTO> list = new ArrayList<>();
            for (EmoocLog log : logs) {
                LoggerTeacherDTO d = new LoggerTeacherDTO();
                d.setId(log.getId());
                d.setWho(log.getWho());
                d.setTime(CommonUtils.dateFormatComplex(log.getTime()));
                d.setOperation(log.getOperation());
                d.setResult(log.getResult());
                d.setIp(log.getIp());
                d.setEquipment(log.getEquipment());
                d.setComment(log.getComment());
                list.add(d);
            }
            dto.setData(list);
            dto.setCode(0);
            dto.setMsg("");
        } catch (Exception e) {
            dto.setCode(1);
            e.printStackTrace();
        }
        return dto;
    }
}
