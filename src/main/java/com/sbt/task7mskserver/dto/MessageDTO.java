package com.sbt.task7mskserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * DTO для отправки
 */
@Data
@AllArgsConstructor
public class MessageDTO {
    /**
     * ID диалога, в который сообщение отправляется
     */
    @Positive
    Long dialogSessionId;
    /**
     * Содержание сообщения
     */
    @NotBlank
    String text;
    /**
     * Никнейм отправителя сообщения
     */
    @NotBlank
    String nickname;

}
