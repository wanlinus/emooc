package cn.wanlinus.emooc.persistence;

import cn.wanlinus.emooc.domain.EmoocError;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wanli
 * @date 2018-04-06 23:02
 */
public interface ErrorRepository extends JpaRepository<EmoocError, String> {
}
