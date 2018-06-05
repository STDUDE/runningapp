package me.runningapp.service;

import me.runningapp.model.Training;
import me.runningapp.model.authority.User;

import java.util.List;

public interface TrainingService {
    List<Training> getAll();
    List<Training> getAll(User user);
    Training get(long id);
    Training get(long id, User user);
    void save(Training training);
    void update(Training training);
    void delete(long id);
    List<Training> report();
}
