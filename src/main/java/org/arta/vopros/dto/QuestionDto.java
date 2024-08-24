package org.arta.vopros.dto;

public record QuestionDto(Long questionId,
                          String questionName,
                          String questionMainPart,
                          Integer likeCount,
                          String meta) {
}
