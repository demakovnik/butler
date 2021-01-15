package org.inofttech.butler.entity.to;



import org.inofttech.butler.validation.FieldMatch;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@FieldMatch(first = "matchingPassword", second = "password", message = "The password fields must match")
public class UserDto {
    @NotNull
    @NotEmpty(message = "Field cannot be empty")
    private String username;

    @NotNull
    @NotEmpty(message = "Field cannot be empty")
    private String password;

    @NotNull
    @NotEmpty(message = "Field cannot be empty")
    public String matchingPassword;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

}