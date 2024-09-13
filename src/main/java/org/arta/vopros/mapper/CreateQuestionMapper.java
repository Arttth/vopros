package org.arta.vopros.mapper;

import org.arta.vopros.db.impl.DisciplineDao;
import org.arta.vopros.db.impl.UserDao;
import org.arta.vopros.domain.Question;
import org.arta.vopros.domain.User;
import org.arta.vopros.dto.CreateQuestionDto;

import java.text.ParseException;

public class CreateQuestionMapper implements Mapper<CreateQuestionDto, Question>{
    private static final CreateQuestionMapper INSTANCE = new CreateQuestionMapper();
    private static final UserDao userDao = UserDao.getInstance();
    private static final DisciplineDao disciplineDao = DisciplineDao.getInstance();

    @Override
    public Question mapFrom(CreateQuestionDto object) {
        return Question.builder()
                .name(object.questionName())
                .questionMainPart(object.questionMainPart())
                .author(userDao.findUserByNickname(object.nickname()).get())
                .discipline(disciplineDao.findByDisciplineName(object.disciplineName()).get())
                .build();
    }

    public static CreateQuestionMapper getInstance() {
        return INSTANCE;
    }

    private CreateQuestionMapper() {}
}
