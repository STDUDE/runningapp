package me.runningapp.model.authority;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import me.runningapp.model.Training;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Boolean> accountLocked;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SetAttribute<User, Role> roles;
	public static volatile SetAttribute<User, Training> trainings;
	public static volatile SingularAttribute<User, Boolean> accountExpired;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, Boolean> credentialsExpired;
	public static volatile SingularAttribute<User, Boolean> enabled;
	public static volatile SingularAttribute<User, String> username;

	public static final String ACCOUNT_LOCKED = "accountLocked";
	public static final String PASSWORD = "password";
	public static final String ROLES = "roles";
	public static final String TRAININGS = "trainings";
	public static final String ACCOUNT_EXPIRED = "accountExpired";
	public static final String ID = "id";
	public static final String CREDENTIALS_EXPIRED = "credentialsExpired";
	public static final String ENABLED = "enabled";
	public static final String USERNAME = "username";

}

