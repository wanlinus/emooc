package cn.wanlinus.emooc.persistence.custom;

import java.util.List;
import java.util.Map;

/**
 * @author wanli
 * @date 2018-03-25 01:30
 */
public interface UserCustomPersistence {
    /**
     * 用户性别饼状图
     *
     * @return
     * @date 2018-3-25 02:19:58
     */
    Map<String, Integer> genderPie();
}
