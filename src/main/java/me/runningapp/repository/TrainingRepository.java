package me.runningapp.repository;

import me.runningapp.model.Training;
import me.runningapp.model.User;

import java.util.List;

public interface TrainingRepository {
    List<Training> getAll();
    List<Training> getAllByUser(User user);

    Training get(long id);
    void save(Training training);
    void update(Training training);
    void delete(Long id);
}

