package org.arta.vopros.mapper;

import lombok.NoArgsConstructor;
import org.arta.vopros.domain.Question;
import org.arta.vopros.dto.QuestionDto;

import java.text.ParseException;

@NoArgsConstructor
public class QuestionMapper implements Mapper<Question, QuestionDto> {
    private static final QuestionMapper INSTANCE = new QuestionMapper();
    @Override
    public QuestionDto mapFrom(Question question) {
        return new QuestionDto(
                question.getId(),
                question.getName(),
                question.getQuestionMainPart(),
                question.getLikeCount(),
                question.getAuthor().getNickname(),
                question.getDiscipline().getName()
        );
    }

    public static QuestionMapper getInstance() {
        return INSTANCE;
    }
}
