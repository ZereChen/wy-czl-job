package com.czl.entity.user;

import java.io.Serializable;

/**
 * @Author zerechen
 * @Date 2017/10/30 下午6:13
 */
public class CommentEntity implements Serializable {

    private String id;
    private String comment;
    private String articleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id='" + id + '\'' +
                ", comment='" + comment + '\'' +
                ", articleId='" + articleId + '\'' +
                '}';
    }
}
