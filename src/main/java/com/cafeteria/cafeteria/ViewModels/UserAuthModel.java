package com.cafeteria.cafeteria.ViewModels;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserAuthModel {
    @NotBlank
    @Size(max = 100)
    public String username;
}
