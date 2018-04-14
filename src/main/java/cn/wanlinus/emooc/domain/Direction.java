package cn.wanlinus.emooc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author wanli
 * @date 2018-04-15 02:43
 */
@Entity
@Table(name = "tb_course_direction")
public class Direction implements Serializable {
    @Id
    @Column(name = "direction_id")
    private String id;

    @Column(name = "direction_name")
    private String name;

    public Direction() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction = (Direction) o;
        return Objects.equals(id, direction.id) &&
                Objects.equals(name, direction.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Direction{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
