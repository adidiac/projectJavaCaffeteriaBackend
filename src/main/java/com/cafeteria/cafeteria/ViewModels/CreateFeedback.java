package com.cafeteria.cafeteria.ViewModels;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
public class CreateFeedback extends UserAuthModel {
    @Size(min = 1, max = 1000)
    public String feedback;
    @Min(1)
    @Max(5)
    public Integer rating;
}
