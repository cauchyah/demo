package com.lulu.lin.mac;

/**
 * Created by lin on 2017/6/26.
 */

public class ActivityBean {
    private String title;
    private Class aClass;
    public ActivityBean(String title,Class<?> aClass){
        this.title=title;
        this.aClass=aClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }
}
