package com.sbt.task7mskserver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    @ManyToMany
    private Set<Dialog> dialogsList;
}
