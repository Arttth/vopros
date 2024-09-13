package org.arta.vopros.dto;

public record CreateQuestionDto(String questionName,
                                String questionMainPart,
                                String nickname,
                                String disciplineName) {
}
