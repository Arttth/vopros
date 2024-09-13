package org.arta.vopros.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Builder
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
}
