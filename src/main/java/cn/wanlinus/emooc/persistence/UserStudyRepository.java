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

import cn.wanlinus.emooc.domain.UserStudy;
import org.springframework.data.jpa.repository.Query;

/**
 * @author wanli
 * @date 2018-04-15 14:05
 */
public interface UserStudyRepository extends BaseRepository<UserStudy, String> {
    /**
     * 用户学习计数
     *
     * @param courseId 课程ID
     * @return 观看相关课程的用户数
     */
    @Query(value = "SELECT count(*) FROM TB_USER_STUDY s WHERE s.STUDY_COURSE_ID=?1", nativeQuery = true)
    Integer studyNum(String courseId);
}
