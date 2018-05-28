package me.runningapp.service;

import me.runningapp.model.Training;
import me.runningapp.model.authority.User;

import java.util.List;

public interface TrainingService {
    List<Training> getAll();
    List<Training> getAllByUser(User user);
    Training get(long id);
    void save(Training training);
    void update(Training training);
    void delete(Long id);
}
