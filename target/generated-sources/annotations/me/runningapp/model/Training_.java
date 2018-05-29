package me.runningapp.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import me.runningapp.model.authority.User;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Training.class)
public abstract class Training_ {

	public static volatile SingularAttribute<Training, Double> distance;
	public static volatile SingularAttribute<Training, Date> start;
	public static volatile SingularAttribute<Training, Long> id;
	public static volatile SingularAttribute<Training, User> user;

	public static final String DISTANCE = "distance";
	public static final String START = "start";
	public static final String ID = "id";
	public static final String USER = "user";

}

