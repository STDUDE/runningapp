package me.runningapp.service;

import me.runningapp.model.Training;
import me.runningapp.model.User;
import me.runningapp.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Transactional
    public List<Training> listTrainings() {
        return trainingRepository.listTrainings();
    }

    @Transactional
    public List<Training> listTrainingsByUser(User user) {
        return trainingRepository.listTrainingsByUser(user);
    }

    @Transactional
    public Training get(long id) {
        return trainingRepository.get(id);
    }

    @Override
    public void save(Training training) {
        trainingRepository.save(training);
    }
}