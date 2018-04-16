package cn.wanlinus.emooc.domain;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wanli
 * @date 2018-04-15 02:47
 */
@Entity
@Table(name = "tb_course_video")
public class CourseVideo implements Serializable {
    @Id
    @Column(name = "video_id")
    private String id;

    @Column(name = "video_name")
    private String name;

    @Column(name = "video_duration")
    private Integer duration;

    @Column(name = "video_sha1")
    private String sha1;

    @ManyToOne
    @JoinColumn(name = "video_section_id", referencedColumnName = "section_id")
    private CourseSection section;

    public CourseVideo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public CourseSection getSection() {
        return section;
    }

    public void setSection(CourseSection section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
