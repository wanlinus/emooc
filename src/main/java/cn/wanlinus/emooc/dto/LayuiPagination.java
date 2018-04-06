package cn.wanlinus.emooc.dto;

import java.io.Serializable;

/**
 * Layui分页组件
 *
 * @author wanli
 * @date 2018-04-06 13:41
 */
public class LayuiPagination implements Serializable {
    /**
     * 每页条数
     */
    Integer limit;
    /**
     * 总数
     */
    Integer count;
    /**
     * 上一页
     */
    Integer prev;
    /**
     * 当前页
     */
    Integer page;
    /**
     * 下一页
     */
    Integer next;
    /**
     * 跳转页
     */
    Integer skip;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrev() {
        return prev;
    }

    public void setPrev(Integer prev) {
        this.prev = prev;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    @Override
    public String toString() {
        return "LayuiPagination{" +
                "limit=" + limit +
                ", count=" + count +
                ", prev=" + prev +
                ", page=" + page +
                ", next=" + next +
                ", skip=" + skip +
                '}';
    }
}
