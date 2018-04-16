package cn.wanlinus.emooc.domain;

import com.alibaba.fastjson.JSON;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author wanli
 * @date 2018-04-15 02:43
 */
@Entity
@Table(name = "tb_course_direction")
public class CourseDirection implements Serializable {
    @Id
    @Column(name = "direction_id")
    private String id;

    @Column(name = "direction_name")
    private String name;

    public CourseDirection() {
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

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
