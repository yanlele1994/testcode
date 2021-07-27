package com.bjpowernode.web;

import java.util.HashMap;
import java.util.Map;

public class DataAndView {
    private String url;
    private Map data = new HashMap();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map getData() {
        return data;
    }

    public void addData(String key, Object obj) {
        data.put(key, obj);
    }

}
