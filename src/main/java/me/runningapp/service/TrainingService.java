package me.runningapp.service;

import me.runningapp.model.Training;
import me.runningapp.model.User;
import me.runningapp.repository.TrainingRepository;
import me.runningapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TrainingService {
    List<Training> getAll();
    List<Training> getAllByUser(User user);
    Training get(long id);
    void save(Training training);
    void update(Training training);
    void delete(Long id);
}
