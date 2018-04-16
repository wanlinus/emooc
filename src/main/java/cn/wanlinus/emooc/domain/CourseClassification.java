package cn.wanlinus.emooc.domain;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wanli
 * @date 2018-04-15 02:37
 */
@Entity
@Table(name = "tb_course_classification")
public class CourseClassification implements Serializable {
    @Id
    @Column(name = "classification_id")
    private String id;

    @Column(name = "classification_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "classification_direction_id", referencedColumnName = "direction_id")
    private CourseDirection direction;

    public CourseClassification() {
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

    public CourseDirection getDirection() {
        return direction;
    }

    public void setDirection(CourseDirection direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
