package com.rjgc.request;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 8:20 PM
 */
public class UserRequest {
    private int pageNow;
    private int pageSize;
    // 查询词
    private String searchKey;
    private int start;

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public int getStart() {
        return (pageNow - 1) * pageSize;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}
