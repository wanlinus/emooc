package cn.wanlinus.emooc.domain;

import com.alibaba.fastjson.JSON;

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
@Table(name = "tb_pic")
public class Pic implements Serializable {

    @Id
    @Column(name = "pic_id")
    private String id;

    @Column(name = "pic_path")
    private String path;

    public Pic() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
