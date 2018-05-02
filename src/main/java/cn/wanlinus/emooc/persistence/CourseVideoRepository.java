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

import cn.wanlinus.emooc.domain.CourseVideo;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @author wanli
 * @date 2018-04-15 14:01
 */
public interface CourseVideoRepository extends BaseRepository<CourseVideo, String> {

    /**
     * 查询某日课程视频添加总数
     *
     * @param date 传入日期
     * @return 当日添加视频总数
     */
    @Query(value = "SELECT count(*) FROM TB_COURSE_VIDEO AS video WHERE date_format(video.VIDEO_CREATE_TIME,'%Y-%m-%d') = DATE_FORMAT( ?1 , '%Y-%m-%d')", nativeQuery = true)
    Long countVideos(Date date);

    /**
     * 通过章节Id查找所有视频
     *
     * @param sectionId 章节ID
     * @return 相关视频
     */
    List<CourseVideo> findAllBySectionId(String sectionId);
}
