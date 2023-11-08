package com.sbt.task7mskserver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dialog {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToMany
    private Set<Client> clientSet;
    @OneToMany
    private ArrayList<Message> messages;
}
