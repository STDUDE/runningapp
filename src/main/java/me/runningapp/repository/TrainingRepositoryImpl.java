package me.runningapp.repository;

import me.runningapp.api.dto.ReportDto;
import me.runningapp.model.Training;
import me.runningapp.model.Training_;
import me.runningapp.model.authority.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Date;
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
    public ReportDto report(Date date1, Date date2, User user) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);

        Root<Training> root = query.from(Training.class);
        Predicate userPredicate = builder.equal(root.get(Training_.user), user);
        Predicate datesPredicate = builder.between(root.get(Training_.start), date1, date2);

        Expression<Double> sumDistance = builder.sum(root.get("distance")).as(Double.class); /*метры*/
        Expression<Double> sumTime = builder.sum(root.get("time")).as(Double.class); /*милисекунды*/
        Expression<Double> sumTimeSec = builder.quot(sumTime, 1000).as(Double.class); /*секунды*/
        Expression<Double> avgSpeed = builder.quot(sumDistance, sumTimeSec).as(Double.class); /*метры/c*/
        Expression<Double> avgTimeSec = builder.quot(builder.avg(root.get("time")), 1000).as(Double.class); /*секунды*/
        query.multiselect(sumDistance, avgSpeed, avgTimeSec);
        query.where(builder.and(userPredicate, datesPredicate));
//        GROUP BY strftime('%W', thedate)
        query.groupBy();

        Object[] objects = DataAccessUtils.singleResult(entityManager.createQuery(query).getResultList());

        return new ReportDto((Double) objects[0], (Double) objects[1], (Double) objects[2]);
    }


}
