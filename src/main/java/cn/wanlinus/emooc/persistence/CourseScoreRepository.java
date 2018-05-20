/*
 * Copyright (C) 2018 - wanli <wanlinus@qq.com>
 *
 * This file is part of emooc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.wanlinus.emooc.persistence;

import cn.wanlinus.emooc.domain.CourseScore;
import cn.wanlinus.emooc.persistence.custom.CourseScoreCustomPersistence;
import org.springframework.data.jpa.repository.Query;

/**
 * @author wanli
 * @date 2018-05-17 21:03
 */
public interface CourseScoreRepository extends BaseRepository<CourseScore, String>, CourseScoreCustomPersistence {

    /**
     * 对所有课程评分进行计数
     *
     * @return 统计量
     */
    @Query(value = "SELECT COUNT(*) AS total FROM(SELECT sc.SCORE_COURSE_ID FROM TB_COURSE_SCORE sc GROUP BY sc.SCORE_COURSE_ID ) AS tb_mid", nativeQuery = true)
    Long totalCourseScore();
}
