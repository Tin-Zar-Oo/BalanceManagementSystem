package com.example.balancemanagement.controller.utils;
import java.util.ArrayList;
import java.util.List;

public class Pagination {

    private int current;
    private int total;
    private boolean first;
    private boolean last;
    private String url;
    private List<Integer> pages;

    public static Builder builder(){
        return new Builder();
    }


    public static class Builder{
        private int current;
        private int total;
        private boolean first;
        private boolean last;
        private String url;

        public Builder current(int current){
            this.current = current;
            return this;
        }
        public Builder total(int total){
            this.total = total;
            return this;
        }
        public Builder first(boolean first){
            this.first = this.first;
            return this;
        }
        public Builder last(boolean last){
            this.last = this.last;
            return this;
        }
        public Builder url(String url){
            this.url = url;
            return this;
        }
        public Pagination build(){
        return new Pagination(current,total,first,last,url);
        }
    }

    private Pagination(int current, int total, boolean first, boolean last, String url) {
        this.current = current;
        this.total = total;
        this.first = first;
        this.last = last;
        this.url = url;
        pages = new ArrayList<>();
        pages.add(current);
        while (pages.size() < 3 && pages.get(0) > 0){
        pages.add(0,pages.get(0)-1);
        }
        while (pages.size() < 5 && pages.get(pages.size()-1) < total-1){
        pages.add(pages.get(pages.size()-1)+1);
        }
        while (pages.size() < 5 && pages.get(0) > 0){
            pages.add(0,pages.get(0)-1);
        }
    }

    public int getCurrent() {
        return current;
    }

    public int getTotal() {
        return total;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isLast() {
        return last;
    }

    public String getUrl() {
        return url;
    }

    public List<Integer> getPages() {
        return pages;
    }
}
