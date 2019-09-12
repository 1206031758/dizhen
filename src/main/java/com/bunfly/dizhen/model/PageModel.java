package com.bunfly.dizhen.model;

import java.util.List;

public class PageModel<T> {

    private int pageSize;//每页的数据量
    private int currentPage;//当前页码
    private int totalCount;//数据总量
    private int pageCount;//总页数
    private int begin;//开始查询的数
    private List<T> result;//分页的数据
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public int getPageCount() {
        return (totalCount-1)/pageSize+1;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public int getBegin() {
        return (currentPage-1)*pageSize;
    }
    public void setBegin(int begin) {
        this.begin = begin;
    }
    public List<T> getResult() {
        return result;
    }
    public void setResult(List<T> result) {
        this.result = result;
    }
    @Override
    public String toString() {
        return "PageModel [pageSize=" + pageSize + ", currentPage=" + currentPage + ", totalCount=" + totalCount
                + ", pageCount=" + pageCount + ", begin=" + begin + ", result=" + result + "]";
    }
}
