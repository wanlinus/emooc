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

import cn.wanlinus.emooc.domain.CourseComment;
import org.springframework.data.jpa.repository.Query;

/**
 * @author wanli
 * @date 2018-04-15 13:56
 */
public interface CourseCommentRepository extends BaseRepository<CourseComment, String> {
    /**
     * 根据课程ID查询评论数
     *
     * @param courseId 课程ID
     * @return 评论数
     */
    @Query(value = "SELECT COUNT(*) FROM TB_COURSE_COMMENT comment WHERE comment.COMMENT_COURSE_ID = ?1", nativeQuery = true)
    Integer commentsNum(String courseId);
}
