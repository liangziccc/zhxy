package com.etoak.zhxy.utils;

import java.util.List;

// 该组件专门 负责分页的所有组件  泛型 代表 对 XX 分页 ; 学生 材料 订单
   //web上的查询分页 第一页有几条  有几页
public class Page<T> {
    private int pageNumber;
    private int pageSize ;
    private int total;
    private List<T> rows;
    private int pageCount;
    private int start;
    private int pre;
    private int next;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getPageCount() {
        return (total+pageSize-1)/pageSize;
    }



    public int getStart() {
        return (pageNumber-1)*pageSize;
    }


    public int getPre() {
        if (pageNumber > 1) {
            return pageNumber - 1;
        }
        return 1;
    }


    public int getNext() {
        if(pageNumber<pageCount){
        return pageNumber+1;}
        return pageCount;
    }


}
