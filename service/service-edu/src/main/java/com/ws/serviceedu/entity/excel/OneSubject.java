package com.ws.serviceedu.entity.excel;

import java.util.ArrayList;
import java.util.List;

public class OneSubject {

    private String id;
    private  String title;

    private List<TwoSubject> children=new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TwoSubject> getChildren() {
        return children;
    }

    public void setChildren(List<TwoSubject> children) {
        this.children = children;
    }
}
