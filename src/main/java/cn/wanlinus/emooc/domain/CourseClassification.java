package cn.wanlinus.emooc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    //TODO


}
