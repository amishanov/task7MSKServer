package com.sbt.task7mskserver.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dialog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Client> clientSet = new HashSet<>();
    @NonNull
    @OneToMany(mappedBy = "dialog")
    private Set<Message> messages = new HashSet<>();

}
