package me.runningapp.service;

import me.runningapp.api.dto.ReportDto;
import me.runningapp.model.Training;
import me.runningapp.model.authority.User;
import me.runningapp.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Transactional
    public List<Training> getAll() {
        return trainingRepository.getAll();
    }

    @Transactional
    public List<Training> getAll(User user) {
        return trainingRepository.getAll(user);
    }

    @Transactional
    public Training get(long id) {
        return trainingRepository.get(id);
    }

    @Transactional
    public Training get(long id, User user) {
        return trainingRepository.get(id, user);
    }

    @Transactional
    public void save(Training training) {
        trainingRepository.save(training);
    }

    @Transactional
    public void update(Training training) {
        trainingRepository.update(training);
    }

    @Transactional
    public void delete(long id) {
        trainingRepository.delete(id);
    }

    @Transactional
    public ReportDto report(Date date1, Date date2, User user) {
        return trainingRepository.report(date1, date2, user);
    }


}
