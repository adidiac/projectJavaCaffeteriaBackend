package com.cafeteria.cafeteria.ViewModels;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserRegisterModel {
    @NotBlank
    public String username = "";
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter and one number")
    public String password = "";
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter and one number")
    public String email = "";
    @NotBlank
    public String firstName = "";
    @NotBlank
    public String lastName = "";
}
