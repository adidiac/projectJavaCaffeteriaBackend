package com.cafeteria.cafeteria.ViewModels;

import javax.validation.constraints.*;


public class UpdateOrderStatus {
    @NotNull
    public Long order_id;
    
    public String status;
}
