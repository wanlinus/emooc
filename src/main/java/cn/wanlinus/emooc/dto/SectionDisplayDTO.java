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

package cn.wanlinus.emooc.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author wanli
 * @date 2018-05-02 12:52
 */
public class SectionDisplayDTO implements Serializable {

    private String sectionId;
    private String sectionName;
    private String sectionDetails;
    private List<VideoDisplayDTO> videos;

    public SectionDisplayDTO() {
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionDetails() {
        return sectionDetails;
    }

    public void setSectionDetails(String sectionDetails) {
        this.sectionDetails = sectionDetails;
    }

    public List<VideoDisplayDTO> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoDisplayDTO> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "SectionDisplayDTO{" +
                "sectionId='" + sectionId + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", sectionDetails='" + sectionDetails + '\'' +
                ", videos=" + videos +
                '}';
    }
}
