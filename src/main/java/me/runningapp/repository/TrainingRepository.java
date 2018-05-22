package me.runningapp.repository;

import me.runningapp.model.Training;
import me.runningapp.model.User;

import java.util.List;

public interface TrainingRepository {
    public List<Training> listTrainings();
    public List<Training> listTrainingsByUser(User user);

    public Training getTraining(int id);
}

