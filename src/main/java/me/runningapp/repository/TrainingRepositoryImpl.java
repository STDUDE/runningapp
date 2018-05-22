package me.runningapp.repository;

import me.runningapp.model.Training;
import me.runningapp.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class TrainingRepositoryImpl implements TrainingRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<Training> listTrainings() {
        return sessionFactory.getCurrentSession().createQuery("from Training").list();
    }

    @SuppressWarnings("unchecked")
    public List<Training> listTrainingsByUser(User user) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Training.class);

        criteria.add(Restrictions.eq("user_id", user.getId()));
        return criteria.list();
    }

    @Override
    public Training getTraining(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Training where id=" + id);
        return (Training) query.uniqueResult();
    }
}
