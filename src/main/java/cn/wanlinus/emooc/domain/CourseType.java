package cn.wanlinus.emooc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author wanli
 * @date 2018-04-15 02:47
 */
@Entity
@Table(name = "tb_course_type")
public class CourseType implements Serializable {

    @Id
    @Column(name = "type_id")
    private String id;

    @Column(name = "type_name")
    private String name;

    public CourseType() {
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
}
