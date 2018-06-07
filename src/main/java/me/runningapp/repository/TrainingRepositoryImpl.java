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
import java.util.*;

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
    public Map<String, ReportDto> report(Integer yearNumber, User user) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);

        Root<Training> root = query.from(Training.class);
        Predicate userPredicate = builder.equal(root.get(Training_.user), user);
        Expression<Integer> year = builder.function("year", Integer.class, root.get(Training_.start));
        Predicate yearPredicate = builder.equal(year, yearNumber);

        Expression<Double> sumDistance = builder.sum(root.get(Training_.distance)).as(Double.class); /*метры*/
        Expression<Double> sumTime = builder.sum(root.get(Training_.time)).as(Double.class); /*милисекунды*/
        Expression<Double> sumTimeSec = builder.quot(sumTime, 1000).as(Double.class); /*секунды*/
        Expression<Double> avgSpeed = builder.quot(sumDistance, sumTimeSec).as(Double.class); /*метры/c*/
        Expression<Double> avgTimeSec = builder.quot(builder.avg(root.get(Training_.time)), 1000).as(Double.class); /*секунды*/
        Expression<Integer> week = builder.function("week", Integer.class, root.get(Training_.start));
        query.multiselect(sumDistance, avgSpeed, avgTimeSec, week);
        query.where(builder.and(userPredicate, yearPredicate));
        query.groupBy(week);

        Map<String, ReportDto> report = initReport(getNumberOfWeeks(yearNumber));
        List<Object[]> listObjects = entityManager.createQuery(query).getResultList();
        for (Object[] objects : listObjects) {
            report.get("week " + objects[3]).setTotalDistance((Double) objects[0]);
            report.get("week " + objects[3]).setAvgSpeed((Double) objects[1]);
            report.get("week " + objects[3]).setAvgTime((Double) objects[2]);
            System.out.println("это номер недели = [" + objects[3] + "]");
        }
        return report;
    }

    private Map<String, ReportDto> initReport(Integer numberOfWeeks) {
        Map<String, ReportDto> report = new TreeMap<>(Comparator.comparing(o -> Integer.valueOf(o.split(" ")[1])));

        for (int i = 1; i <= numberOfWeeks; i++) {
            report.put("week " + i, new ReportDto((double) 0, (double) 0, (double) 0));
        }
        return report;
    }

    private int getNumberOfWeeks(Integer year) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.DECEMBER);
        c.set(Calendar.DAY_OF_MONTH, 31);

        int ordinalDay = c.get(Calendar.DAY_OF_YEAR);
        int weekDay = c.get(Calendar.DAY_OF_WEEK) - 1;
        return (ordinalDay - weekDay + 10) / 7;
    }


}
