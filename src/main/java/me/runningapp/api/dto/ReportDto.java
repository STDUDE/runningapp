package me.runningapp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    @NotNull
    private Double totalDistance;
    @NotNull
    private Double avgSpeed;
    @NotNull
    private Double avgTime;
}