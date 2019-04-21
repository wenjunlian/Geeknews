package com.example.greeknews.bean;

import org.jsoup.nodes.Element;

public class V2exBean {
    public String img;
    public String author;
    public String nunber;
    public String href;
    public String topic;
    public String titles;

    public V2exBean(String img, String author, String nunber, String href, String topic, String titles) {
        this.img = img;
        this.author = author;
        this.nunber = nunber;
        this.href = href;
        this.topic = topic;
        this.titles = titles;
    }

    @Override
    public String toString() {
        return "V2exBean{" +
                "img='" + img + '\'' +
                ", author='" + author + '\'' +
                ", nunber='" + nunber + '\'' +
                ", href='" + href + '\'' +
                ", topic='" + topic + '\'' +
                ", titles='" + titles + '\'' +
                '}';
    }
}
