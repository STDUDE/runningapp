package me.runningapp.model;

import me.runningapp.model.authority.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "training")
public class Training {
    private Long id;
    private Date start;
    private double distance;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
