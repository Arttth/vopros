package org.arta.vopros.domain;

import java.util.List;

public class Question {
    private String title;
    private String questionExplPart;
    private Integer reputation;
    private User author;
    private boolean isPublished;
    private List<Discipline> disciplineList;
    private List<Tag> tagList;
    private List<Commentary> commentaryList;

    public Question() {
        this.isPublished = false;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuestionExplPart(String questionExplPart) {
        this.questionExplPart = questionExplPart;
    }

    public void publish() {
        this.isPublished = true;
    }

    public void increaseReputation(Integer count) {
        this.reputation += count;
    }

    public void addDiscipline(Discipline discipline) {
        this.disciplineList.add(discipline);
    }

    public void addTag(Tag tag) {
        this.tagList.add(tag);
    }

    public void comment(Commentary commentary) {
        this.commentaryList.add(commentary);
    }
}
