package com.sbt.task7mskserver.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dialog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Client> clientSet = new HashSet<>();
    @OneToMany(mappedBy = "dialog", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Message> messages = new HashSet<>();
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "dialog_id")
    private Set<Notification> notifications = new HashSet<>();

}
