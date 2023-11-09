package com.sbt.task7mskserver.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String nickname;
    @NonNull
    @ManyToMany(mappedBy = "clientSet", fetch = FetchType.EAGER)
    private Set<Dialog> dialogsList = new HashSet<>();

    public Client(String nickname) {
        this.nickname = nickname;
    }
}
