package com.de.utils;

/**
 * @编写人:de
 * @时间:2019/10/20
 * @描述:请求分页参数
 */

public abstract class MyPageParams {
    public int page;
    public int limit;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
