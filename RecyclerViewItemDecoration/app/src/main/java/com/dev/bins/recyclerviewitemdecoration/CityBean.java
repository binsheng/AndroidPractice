package com.dev.bins.recyclerviewitemdecoration;

import com.github.promeg.pinyinhelper.Pinyin;

/**
 * Created by bin on 02/01/2017.
 */

public class CityBean {

    private String tag;
    private String name;

    public CityBean(String name) {
        this.name = name;
        tag = Pinyin.toPinyin(name.charAt(0)).substring(0,1).toUpperCase();
        System.out.println(tag);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
