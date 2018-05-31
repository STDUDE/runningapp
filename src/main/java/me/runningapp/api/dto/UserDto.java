package me.runningapp.api.dto;

import lombok.Getter;
import lombok.Setter;
import me.runningapp.validators.PasswordConfirm;
import me.runningapp.validators.ValidPassword;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordConfirm
@Setter @Getter
public class UserDto {
    @NotNull
    @Size(min = 1, message = "{Size.userDto.username}")
    private String username;

    @ValidPassword
    private String password;

    @NotNull
    @Size(min = 1)
    private String confirmPassword;

    private Integer role;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [username=").append(username).append(", password=").append(password).append(", confirmPassword=").append(confirmPassword)
                .append(", role=").append(role).append("]");
        return builder.toString();
    }

}