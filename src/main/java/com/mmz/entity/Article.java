package com.mmz.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author: mamingze
 * @date: 2019-04-29 11:11
 * @description:
 */
@Document(indexName = "sdes_blog",type = "article")
public class Article {
    @Id
    @Field(type = FieldType.Long,store = true)
    private long id;
    @Field(type = FieldType.Text,store = true,analyzer = "ik_smart")
    private String title;
    @Field(type = FieldType.Text,store = true,analyzer = "ik_smart")
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
