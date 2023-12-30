package com.cafeteria.cafeteria.ViewModels;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserLoginModel {
    @NotBlank
    @Size(max = 100)
    public String username = "";
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter and one number")
    public String password = "";
}
