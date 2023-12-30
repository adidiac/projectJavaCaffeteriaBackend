package com.cafeteria.cafeteria.DbModels;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    private Long user_id;
    private String order_status;
    private String order_date;

    public Order() {
    }

    public Order(Long order_id, Long user_id, String order_status, String order_date) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.order_status = order_status;
        this.order_date = order_date;
    }
    // write everything needed
    public Long getOrderId() {
        return this.order_id;
    }

    public void setOrderId(Long order_id) {
        this.order_id = order_id;
    }

    public Long getUserId() {
        return this.user_id;
    }

    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public String getOrderStatus() {
        return this.order_status;
    }

    public void setOrderStatus(String order_status) {
        this.order_status = order_status;
    }

    public String getOrderDate() {
        return this.order_date;
    }

    public void setOrderDate(String order_date) {
        this.order_date = order_date;
    }

    @Override
    public String toString() {
        return "{" +
            " order_id='" + getOrderId() + "'" +
            ", user_id='" + getUserId() + "'" +
            ", order_status='" + getOrderStatus() + "'" +
            ", order_date='" + getOrderDate() + "'" +
            "}";
    }
    public void setDateFromLocalDate() {
        java.time.LocalDate date = java.time.LocalDate.now();
        this.order_date = date.toString();
    }
}
