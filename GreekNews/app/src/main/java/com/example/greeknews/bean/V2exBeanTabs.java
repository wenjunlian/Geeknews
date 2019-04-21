package com.example.greeknews.bean;

public class V2exBeanTabs {
    public String linkHref;
    public String tabs;

    public V2exBeanTabs(String linkHref, String tabs) {
        this.linkHref = linkHref;
        this.tabs = tabs;
    }

    @Override
    public String toString() {
        return "V2exBeanTabs{" +
                "linkHref='" + linkHref + '\'' +
                ", tabs='" + tabs + '\'' +
                '}';
    }
}
