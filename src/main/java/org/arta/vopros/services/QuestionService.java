package org.arta.vopros.services;

import org.arta.vopros.db.impl.QuestionDao;
import org.arta.vopros.dto.QuestionDto;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionService {
    private static final QuestionService INSTANCE = new QuestionService();
    private static final QuestionDao questionDao = QuestionDao.getInstance();

    public List<QuestionDto> findAll() {
        return questionDao.findAll().stream().map(question ->
                new QuestionDto(
                        question.getId(),
                        question.getName(),
                        question.getQuestionMainPart(),
                        question.getLikeCount(),
                        "%s - %s".formatted(question.getDiscipline(), question.getAuthor())
                )).collect(Collectors.toList());
    }

    private QuestionService() {}
    public static QuestionService getInstance() {
        return INSTANCE;
    }
}
