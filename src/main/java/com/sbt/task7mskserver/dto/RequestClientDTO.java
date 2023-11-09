package com.sbt.task7mskserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestClientDTO {
    //TODO поправить нейминг DTO классов (сейчас непонятно, почему в Client DTO nickname, в Dialog DTO
    // лежат ID'шники клиентов и т.д.
    String nickname;
}
