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

package cn.wanlinus.emooc.service;

import cn.wanlinus.emooc.domain.CourseVideo;

import java.util.Date;

/**
 * @author wanli
 * @date 2018-05-03 19:12
 */
public interface CourseVideoService {
    /**
     * 对所有视频进行计数
     *
     * @return 返回统计量
     */
    Long countVideos();

    /**
     * 对相应日期的视频进行计数
     *
     * @param date 相应日期
     * @return 视频统计量
     */
    Long countVideos(Date date);

    /**
     * 保存视频
     *
     * @param video 需要保存的视频对象
     * @return 数据库中的对象
     */
    CourseVideo saveVideo(CourseVideo video);

    /**
     * 通过视频Id查找相应视频
     *
     * @param videoId 需要查找的视频Id
     * @return 相应的视频对象
     */
    CourseVideo findVideo(String videoId);
}
