package org.arta.vopros.services;

import org.arta.vopros.db.impl.QuestionDao;
import org.arta.vopros.db.impl.UserDao;
import org.arta.vopros.domain.Question;
import org.arta.vopros.domain.User;
import org.arta.vopros.dto.CreateQuestionDto;
import org.arta.vopros.dto.QuestionDto;
import org.arta.vopros.mapper.CreateQuestionMapper;
import org.arta.vopros.mapper.QuestionMapper;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionService {
    private static final QuestionService INSTANCE = new QuestionService();
    private static final QuestionDao questionDao = QuestionDao.getInstance();
    private static final CreateQuestionMapper createQuestionMapper = CreateQuestionMapper.getInstance();
    private static final QuestionMapper questionMapper = QuestionMapper.getInstance();

    public List<QuestionDto> findAll() {
        return questionDao.findAll().stream().map(question ->
                new QuestionDto(
                        question.getId(),
                        question.getName(),
                        question.getQuestionMainPart(),
                        question.getLikeCount(),
                        question.getAuthor().getNickname(),
                        question.getDiscipline().getName()
                )).collect(Collectors.toList());
    }

    public List<QuestionDto> findAllByUserId(Long userId) {
        return questionDao.findAllByUserId(userId).stream().map(question ->
                new QuestionDto(
                        question.getId(),
                        question.getName(),
                        question.getQuestionMainPart(),
                        question.getLikeCount(),
                        question.getAuthor().getNickname(),
                        question.getDiscipline().getName()
                )).collect(Collectors.toList());
    }

    public QuestionDto findById(Long questionId) {
        return questionMapper.mapFrom(questionDao.findById(questionId));
    }

    public Long create(CreateQuestionDto createQuestionDto) {
        Question question = createQuestionMapper.mapFrom(createQuestionDto);
        return questionDao.save(question).getId();
    }

    private QuestionService() {}
    public static QuestionService getInstance() {
        return INSTANCE;
    }
}
