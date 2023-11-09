package com.sbt.task7mskserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestMessageDTO {
    @NonNull
    Long dialogSessionId;
    @NonNull
    String text;
    @NonNull
    String nickname;

}
