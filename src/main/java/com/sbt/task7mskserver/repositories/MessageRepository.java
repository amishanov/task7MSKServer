package com.sbt.task7mskserver.repositories;

import com.sbt.task7mskserver.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
