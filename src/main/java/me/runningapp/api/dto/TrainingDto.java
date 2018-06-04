package me.runningapp.api.dto;

import lombok.Getter;
import lombok.Setter;
import me.runningapp.model.authority.User;
import me.runningapp.utils.dto.Dto;

import javax.imageio.spi.IIOServiceProvider;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class TrainingDto {
    @NotNull
    private Date start;

    @NotNull
    private Double distance;

    private Long userId;

}
