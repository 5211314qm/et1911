package com.etoak.page;

import java.util.List;

public class Page<T> {

    private int pageNumber;
    private int pageSize;
    private int total;
    private List<T> rows;

    private int pageCount;
    private int pre;
    private int next;
    private int start;

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotal() {
        return total;
    }

    public List<T> getRows() {
        return rows;
    }

    public int getPageCount() {
        return (total + pageSize - 1) / pageSize;
    }

    public int getPre() {
        if(pageNumber > 1) return pageNumber - 1;
        return 1;
    }

    public int getNext() {
        if(pageNumber < getPageCount()) return pageNumber + 1;
        return getPageCount();
    }

    public int getStart() {
        return (pageNumber - 1) * pageSize;
    }

}
