package me.runningapp.repository;

import me.runningapp.model.Training;
import me.runningapp.model.authority.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class TrainingRepositoryImpl implements TrainingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Training> getAll() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Training> query = builder.createQuery(Training.class);
        Root<Training> root = query.from(Training.class);
        query.select(root).distinct(true);
        TypedQuery<Training> allQuery = entityManager.createQuery(query);

//        return entityManager.createQuery("from Training").getResultList();
        return allQuery.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Training> getAllByUser(User user) {
//        Criteria criteria = entityManager.getCriteriaBuilder().equal(Restrictions.eq("user_id", user.getId()));

        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Training> getAllByUser(Long id) {

        return null;
    }

    @Override
    public Training get(long id) {
        return (Training) entityManager.createQuery("from Training where id=" + id).getSingleResult();
    }

    @Override
    public void save(Training training) {
        entityManager.persist(training);
    }

    @Override
    public void update(Training training) {
        entityManager.merge(training);
    }

    @Override
    public void delete(Long id) {
        Training training = entityManager.find(Training.class, id);
        delete(training);
    }

    @Override
    public void delete(Training training) {
        entityManager.remove(training);
    }

}
