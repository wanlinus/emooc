package cn.wanlinus.emooc.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wanli
 * @date 2018-04-15 02:30
 */
@Entity
@Table(name = "tb_course")
public class Course implements Serializable {
    @Id
    @Column(name = "course_id")
    private String id;

    @Column(name = "course_name")
    private String name;

    @Column(name = "course_tariff")
    private Double tariff;

    @Column(name = "course_duration")
    private Integer duration;

    @Column(name = "course_score")
    private Double score;

    @Column(name = "course_notice")
    private String notice;

    @Column(name = "course_wtcanlearn")
    private String wtcanlearn;

    @Column(name = "course_image")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "course_teach_id", referencedColumnName = "teacher_id")
    private Teacher teacher;

    //TODO

}
