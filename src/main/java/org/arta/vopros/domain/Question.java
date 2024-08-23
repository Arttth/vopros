package org.arta.vopros.domain;

import java.util.List;
import java.util.Objects;

public class Question {
    private Long id;
    private String name;
    private String questionMainPart;
    private Integer likeCount;
    private User author;
    private Discipline discipline;

    public Question() {}

    public Question(Long id, String name, String questionMainPart, Integer likeCount, User author, Discipline discipline) {
        this.id = id;
        this.name = name;
        this.questionMainPart = questionMainPart;
        this.likeCount = likeCount;
        this.author = author;
        this.discipline = discipline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestionMainPart() {
        return questionMainPart;
    }

    public void setQuestionMainPart(String questionMainPart) {
        this.questionMainPart = questionMainPart;
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

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(name, question.name) && Objects.equals(questionMainPart, question.questionMainPart) && Objects.equals(likeCount, question.likeCount) && Objects.equals(author, question.author) && Objects.equals(discipline, question.discipline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, questionMainPart, likeCount, author, discipline);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", questionMainPart='" + questionMainPart + '\'' +
                ", likeCount=" + likeCount +
                ", author=" + author +
                ", discipline=" + discipline +
                '}';
    }
}
