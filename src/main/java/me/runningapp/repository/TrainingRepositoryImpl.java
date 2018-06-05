package me.runningapp.repository;

import me.runningapp.model.Training;
import me.runningapp.model.Training_;
import me.runningapp.model.authority.User;
import org.springframework.dao.support.DataAccessUtils;
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

        return allQuery.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Training> getAll(User user) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Training> query = builder.createQuery(Training.class);

        Root<Training> root = query.from(Training.class);
        Predicate idPredicate = builder.equal(root.get(Training_.USER), user);
        query.where(builder.and(idPredicate));

        return entityManager.createQuery(query).getResultList();
    }

    @SuppressWarnings("unchecked")
    public Training get(long id) {
//        return (Training) entityManager.createQuery("from Training where id=" + id).getSingleResult();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Training> query = builder.createQuery(Training.class);

        Root<Training> root = query.from(Training.class);
        Predicate idPredicate = builder.equal(root.get(Training_.id), id);
        query.where(builder.and(idPredicate));
        return DataAccessUtils.singleResult(entityManager.createQuery(query).getResultList());
    }

    @SuppressWarnings("unchecked")
    public Training get(long id, User user) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Training> query = builder.createQuery(Training.class);

        Root<Training> root = query.from(Training.class);
        Predicate idPredicate = builder.equal(root.get(Training_.id), id);
        Predicate userPredicate = builder.equal(root.get(Training_.user), user);
        query.where(builder.and(idPredicate, userPredicate));
        return DataAccessUtils.singleResult(entityManager.createQuery(query).getResultList());
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
    public void delete(long id) {
        Training training = entityManager.find(Training.class, id);
        delete(training);
    }

    @Override
    public void delete(Training training) {
        entityManager.remove(training);
    }

    @Override
    public List<Training> report() {
        return null;
    }


}
