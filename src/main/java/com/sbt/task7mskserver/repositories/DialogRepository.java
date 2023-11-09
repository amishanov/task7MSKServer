package com.sbt.task7mskserver.repositories;

import com.sbt.task7mskserver.models.Client;
import com.sbt.task7mskserver.models.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DialogRepository extends JpaRepository<Dialog, Long> {
}
