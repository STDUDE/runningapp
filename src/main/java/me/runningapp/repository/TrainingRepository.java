package me.runningapp.repository;

import me.runningapp.model.Role;
import me.runningapp.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}

