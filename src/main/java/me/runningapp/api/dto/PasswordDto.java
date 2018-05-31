package me.runningapp.api.dto;

import lombok.Getter;
import lombok.Setter;
import me.runningapp.validators.ValidPassword;

@Getter @Setter
public class PasswordDto {

    private String oldPassword;

    @ValidPassword
    private String newPassword;

}
