package com.sbt.task7mskserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * DTO для отправки списка сообщений из выбранного диалога
 * Отличие от MessageDTO - отсутствие ID, поскольку тот передаётся через строку
 */
@Data
@AllArgsConstructor
public class ResponseMessageDTO {
    @NotBlank
    private String Text;
    @NotBlank
    private String nickname;
}
