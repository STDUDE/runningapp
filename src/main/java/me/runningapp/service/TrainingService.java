package me.runningapp.service;

import me.runningapp.model.Training;
import me.runningapp.model.User;
import me.runningapp.repository.TrainingRepository;
import me.runningapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TrainingService {
    public List<Training> listTrainings();
//    public List<Training> listTrainingsByUser(User user);

    public Training getTraining(int id);
}
