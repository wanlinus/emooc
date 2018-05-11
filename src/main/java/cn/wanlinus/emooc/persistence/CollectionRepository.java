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

import cn.wanlinus.emooc.domain.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author wanli
 * @date 2018/5/11
 */
public interface CollectionRepository extends JpaRepository<Collection, String> {
    /**
     * 保存收藏信息
     *
     * @param id       收藏Id
     * @param uid      用户ID
     * @param courseId 课程ID
     * @return 受影响行数
     */
    @Modifying
    @Query(value = "INSERT INTO tb_collection(COLLECTION_ID, COLLECTION_USER_ID, COLLECTION_COURSE_ID, COLLECTION_TIME, COLLECTION_STATUS) VALUES (?1,?2,?3,CURRENT_TIMESTAMP,'1')", nativeQuery = true)
    Integer save(String id, String uid, String courseId);

    /**
     * 删除用户收藏的课程
     *
     * @param uid      用户ID
     * @param courseId 课程ID
     * @return 受影响的行数
     */
    @Modifying
    Integer deleteByUserIdAndCourseId(String uid, String courseId);
}
