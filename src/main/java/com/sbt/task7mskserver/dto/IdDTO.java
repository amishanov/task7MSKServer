package com.sbt.task7mskserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Positive;

/**
 * Универсальный DTO для передачи ID
 */
@Data
@AllArgsConstructor
public class IdDTO {
    @Positive
    private Long id;
}
