package com.sbt.task7mskserver.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nickname;
    @NotNull
    @ManyToMany(mappedBy = "clientSet", fetch = FetchType.LAZY)
    private Set<Dialog> dialogsList = new HashSet<>();

    public Client(String nickname) {
        this.nickname = nickname;
    }
}
