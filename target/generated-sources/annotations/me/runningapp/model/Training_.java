package me.runningapp.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import me.runningapp.model.authority.User;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Training.class)
public abstract class Training_ {

	public static volatile SingularAttribute<Training, Long> id;
	public static volatile SingularAttribute<Training, User> user;

}
