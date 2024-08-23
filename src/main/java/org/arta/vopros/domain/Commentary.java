package org.arta.vopros.domain;

import java.util.Objects;

public class Commentary {
    private Long id;
    private String content;
    private Integer likeCount;
    private User author;
    private Question question;

    public Commentary() {
    }

    public Commentary(Long id, String content, Integer likeCount, User author, Question question) {
        this.id = id;
        this.content = content;
        this.likeCount = likeCount;
        this.author = author;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commentary that = (Commentary) o;
        return Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(likeCount, that.likeCount) && Objects.equals(author, that.author) && Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, likeCount, author, question);
    }

    @Override
    public String toString() {
        return "Commentary{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", likeCount=" + likeCount +
                ", author=" + author +
                ", question=" + question +
                '}';
    }
}
