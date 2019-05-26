package com.xy.simplewandroid.bean;

import java.io.Serializable;
import java.util.List;



public class NavigationListData implements Serializable {



    private List<HomeArticleData> articles;
    private int cid;
    private String name;

    public List<HomeArticleData> getArticles() {
        return articles;
    }

    public void setArticles(List<HomeArticleData> articles) {
        this.articles = articles;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
