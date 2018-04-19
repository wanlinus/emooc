/*
 * Copyright (C) 2018. - wanli <wanlinus@qq.com>
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

/**
 * Layui分页组件
 *
 * @author wanli
 * @date 2018-04-06 13:41
 */
public class LayuiPaginationDTO implements Serializable {
    /**
     * 每页条数
     */
    private Integer limit;
    /**
     * 总数
     */
    private Integer count;
    /**
     * 上一页
     */
    private Integer prev;
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 下一页
     */
    private Integer next;
    /**
     * 跳转页
     */
    private Integer skip;

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
        return "LayuiPaginationDTO{" +
                "limit=" + limit +
                ", count=" + count +
                ", prev=" + prev +
                ", page=" + page +
                ", next=" + next +
                ", skip=" + skip +
                '}';
    }
}
