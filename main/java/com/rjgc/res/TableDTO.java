package com.rjgc.res;

import java.util.Vector;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 8:31 PM
 */
public class TableDTO {
    private Vector<Vector<Object>> data;
    private int totalCount;

    public Vector<Vector<Object>> getData() {
        return data;
    }

    public void setData(Vector<Vector<Object>> data) {
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
