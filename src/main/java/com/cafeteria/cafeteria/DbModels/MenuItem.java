package com.cafeteria.cafeteria.DbModels;
// MenuItem.java

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu_item")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private Long id;

    private String name;
    private String description;
    private double price;
    private String category;

    // getters and setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MenuItem id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name.length() > 50) {
            throw new IllegalArgumentException("Name cannot be longer than 50 characters");
        }
        this.name = name;
    }

    public MenuItem name(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        if (description.length() > 255) {
            throw new IllegalArgumentException("Description cannot be longer than 255 characters");
        }
        this.description = description;
    }

    public MenuItem description(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public MenuItem price(double price) {
        this.price = price;
        return this;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        if (category.length() > 50) {
            throw new IllegalArgumentException("Category cannot be longer than 50 characters");
        }
        this.category = category;
    }

    public MenuItem category(String category) {
        this.category = category;
        return this;
    }

    // constructors
    MenuItem() {
    }

    MenuItem(String name, String description, double price, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
}

