package me.runningapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import me.runningapp.model.authority.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "training")
@Getter @Setter
public class Training implements Serializable {

    @Id
//    @ApiModelProperty(readOnly = true)
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "start")
    private Date start;

    @Column(name = "distance")
    private Double distance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
