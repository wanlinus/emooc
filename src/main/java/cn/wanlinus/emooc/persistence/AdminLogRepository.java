package cn.wanlinus.emooc.persistence;

import cn.wanlinus.emooc.domain.AdminLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wanli
 * @date 2018-04-06 22:16
 */
public interface AdminLogRepository extends JpaRepository<AdminLog, String> {
}
